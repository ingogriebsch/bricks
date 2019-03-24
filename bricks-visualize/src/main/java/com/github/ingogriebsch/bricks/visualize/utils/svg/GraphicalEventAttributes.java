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

import static org.apache.batik.util.SVGConstants.SVG_ONACTIVATE_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_ONCLICK_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_ONFOCUSIN_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_ONFOCUSOUT_ATTRIBUTE;

import lombok.NonNull;

public interface GraphicalEventAttributes<B> extends AttributeAware<B> {

    default B onFocusIn(@NonNull String onFocusIn) {
        return setAttribute(SVG_ONFOCUSIN_ATTRIBUTE, onFocusIn);
    }

    default B onFocusOut(@NonNull String onFocusOut) {
        return setAttribute(SVG_ONFOCUSOUT_ATTRIBUTE, onFocusOut);
    }

    default B onActivate(@NonNull String onActivate) {
        return setAttribute(SVG_ONACTIVATE_ATTRIBUTE, onActivate);
    }

    default B onClick(@NonNull String onClick) {
        return setAttribute(SVG_ONCLICK_ATTRIBUTE, onClick);
    }

}
