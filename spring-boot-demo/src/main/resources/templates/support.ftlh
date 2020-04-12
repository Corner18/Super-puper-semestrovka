<#ftl encoding='UTF-8'>
<#include "base.ftlh"/>
<#macro content>
    <head>
        <script
                src="https://code.jquery.com/jquery-3.4.1.js"
                integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
                crossorigin="anonymous"></script>
        <script>
            var webSocket;

            function connect() {
                document.cookie = 'X-Authorization=' + '123456' + ';path=/';
                webSocket = new WebSocket('ws://localhost:8080/chat');

                webSocket.onmessage = function receiveMessage(response) {
                    let data = response['data'];
                    let json = JSON.parse(data);
                    $('#messageList').append("<strong>" + json['sender'] + ": </strong>" + json['text'] + "<br>")
                };

                webSocket.onerror = function errorShow() {
                    alert('Ошибка авторизации')
                }
            }

            function sendMessage(text, sender, receiver) {
                let message = {
                    'text': text,
                    'sender': sender,
                    'receiver': receiver
                };
                document.getElementById("message").value = "";
                webSocket.send(JSON.stringify(message));
            }

            $(document).ready(function () {
                connect();
                $('#receiver').change(function () {
                    window.location.href = location.protocol + '//' + location.host + ':' + location.port +  "/admin"
                        + '/' + $(this).val();
                });
                let queryParams = new URLSearchParams(window.location.search);
                if (queryParams.has("receiver")) {
                    console.log(queryParams.get("receiver"));
                    document.getElementById("receiver").value = queryParams.get("receiver");
                }
            });

        </script>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Chat</title>
        <link rel="stylesheet" href="/public/boostrap/bootstrap.css">
        <link rel="stylesheet"
              href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/posts.css" type="text/css">
    </head>

    <div>
        <label for="message">Текст сообщения</label>
        <input name="message" id="message" placeholder="Message">
        <button onclick="sendMessage(document.getElementById('message').value,
        '${user.email}', <#if user.role == "ADMIN"> '${receiver}' <#else> '${admin}' </#if>)">Send</button>
    </div>
    <br>

    <div>
        <h3> Messages </h3>

        <div id="messageList">
            <#list messages as message>
                <strong> ${message.sender}: </strong> ${message.text} <br>
            </#list>
        </div>
    </div>

</#macro>
<@main/>