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

import static org.apache.batik.util.SVGConstants.SVG_CIRCLE_TAG;
import static org.apache.batik.util.SVGConstants.SVG_CX_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_CY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_R_ATTRIBUTE;

import org.w3c.dom.Document;

import lombok.NonNull;

public class CircleBuilder extends ElementBuilder<CircleBuilder> implements CoreAttributes<CircleBuilder>,
    GraphicalEventAttributes<CircleBuilder>, PresentationAttributes<CircleBuilder>, GraphicsAttributes<CircleBuilder> {

    private CircleBuilder(Document document) {
        super(document.createElementNS(SVG_NAMESPACE_URI, SVG_CIRCLE_TAG));
    }

    public CircleBuilder cx(@NonNull Integer cx) {
        return setAttribute(SVG_CX_ATTRIBUTE, cx);
    }

    public CircleBuilder cy(@NonNull Integer cy) {
        return setAttribute(SVG_CY_ATTRIBUTE, cy);
    }

    public CircleBuilder r(@NonNull Integer r) {
        return setAttribute(SVG_R_ATTRIBUTE, r);
    }

    static CircleBuilder builder(@NonNull Document document) {
        return new CircleBuilder(document);
    }
}
