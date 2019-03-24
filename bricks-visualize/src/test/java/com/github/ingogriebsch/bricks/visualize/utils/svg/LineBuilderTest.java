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
import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_SVG_TAG;
import static org.apache.batik.util.SVGConstants.SVG_X1_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_X2_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_Y1_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_Y2_ATTRIBUTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGLineElement;

public class LineBuilderTest {

    private static final Document document = getDOMImplementation().createDocument(SVG_NAMESPACE_URI, SVG_SVG_TAG, null);

    @Test
    public void builder_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> LineBuilder.builder(null));
    }

    @Test
    public void build_should_return_builder() {
        assertThat(LineBuilder.builder(document)).isNotNull();
    }

    @Test
    public void build_should_return_matching_element() {
        LineBuilder builder = LineBuilder.builder(document);
        assertThat(builder.build()).isNotNull().isInstanceOf(SVGLineElement.class);
    }

    @Test
    public void x1_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> LineBuilder.builder(document).x1(null));
    }

    @Test
    public void y1_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> LineBuilder.builder(document).y1(null));
    }

    @Test
    public void x2_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> LineBuilder.builder(document).x2(null));
    }

    @Test
    public void y2_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> LineBuilder.builder(document).y2(null));
    }

    @Test
    public void build_should_return_element_containing_matching_attributes() {
        Integer x1 = 11, y1 = 22, x2 = 33, y2 = 44;
        Element element = LineBuilder.builder(document).x1(x1).y1(y1).x2(x2).y2(y2).build();
        assertThat(element.getAttribute(SVG_X1_ATTRIBUTE)).isEqualTo(x1.toString());
        assertThat(element.getAttribute(SVG_Y1_ATTRIBUTE)).isEqualTo(y1.toString());
        assertThat(element.getAttribute(SVG_X2_ATTRIBUTE)).isEqualTo(x2.toString());
        assertThat(element.getAttribute(SVG_Y2_ATTRIBUTE)).isEqualTo(y2.toString());
    }
}
