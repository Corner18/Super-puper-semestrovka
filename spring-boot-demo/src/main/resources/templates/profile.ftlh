<#ftl encoding="UTF-8"/>
<#include "base.ftlh"/>
<#import "spring.ftl" as spring />
<#macro content>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="css/profile.css" type="text/css">
    <meta charset="UTF-8">
    <body>

    <div class="container">
        <div class="row">
            <div class="col-md-6 img">
                <#if url?has_content>
                    <img src="${url}" width="300" height="150"
                         alt="" class="img-rounded">
                </#if>

            </div>
            <div class="col-md-6 details">
                <blockquote>
                    <#if user??>
                        <h5>${user.name}</h5>
                        <h5>${user.email}</h5>
                        <h5><@spring.message 'profile.page.photo'/></h5>
                        <h5><a href="/storage"><@spring.message 'profile.page.doit'/></a></h5>

                        <div class="row second">
                            <a href="/favourites" class="btn btn-primary"><@spring.message 'profile.page.favs'/></a>
                        </div>

                    </#if>
                </blockquote>
            </div>
        </div>


    </div>
    </body>
</#macro>
<@main/>