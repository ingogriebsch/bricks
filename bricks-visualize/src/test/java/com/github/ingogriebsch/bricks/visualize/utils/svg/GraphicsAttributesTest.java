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
package com.github.ingogriebsch.bricks.visualize.utils.svg;

import static org.apache.batik.util.SVGConstants.SVG_CLASS_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_EXTERNAL_RESOURCES_REQUIRED_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_STYLE_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_TRANSFORM_ATTRIBUTE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class GraphicsAttributesTest {

    @Test
    public void clazz_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(GraphicsAttributes.class).clazz(null));
    }

    @Test
    public void clazz_should_call_setAttribute_with_given_input() {
        String value = "value";

        GraphicsAttributes<?> attributes = spy(GraphicsAttributes.class);
        attributes.clazz(value);

        verify(attributes).setAttribute(SVG_CLASS_ATTRIBUTE, value);
    }

    @Test
    public void style_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(GraphicsAttributes.class).style(null));
    }

    @Test
    public void style_should_call_setAttribute_with_given_input() {
        String value = "value";

        GraphicsAttributes<?> attributes = spy(GraphicsAttributes.class);
        attributes.style(value);

        verify(attributes).setAttribute(SVG_STYLE_ATTRIBUTE, value);
    }

    @Test
    public void transform_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(GraphicsAttributes.class).transform(null));
    }

    @Test
    public void transform_should_call_setAttribute_with_given_input() {
        String value = "value";

        GraphicsAttributes<?> attributes = spy(GraphicsAttributes.class);
        attributes.transform(value);

        verify(attributes).setAttribute(SVG_TRANSFORM_ATTRIBUTE, value);
    }

    @Test
    public void externalResourcesRequired_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(GraphicsAttributes.class).externalResourcesRequired(null));
    }

    @Test
    public void externalResourcesRequired_should_call_setAttribute_with_given_input() {
        Boolean value = Boolean.TRUE;

        GraphicsAttributes<?> attributes = spy(GraphicsAttributes.class);
        attributes.externalResourcesRequired(value);

        verify(attributes).setAttribute(SVG_EXTERNAL_RESOURCES_REQUIRED_ATTRIBUTE, value);
    }
}
