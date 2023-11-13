package com.example.work.service.impl;

import com.example.work.models.Card;
import com.example.work.models.Users;
import com.example.work.repository.CardRepository;
import com.example.work.repository.UserRepository;
import com.example.work.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Card findByPincode(Long pincode) {
        return cardRepository.findByPincode(pincode);
    }

    @Override
    public Card saveCard(Card card) {
        Optional<Users> users=userRepository.findByEmail(card.getEmail());
        card.setUser(users.get());
        try
        {
            if (cardRepository.findByPincode(card.getPincode()) == null &&
                    cardRepository.findByCardPassword(card.getCardPassword()) == null)
                return cardRepository.save(card);
        }
        catch (Exception e)
        {
            log.warn("Your card is not valid");
        }
        return null;
    }

    @Override
    public Optional<Card> findByCardId(Integer cardId) {
        return cardRepository.findById(cardId);
    }


    @Override
    public void doPayment(Optional<Card> card,Integer pay) {
        if(pay<=card.get().getAccount()) {
            Card cardNew=cardRepository.findByCardPassword(card.get().getCardPassword());
            cardNew.setAccount(card.get().getAccount()-pay);
        }
    }

    @Override
    public Card findByCardPassword(String cardPassword) {
        return cardRepository.findByCardPassword(cardPassword);
    }




}