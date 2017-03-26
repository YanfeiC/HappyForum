$(document).ready(function () {
    //Ajax with SpringSecurity CSRF
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    var username_ok = 0
    var email_ok = 0

    $("#username").blur(function () {
        if (/^[A-Za-z0-9]+$/.test($("#username").val())) {
            $.post("/signup/username", {
                username: $("#username").val()
            }, function (data, textStatus) {
                if (data == true) {
                    $("#username_ok").html("用户名已注册");
                    username_ok = 1;
                } else {
                    $("#username_ok").html("用户名未注册");
                    username_ok = 0;
                }
            })
        } else {
            $("#username_ok").html("用户名格式错误");
        }
    });


    $("#email").blur(function () {
        if (/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test($("#email").val())) {
            $.post("/signup/email", {
                email : $("#email").val()
            }, function (data, textStatus) {
                if (data == true) {
                    $("#email_ok").html("该邮箱已注册");
                    email_ok = 1;
                } else {
                    $("#email_ok").html("该邮箱未注册");
                    email_ok = 0;
                }
            })
        } else {
            $("#email_ok").html("邮箱格式错误");
        }
    });


})

