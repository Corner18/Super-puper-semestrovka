<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring />
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
                <a href="/profile" class="btn btn-light"><@spring.message 'base.page.profile'/></a>
                <a href="/logout" class="btn btn-light"><@spring.message 'base.page.logout'/></a>
                <a href="/support" class="btn btn-light"><@spring.message 'base.page.chat'/></a>
                <#if user.role == "ADMIN">
                    <a href="/admin" class="btn btn-light">ADMIN</a>
                </#if>
            <#else>
                <a href="/login" class="btn btn-light"><@spring.message 'base.page.login'/></a>
            </#if>
        </div>
    </nav>
    <@content/>
    </body>
    </html>
</#macro>