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

import org.w3c.dom.Element;

import lombok.NonNull;

abstract class ContainerElementBuilder<B extends ContainerElementBuilder<B>> extends ElementBuilder<B> {

    protected ContainerElementBuilder(@NonNull Element element) {
        super(element);
    }

    public B element(@NonNull Element child) {
        return appendChild(child, getElement());
    }

    @SuppressWarnings("unchecked")
    private B appendChild(Element child, Element parent) {
        parent.appendChild(child);
        return (B) this;
    }

}
