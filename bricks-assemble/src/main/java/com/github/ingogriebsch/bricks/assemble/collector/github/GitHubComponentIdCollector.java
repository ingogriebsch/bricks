/*-
 * #%L
 * Bricks Assemble
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
package com.github.ingogriebsch.bricks.assemble.collector.github;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

import com.github.ingogriebsch.bricks.assemble.collector.ComponentIdCollector;
import com.github.ingogriebsch.bricks.assemble.utils.github.GitHubConfiguration;
import com.github.ingogriebsch.bricks.assemble.utils.github.GitHubConfiguration.Credentials;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;

import lombok.NonNull;

public class GitHubComponentIdCollector implements ComponentIdCollector {

    private final Validator validator = new Validator();
    private final RepositoryService repositoryService;
    private final BiPredicate<String, String> repositoryNameFilter;

    public GitHubComponentIdCollector(@NonNull GitHubConfiguration configuration) {
        this(configuration, (r, a) -> true);
    }

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
