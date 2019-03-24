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

import static org.apache.batik.util.SVGConstants.SVG_LINE_TAG;
import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_X1_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_X2_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_Y1_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_Y2_ATTRIBUTE;

import org.w3c.dom.Document;

import lombok.NonNull;

public class LineBuilder extends ElementBuilder<LineBuilder> implements CoreAttributes<LineBuilder>,
    GraphicalEventAttributes<LineBuilder>, PresentationAttributes<LineBuilder>, GraphicsAttributes<LineBuilder> {

    private LineBuilder(Document document) {
        super(document.createElementNS(SVG_NAMESPACE_URI, SVG_LINE_TAG));
    }

    public LineBuilder x1(@NonNull Integer x1) {
        return setAttribute(SVG_X1_ATTRIBUTE, x1);
    }

    public LineBuilder y1(@NonNull Integer y1) {
        return setAttribute(SVG_Y1_ATTRIBUTE, y1);
    }

    public LineBuilder x2(@NonNull Integer x2) {
        return setAttribute(SVG_X2_ATTRIBUTE, x2);
    }

    public LineBuilder y2(@NonNull Integer y2) {
        return setAttribute(SVG_Y2_ATTRIBUTE, y2);
    }

    static LineBuilder builder(@NonNull Document document) {
        return new LineBuilder(document);
    }

}
