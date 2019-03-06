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

    private final ResourceOriginProvider resourceOriginProvider;
    private final ContentsService contentsService;

    public GitHubBasedResourceLoader(@NonNull GitHubConfiguration configuration,
        @NonNull ResourceOriginProvider resourceOriginProvider) {
        this(configuration, resourceOriginProvider, new ContentsService(createClient(validate(configuration))));
    }

    GitHubBasedResourceLoader(@NonNull GitHubConfiguration configuration, @NonNull ResourceOriginProvider resourceOriginProvider,
        @NonNull ContentsService contentsService) {
        this.resourceOriginProvider = resourceOriginProvider;
        this.contentsService = contentsService;
    }

    @Override
    public InputStream load(@NonNull String id) throws IOException {
        ResourceOrigin resourceOrigin = resourceOriginProvider.get(id);
        List<RepositoryContents> contents = contentsService.getContents(repositoryId(resourceOrigin.getRepositoryId()),
            resourceOrigin.getPath(), resourceOrigin.getRef());

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

    private static <T> T validate(T object) {
        new Validator().validate(object);
        return object;
    }

    private static IRepositoryIdProvider repositoryId(String id) {
        return new IRepositoryIdProvider() {

            @Override
            public String generateId() {
                return id;
            }
        };
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
