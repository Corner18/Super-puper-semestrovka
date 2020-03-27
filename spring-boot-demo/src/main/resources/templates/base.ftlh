<#ftl encoding="UTF-8"/>
<#macro content></#macro>
<#macro main>
    <!doctype html>
    <html lang="en">

    <body>
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="/main">
            <img src="https://www.meme-arsenal.com/memes/790c8ef330ea19d59921b9b87ea911ca.jpg" width="150" height="75" class="d-inline-block align-top" alt="">
        </a>
        <div class="navbar-button-wrapper">
            <#if user??>
                <a href="/profile" class="btn btn-light">Profile</a>
                <a href="/logout" class="btn btn-light">Logout</a>
            <#else>
                <a href="/login" class="btn btn-light">Login</a>
            </#if>
        </div>
    </nav>
    <@content/>
    </body>
    </html>
</#macro>