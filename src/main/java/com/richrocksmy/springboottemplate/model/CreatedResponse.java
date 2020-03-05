package com.richrocksmy.springboottemplate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public final class CreatedResponse {

    private final UUID id;
}
