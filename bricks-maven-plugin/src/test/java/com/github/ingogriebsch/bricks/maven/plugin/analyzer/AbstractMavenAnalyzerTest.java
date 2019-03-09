package com.github.ingogriebsch.bricks.maven.plugin.analyzer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.github.ingogriebsch.bricks.model.Component;

public class AbstractMavenAnalyzerTest {

    @Test(expected = NullPointerException.class)
    public void testNullContext() throws Exception {
        NullAnalyzer uut = new NullAnalyzer();
        uut.augment(null, Mockito.mock(Component.class));
    }

    @Test(expected = NullPointerException.class)
    public void testNullComponent() throws Exception {
        NullAnalyzer uut = new NullAnalyzer();
        uut.augment(Mockito.mock(AnalyzerContext.class), null);
    }

    public void testParameterPassesUnchanged() throws Exception {
        NullAnalyzer uut = mock(NullAnalyzer.class);
        ArgumentCaptor<Component> cap = ArgumentCaptor.forClass(Component.class);
        when(uut.augment(cap.capture())).thenReturn(AnalysisResult.OK);
        Component probe = new Component();

        uut.augment(Mockito.mock(AnalyzerContext.class, RETURNS_MOCKS), probe);

        assertSame(cap.getValue(), probe);
    }

    static class NullAnalyzer extends AbstractMavenAnalyzer {

        @Override
        protected AnalysisResult augment(Component c) {
            assertNotNull(c);
            return AnalysisResult.OK;
        }
    }
}
