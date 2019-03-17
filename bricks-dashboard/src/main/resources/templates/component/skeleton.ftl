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
<#function active tab>
    <#if tab == "${view}">
        <#return " class=\"active\"">
    <#else>
        <#return "">
    </#if>
</#function>
<#macro compo title>
<#import "../skeleton.ftl" as skeleton>
<@skeleton.skeleton "${title}">
    <ul class="tabs z-depth-1">
        <li class="tab"><a href="/applications/${application.id}/components/${component.id}/overview"${active("overview")}>Overview</a></li>
    </ul>
    <#nested />
</@skeleton.skeleton>
</#macro>
