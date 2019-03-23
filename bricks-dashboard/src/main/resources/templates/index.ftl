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
<@skeleton.skeleton "Bricks Dashboard - Applications">
<div class="container">
    <#if applications?has_content>
    <div class="row">
    <#list applications as application>
        <div class="col s4">
            <div class="card hoverable">
                <div class="card-image waves-effect waves-block waves-light">
                  <img class="activator" src="images/application-template.png">
                </div>
                <div class="card-content">
                  <span class="card-title activator"><a href="/applications/${application.id}">${application.name} ${application.version}</a><i class="material-icons right">more_vert</i></span>
                </div>
                <div class="card-reveal">
                  <span class="card-title">${application.name} ${application.version}<i class="material-icons right">close</i></span>
                  <p>${application.description}</p>
                </div>
            </div>
        </div>
    <#if application?counter % 3 == 0>
    </div>
    <div class="row">
    </#if>
    </#list>
    </div>
    <#else>
    <div class="row">
        <div class="col s12 center">
            <span class="grey-text text-darken-5"><h5>There are no applications available...</h5></span>        
        </div>
    </div>
    </#if>
</div>
</@skeleton.skeleton>
