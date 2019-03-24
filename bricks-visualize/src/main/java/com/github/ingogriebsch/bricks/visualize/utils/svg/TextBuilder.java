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

import static org.apache.batik.util.SVGConstants.SVG_DX_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_DY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_LENGTH_ADJUST_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_ROTATE_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_TEXT_LENGTH_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_TEXT_TAG;
import static org.apache.batik.util.SVGConstants.SVG_X_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_Y_ATTRIBUTE;

import org.w3c.dom.Document;

import lombok.NonNull;

public class TextBuilder extends ElementBuilder<TextBuilder>
    implements CoreAttributes<TextBuilder>, GraphicalEventAttributes<TextBuilder>, PresentationAttributes<TextBuilder> {

    private TextBuilder(Document document) {
        super(document.createElementNS(SVG_NAMESPACE_URI, SVG_TEXT_TAG));
    }

    public TextBuilder lengthAdjust(@NonNull LengthAdjust lengthAdjust) {
        return setAttribute(SVG_LENGTH_ADJUST_ATTRIBUTE, lengthAdjust);
    }

    public TextBuilder x(@NonNull Integer x) {
        return setAttribute(SVG_X_ATTRIBUTE, x);
    }

    public TextBuilder y(@NonNull Integer y) {
        return setAttribute(SVG_Y_ATTRIBUTE, y);
    }

    public TextBuilder dx(@NonNull Integer dx) {
        return setAttribute(SVG_DX_ATTRIBUTE, dx);
    }

    public TextBuilder dy(@NonNull Integer dy) {
        return setAttribute(SVG_DY_ATTRIBUTE, dy);
    }

    public TextBuilder rotate(@NonNull String rotate) {
        return setAttribute(SVG_ROTATE_ATTRIBUTE, rotate);
    }

    public TextBuilder textLength(@NonNull Integer textLength) {
        return setAttribute(SVG_TEXT_LENGTH_ATTRIBUTE, textLength);
    }

    public TextBuilder content(@NonNull String content) {
        getElement().setTextContent(content);
        return this;
    }

    static TextBuilder builder(@NonNull Document document) {
        return new TextBuilder(document);
    }

    public static enum LengthAdjust {
            spacing, spacingAndGlyphs;
    }

}
