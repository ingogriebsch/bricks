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

import static org.apache.batik.util.SVGConstants.SVG_CLASS_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_EXTERNAL_RESOURCES_REQUIRED_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_STYLE_ATTRIBUTE;
import static org.apache.batik.util.SVGConstants.SVG_TRANSFORM_ATTRIBUTE;

import lombok.NonNull;

public interface GraphicsAttributes<B> extends AttributeAware<B> {

    default B clazz(@NonNull String clazz) {
        return setAttribute(SVG_CLASS_ATTRIBUTE, clazz);
    }

    default B style(@NonNull String style) {
        return setAttribute(SVG_STYLE_ATTRIBUTE, style);
    }

    default B externalResourcesRequired(@NonNull Boolean externalResourcesRequired) {
        return setAttribute(SVG_EXTERNAL_RESOURCES_REQUIRED_ATTRIBUTE, externalResourcesRequired);
    }

    default B transform(@NonNull String transform) {
        return setAttribute(SVG_TRANSFORM_ATTRIBUTE, transform);
    }

}
