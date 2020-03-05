package com.richrocksmy.springboottemplate.controller;

import com.richrocksmy.springboottemplate.model.CreatedResponse;
import com.richrocksmy.springboottemplate.model.dto.MessageDto;
import com.richrocksmy.springboottemplate.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public final class MessageController {

    private final MessageService messageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = {"application/json"}, consumes = "application/json")
    public CreatedResponse createMessage(@RequestBody final MessageDto messageDto) {
        return new CreatedResponse(messageService.saveMessage(messageDto.getMessageText()).getId());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{messageId}")
    public MessageDto retrieveMessage(@PathVariable UUID messageId) {
        return new MessageDto(messageService.retrieveMessage(messageId).getMessageText());
    }
}