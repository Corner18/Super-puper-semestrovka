<#ftl encoding="UTF-8"/>
<#include "base.ftlh"/>
<#macro content>

    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/public/js/registration.js"></script>
    <!------ Include the above in your HEAD tag ---------->


    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Fonts -->
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <title>Registration</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css" type="text/css">
    <link rel="stylesheet" href="css/auth.css" type="text/css">
    </head>
    <body>
    <main class="my-form">
        <div class="cotainer">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Registration</div>
                        <div class="card-body">
                            <form name="my-form" onsubmit="return validform()" action="/registration" method="post">


                                <div class="form-group row">
                                    <label for="first_name" class="col-md-4 col-form-label text-md-right">Email</label>
                                    <div class="col-md-6">
                                        <input type="text" id="email" class="form-control" name="email">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="login"
                                           class="col-md-4 col-form-label text-md-right">login</label>
                                    <div class="col-md-6">
                                        <input type="text" id="login" class="form-control" name="login">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="name"
                                           class="col-md-4 col-form-label text-md-right">name</label>
                                    <div class="col-md-6">
                                        <input type="text" id="name" class="form-control" name="name">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="name"
                                           class="col-md-4 col-form-label text-md-right">phoneNumber</label>
                                    <div class="col-md-6">
                                        <input type="text" id="phoneNumber" class="form-control" name="phoneNumber">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                    <div class="col-md-6">
                                        <input type="password" id="password" class="form-control" name="password">
                                    </div>
                                </div>

                                <div class="col-md-6 offset-md-4">
                                    <button class="btn btn-info btn-block my-4" type="submit">Join us</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>

    </main>

    </body>
    </html>
</#macro>
<@main/>