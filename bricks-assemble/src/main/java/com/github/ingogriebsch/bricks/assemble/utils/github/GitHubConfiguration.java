/*
 * Copyright © 2018 Ingo Griebsch (https://ingogriebsch.de/)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ingogriebsch.bricks.assemble.utils.github;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// FIXME Validate that either credentials or token is set
// FIXME Validate that port is either -1 or valid port
@AllArgsConstructor
@Data
@NoArgsConstructor
public final class GitHubConfiguration {

    @NotBlank
    private String scheme = "https";
    @NotBlank
    private String host;
    private int port = -1;

    @Valid
    private Credentials credentials;
    private String oAuth2Token;

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static final class Credentials {

        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

}
