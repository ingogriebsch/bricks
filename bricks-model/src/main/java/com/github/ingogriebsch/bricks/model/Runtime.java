/*-
 * #%L
 * Bricks Model
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
package com.github.ingogriebsch.bricks.model;

import javax.validation.Valid;

import lombok.Data;

@Data
public class Runtime {

    /**
     * Specifies if the component is failover capable.
     */
    boolean failoverCapable;

    /**
     * Specifies if the component is horizontal scalable.
     */
    boolean horizontalScalable;

    /**
     * Describes the administration possibilities of the component.
     */
    @Valid
    Administration administration;

    /**
     * Describes the memory footprint of the component.
     */
    @Valid
    MemoryFootprint memoryFootprint;

    /**
     * Describes the monitoring capabilities of the component.
     */
    @Valid
    Monitoring monitoring;
}
