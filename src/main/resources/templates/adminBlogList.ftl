<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>博客管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/admin/layui_2.0/css/layui.css"  media="all">
    <link rel="stylesheet" href="/static/css/admin_customize.css"  media="all">
    <link rel="icon" type="image/x-icon" href="/static/images/favicon.ico"/>
</head>
<body>
    <div class="mar-lr5-td10">
        <div class="capHead">
            <form class="layui-form" action="/admin/blogList" method="post" id="submitForm">
                <input type="hidden" id="currentPage" name="currentPage">
                <input type="hidden" id="pageSize" name="pageSize">
                <div class="w50pre fl">
                    <ul class="ddBar">
                        <li>
                            <label class="layui-form-label">文章标题：</label>
                            <div class="layui-input-inline" style="width:275px">
                                <input type="tel" id="title" name="title" lay-verify="required|phone" autocomplete="off" class="layui-input">
                            </div>
                        <li>
                            <label class="layui-form-label">文章类型：</label>
                            <div class="layui-input-inline" style="width:275px">
                                <select name="typeId" lay-verify="required" lay-search="">
                                    <option value="0">直接选择或搜索选择</option>
                                    <#list blogTypeList as blogType>
                                        <option value="${blogType.id}">${blogType.typeName}</option>
                                    </#list>
                                </select>
                            </div>
                        </li>
                        <li>
                            <label class="layui-form-label">创建时间：</label>
                            <div class="layui-input-inline" style="width:275px">
                                <input type="text" class="layui-input" readonly id="startTime" name="startTime">
                            </div>
                            ~
                            <div class="layui-input-inline" style="width:275px">
                                <input type="text" class="layui-input" readonly id="endTime" name="endTime">
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="w50pre fl">
                    <ul class="ddBar">
                        <li>
                            <label class="layui-form-label">文章状态：</label>
                            <div class="layui-input-block" style="width:275px">
                                <select name="status">
                                    <option value="-1"></option>
                                    <option value="0">草稿</option>
                                    <option value="1">发表</option>
                                    <option value="2">删除</option>
                                </select>
                        </li>
                        <li>
                            <label class="layui-form-label">文章标签：</label>
                            <div class="layui-input-inline" style="width:275px">
                                <select name="tagId" lay-verify="required" lay-search="">
                                    <option value="0">直接选择或搜索选择</option>
                                    <#list tagsList as tag>
                                        <option value="${tag.id}">${tag.tagName}</option>
                                    </#list>
                                </select>
                            </div>
                        </li>
                    </ul>
                </div>
            </form>
            <div class="fl" style="text-align: center;width: 100%;">
                <ul class="ddBar">
                    <li>
                        <span>
                            <button class="layui-btn" type="button" onclick="submitForm()">查询</button>
                            <button class="layui-btn layui-btn-primary" type="button">清空</button>
                        </span>
                    </li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>博文列表</legend>
        </fieldset>

        <div class="layui-form" >
            <table class="layui-table" style="text-align: center;">
                <colgroup>
                    <col width="150">
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th style="text-align: center;">博文标题</th>
                    <th style="text-align: center;">博文类型</th>
                    <th style="text-align: center;">博文标签</th>
                    <th style="text-align: center;">点击量</th>
                    <th style="text-align: center;">博文状态</th>
                    <th style="text-align: center;">创建时间</th>
                    <th style="text-align: center;">操作</th>
                </tr>
                </thead>
                <tbody>
                    <#list blogEntities as blog>
                        <tr>
                            <td>${blog.title}</td>
                            <td>${blog.typeName}</td>
                            <td>
                                <#list blog.tagList as tag>
                                    <#if tag_index == 0>
                                        ${tag}
                                    </#if>
                                    <#if tag_index gt 0>
                                        ,${tag}
                                    </#if>
                                </#list>
                            </td>
                            <td>${blog.clickNum}</td>
                            <td>
                                <#if blog.status == 0>
                                    草稿
                                </#if>
                                <#if blog.status == 1>
                                    发表
                                </#if>
                                <#if blog.status == 2>
                                    删除
                                </#if>
                            </td>
                            <td>${blog.createTime?string('yyyy-MM-dd hh:mm:ss')}</td>
                            <td>
                                <button class="layui-btn" title="编辑" onclick="updateBlog(${blog.id})"><i class="layui-icon">&#xe642;</i></button>
                                <#if blog.status != 1>
                                    <button class="layui-btn layui-btn-normal" title="发表" onclick="updateStatus(${blog.id}, 1)"><i class="layui-icon">&#xe609;</i></button>
                                </#if>
                                <#if blog.status != 2>
                                    <button class="layui-btn layui-btn-danger" title="删除" onclick="updateStatus(${blog.id}, 2)"><i class="layui-icon">&#xe640;</i></button>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <div id="page" style="float: right;margin-right: 30px;"></div>
    </div>
<script src="/static/js/jquery-1.11.1.min.js" charset="utf-8"></script>
<script src="/static/admin/layui_2.0/layui.js" charset="utf-8"></script>
<script>
    var initNum = 1;
    layui.use(['laydate','form','laypage'], function(){
        var laydate = layui.laydate;
        var form =layui.form;
        var laypage = layui.laypage;
        var layer = layui.layer;
        form.render();
        laydate.render({
            elem: '#startTime',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm:ss'
        });
        laydate.render({
            elem: '#endTime',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm:ss'
        });

        //分页完整功能
        laypage.render({
            elem: 'page',
            count: ${blogCount},
            curr: ${currentPage},
            limit: ${pageSize}
            ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            ,jump: function(obj){
                $("#currentPage").val(obj.curr);
                $("#pageSize").val(obj.limit);
                if(initNum == 0){
                    $("#submitForm").submit();
                }
                initNum = 0;
            }
        });
    });
    function submitForm(){
        $("#currentPage").val(1);
        $("#pageSize").val(10);
        $("#submitForm").submit();
    }
    function updateBlog(id){
        window.location.href="/admin/updateBlog";
    }
    function updateStatus(id, status) {
        $.ajax({
            url:"/admin/updateStatus",
            data:{id:id, status:status},
            dataType:'json',
            success:function (result) {
                layer.confirm(result.message, {
                    btn: ['确定'] //按钮
                }, function(index){
                    layer.close();
                    layer.close(index);
                }, function(){
                })
                if(result.success){
                    window.location.reload();
                }
            }
        })
    }
</script>
</body>
</html>