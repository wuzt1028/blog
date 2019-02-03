<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>后台登录</title>
    <link rel="stylesheet" type="text/css" href="/static/admin/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="/static/admin/css/login.css" />
    <link rel="icon" type="image/x-icon" href="/static/images/favicon.ico"/>
</head>

<body>
<div class="m-login-bg">
    <div class="m-login">
        <h3>后台系统登录</h3>
        <div class="m-login-warp">
            <form class="layui-form" id="submitForm">
                <div class="layui-form-item">
                    <input type="text" name="username" required lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="password" name="password" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <input type="text" name="verity" required lay-verify="required" placeholder="验证码" autocomplete="off" class="layui-input">
                        <input type="hidden" id="codeName" name="codeName">
                    </div>
                    <div class="layui-inline">
                        <img class="verifyImg" onclick="changeCode()" id="ranCode" src="" />
                    </div>
                </div>
                <div class="layui-form-item m-login-btn">
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-normal" type="button" onclick="submitForm()">登录</button>
                    </div>
                    <div class="layui-inline">
                        <button type="reset" class="layui-btn layui-btn-primary">取消</button>
                    </div>
                </div>
            </form>
        </div>
        <p class="copyright">Copyright 2019-至今 by wuzt</p>
    </div>
</div>
<script src="/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<!-- js -->
<script src="/static/js/jquery-1.11.1.min.js"></script>
<script>
    $(function(){
        changeCode();
        layui.use('form', function() {
            var form = layui.form(),
                    layer = layui.layer;
        })
    })
    function changeCode(){
        var codeName = "codeName_"+new Date().getTime();
        $("#codeName").val(codeName);
        $("#ranCode").attr("src","/admin/randomCode?codeName="+codeName);
    }
    function submitForm(){
        $.ajax({
            url:'/admin/userLogin',
            type:'post',
            data:$("#submitForm").serialize(),
            dataType:'json',
            cache:false,
            success:function(result) {
                if (result.success){
                    window.location.href = "/admin/main";
                } else {
                    layer.confirm(result.message, {
                        btn: ['确定'] //按钮
                    }, function(index){
                        layer.close();
                        layer.close(index);
                    }, function(){
                    })
                }
            }
        });
        return false;
    }
</script>
</body>

</html>