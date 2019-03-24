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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.w3c.dom.Element;

@ExtendWith(MockitoExtension.class)
public class SvgFactoryTest {

    @Test
    public void instance_should_return_different_instances() {
        assertThat(SvgFactory.instance()).isNotNull().isNotSameAs(SvgFactory.instance()).isNotSameAs(SvgFactory.instance());
    }

    @Test
    public void create_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> SvgFactory.instance().create(null));
    }

    @Test
    public void svg_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> SvgFactory.instance().svg(null));
    }

    @Test
    public void svg_should_provide_builder_instance() {
        SvgFactory.instance().svg((SvgBuilder sb) -> {
            assertThat(sb).isNotNull();
            return null;
        });
    }

    @Test
    public void svg_should_return_matching_element(@Mock Element element) {
        Element outer = SvgFactory.instance().svg((SvgBuilder sb) -> {
            return element;
        });

        assertThat(outer).isNotNull().isSameAs(element);
    }

    @Test
    public void circle_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> SvgFactory.instance().circle(null));
    }

    @Test
    public void circle_should_provide_builder_instance() {
        SvgFactory.instance().circle((CircleBuilder sb) -> {
            assertThat(sb).isNotNull();
            return null;
        });
    }

    @Test
    public void circle_should_return_matching_element(@Mock Element element) {
        Element outer = SvgFactory.instance().circle((CircleBuilder sb) -> {
            return element;
        });

        assertThat(outer).isNotNull().isSameAs(element);
    }

    @Test
    public void ellipse_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> SvgFactory.instance().ellipse(null));
    }

    @Test
    public void ellipse_should_provide_builder_instance() {
        SvgFactory.instance().ellipse((EllipseBuilder sb) -> {
            assertThat(sb).isNotNull();
            return null;
        });
    }

    @Test
    public void ellipse_should_return_matching_element(@Mock Element element) {
        Element outer = SvgFactory.instance().ellipse((EllipseBuilder sb) -> {
            return element;
        });

        assertThat(outer).isNotNull().isSameAs(element);
    }

    @Test
    public void group_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> SvgFactory.instance().group(null));
    }

    @Test
    public void group_should_provide_builder_instance() {
        SvgFactory.instance().group((GroupBuilder sb) -> {
            assertThat(sb).isNotNull();
            return null;
        });
    }

    @Test
    public void group_should_return_matching_element(@Mock Element element) {
        Element outer = SvgFactory.instance().group((GroupBuilder sb) -> {
            return element;
        });

        assertThat(outer).isNotNull().isSameAs(element);
    }

    @Test
    public void line_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> SvgFactory.instance().line(null));
    }

    @Test
    public void line_should_provide_builder_instance() {
        SvgFactory.instance().line((LineBuilder sb) -> {
            assertThat(sb).isNotNull();
            return null;
        });
    }

    @Test
    public void line_should_return_matching_element(@Mock Element element) {
        Element outer = SvgFactory.instance().line((LineBuilder sb) -> {
            return element;
        });

        assertThat(outer).isNotNull().isSameAs(element);
    }

    @Test
    public void polygon_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> SvgFactory.instance().polygon(null));
    }

    @Test
    public void polygon_should_provide_builder_instance() {
        SvgFactory.instance().polygon((PolygonBuilder sb) -> {
            assertThat(sb).isNotNull();
            return null;
        });
    }

    @Test
    public void polygon_should_return_matching_element(@Mock Element element) {
        Element outer = SvgFactory.instance().polygon((PolygonBuilder sb) -> {
            return element;
        });

        assertThat(outer).isNotNull().isSameAs(element);
    }

    @Test
    public void polyline_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> SvgFactory.instance().polyline(null));
    }

    @Test
    public void polyline_should_provide_builder_instance() {
        SvgFactory.instance().polyline((PolylineBuilder sb) -> {
            assertThat(sb).isNotNull();
            return null;
        });
    }

    @Test
    public void polyline_should_return_matching_element(@Mock Element element) {
        Element outer = SvgFactory.instance().polyline((PolylineBuilder sb) -> {
            return element;
        });

        assertThat(outer).isNotNull().isSameAs(element);
    }

    @Test
    public void rect_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> SvgFactory.instance().rect(null));
    }

    @Test
    public void rect_should_provide_builder_instance() {
        SvgFactory.instance().rect((RectBuilder sb) -> {
            assertThat(sb).isNotNull();
            return null;
        });
    }

    @Test
    public void rect_should_return_matching_element(@Mock Element element) {
        Element outer = SvgFactory.instance().rect((RectBuilder sb) -> {
            return element;
        });

        assertThat(outer).isNotNull().isSameAs(element);
    }

    @Test
    public void text_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> SvgFactory.instance().text(null));
    }

    @Test
    public void text_should_provide_builder_instance() {
        SvgFactory.instance().text((TextBuilder sb) -> {
            assertThat(sb).isNotNull();
            return null;
        });
    }

    @Test
    public void text_should_return_matching_element(@Mock Element element) {
        Element outer = SvgFactory.instance().text((TextBuilder sb) -> {
            return element;
        });

        assertThat(outer).isNotNull().isSameAs(element);
    }

}
