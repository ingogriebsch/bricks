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
package com.github.ingogriebsch.bricks.assemble.collector;

import static java.lang.String.format;

import java.util.HashSet;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReaderFactory;
import com.github.ingogriebsch.bricks.model.Application;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApplicationCollector {

    @NonNull
    private final ApplicationIdCollector applicationIdCollector;
    @NonNull
    private final ApplicationReaderFactory applicationReaderFactory;

    public Set<Application> collect() throws Exception {
        Set<String> applicationIds = applicationIdCollector.collect();

        Set<Application> result = new HashSet<>(applicationIds.size());
        for (String id : applicationIds) {
            ApplicationReader reader = applicationReaderFactory.create(id);
            if (reader == null) {
                throw new IllegalStateException(format("No reader found for application '%s'!", id));
            }

            Application application = reader.read(id);
            if (application != null) {
                result.add(application);
            }
        }
        return result;
    }

}
