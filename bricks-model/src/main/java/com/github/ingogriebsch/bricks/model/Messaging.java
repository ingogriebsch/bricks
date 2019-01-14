package com.github.ingogriebsch.bricks.model;

import java.util.Set;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Messaging {

    /**
     * The underlying system which transports the messages. Legal values are 'kafka', 'rabbitmq', etc.
     */
    @NotBlank
    private String carrier;

    /**
     * The communication protocol which is used to abstract the messages. Legal values are 'amqp', 'protobuf', etc.
     */
    @NotBlank
    private String protocol;

    /**
     * A collection of messages the component provides.
     */
    @Valid
    private Set<Message> messages;
}
