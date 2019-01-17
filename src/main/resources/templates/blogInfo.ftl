<!DOCTYPE html>
<html>
<head>
    <title>Blog</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Great Taste Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
    function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //for-mobile-apps -->
    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <link href="/static/css/style_blog.css" rel="stylesheet" type="text/css" media="all" />
    <!-- js -->
    <script src="/static/js/jquery-1.11.1.min.js"></script>
    <!-- //js -->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Comfortaa:400,300,700' rel='stylesheet' type='text/css'>
    <script type="text/javascript">
        //翻页跳转
        function goPage(page) {
            $("#currentPage").val(page);
            $("#subForm").submit();
        }
    </script>
</head>
<body>

<!-- banner-body -->
<div class="banner-body">
    <div class="container">
        <div class="banner-body-content">
            <div class="col-xs-3 banner-body-left">
                <div class="logo">
                    <img src="${user.avatar}" style="z-index: 99999;width: 100%;height: 100%;display: inline-block;border: 3px solid;border-radius: 100px;">
                </div>
                <div style="width: 100%;margin-top: 1rem;text-align: center">
                    <span style="font-size: 1.8rem;font-family: STKaiti"><strong>${user.nickname}</strong></span>
                    <br/>
                    <span style="font-size: 1.2rem;font-family: FangSong;color: #929292">${user.description}</span>
                </div>
                <div class="top-nav">
                    <nav class="navbar navbar-default">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
                            <nav class="stroke">
                                <ul class="nav navbar-nav">
                                    <li><a href="/" class="hvr-underline-from-left"><i class="home1"></i>个人主页</a></li>
                                    <li><a href="https://github.com/wuzt1028" target="_blank" class="hvr-underline-from-left"><i class="github1"></i>GitHub</a></li>
                                    <li><a href="https://weibo.com/u/3883394217" target="_blank" class="hvr-underline-from-left"><i class="weibo1"></i>微博</a></li>
                                    <li><a href="#"><i class="qq1"></i>${user.qq}</a></li>
                                    <li><a href="#" class="hvr-underline-from-left"><i class="email1"></i>${user.email}</a></li>
                                </ul>
                            </nav>
                        </div>
                        <!-- /.navbar-collapse -->
                    </nav>
                </div>
                <div class="latest-news">
                    <h2>Latest News</h2>
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingOne">
                                <h4 class="panel-title">
                                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                        Michael Vol
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                <div class="panel-body">
                                    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry.
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingTwo">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                        Andrew Rich
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                <div class="panel-body">
                                    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry.
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingThree">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                        Rita Rock
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                <div class="panel-body">
                                    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="join">
                        <a href="single.html">Learn More</a>
                    </div>
                    <h3>Benefits</h3>
                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.</p>
                </div>
            </div>
            <div class="col-xs-9 banner-body-right">
                <div class="blog" style="margin: 2rem 1rem">
                    <h1 class="blog_title">${blogEntity.title}</h1>
                    <div style="margin: 1rem 0rem;padding-bottom: 0.8rem;font-size: 1.1rem;color: #858585;border-bottom: 1px solid #e0e0e0;">
                        <span style="padding-right: 0.8rem;">${blogEntity.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                        <span style="padding-right: 0.8rem;">阅读数：${blogEntity.clickNum}</span>
                        <span style="padding-right: 0.8rem;"><a href="/blog/index?type=${blogEntity.typeId}" 文章类型：${blogEntity.typeName}</span>
                    </div>
                    <#-- 正文 -->
                    <div id="blog_content">
                        ${blogEntity.content}
                    </div>
                    <#-- 标签 -->
                    <div>
                        <span style="font-size: 14px;color:#999;">标签：</span>
                            <#list blogEntity.tagList as tag>
                                <a href="/blog/index?tagName=${tag}" class="style-tag">${tag}</a>
                            </#list>
                    </div>
                </div>
            </div>
            <div class="clearfix"> </div>
            <div class="footer">

            </div>
        </div>
    </div>
</div>
<!-- //banner-body -->
<!-- for bootstrap working -->
<script src="/static/js/bootstrap.js"></script>
<!-- //for bootstrap working -->
</body>
</html>