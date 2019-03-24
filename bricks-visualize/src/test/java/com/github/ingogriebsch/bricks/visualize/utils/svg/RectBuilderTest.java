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

import static org.apache.batik.anim.dom.SVGDOMImplementation.getDOMImplementation;
import static org.apache.batik.util.SVGConstants.SVG_HEIGHT_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_RX_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_RY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_SVG_TAG;
import static org.apache.batik.util.SVGConstants.SVG_WIDTH_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_X_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_Y_ATTRIBUTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

public class RectBuilderTest {

    private static final Document document = getDOMImplementation().createDocument(SVG_NAMESPACE_URI, SVG_SVG_TAG, null);

    @Test
    public void builder_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> RectBuilder.builder(null));
    }

    @Test
    public void build_should_return_builder() {
        assertThat(RectBuilder.builder(document)).isNotNull();
    }

    @Test
    public void build_should_return_matching_element() {
        RectBuilder builder = RectBuilder.builder(document);
        assertThat(builder.build()).isNotNull().isInstanceOf(SVGRectElement.class);
    }

    @Test
    public void x_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> RectBuilder.builder(document).x(null));
    }

    @Test
    public void y_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> RectBuilder.builder(document).y(null));
    }

    @Test
    public void width_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> RectBuilder.builder(document).width(null));
    }

    @Test
    public void height_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> RectBuilder.builder(document).height(null));
    }

    @Test
    public void rx_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> RectBuilder.builder(document).rx(null));
    }

    @Test
    public void ry_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> RectBuilder.builder(document).ry(null));
    }

    @Test
    public void build_should_return_element_containing_matching_attributes() {
        Integer x = 11, y = 22, width = 55, height = 66, rx = 33, ry = 44;
        Element element = RectBuilder.builder(document).x(x).y(y).width(width).height(height).rx(rx).ry(ry).build();
        assertThat(element.getAttribute(SVG_X_ATTRIBUTE)).isEqualTo(x.toString());
        assertThat(element.getAttribute(SVG_Y_ATTRIBUTE)).isEqualTo(y.toString());
        assertThat(element.getAttribute(SVG_WIDTH_ATTRIBUTE)).isEqualTo(width.toString());
        assertThat(element.getAttribute(SVG_HEIGHT_ATTRIBUTE)).isEqualTo(height.toString());
        assertThat(element.getAttribute(SVG_RX_ATTRIBUTE)).isEqualTo(rx.toString());
        assertThat(element.getAttribute(SVG_RY_ATTRIBUTE)).isEqualTo(ry.toString());
    }
}
