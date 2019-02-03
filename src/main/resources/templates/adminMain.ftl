<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>博客管理</title>
    <link rel="stylesheet" type="text/css" href="/static/admin/layui_2.0/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="/static/admin/css/admin.css"/>
    <link rel="icon" type="image/x-icon" href="/static/images/favicon.ico"/>
</head>
<body>
<div class="main-layout" id='main-layout'>
    <!--侧边栏-->
    <div class="main-layout-side">
        <div class="m-logo">
        </div>
        <ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;"><i class="iconfont">&#xe608;</i>博客管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" target="iframe" data-url="/admin/blogList" data-id='3' data-text="博客列表"><span class="l-line"></span>博客列表</a></dd>
                    <dd><a href="javascript:;" target="iframe" data-url="/admin/toAddBlog" data-id='9' data-text="写博客"><span class="l-line"></span>写博客</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;" data-url="system.html" data-id='6' data-text="评论管理"><i class="iconfont">&#xe604;</i>评论管理</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;" data-url="admin-info.html" data-id='5' data-text="个人信息"><i class="iconfont">&#xe606;</i>个人信息</a>
            </li>
            <#--<li class="layui-nav-item">
                <a href="javascript:;" data-url="system.html" data-id='6' data-text="系统设置"><i class="iconfont">&#xe60b;</i>系统设置</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><i class="iconfont">&#xe607;</i>菜单管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" data-url="menu1.html" data-id='1' data-text="后台菜单"><span class="l-line"></span>后台菜单</a></dd>
                    <dd><a href="javascript:;" data-url="menu2.html" data-id='2' data-text="前台菜单"><span class="l-line"></span>前台菜单</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><i class="iconfont">&#xe604;</i>推荐位管理</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><i class="iconfont">&#xe60c;</i>友情链接</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><i class="iconfont">&#xe60a;</i>RBAC</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;" data-url="email.html" data-id='4' data-text="邮件系统"><i class="iconfont">&#xe603;</i>邮件系统</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><i class="iconfont">&#xe60d;</i>生成静态</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><i class="iconfont">&#xe600;</i>备份管理</a>
            </li>-->
        </ul>
    </div>
    <!--右侧内容-->
    <div class="main-layout-container">
        <!--头部-->
        <div class="main-layout-header">
            <div class="menu-btn" id="hideBtn">
                <a href="javascript:;">
                    <span class="iconfont">&#xe60e;</span>
                </a>
            </div>
            <ul class="layui-nav" lay-filter="rightNav">
                <li class="layui-nav-item"><a href="javascript:;" data-url="email.html" data-id='4' data-text="邮件系统"><i class="iconfont">&#xe603;</i></a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;" data-url="admin-info.html" data-id='5' data-text="个人信息">${adminUserName}</a>
                </li>
                <li class="layui-nav-item"><a href="javascript:exitUser()">退出</a></li>
            </ul>
        </div>
        <!--主体内容-->
        <div class="main-layout-body">
            <!--tab 切换-->
            <div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
                <ul class="layui-tab-title">
                    <li class="layui-this welcome">后台主页</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
                        <!--1-->
                        <iframe src="/admin/index" width="100%" height="100%" name="iframe" scrolling="auto" class="iframe" framborder="0"></iframe>
                        <!--1end-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--遮罩-->
    <div class="main-mask">

    </div>
</div>
<script src="/static/js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var scope={
        link:'./welcome.html'
    }
    function exitUser(){
        $.ajax({
            url:"/admin/exit",
            dataType:'json',
            success:function (result) {
                layer.confirm(result.message, {
                    btn: ['确定'] //按钮
                }, function(index){
                    layer.close();
                    layer.close(index);
                    if(result.success){
                        window.location.href = "/admin/login";
                    }
                }, function(){
                })
            }
        });
    }
</script>
<script src="../../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../../static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="../../static/admin/js/main.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>
