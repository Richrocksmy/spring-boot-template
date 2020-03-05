package com.richrocksmy.springboottemplate.service;

import com.richrocksmy.springboottemplate.NotFoundException;
import com.richrocksmy.springboottemplate.model.db.Message;
import com.richrocksmy.springboottemplate.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    private MessageService messageService;

    @BeforeEach
    public void setup() {
        messageService = new MessageService(messageRepository);
    }

    @Test
    void shouldSaveMessage() {
        // Given
        Message message = Message.builder().id(UUID.randomUUID()).messageText("message text").build();
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        // When
        Message retrievedMessage = messageService.saveMessage(message.getMessageText());

        // Then
        assertThat(retrievedMessage.getMessageText()).isEqualTo(message.getMessageText());
        verify(messageRepository).save(any(Message.class));
    }

    @Test
    void shouldRetrieveMessageWhenItExistsInTheDb() {
        // Given
        Message message = Message.builder().id(UUID.randomUUID()).messageText("message text").build();
        when(messageRepository.findById(message.getId())).thenReturn(Optional.of(message));

        // When
        Message retrievedMessage = messageService.retrieveMessage(message.getId());

        // Then
        assertThat(retrievedMessage).isEqualTo(message);
    }

    @Test
    void shouldThrowExceptionWhenMessageDoesNotExistInTheDb() {
        // Given
        Message message = Message.builder().id(UUID.randomUUID()).messageText("message text").build();
        when(messageRepository.findById(message.getId())).thenReturn(Optional.empty());

        // When / Then
        assertThatThrownBy(() -> messageService.retrieveMessage(message.getId()))
                .isInstanceOf(NotFoundException.class);
    }
}