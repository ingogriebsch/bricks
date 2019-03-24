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

import static org.apache.batik.util.SVGConstants.SVG_ID_ATTRIBUTE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CoreAttributesTest {

    @Test
    public void id_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> spy(CoreAttributes.class).id(null));
    }

    @Test
    public void id_should_call_setAttribute_with_given_input() {
        String id = "id";

        CoreAttributes<?> coreAttributes = spy(CoreAttributes.class);
        coreAttributes.id(id);

        verify(coreAttributes).setAttribute(SVG_ID_ATTRIBUTE, id);
    }
}
