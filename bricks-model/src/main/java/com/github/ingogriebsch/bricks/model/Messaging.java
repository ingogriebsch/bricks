/*-
 * #%L
 * Bricks Model
 * %%
 * Copyright (C) 2018 - 2019 Ingo Griebsch
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.github.ingogriebsch.bricks.model;

import java.util.Set;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * Messaging is the exchange of messages (specially-formatted data describing events, requests, and replies). Messaging makes it
 * easier for programs to communicate across different programming environments.
 */
@Data
public class Messaging {

    /**
     * The underlying system which transports the messages. Legal values are 'kafka', 'rabbitmq', etc.
     */
    @NotBlank
    String carrier;

    /**
     * The communication protocol which is used to abstract the messages. Legal values are 'amqp', 'protobuf', etc.
     */
    @NotBlank
    String protocol;

    /**
     * A collection of messages the component provides.
     */
    @Valid
    Set<Message> messages;
}
