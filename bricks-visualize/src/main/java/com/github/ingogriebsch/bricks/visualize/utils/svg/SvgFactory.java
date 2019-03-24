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

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.Function;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.svg2svg.SVGTranscoder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import lombok.NonNull;

public class SvgFactory {

    @NonNull
    private final Document document;

    private SvgFactory(@NonNull Document document) {
        this.document = document;
    }

    public static SvgFactory instance() {
        return new SvgFactory(getDOMImplementation().createDocument(SVG_NAMESPACE_URI, SVG_SVG_TAG, null));
    }

    public void create(@NonNull OutputStream output) throws Exception {
        new SVGTranscoder().transcode(new TranscoderInput(document), new TranscoderOutput(new OutputStreamWriter(output)));
    }

    public Element svg(ElementFunction<SvgBuilder> function) {
        return function.apply(SvgBuilder.builder(document));
    }

    public Element circle(ElementFunction<CircleBuilder> function) {
        return function.apply(CircleBuilder.builder(document));
    }

    public Element ellipse(ElementFunction<EllipseBuilder> function) {
        return function.apply(EllipseBuilder.builder(document));
    }

    public Element group(ElementFunction<GroupBuilder> function) {
        return function.apply(GroupBuilder.builder(document));
    }

    public Element line(ElementFunction<LineBuilder> function) {
        return function.apply(LineBuilder.builder(document));
    }

    public Element polygon(ElementFunction<PolygonBuilder> function) {
        return function.apply(PolygonBuilder.builder(document));
    }

    public Element polyline(ElementFunction<PolylineBuilder> function) {
        return function.apply(PolylineBuilder.builder(document));
    }

    public Element rect(ElementFunction<RectBuilder> function) {
        return function.apply(RectBuilder.builder(document));
    }

    public Element text(ElementFunction<TextBuilder> function) {
        return function.apply(TextBuilder.builder(document));
    }

    public static interface ElementFunction<B extends ElementBuilder<B>> extends Function<B, Element> {
        // Marker interface...
    }

}
