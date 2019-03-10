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
package com.github.ingogriebsch.bricks.maven.plugin.analyzer.standard;

import java.util.List;
import java.util.stream.Collectors;

import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AbstractMavenAnalyzer;
import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalysisResult;
import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.model.License;

import lombok.SneakyThrows;

public class LicenseAnalyzer extends AbstractMavenAnalyzer {

    @Override
    @SneakyThrows
    protected AnalysisResult augment(Component c) {

        List<org.apache.maven.model.License> licenses = project.getLicenses();

        if (licenses == null || licenses.isEmpty())
            return AnalysisResult.SKIPPED;

        if (licenses.size() > 1) {
            log.info("More than one Licenses detected: "
                + licenses.stream().map(org.apache.maven.model.License::getName).collect(Collectors.toList()));
            log.info("Only using the first one.");
        }

        org.apache.maven.model.License license = licenses.get(0);

        License modelLicense = new License();
        modelLicense.setName(license.getName());
        modelLicense.setUrl(license.getUrl());

        c.setLicense(modelLicense);
        return AnalysisResult.OK;

    }
}
