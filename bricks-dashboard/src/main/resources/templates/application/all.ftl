<!DOCTYPE html>
<html>
    <head>
        <title>Applications</title>
    </head>
    <body>
        <h2>Some applications... :)</h2>
        <ul>
            <#list applications as application>
            <li><a href="/applications/${application.id}">${application.name}</a></li>            
            </#list>
        </ul>
    </body>
</html>
