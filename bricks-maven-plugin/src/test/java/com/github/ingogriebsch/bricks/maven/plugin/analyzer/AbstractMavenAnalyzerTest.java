/*-
 * #%L
 * Bricks Maven Plugin
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
package com.github.ingogriebsch.bricks.maven.plugin.analyzer;

import static com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalysisResult.OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.ingogriebsch.bricks.model.Component;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class AbstractMavenAnalyzerTest {

    @Test
    public void testNullContext() throws Exception {
        NullAnalyzer uut = new NullAnalyzer();

        assertThrows(NullPointerException.class, () -> {
            uut.augment(null, mock(Component.class));
        });
    }

    @Test
    public void testNullComponent() throws Exception {
        NullAnalyzer uut = new NullAnalyzer();

        assertThrows(NullPointerException.class, () -> {
            uut.augment(mock(AnalyzerContext.class), null);
        });
    }

    public void testParameterPassesUnchanged() throws Exception {
        NullAnalyzer uut = mock(NullAnalyzer.class);
        ArgumentCaptor<Component> cap = forClass(Component.class);
        when(uut.augment(cap.capture())).thenReturn(OK);

        Component probe = new Component();
        uut.augment(mock(AnalyzerContext.class, RETURNS_MOCKS), probe);
        assertSame(cap.getValue(), probe);
    }

    static class NullAnalyzer extends AbstractMavenAnalyzer {

        @Override
        protected AnalysisResult augment(Component c) {
            assertNotNull(c);
            return OK;
        }
    }
}
