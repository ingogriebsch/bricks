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

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_POINTS_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_POLYGON_TAG;

import java.awt.Point;

import org.w3c.dom.Document;

import lombok.NonNull;

public class PolygonBuilder extends ElementBuilder<PolygonBuilder> implements CoreAttributes<PolygonBuilder>,
    GraphicalEventAttributes<PolygonBuilder>, PresentationAttributes<PolygonBuilder>, GraphicsAttributes<PolygonBuilder> {

    private PolygonBuilder(Document document) {
        super(document.createElementNS(SVG_NAMESPACE_URI, SVG_POLYGON_TAG));
    }

    public PolygonBuilder calculate(@NonNull Integer cx, @NonNull Integer cy, @NonNull Integer sides, @NonNull Integer radius,
        Integer startAngle) {
        return points(asPoints(cx, cy, sides, radius, startAngle));
    }

    public PolygonBuilder points(@NonNull Point... points) {
        return setAttribute(SVG_POINTS_ATTRIBUTE, asString(points));
    }

    static PolygonBuilder builder(@NonNull Document document) {
        return new PolygonBuilder(document);
    }

    private static Point[] asPoints(Integer cx, Integer cy, Integer sides, Integer radius, Integer startAngle) {
        // FIXME Implement me... :)
        return null;
    }

    private static String asString(Point[] points) {
        return asList(points).stream().map(p -> asString(p)).collect(joining(" "));
    }

    private static String asString(Point point) {
        return point.x + "," + point.y;
    }

}
