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
package com.github.ingogriebsch.bricks.maven.plugin.analyzer.discovery;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AbstractMavenAnalyzer;
import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalysisResult;
import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalyzerContext;
import com.github.ingogriebsch.bricks.model.Component;
import com.google.common.collect.Lists;

import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.junit.Test;
import org.mockito.Matchers;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("unchecked")
public class AnalyzersTest {

    @Test
    public void testOK() throws Exception {
        AnalyzerDiscovery testDiscovery = x -> Lists.newArrayList(DummyAnalyzer.of(AnalysisResult.OK));
        AnalyzerContext context = mock(AnalyzerContext.class, RETURNS_MOCKS);
        Log log = mock(Log.class);

        when(context.log()).thenReturn(log);
        Analyzers uut = new Analyzers(context, testDiscovery);

        Component c = new Component();
        uut.augment(c);

        verify(log).info(Matchers.startsWith(AnalysisResult.OK + " " + DummyAnalyzer.class.getSimpleName() + " done"));
    }

    @Test
    public void testSkipped() throws Exception {
        AnalyzerDiscovery testDiscovery = x -> Lists.newArrayList(DummyAnalyzer.of(AnalysisResult.SKIPPED));
        AnalyzerContext context = mock(AnalyzerContext.class, RETURNS_MOCKS);
        Log log = mock(Log.class);
        when(context.log()).thenReturn(log);
        Analyzers uut = new Analyzers(context, testDiscovery);

        Component c = new Component();
        uut.augment(c);

        verify(log).info(Matchers.startsWith(AnalysisResult.SKIPPED + " " + DummyAnalyzer.class.getSimpleName() + " done"));
    }

    @Test
    public void testFatalOnReturningNull() throws Exception {
        AnalyzerDiscovery testDiscovery = x -> Lists.newArrayList(DummyAnalyzer.of(null));
        AnalyzerContext context = mock(AnalyzerContext.class, RETURNS_MOCKS);
        Log log = mock(Log.class);
        when(context.log()).thenReturn(log);
        Analyzers uut = new Analyzers(context, testDiscovery);

        Component c = new Component();
        try {
            uut.augment(c);
            fail("should have thrown a MojoFailureException");
        } catch (MojoFailureException e) {
            verify(log).info(Matchers.startsWith(AnalysisResult.FATAL.toString()));
        }
    }

    @Test
    public void testFatalOnMojoException() throws Exception {

        DummyAnalyzer analyzer = mock(DummyAnalyzer.class);
        when(analyzer.augment(any())).thenThrow(MojoFailureException.class);

        AnalyzerDiscovery testDiscovery = x -> Lists.newArrayList(analyzer);
        AnalyzerContext context = mock(AnalyzerContext.class, RETURNS_MOCKS);
        Log log = mock(Log.class);
        when(context.log()).thenReturn(log);
        Analyzers uut = new Analyzers(context, testDiscovery);

        Component c = new Component();
        try {
            uut.augment(c);
            fail("should have thrown a MojoFailureException");
        } catch (MojoFailureException e) {
            verify(log).info(Matchers.startsWith(AnalysisResult.FATAL.toString()));
        }
    }

    @Test
    public void testFailOnArbitraryException() throws Exception {

        DummyAnalyzer analyzer = mock(DummyAnalyzer.class);
        when(analyzer.augment(any())).thenThrow(IllegalStateException.class);

        AnalyzerDiscovery testDiscovery = x -> Lists.newArrayList(analyzer);
        AnalyzerContext context = mock(AnalyzerContext.class, RETURNS_MOCKS);
        Log log = mock(Log.class);
        when(context.log()).thenReturn(log);
        Analyzers uut = new Analyzers(context, testDiscovery);

        Component c = new Component();
        uut.augment(c);
        verify(log).info(Matchers.startsWith(AnalysisResult.FAIL.toString()));
    }

    @RequiredArgsConstructor(staticName = "of")
    static class DummyAnalyzer extends AbstractMavenAnalyzer {

        final AnalysisResult result;

        @Override
        protected AnalysisResult augment(Component c) {
            return result;
        }

    }

}
