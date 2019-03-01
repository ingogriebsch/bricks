<#--
 #%L
 Bricks Dashboard
 %%
 Copyright (C) 2018 - 2019 Ingo Griebsch
 %%
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 #L%
-->
<#import "skeleton.ftl" as skeleton>
<@skeleton.app "Bricks Dashboard - Applications - ${application.name} - Overview">
<div class="container">
    <div class="row">
        <div class="col s12">
            <h3>${application.name} ${application.version}</h3>
        </div>
    </div>
    <div class="row">
        <div class="col s6">
            <div class="card">
                <div class="card-image">
                    <img src="/images/application-template.png">
                </div>
            </div>
        </div>
        <div class="col s6">
            <h5><p>${application.description}</p:</h5>
        </div>
    </div>
    <div class="row">
        <div class="divider"></div>
        <h6><em>Responsibles:</em></h6>
        <div class="col s12">
            <p>
            <#list application.responsibles as responsible>
                <span><a href="mailto:${responsible.email}">${responsible.name}</a></span><#sep>, </#sep>
            </#list>
            </p>
        </div>
    </div>
</div>
</@skeleton.app>
