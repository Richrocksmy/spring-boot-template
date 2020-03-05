package com.richrocksmy.springboottemplate.controller;

import com.richrocksmy.springboottemplate.model.CreatedResponse;
import com.richrocksmy.springboottemplate.model.db.Message;
import com.richrocksmy.springboottemplate.model.dto.MessageDto;
import com.richrocksmy.springboottemplate.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    @Mock
    private MessageService messageService;

    private MessageController messageController;

    @BeforeEach
    public void setup() {
        messageController = new MessageController(messageService);
    }

    @Test
    public void shouldCreateMessage() {
        // Given
        Message message = Message.builder().id(UUID.randomUUID()).messageText("message text").build();
        when(messageService.saveMessage(message.getMessageText())).thenReturn(message);

        // When
        CreatedResponse createdResponse = messageController.createMessage(new MessageDto(message.getMessageText()));

        // Then
        assertThat(createdResponse.getId()).isNotNull();
        verify(messageService).saveMessage(message.getMessageText());
    }

    @Test
    public void shouldRetrieveMessage() {
        // Given
        Message message = Message.builder().id(UUID.randomUUID()).messageText("message text").build();
        when(messageService.retrieveMessage(message.getId())).thenReturn(message);

        // When
        MessageDto messageDto = messageController.retrieveMessage(message.getId());

        // Then
        assertThat(messageDto.getMessageText()).isEqualTo(message.getMessageText());
        verify(messageService).retrieveMessage(message.getId());
    }

}