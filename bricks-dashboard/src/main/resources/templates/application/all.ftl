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
<#import "../skeleton.ftl" as skeleton>
<@skeleton.skeleton "Bricks Dashboard - Applications">
<div class="container">
    <h2>Some applications... :)</h2>
    <ul>
        <#list applications as application>
        <li><a href="/applications/${application.id}">${application.name}</a></li>            
        </#list>
    </ul>
</div>
</@skeleton.skeleton>
