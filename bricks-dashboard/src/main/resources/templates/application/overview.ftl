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
    Application '${application.id}'! :)
    <br>
    Name: ${application.name}
    <br>
    Version: ${application.version}
    <br>
    <a href="/applications/${application.id}/components">Components</a>
</div>
</@skeleton.app>
