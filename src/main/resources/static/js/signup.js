$(document).ready(function () {
    //Ajax with SpringSecurity CSRF
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    var username_ok = 1;
    var email_ok = 1;
    var rightImg = '<img src="/img/right.png" alt="正确"  width="15px" height="15px" style="vertical-align:middle"/>' + '&nbsp &nbsp';
    var errorImg = '<img src="/img/error.png" alt="正确"  width="15px" height="15px" style="vertical-align:middle"/>' + '&nbsp &nbsp';
    $("#username").blur(function () {
        if (/^[A-Za-z0-9]+$/.test($("#username").val())) {
            $.post("/signup/username", {
                username: $("#username").val()
            }, function (data, textStatus) {
                if (data == true) {
                    $("#username_ok").html(errorImg + '用户名已注册');
                    username_ok = 1;
                } else {
                    $("#username_ok").html(rightImg);
                    username_ok = 0;
                }
            })
        } else {
            $("#username_ok").html(errorImg + "用户名格式错误");
        }
    });


    $("#email").blur(function () {
        if (/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test($("#email").val())) {
            $.post("/signup/email", {
                email: $("#email").val()
            }, function (data, textStatus) {
                if (data == true) {
                    $("#email_ok").html(errorImg + "该邮箱已注册");
                    email_ok = 1;
                } else {
                    $("#email_ok").html(rightImg);
                    email_ok = 0;
                }
            })
        } else {
            $("#email_ok").html(errorImg + "邮箱格式错误");
        }
    });

    $("#passwd").blur(function () {
        $("#passwd").val() == '' ? $("#passwd_ok").html(errorImg + "请填写密码") : $("#passwd_ok").html(rightImg);
    });


    $("#form").submit(function () {
        $("#username").val() == '' ? $("#username_ok").html(errorImg + "请填写用户名") : "";
        $("#passwd").val() == '' ? $("#passwd_ok").html(errorImg + "请填写密码") : "";
        $("#email").val() == '' ? $("#email_ok").html(errorImg + "请填写邮箱") : "";
        if (username_ok == 0 & email_ok == 0
        ) {
            return true;
        } else {
            return false;
        }
    });
})

