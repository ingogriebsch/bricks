package com.github.ingogriebsch.bricks.assemble.utils.artifactory;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ArtifactoryConfiguration {

    @NotBlank
    private String url;

    @Valid
    private Credentials credentials;
    private String accessToken;

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
