/*
 * Copyright © 2018 Ingo Griebsch (https://ingogriebsch.de/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ingogriebsch.bricks.assemble.collector;

import java.util.HashSet;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.assembler.ApplicationAssembler;
import com.github.ingogriebsch.bricks.model.Application;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApplicationCollector {

    @NonNull
    private final ApplicationIdCollector applicationIdCollector;
    @NonNull
    private final ApplicationAssembler applicationAssembler;

    public Set<Application> collect() throws Exception {
        Set<String> applicationIds = applicationIdCollector.collect();

        Set<Application> result = new HashSet<>(applicationIds.size());
        for (String id : applicationIds) {
            Application application = applicationAssembler.assemble(id);
            if (application != null) {
                result.add(application);
            }
        }
        return result;
    }

}
