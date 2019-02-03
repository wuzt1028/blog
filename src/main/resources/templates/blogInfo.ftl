<!DOCTYPE html>
<html>
<head>
    <title>wuztBlog</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Great Taste Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <style>
        #tagscloud{width:225px;height:250px;position:relative;font-size:12px;color:#333;margin:20px auto 0;text-align:center;}
        #tagscloud a{position:absolute;top:0px;left:0px;color:#333;font-family:Arial;text-decoration:none;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;}
        #tagscloud a:hover{color:#fff;padding:5px 5px;display:block;background:#D02F53;}
        #tagscloud a.tagc{margin:0 10px 15px 0;line-height:18px;width:70px;text-align:center;font-size:12px;padding:5px 5px;white-space:nowrap;display:inline-block;border-radius:3px;background:#337ab7;color:#fff;}

        ul,li{list-style:none;}
        a{text-decoration:none;color:#3381BF;}
        a:hover{text-decoration:underline;}
        #movie_rank{ margin-top: 1rem;}
        .box2{text-align:left;overflow:hidden;color:#9C9C9C;text-align:left;font-size:12px}
        .box2{margin-bottom:7px;}
        .box2 h2{background:#EEF7FE;height:21px;line-height:21px;overflow-y:hidden;border-bottom:1px solid #ADDFF2;color:#1974C8;font-size:12px;padding:0px 8px;}
        .box2 h2 span{margin-left:5px;font-weight:normal;color:#B9B7B8;}
        .box2 .inner{padding:8px;line-height:18px;overflow:hidden;color:#3083C7;}
        .box2 a{color:#3083C7;white-space:nowrap;width: 85%;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;display: inline-block;}
        .rank_list{line-height:14px;margin:auto;padding-top:5px;}
        .rank_list li{height:14px;margin-bottom:8px;width:100%;padding-left:20px;white-space:nowrap;overflow:hidden;position:relative;}
        .rank_list li.top3 em{background:#FFE4B7;border:1px solid #FFBB8B;color:#FF6800;}
        .rank_list em{position:absolute;left:0;top:0;width:12px;height:12px;border:1px solid #B1E0F4;color:#6298CC;font-style:normal;font-size:10px;font-family:Arial;background:#E6F0FD;text-align:center;line-height:12px;overflow:hidden;}
        .rank_list span{position:absolute;width:60px;color:#B7B7B7;text-align:right;height:14px;background:#fff;left:110px;display: contents;}
        #movie_rank .rank_list span{position:absolute;width:40px;color:#B7B7B7;text-align:right;height:14px;background:#fff;left:149px;}
    </style>
    <script type="application/x-javascript"></script>
    <!-- //for-mobile-apps -->
    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/static/css/style_blog.css" rel="stylesheet" type="text/css" media="all"/>
    <link rel="icon" type="image/x-icon" href="/static/images/favicon.ico"/>
    <!-- js -->
    <script src="/static/js/jquery-1.11.1.min.js"></script>
    <!-- //js -->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
          rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Comfortaa:400,300,700' rel='stylesheet' type='text/css'>
    <script src="/static/js/tagscloud.js"></script>
    <script type="text/javascript">
        //翻页跳转
        function goPage(page) {
            $("#currentPage").val(page);
            $("#subForm").submit();
        }
        function searchTitle() {
            if ($("#title").val().trim() != ''){
                $("#titleForm").submit();
            }
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
                    <img src="${user.avatar}"
                         style="z-index: 99999;width: 100%;height: 100%;display: inline-block;border: 3px solid;border-radius: 100px;">
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
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                    data-target="#bs-example-navbar-collapse-1">
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
                                    <li><a href="https://github.com/wuzt1028" target="_blank"
                                           class="hvr-underline-from-left"><i class="github1"></i>GitHub</a></li>
                                    <li><a href="https://weibo.com/u/3883394217" target="_blank"
                                           class="hvr-underline-from-left"><i class="weibo1"></i>微博</a></li>
                                    <li><a href="#"><i class="qq1"></i>${user.qq}</a></li>
                                    <li><a href="#" class="hvr-underline-from-left"><i class="email1"></i>${user.email}
                                    </a></li>
                                </ul>
                            </nav>
                        </div>
                        <!-- /.navbar-collapse -->
                    </nav>
                </div>
                <div class="col-lg-6" style="width:100%;">
                    <form action="/blog/list" method="post" id="titleForm">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search for..." id="title" name="title">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" onclick="searchTitle()"><i class="glyphicon glyphicon-search"></i></button>
                            </span>
                        </div><!-- /input-group -->
                    </form>
                </div><!-- /.col-lg-6 -->
                <div class="latest-news">
                    <h2>点击量排行榜</h2>
                    <div class="box2" id="movie_rank">
                        <div class="inner">
                            <ul class="rank_list">
                                <#list clickDESCList as clickDESC>
                                    <li class="top3">
                                        <em>${clickDESC_index+1}</em>
                                        <a title="${clickDESC.title}" target="_blank" href="/blog/${clickDESC.id}">
                                            ${clickDESC.title}</a>
                                        <span>${clickDESC.clickNum}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    <h3>标签云</h3>
                    <div id="tagscloud">
                        <#if tagList?size gte 16>
                            <#list tagList as tag1>
                                <a href="/blog/list?tagName=${tag1.tagName}" class="tagc" title="${tag1.tagName}">${tag1.tagName}</a>
                            </#list>
                        </#if>
                        <#if tagList?size lt 16>
                            <#list tagList as tag1>
                                <a href="/blog/list?tagName=${tag1.tagName}" class="tagc" title="${tag1.tagName}">${tag1.tagName}</a>
                            </#list>
                            <#list tagList as tag2>
                                <a href="/blog/list?tagName=${tag2.tagName}" class="tagc" title="${tag2.tagName}">${tag2.tagName}</a>
                            </#list>
                        </#if>

                    </div>
                </div>
            </div>
            <div class="col-xs-9 banner-body-right">
                <div class="blog" style="margin: 2rem 1rem">
                    <h1 class="blog_title">${blogEntity.title}</h1>
                    <div style="margin: 1rem 0rem;padding-bottom: 0.8rem;font-size: 1.1rem;color: #858585;border-bottom: 1px solid #e0e0e0;">
                        <span style="padding-right: 0.8rem;">${blogEntity.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                        <span style="padding-right: 0.8rem;">阅读数：${blogEntity.clickNum}</span>
                        <span style="padding-right: 0.8rem;">文章类型：<a href="/blog/list?type=${blogEntity.typeId}">${blogEntity.typeName}</a></span>
                    </div>
                <#-- 正文 -->
                    <div id="blog_content" style="word-wrap: break-word;">
                    ${blogEntity.content}
                    </div>
                <#-- 标签 -->
                    <div>
                        <#if blogEntity?? && blogEntity.tagList??>
                            <span style="font-size: 14px;color:#999;">标签：</span>
                            <#list blogEntity.tagList as tag>
                                <a href="/blog/list?tagName=${tag}" class="style-tag">${tag}</a>
                            </#list>
                        </#if>
                    </div>
                    <div class="bdsharebuttonbox" style="margin-top:1rem;"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a></div>
                    <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"http://140.143.233.156:90/images/20180720/ec75aa07-ad81-4758-8582-9106b0a81277.jpg","bdStyle":"1","bdSize":"24"},"share":{},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["weixin","qzone","tsina","tqq","sqq"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
                    <hr style="margin-top:3rem;">
                    <div style="margin-top:2rem;" id="vcomments"></div>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="footer">

            </div>
        </div>
    </div>
</div>
<!-- //banner-body -->
<!-- for bootstrap working -->
<script src="/static/js/bootstrap.js"></script>
<!-- //for bootstrap working -->
<script src="//cdn1.lncld.net/static/js/3.0.4/av-min.js"></script>
<script src='//unpkg.com/valine/dist/Valine.min.js'></script>
<script>
    new Valine({
        el: '#vcomments',
        appId:'${appId}',
        appKey:'${appKey}',
        lang:'zh-cn',
        avatar:'robohash'
    })
</script>
</body>
</html>