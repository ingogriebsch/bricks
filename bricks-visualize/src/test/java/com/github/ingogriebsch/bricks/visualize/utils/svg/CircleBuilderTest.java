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
import static org.apache.batik.util.SVGConstants.SVG_CX_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_CY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_R_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_SVG_TAG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGCircleElement;

public class CircleBuilderTest {

    private static final Document document = getDOMImplementation().createDocument(SVG_NAMESPACE_URI, SVG_SVG_TAG, null);

    @Test
    public void builder_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> CircleBuilder.builder(null));
    }

    @Test
    public void build_should_return_builder() {
        assertThat(CircleBuilder.builder(document)).isNotNull();
    }

    @Test
    public void build_should_return_matching_element() {
        CircleBuilder builder = CircleBuilder.builder(document);
        assertThat(builder.build()).isNotNull().isInstanceOf(SVGCircleElement.class);
    }

    @Test
    public void cx_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> CircleBuilder.builder(document).cx(null));
    }

    @Test
    public void cy_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> CircleBuilder.builder(document).cy(null));
    }

    @Test
    public void r_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> CircleBuilder.builder(document).r(null));
    }

    @Test
    public void build_should_return_element_containing_matching_attributes() {
        Integer cx = 11, cy = 22, r = 33;
        Element element = CircleBuilder.builder(document).cx(cx).cy(cy).r(r).build();
        assertThat(element.getAttribute(SVG_CX_ATTRIBUTE)).isEqualTo(cx.toString());
        assertThat(element.getAttribute(SVG_CY_ATTRIBUTE)).isEqualTo(cy.toString());
        assertThat(element.getAttribute(SVG_R_ATTRIBUTE)).isEqualTo(r.toString());
    }
}
