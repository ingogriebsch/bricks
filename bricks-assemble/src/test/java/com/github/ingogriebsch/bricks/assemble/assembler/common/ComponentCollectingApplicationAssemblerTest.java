package com.github.ingogriebsch.bricks.assemble.assembler.common;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Set;

import javax.validation.ValidationException;

import com.github.ingogriebsch.bricks.assemble.collector.ComponentCollector;
import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
import com.github.ingogriebsch.bricks.model.Application;
import com.github.ingogriebsch.bricks.model.Component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ComponentCollectingApplicationAssemblerTest {

    @Mock
    private ApplicationReader applicationReader;

    @Mock
    private ComponentCollector componentCollector;

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new ComponentCollectingApplicationAssembler(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_first_parameter_is_null() {
        new ComponentCollectingApplicationAssembler(null, componentCollector);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_second_parameter_is_null() {
        new ComponentCollectingApplicationAssembler(applicationReader, null);
    }

    @Test
    public void creation_should_succeed_if_input_is_given() {
        new ComponentCollectingApplicationAssembler(applicationReader, componentCollector);
    }

    @Test(expected = NullPointerException.class)
    public void assemble_should_throw_exception_if_input_is_null() throws Exception {
        new ComponentCollectingApplicationAssembler(applicationReader, componentCollector).assemble(null);
    }

    @Test
    public void assemble_should_return_null_if_application_is_not_available() throws Exception {
        String id = randomAlphabetic(10);
        given(applicationReader.read(id)).willReturn(null);

        new ComponentCollectingApplicationAssembler(applicationReader, componentCollector).assemble(id);
        verify(componentCollector, times(0)).collect(id);
    }

    @Test
    public void assemble_should_return_application_without_components_if_the_collector_returns_no_components() throws Exception {
        String id = randomAlphabetic(10);
        given(applicationReader.read(id)).willReturn(new Application());
        given(componentCollector.collect(id)).willReturn(newHashSet());

        ComponentCollectingApplicationAssembler componentCollectingApplicationAssembler =
            new ComponentCollectingApplicationAssembler(applicationReader, componentCollector, false);

        Application application = componentCollectingApplicationAssembler.assemble(id);
        assertThat(application).isNotNull();

        Set<Component> components = application.getComponents();
        assertThat(components).isNotNull().isEmpty();
    }

    @Test
    public void assemble_should_return_application_without_matching_components_if_the_collector_returns_some_components()
        throws Exception {
        String id = randomAlphabetic(10);
        Set<Component> components = newHashSet(new Component(), new Component());

        given(applicationReader.read(id)).willReturn(new Application());
        given(componentCollector.collect(id)).willReturn(components);

        ComponentCollectingApplicationAssembler componentCollectingApplicationAssembler =
            new ComponentCollectingApplicationAssembler(applicationReader, componentCollector, false);

        Application application = componentCollectingApplicationAssembler.assemble(id);
        assertThat(application).isNotNull();

        assertThat(application.getComponents()).isNotNull().isEqualTo(components);
    }

    @Test(expected = ValidationException.class)
    public void assemble_should_throw_exception_if_application_is_not_valid() throws Exception {
        String id = randomAlphabetic(10);
        given(applicationReader.read(id)).willReturn(new Application());
        given(componentCollector.collect(id)).willReturn(newHashSet());

        ComponentCollectingApplicationAssembler componentCollectingApplicationAssembler =
            new ComponentCollectingApplicationAssembler(applicationReader, componentCollector, true);

        componentCollectingApplicationAssembler.assemble(id);
    }
}
