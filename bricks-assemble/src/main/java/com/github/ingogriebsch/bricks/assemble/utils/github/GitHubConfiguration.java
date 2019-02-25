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
