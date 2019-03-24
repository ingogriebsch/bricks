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

import static org.apache.batik.util.SVGConstants.SVG_CX_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_CY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_ELLIPSE_TAG;
import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_RX_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_RY_ATTRIBUTE;

import org.w3c.dom.Document;

import lombok.NonNull;

public class EllipseBuilder extends ElementBuilder<EllipseBuilder> implements CoreAttributes<EllipseBuilder>,
    GraphicalEventAttributes<EllipseBuilder>, PresentationAttributes<EllipseBuilder>, GraphicsAttributes<EllipseBuilder> {

    private EllipseBuilder(Document document) {
        super(document.createElementNS(SVG_NAMESPACE_URI, SVG_ELLIPSE_TAG));
    }

    public EllipseBuilder cx(@NonNull Integer cx) {
        return setAttribute(SVG_CX_ATTRIBUTE, cx);
    }

    public EllipseBuilder cy(@NonNull Integer cy) {
        return setAttribute(SVG_CY_ATTRIBUTE, cy);
    }

    public EllipseBuilder rx(@NonNull Integer rx) {
        return setAttribute(SVG_RX_ATTRIBUTE, rx);
    }

    public EllipseBuilder ry(@NonNull Integer ry) {
        return setAttribute(SVG_RY_ATTRIBUTE, ry);
    }

    static EllipseBuilder builder(@NonNull Document document) {
        return new EllipseBuilder(document);
    }

}
