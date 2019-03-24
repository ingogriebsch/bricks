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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.batik.anim.dom.SVGOMSVGElement;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

public class SvgBuilderTest {

    private static final Document document = getDOMImplementation().createDocument(SVG_NAMESPACE_URI, SVG_SVG_TAG, null);

    @Test
    public void builder_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> SvgBuilder.builder(null));
    }

    @Test
    public void build_should_return_builder() {
        assertThat(SvgBuilder.builder(document)).isNotNull();
    }

    @Test
    public void build_should_return_matching_element() {
        SvgBuilder builder = SvgBuilder.builder(document);
        assertThat(builder.build()).isNotNull().isInstanceOf(SVGOMSVGElement.class);
    }
}
