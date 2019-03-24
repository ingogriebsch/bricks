/*-
 * #%L
 * Bricks Visualize
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
package com.github.ingogriebsch.bricks.visualize.svg;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;

import com.github.ingogriebsch.bricks.model.Component;

import org.junit.jupiter.api.Test;

public class Component2SvgVisualizerTest {

    @Test
    public void visualize_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> new Component2SvgVisualizer().visualize(null, null));
    }

    @Test
    public void visualize_should_use_given_output_stream() throws Exception {
        Component component = new Component();
        component.setId("componen1");
        component.setName("Component 1");
        component.setVersion("1.0.0");
        component.setDescription("Some text which describes the component...");

        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            assertThat(output.size()).isEqualTo(0);
            new Component2SvgVisualizer().visualize(component, output);
            assertThat(output.size()).isGreaterThan(0);
        }
    }

    // FIXME How to test that the output is generated correctly?

}
