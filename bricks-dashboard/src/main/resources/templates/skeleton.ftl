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
<#macro skeleton title>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>${title}</title>

        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    </head>
    <body>
        <header>
            <nav>
                <div class="nav-wrapper bricks-background">
                    <div class="col s12">
                        <#list breadcrumb.entries as entry>
                        <a href="${entry.href}" class="breadcrumb">${entry.name}</a>
                        </#list>
                    </div>
                </div>
            </nav>      
        </header>
        <main>
            <#nested />
        </main>
        <footer class="page-footer bricks-background">
            <div class="footer-copyright white-text">
              © 2014-<noscript>2018</noscript><script type="text/javascript">document.write(new Date().getFullYear());</script>, All rights reserved.
              <a class="white-text right" href="https://github.com/ingogriebsch/bricks">Bricks</a>
            </div>
        </footer>
        
        <!-- Scripts-->
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="/js/materialize.js"></script>
        <script src="/js/init.js"></script>
    </body>
</html>
</#macro>
