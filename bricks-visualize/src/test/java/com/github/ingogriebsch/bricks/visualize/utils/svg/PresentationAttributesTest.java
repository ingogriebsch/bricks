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

import static org.apache.batik.util.SVGConstants.SVG_FILL_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_FONT_SIZE_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_FONT_VARIANT_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_STROKE_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_STROKE_DASHARRAY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_STROKE_OPACITY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import com.github.ingogriebsch.bricks.visualize.utils.svg.PresentationAttributes.FontVariant;
import com.github.ingogriebsch.bricks.visualize.utils.svg.PresentationAttributes.Visibility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PresentationAttributesTest {

    @Test
    public void fill_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(PresentationAttributes.class).fill(null));
    }

    @Test
    public void fill_should_call_setAttribute_with_given_input() {
        String value = "value";

        PresentationAttributes<?> attributes = spy(PresentationAttributes.class);
        attributes.fill(value);

        verify(attributes).setAttribute(SVG_FILL_ATTRIBUTE, value);
    }

    @Test
    public void fillOpacity_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(PresentationAttributes.class).fillOpacity(null));
    }

    @Test
    public void fillOpacity_should_call_setAttribute_with_given_input() {
        String value = "value";

        PresentationAttributes<?> attributes = spy(PresentationAttributes.class);
        attributes.fillOpacity(value);

        verify(attributes).setAttribute(SVG_FILL_OPACITY_ATTRIBUTE, value);
    }

    @Test
    public void fontFamily_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(PresentationAttributes.class).fontFamily(null));
    }

    @Test
    public void fontFamily_should_call_setAttribute_with_given_input() {
        String value = "value";

        PresentationAttributes<?> attributes = spy(PresentationAttributes.class);
        attributes.fontFamily(value);

        verify(attributes).setAttribute(SVG_FONT_FAMILY_ATTRIBUTE, value);
    }

    @Test
    public void fontSize_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(PresentationAttributes.class).fontSize(null));
    }

    @Test
    public void fontSize_should_call_setAttribute_with_given_input() {
        String value = "value";

        PresentationAttributes<?> attributes = spy(PresentationAttributes.class);
        attributes.fontSize(value);

        verify(attributes).setAttribute(SVG_FONT_SIZE_ATTRIBUTE, value);
    }

    @Test
    public void fontVariant_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(PresentationAttributes.class).fontVariant(null));
    }

    @Test
    public void fontVariant_should_call_setAttribute_with_given_input() {
        FontVariant value = FontVariant.SmallCaps;
        PresentationAttributes<?> attributes = spy(PresentationAttributes.class);
        attributes.fontVariant(value);

        verify(attributes).setAttribute(SVG_FONT_VARIANT_ATTRIBUTE, value);
    }

    @Test
    public void stroke_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(PresentationAttributes.class).stroke(null));
    }

    @Test
    public void stroke_should_call_setAttribute_with_given_input() {
        String value = "value";

        PresentationAttributes<?> attributes = spy(PresentationAttributes.class);
        attributes.stroke(value);

        verify(attributes).setAttribute(SVG_STROKE_ATTRIBUTE, value);
    }

    @Test
    public void strokeOpacity_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(PresentationAttributes.class).strokeOpacity(null));
    }

    @Test
    public void strokeOpacity_should_call_setAttribute_with_given_input() {
        String value = "value";

        PresentationAttributes<?> attributes = spy(PresentationAttributes.class);
        attributes.strokeOpacity(value);

        verify(attributes).setAttribute(SVG_STROKE_OPACITY_ATTRIBUTE, value);
    }

    @Test
    public void strokeDasharray_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(PresentationAttributes.class).strokeDasharray(null));
    }

    @Test
    public void strokeDasharray_should_call_setAttribute_with_given_input() {
        String value = "value";

        PresentationAttributes<?> attributes = spy(PresentationAttributes.class);
        attributes.strokeDasharray(value);

        verify(attributes).setAttribute(SVG_STROKE_DASHARRAY_ATTRIBUTE, value);
    }

    @Test
    public void strokeWidth_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(PresentationAttributes.class).strokeWidth(null));
    }

    @Test
    public void strokeWidth_should_call_setAttribute_with_given_input() {
        String value = "value";

        PresentationAttributes<?> attributes = spy(PresentationAttributes.class);
        attributes.strokeWidth(value);

        verify(attributes).setAttribute(SVG_STROKE_WIDTH_ATTRIBUTE, value);
    }

    @Test
    public void visibility_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(PresentationAttributes.class).visibility(null));
    }

    @Test
    public void visibility_should_call_setAttribute_with_given_input() {
        Visibility value = Visibility.Collapse;
        PresentationAttributes<?> attributes = spy(PresentationAttributes.class);
        attributes.visibility(value);

        verify(attributes).setAttribute("visibility", value);
    }
}
