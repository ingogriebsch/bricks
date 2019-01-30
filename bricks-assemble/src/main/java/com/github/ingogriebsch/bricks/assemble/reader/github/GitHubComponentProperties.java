package com.github.ingogriebsch.bricks.assemble.reader.github;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public final class GitHubComponentProperties {

    @NotBlank
    private String contentFilename = "bricks.json";
    private String ref;

}
