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

import static org.apache.batik.util.SVGConstants.SVG_G_TAG;
import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;

import org.w3c.dom.Document;

import lombok.NonNull;

public class GroupBuilder extends ContainerElementBuilder<GroupBuilder>
    implements PresentationAttributes<GroupBuilder>, GraphicsAttributes<GroupBuilder> {

    private GroupBuilder(Document document) {
        super(document.createElementNS(SVG_NAMESPACE_URI, SVG_G_TAG));
    }

    static GroupBuilder builder(@NonNull Document document) {
        return new GroupBuilder(document);
    }

}
