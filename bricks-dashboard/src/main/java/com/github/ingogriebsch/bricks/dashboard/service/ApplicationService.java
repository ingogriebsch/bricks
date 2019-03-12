/*-
 * #%L
 * Bricks Dashboard
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
package com.github.ingogriebsch.bricks.dashboard.service;

import static com.google.common.collect.MoreCollectors.toOptional;

import java.util.Optional;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ApplicationCollector;
import com.github.ingogriebsch.bricks.model.Application;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationService {

    @NonNull
    private final ApplicationCollector applicationCollector;

    public Set<Application> findAll() throws Exception {
        return applicationCollector.collect();
    }

    public Optional<Application> findOne(@NonNull String id) throws Exception {
        return findAll().stream().filter(a -> id.equals(a.getId())).collect(toOptional());
    }
}
