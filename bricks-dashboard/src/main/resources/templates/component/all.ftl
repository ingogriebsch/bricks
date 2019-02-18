<!DOCTYPE html>
<html>
    <head>
        <title>Components</title>
    </head>
    <body>
        <h2>The components of application '${applicationId}'... :)</h2>
        <ul>
            <#list components as component>
            <li><a href="/applications/${applicationId}/components/${component.id}">${component.name}</a></li>            
            </#list>
        </ul>
    </body>
</html>
