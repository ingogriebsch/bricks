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
    <#list applications as application>
    <div class="row">
        <div class="col s4">
            <a href="/applications/${application.id}/overview">
                <div class="card blue-grey darken-1">
                    <div class="card-content white-text">
                        <span class="card-title">${application.name}</span>
                    </div>
                </div>
            </a>
        </div>
    </div>
    </#list>
</div>
</@skeleton.skeleton>
