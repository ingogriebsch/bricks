package com.github.ingogriebsch.bricks.assemble.collector.github;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;

import com.github.ingogriebsch.bricks.assemble.collector.ComponentIdCollector;
import com.github.ingogriebsch.bricks.assemble.utils.github.GitHubConfiguration;
import com.github.ingogriebsch.bricks.assemble.utils.github.GitHubConfiguration.Credentials;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;

import lombok.NonNull;

public class GitHubComponentIdCollector implements ComponentIdCollector {

    private final Validator validator = new Validator();
    private final RepositoryService repositoryService;
    private final BiPredicate<String, String> repositoryNameFilter;

    public GitHubComponentIdCollector(@NonNull GitHubConfiguration configuration,
        @NonNull BiPredicate<String, String> repositoryNameFilter) {
        validator.validate(configuration);
        repositoryService = new RepositoryService(createClient(configuration));
        this.repositoryNameFilter = repositoryNameFilter;
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

    @Override
    public Set<String> collect(@NonNull String applicationId) {
        Set<String> result = new HashSet<>();
        repositoryService.pageRepositories().forEach(r -> collect(applicationId, r, result));
        return result;
    }

    private void collect(String applicationId, Collection<Repository> repositories, Set<String> result) {
        repositories.stream().filter(r -> repositoryNameFilter.test(r.getName(), applicationId))
            .forEach(r -> result.add(r.getName()));
    }

}
