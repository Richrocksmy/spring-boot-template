package com.richrocksmy.springboottemplate.service;

import com.richrocksmy.springboottemplate.NotFoundException;
import com.richrocksmy.springboottemplate.model.db.Message;
import com.richrocksmy.springboottemplate.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public final class MessageService {

    private final MessageRepository messageRepository;

    public Message saveMessage(final String messageText) {
        return messageRepository.save(Message.builder().messageText(messageText).build());
    }

    public Message retrieveMessage(final UUID messageId) {
        return messageRepository.findById(messageId).orElseThrow(NotFoundException::new);
    }
}
