/*-
 * #%L
 * Bricks Dashboard
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
package com.github.ingogriebsch.bricks.dashboard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DashboardPropertiesTest {

    @Test
    public void set_RedirectToApplicationViewIfOnlyOneAvailable_returns_matching_value() {
        DashboardProperties dashboardProperties = new DashboardProperties();

        boolean value = true;
        dashboardProperties.setRedirectToApplicationViewIfOnlyOneAvailable(value);
        assertThat(dashboardProperties.isRedirectToApplicationViewIfOnlyOneAvailable()).isEqualTo(value);

        value = false;
        dashboardProperties.setRedirectToApplicationViewIfOnlyOneAvailable(value);
        assertThat(dashboardProperties.isRedirectToApplicationViewIfOnlyOneAvailable()).isEqualTo(value);
    }

}
