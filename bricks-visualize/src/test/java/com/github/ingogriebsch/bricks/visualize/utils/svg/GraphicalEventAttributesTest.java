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

import static org.apache.batik.util.SVGConstants.SVG_ONACTIVATE_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_ONCLICK_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_ONFOCUSIN_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_ONFOCUSOUT_ATTRIBUTE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GraphicalEventAttributesTest {

    @Test
    public void onFocusIn_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(GraphicalEventAttributes.class).onFocusIn(null));
    }

    @Test
    public void onFocusIn_should_call_setAttribute_with_given_input() {
        String value = "value";

        GraphicalEventAttributes<?> attributes = spy(GraphicalEventAttributes.class);
        attributes.onFocusIn(value);

        verify(attributes).setAttribute(SVG_ONFOCUSIN_ATTRIBUTE, value);
    }

    @Test
    public void onFocusOut_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(GraphicalEventAttributes.class).onFocusOut(null));
    }

    @Test
    public void onFocusOut_should_call_setAttribute_with_given_input() {
        String value = "value";

        GraphicalEventAttributes<?> attributes = spy(GraphicalEventAttributes.class);
        attributes.onFocusOut(value);

        verify(attributes).setAttribute(SVG_ONFOCUSOUT_ATTRIBUTE, value);
    }

    @Test
    public void onActivate_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(GraphicalEventAttributes.class).onActivate(null));
    }

    @Test
    public void onActivate_should_call_setAttribute_with_given_input() {
        String value = "value";

        GraphicalEventAttributes<?> attributes = spy(GraphicalEventAttributes.class);
        attributes.onActivate(value);

        verify(attributes).setAttribute(SVG_ONACTIVATE_ATTRIBUTE, value);
    }

    @Test
    public void onClick_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(GraphicalEventAttributes.class).onClick(null));
    }

    @Test
    public void onClick_should_call_setAttribute_with_given_input() {
        String value = "value";

        GraphicalEventAttributes<?> attributes = spy(GraphicalEventAttributes.class);
        attributes.onClick(value);

        verify(attributes).setAttribute(SVG_ONCLICK_ATTRIBUTE, value);
    }
}
