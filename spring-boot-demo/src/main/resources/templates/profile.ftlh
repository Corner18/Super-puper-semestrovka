<#ftl encoding="UTF-8"/>
<#include "base.ftlh"/>
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
                        <h5>Do you want upload photo?
                            <a href="/storage">Let's do it</a>
                        </h5>
                        <div class="row second">
                            <a href="/favourites" class="btn btn-primary">Favourite posts</a>
                        </div>

                    </#if>
                </blockquote>
            </div>
        </div>


    </div>
    </body>
</#macro>
<@main/>