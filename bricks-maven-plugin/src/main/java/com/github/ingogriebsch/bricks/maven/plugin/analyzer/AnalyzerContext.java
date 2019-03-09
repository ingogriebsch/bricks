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

import java.util.concurrent.TimeUnit;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.reflections.Reflections;

import com.google.common.base.Stopwatch;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

public interface AnalyzerContext extends AutoCloseable {

    void close(); // promises to not throw an Exception!

    Log log();

    MavenProject project();

    Reflections reflections();

    MavenSession session();

    AnalyzerContext childContext(String prefix);

    public static AnalyzerContextImpl of(@NonNull MavenSession session, @NonNull Log log) {
        return new AnalyzerContextImpl(session, log);
    }
}

@Accessors(fluent = true)
class AnalyzerContextImpl implements AnalyzerContext {

    @Getter
    private final MavenSession session;

    @Getter
    private final MavenProject project;

    @Getter
    private final Log log;

    private final Stopwatch stopWatch;

    private Reflections reflections;

    public AnalyzerContextImpl(@NonNull MavenSession session, @NonNull Log log) {
        this.log = log;
        this.session = session;
        this.project = session.getCurrentProject();
        this.stopWatch = Stopwatch.createStarted();
    }

    public synchronized Reflections reflections() {
        // init lazily, as object creation is very expensive.
        // note that this optimization is gone if you use AbstractMavenAnalyzer
        if (this.reflections == null) {
            reflections = new Reflections();
        }
        return reflections;
    }

    @Override
    public void close() {
        log.info("");
        log.info("Overall Runtime: " + stopWatch.stop().elapsed(TimeUnit.MILLISECONDS) + "ms");
    }

    public AnalyzerContext childContext(String prefix) {
        return new ChildAnalyzerContext(this, prefix);
    }

}

// decorator changing the logger to a prefixlogger
@RequiredArgsConstructor
class ChildAnalyzerContext implements AnalyzerContext {

    private interface MethodExclusions {

        Log log();

        AnalyzerContext childContext(String prefix);
    }

    @Delegate(excludes = MethodExclusions.class)
    final AnalyzerContext parent;

    final String prefix;

    public Log log() {
        return new PrefixLog("  " + prefix + "> ", parent.log());
    }

    public AnalyzerContext childContext(String prefix) {
        return new ChildAnalyzerContext(this, prefix);
    }

}

// Log decorator adding a prefix
@RequiredArgsConstructor
class PrefixLog implements Log {

    private final String prefix;

    @Delegate(types = MethodInclusions.class)
    private final Log parent;

    private interface MethodInclusions {

        void debug(Throwable error);

        boolean isDebugEnabled();

        void info(Throwable error);

        boolean isInfoEnabled();

        void warn(Throwable error);

        boolean isWarnEnabled();

        void error(Throwable error);

        boolean isErrorEnabled();

    }

    public void debug(CharSequence content) {
        parent.debug(prefix + content);
    }

    public void debug(CharSequence content, Throwable error) {
        parent.debug(prefix + content, error);
    }

    public void info(CharSequence content) {
        parent.info(prefix + content);
    }

    public void info(CharSequence content, Throwable error) {
        parent.info(prefix + content, error);
    }

    public void warn(CharSequence content) {
        parent.warn(prefix + content);
    }

    public void warn(CharSequence content, Throwable error) {
        parent.warn(prefix + content, error);
    }

    public void error(CharSequence content) {
        parent.error(prefix + content);
    }

    public void error(CharSequence content, Throwable error) {
        parent.error(prefix + content, error);
    }

}
