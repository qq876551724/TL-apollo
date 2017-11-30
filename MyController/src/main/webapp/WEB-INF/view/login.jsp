<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <script src="<%=request.getContextPath()%>/static/js/jquery-1.8.3.min.js"></script>
</head>
<body>
username: <input type="text" id="username"><br><br>
password: <input type="password" id="password"><br><br>
<div>
    <div>
        <input type="text" name="" id="auth" value="" />
    </div>
    <a href="javascript:;">
        <img id="codeImg" onclick="changeCode()" src=<%="getCaptcha"%> />
    </a>
</div>
<button id="loginbtn">登录</button>
</body>
<script type="text/javascript">
    function changeCode() {
        var imgNode = document.getElementById("codeImg");//重新加载验证码，达到刷新的目的
        imgNode.src = "getCaptcha?t=" + Math.random();  // 防止浏览器缓存的问题
    }
    $('#loginbtn').click(function() {
        var param = {
            username : $("#username").val(),
            password : $("#password").val(),
            auth : $("#auth").val()
        };
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>" + "/checkLogin.json",
            data: param,
            dataType: "json",
            success: function(data) {
                if(data.success == false){
                    alert(data.errorMsg);
                }else{
                    //登录成功
                    window.location.href = "<%=request.getContextPath()%>" +  "/success.jhtml";
                }
            },
            error: function(data) {
                alert("调用失败....");
            }
        });
    });
</script>
</html>