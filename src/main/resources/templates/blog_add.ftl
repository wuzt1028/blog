<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0042)flipin.html -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>写博客</title>
    <link rel="stylesheet" type="text/css" href="/static/css/blog.css">
    <link href="/static/css/demo.css" rel="stylesheet" type="text/css">
    <!--Framework-->
    <script src="/static/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="/static/js/jquery-ui.js" type="text/javascript"></script>
    <!--End Framework-->
    <!--ckeditor-->
    <script type="text/javascript" src="/static/ckeditor/ckeditor.js"></script>
    <!--End ckeditor-->
    <!--Bootstrap-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/htmleaf-demo.css">
    <!--End Bootstrap-->
    <script src="/static/js/jquery.ffform.js" type="text/javascript"></script>
    <script type="text/javascript">

        function addTag(obj){
            var tagVal = $(obj).attr("data-tagName");
            var tagId = $(obj).attr("data-tagId");
            if ($(obj).is(':checked')){
                var tagNum = $(".tagnum-mark").length;
                if(parseInt(tagNum) >= 3){//添加时最多三个标签
                    $(obj).removeAttr("checked");
                    alert("最多三个标签");
                    return ;
                }
                $("#addTag-a").before("<span class='tag-span "+tagId+"'>"+tagVal+"<input type='hidden' class='tagnum-mark' name='tags' readonly='readonly' value='"+tagVal+"'></span>");
            }else {
                $("."+tagId).remove();
            }
        }

        function addTagInput() {
            var tagNum = $(".tagnum-mark").length;
            if(parseInt(tagNum) >= 3){//最多三个标签
                alert("最多三个标签");
                return ;
            }
            $("#addTag-a").before("<input type='text' name='tags' class='addinput-mark tagnum-mark' onblur='addTagChange(this)' style='background: #8e8e8e;\n" +
                    "    border: 2px;\n" +
                    "    border-radius: 2px;\n" +
                    "    padding: 1px 5px;\n" +
                    "    -moz-border-radius: 25px;\n" +
                    "    margin: 0px 5px;width:80px;color: #000000;font-size: 17px;'>");
            $(".addinput-mark").focus();
            $(".addinput-mark").removeClass("addinput-mark");
        }

        function addTagChange(obj){
            var value = $(obj).val();
            if($.trim(value)==""){
                $(obj).remove();
            }else if(value.length > 16){
                alert("添加的标签过长");
                //$(obj).remove();
            }
            //验证标签名称是否重复
        }

        function switchType(val){
            if(val==0){//添加新分类
                $("#blogType_existed").hide();
                $("#blogType").show();
                $("#newType").hide();
                $("#oldType").show();
                $("#selectType").val(2);
            }else {//以前的分类
                $("#blogType_existed").show();
                $("#blogType").hide();
                $("#newType").show();
                $("#oldType").hide();
                $("#selectType").val(1);
            }
        }

        $(document).ready(function () {
            $('#blogForm').ffform({ animation: 'flip', submitButton: '#submit', validationIndicator: '#validation', errorIndicator: '#error', successIndicator: '#success', 'fields': [{ 'id': 'name', required: true,requiredMsg:'Name is required', type: 'alpha', validate: true, msg: 'Invalid Name' }, { 'id': 'email', required: true,requiredMsg:'E-Mail is required', type: 'email', validate: true, msg: 'Invalid E-Mail Address' }, { 'id': 'phone', required: false, type: 'custom', validate: false, msg: 'Invalid Phone #' }, { 'id': 'message', required: false, type: 'text', validate: false, msg: ''}] });
        });

        //替换指定name的textarea为富文本编辑器
        CKEDITOR.replace('blogEditor',{ height: '600px',width: '100%'})

        function sendBlog(status){
            var title = $("#title").val();
            if($.trim(title) == "" || title.length == 0){
                alert("博客标题不能为空");
                return ;
            }else{
                if(title.length > 50){
                    alert("博客标题过长");
                    return ;
                }
            }
            var editor_data = CKEDITOR.instances.blogEditor.getData();//富文本编辑器内容
            if($.trim(editor_data) == ""){
                alert("博客内容不能为空");
                return ;
            }else {
                $("#content").val(editor_data);
                var CText = CKEDITOR.instances.blogEditor.document.getBody().getText(); //取得纯文本
                $("#subContent").val(CText);
            }
            var tagList = $(".tagnum-mark");
            for (var i=0;i<tagList.length;i++){
                if(tagList[0].length>6){
                    alert("新增标签名称过长");
                    return ;
                }
            }
            var selectType = $("#selectType").val();
            var blogType_existed = $("#blogType_existed").val();
            var blogType = $("#blogType").val();
            if(selectType==2){
                if ($.trim(blogType) == "" || blogType.length == 0){
                    alert("请输入博客所属类别");
                    return ;
                }
                if(blogType.length>10){
                    alert("输入的博客所属类别过长");
                    return ;
                }
            }else {
                if (blogType_existed == 0){
                    alert("请选择博客所属类别");
                    return ;
                }
            }
            $("#status").val(status);
            $("#blogForm").submit();
        }

    </script>
</head>
<body>
<section id="getintouch" >
    <div class="container" style="border-bottom: 0;padding: 10px 0px;">
        <div class="editBlog_header">
                <#if userEntity??>
                    <img src="${(userEntity.avatar)!"/static/images/demoAvatar.png"}" class="round_icon"  alt="">
                </#if>
                <span class="editBlog_header_span">
                    <#if userEntity??>
                        ${userEntity.nickname}
                    </#if>
                </span>
        </div>
    </div>
    <div class="container">
        <form class="contact" action="/blog/addBlog" method="post" id="blogForm">
            <input value="${userEntity.id}" name="userId" type="hidden"/>
            <input value="" type="hidden" name="content" id="content"/>
            <input value="" type="hidden" name="subContent" id="subContent"/>
            <input value="" type="hidden" name="status" id="status"/>
            <div class="row clearfix">
                <div class="lbl fcb">
                    <label for="title">文章标题</label>
                </div>
                <div class="ctrl">
                    <input type="text" id="title" name="title" data-required="true" data-validation="text"
                           data-msg="Invalid Name" placeholder="输入文章标题">
                </div>
            </div>
            <div class="row clearfix fcb">
                <div class="lbl">
                    <label for="content">内容</label>
                </div>
                <div class="ctrl">
                    <!--加入ckdeitor类使其为富文本编辑器-->
                    <textarea class="ckeditor" name="blogEditor" style="height:450px"></textarea>
                </div>
            </div>
            <div class="row clearfix fcb">
                <div class="lbl">
                    <label for="tags">文章标签</label>
                </div>
                <div class="ctrl">
                    <div id="tags-div"><a class="btn btn-success" onclick="addTagInput()" id="addTag-a">＋</a></div>
                </div>
                <div class="tag-div">
                    <#list tagsList as tag>
                        <span style="width:108px;display: inline-block;"><input type="checkbox" onclick="addTag(this)" data-tagId="tagId_${tag.id}" data-tagName="${tag.tagName}" class="checkbox-style">&nbsp;&nbsp;${tag.tagName}</span>
                    </#list>
                </div>
            </div>
            <div class="row clearfix fcb">
                <div class="lbl">
                    <label for="blogType">文章分类</label>
                </div>
                <div class="ctrl">
                    <input type="text" name="blogType" id="blogType" style="display:none;width: 120px">
                    <input type="hidden" name="selectType" id="selectType" value="1">
                    <select id="blogType_existed" name="typeId" class="blogType-select">
                        <option value="0">请选择分类</option>
                        <#list blogTypeList as blogType>
                            <option value="${blogType.id}">${blogType.typeName}</option>
                        </#list>
                    </select>
                    <a href="javascript:void(0)"><span id="newType" onclick="switchType(0)" style="color:#0000FF;">添加新分类</span></a>
                    <a href="javascript:void(0)"><span style="display:none;color:#ff0000;" id="oldType" onclick="switchType(1)">使用以前的分类</span></a>
                </div>
            </div>
            <div class="row  clearfix">
                <div class="span10 offset2">
                    <a class="btn btn-success btn-next" href="javascript:sendBlog(1)">发布博客</a>
                    <a class="btn btn-primary btn-next" role="button" href="javascript:sendBlog(0)">存为草稿</a>
                </div>
            </div>
        </form>
    </div>
</section>
</body>
</html>
