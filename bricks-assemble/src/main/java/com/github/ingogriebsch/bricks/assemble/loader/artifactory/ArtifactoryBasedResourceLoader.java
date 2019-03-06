package com.github.ingogriebsch.bricks.assemble.loader.artifactory;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.IOException;
import java.io.InputStream;

import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;
import com.github.ingogriebsch.bricks.assemble.utils.artifactory.ArtifactoryConfiguration;
import com.github.ingogriebsch.bricks.assemble.utils.artifactory.ArtifactoryConfiguration.Credentials;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;

import org.jfrog.artifactory.client.Artifactory;
import org.jfrog.artifactory.client.ArtifactoryClientBuilder;
import org.jfrog.artifactory.client.DownloadableArtifact;
import org.jfrog.artifactory.client.RepositoryHandle;

import lombok.NonNull;

public class ArtifactoryBasedResourceLoader implements ResourceLoader {

    private final ResourceOriginProvider resourceOriginProvider;
    private final Artifactory artifactory;

    public ArtifactoryBasedResourceLoader(@NonNull ArtifactoryConfiguration configuration,
        @NonNull ResourceOriginProvider resourceOriginProvider) {
        this(resourceOriginProvider, createArtifactory(validate(configuration)));
    }

    ArtifactoryBasedResourceLoader(@NonNull ResourceOriginProvider resourceOriginProvider, @NonNull Artifactory artifactory) {
        this.artifactory = artifactory;
        this.resourceOriginProvider = resourceOriginProvider;
    }

    @Override
    public InputStream load(@NonNull String id) throws IOException {
        ResourceOrigin resourceOrigin = resourceOriginProvider.get(id);

        RepositoryHandle repository = artifactory.repository(resourceOrigin.getRepositoryName());
        if (repository == null) {
            return null;
        }

        DownloadableArtifact download = repository.download(resourceOrigin.getPath());
        if (download == null) {
            return null;
        }

        return download.doDownload();
    }

    private static <T> T validate(T object) {
        new Validator().validate(object);
        return object;
    }

    private static Artifactory createArtifactory(ArtifactoryConfiguration configuration) {
        ArtifactoryClientBuilder builder = ArtifactoryClientBuilder.create();
        builder.setUrl(configuration.getUrl());

        String accessToken = configuration.getAccessToken();
        if (!isBlank(accessToken)) {
            builder.setAccessToken(accessToken);
        } else {
            Credentials credentials = configuration.getCredentials();
            builder.setUsername(credentials.getUsername()).setPassword(credentials.getPassword());
        }
        return builder.build();
    }
}
