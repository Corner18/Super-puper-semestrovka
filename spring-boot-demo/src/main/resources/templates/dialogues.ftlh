<#ftl encoding="UTF-8"/>
<#include "base.ftlh"/>
<#macro content>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/public/boostrap/bootstrap.css">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/posts.css" type="text/css">
    <meta charset="UTF-8">
    <title>Title</title>
    <body>



    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">

    <div class="active-purple-4 mb-4" style="width: 300px; display: inline-block; margin-left: 600px; margin-top: 10px">
        <form id="search-form">
            <input class="form-control" type="text" placeholder="Search" aria-label="Search">
        </form>
    </div>
    <div style="display: inline-block"><input type="submit" form="search-form" class="btn btn-light"></div>

    <div class="cars-horizon">
        <div class="container">

            <div class="row">
                <div class="col-md-9">
                    <#list dialogues as dialogue>
                        <div class="row border">
                            <div class="col-md-4 car-image-block">
                                <li><a href="/admin/${dialogue}" class="list">${dialogue}</a></li>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>

    </body>
</#macro>
<@main/>