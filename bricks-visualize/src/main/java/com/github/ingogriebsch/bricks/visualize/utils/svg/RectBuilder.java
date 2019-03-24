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

import static org.apache.batik.util.SVGConstants.SVG_HEIGHT_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_RECT_TAG;
import static org.apache.batik.util.SVGConstants.SVG_RX_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_RY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_WIDTH_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_X_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_Y_ATTRIBUTE;

import org.w3c.dom.Document;

import lombok.NonNull;

public class RectBuilder extends ElementBuilder<RectBuilder> implements CoreAttributes<RectBuilder>,
    GraphicalEventAttributes<RectBuilder>, PresentationAttributes<RectBuilder>, GraphicsAttributes<RectBuilder> {

    private RectBuilder(Document document) {
        super(document.createElementNS(SVG_NAMESPACE_URI, SVG_RECT_TAG));
    }

    public RectBuilder x(@NonNull Integer x) {
        return setAttribute(SVG_X_ATTRIBUTE, x);
    }

    public RectBuilder y(@NonNull Integer y) {
        return setAttribute(SVG_Y_ATTRIBUTE, y);
    }

    public RectBuilder width(@NonNull Integer width) {
        return setAttribute(SVG_WIDTH_ATTRIBUTE, width);
    }

    public RectBuilder height(@NonNull Integer height) {
        return setAttribute(SVG_HEIGHT_ATTRIBUTE, height);
    }

    public RectBuilder rx(@NonNull Integer rx) {
        return setAttribute(SVG_RX_ATTRIBUTE, rx);
    }

    public RectBuilder ry(@NonNull Integer ry) {
        return setAttribute(SVG_RY_ATTRIBUTE, ry);
    }

    static RectBuilder builder(@NonNull Document document) {
        return new RectBuilder(document);
    }
}
