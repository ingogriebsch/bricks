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

import static org.apache.batik.util.SVGConstants.SVG_FILL_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_FONT_SIZE_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_FONT_VARIANT_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_STROKE_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_STROKE_DASHARRAY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_STROKE_OPACITY_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE;

import lombok.NonNull;

public interface PresentationAttributes<B> extends AttributeAware<B> {

    default B fill(@NonNull String fill) {
        return setAttribute(SVG_FILL_ATTRIBUTE, fill);
    }

    default B fillOpacity(@NonNull String fillOpacity) {
        return setAttribute(SVG_FILL_OPACITY_ATTRIBUTE, fillOpacity);
    }

    default B fontFamily(@NonNull String fontFamily) {
        return setAttribute(SVG_FONT_FAMILY_ATTRIBUTE, fontFamily);
    }

    default B fontSize(@NonNull String fontSize) {
        return setAttribute(SVG_FONT_SIZE_ATTRIBUTE, fontSize);
    }

    default B fontVariant(@NonNull FontVariant fontVariant) {
        return setAttribute(SVG_FONT_VARIANT_ATTRIBUTE, fontVariant);
    }

    default B stroke(@NonNull String stroke) {
        return setAttribute(SVG_STROKE_ATTRIBUTE, stroke);
    }

    default B strokeOpacity(@NonNull String strokeOpacity) {
        return setAttribute(SVG_STROKE_OPACITY_ATTRIBUTE, strokeOpacity);
    }

    default B strokeDasharray(@NonNull String strokeDasharray) {
        return setAttribute(SVG_STROKE_DASHARRAY_ATTRIBUTE, strokeDasharray);
    }

    default B strokeWidth(@NonNull String strokeWidth) {
        return setAttribute(SVG_STROKE_WIDTH_ATTRIBUTE, strokeWidth);
    }

    default B visibility(@NonNull Visibility visibility) {
        return setAttribute("visibility", visibility);
    }

    enum Visibility {
            Visible("visible"), Hidden("hidden"), Collapse("collapse"), Inherit("inherit");

        private final String label;

        private Visibility(@NonNull String label) {
            this.label = label;
        }

        public String label() {
            return label;
        }

        @Override
        public String toString() {
            return label;
        }

        Visibility valueOfLabel(@NonNull String label) {
            return asList(values()).stream().filter(v -> label.equals(v.label)).findFirst().orElse(null);
        }
    }

    enum FontVariant {
            Normal("normal"), SmallCaps("small-caps"), Inherit("inherit");

        private final String label;

        private FontVariant(@NonNull String label) {
            this.label = label;
        }

        public String label() {
            return label;
        }

        @Override
        public String toString() {
            return label;
        }

        FontVariant valueOfLabel(@NonNull String label) {
            return asList(values()).stream().filter(v -> label.equals(v.label)).findFirst().orElse(null);
        }
    }
}
