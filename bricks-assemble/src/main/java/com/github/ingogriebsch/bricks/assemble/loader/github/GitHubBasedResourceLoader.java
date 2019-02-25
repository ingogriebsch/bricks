package com.github.ingogriebsch.bricks.assemble.loader.github;

import static java.lang.String.format;

import static org.apache.commons.io.IOUtils.toInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;
import com.github.ingogriebsch.bricks.assemble.utils.github.GitHubConfiguration;
import com.github.ingogriebsch.bricks.assemble.utils.github.GitHubConfiguration.Credentials;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;

import lombok.NonNull;

public class GitHubBasedResourceLoader implements ResourceLoader {

    private final GitHubBasedResourceProperties properties;
    private final RepositoryIdProvider repositoryIdProvider;
    private final ContentsService contentsService;

    public GitHubBasedResourceLoader(@NonNull GitHubConfiguration configuration,
        @NonNull GitHubBasedResourceProperties properties) {
        this(configuration, properties, new OneOnOneRepositoryIdProvider());
    }

    public GitHubBasedResourceLoader(@NonNull GitHubConfiguration configuration,
        @NonNull GitHubBasedResourceProperties properties, @NonNull RepositoryIdProvider repositoryIdProvider) {
        this(configuration, properties, repositoryIdProvider, new ContentsService(createClient(configuration)));
    }

    GitHubBasedResourceLoader(@NonNull GitHubConfiguration configuration, @NonNull GitHubBasedResourceProperties properties,
        @NonNull RepositoryIdProvider repositoryIdProvider, @NonNull ContentsService contentsService) {
        validate(configuration);
        this.properties = validate(properties);
        this.repositoryIdProvider = repositoryIdProvider;
        this.contentsService = contentsService;
    }

    @Override
    public InputStream load(@NonNull String id) throws IOException {
        List<RepositoryContents> contents =
            contentsService.getContents(repositoryId(id), properties.getContentFilename(), properties.getRef());

        if (contents == null || contents.isEmpty()) {
            return null;
        }

        if (contents.size() > 1) {
            throw new IllegalStateException(
                format("Weird things happen! More then one repository contents is available for id '%s'!", id));
        }

        RepositoryContents content = contents.iterator().next();
        return toInputStream(content.getContent(), content.getEncoding());
    }

    private IRepositoryIdProvider repositoryId(String id) {
        return new IRepositoryIdProvider() {

            @Override
            public String generateId() {
                return repositoryIdProvider.getId(id);
            }
        };
    }

    private static <T> T validate(T object) {
        new Validator().validate(object);
        return object;
    }

    private static GitHubClient createClient(GitHubConfiguration configuration) {
        GitHubClient client = new GitHubClient(configuration.getHost(), configuration.getPort(), configuration.getScheme());

        if (!StringUtils.isBlank(configuration.getOAuth2Token())) {
            client.setOAuth2Token(configuration.getOAuth2Token());
        } else {
            Credentials credentials = configuration.getCredentials();
            client.setCredentials(credentials.getUsername(), credentials.getPassword());
        }
        return client;
    }

}
