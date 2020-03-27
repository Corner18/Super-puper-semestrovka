<#ftl encoding='UTF-8'>
<#include "base.ftlh"/>
<#macro content>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"
      type="text/css">
<link rel="stylesheet" href="css/auth.css" type="text/css">
<main class="my-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Registration</div>
                    <div class="card-body">
                        <div class="feed-container">
                            <div class="feed">
                                <h1>Files</h1>
                                <form action="/files" class="col-md-4 col-form-label text-md-right" method="post"
                                      name="data" id="data" enctype="multipart/form-data">
                                    <div id="namer" class="col-md-4 col-form-label text-md-right">
                                        <div id="namer-input" class="col-md-4 col-form-label text-md-right">
                                            <input type="file" id="file" name="file"/>
                                        </div>
                                    </div>
                                    <button class="btn btn-info btn-block my-4">Submit</button>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>


    <script type="application/javascript">
        $("form#data").submit(function (e) {
            e.preventDefault();
            var formData = new FormData(this);

            $.ajax({
                url: '/files',
                type: 'POST',
                data: formData,
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function (data) {
                    alert(data)
                },
                cache: false,
                contentType: false,
                processData: false
            });
        });
    </script>
    <div>
        <#if message?has_content>
            <p>${message}</p>
        <#else>
        </#if>
    </div>
    </#macro>
    <#macro title>
        <title>Loading File</title>
    </#macro>
    <@main/>
