<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gentelella Alela! | </title>
    <!-- Bootstrap -->
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet" />
    <!-- bootstrap-daterangepicker -->
    <link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
    <div class="container body">
        <div class="main_container">
            <div class="col-md-3 left_col menu_fixed">
                <div class="left_col scroll-view">
                    <div class="navbar nav_title" style="border: 0;">
                        <a href="index.html" class="site_title"><i class="fa fa-h-square"></i> <span>H<span style="font-size: 10px">2</span> Blog~!</span></a>
                    </div>
                    <div class="clearfix"></div>
                    <!-- menu profile quick info -->
                    <div class="profile clearfix">
                        <div class="profile_pic">
                            <img src="${user.userAvatar }" alt="..." class="img-circle profile_img">
                        </div>
                        <div class="profile_info">
                            <span>欢迎登录氢博客后台,</span>
                            <h2>${user.userNickName }</h2>
                        </div>
                    </div>
                    <!-- /menu profile quick info -->
                    <br />
                    <!-- sidebar menu -->
                    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                        <div class="menu_section">
                            <h3>我的博客</h3>
                            
                            <ul class="nav side-menu">
                                <li>
                                	<a href="#"><i class="fa fa-edit"></i> 写博客 </a>
                                </li>
                                <li><a><i class="fa fa-book"></i> 博文管理 <span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu" id="articleManagement" >
                                    	<c:forEach items="${categoryList}" var="pCategory">
	                                        <li>
	                                        	<a class="blogManage" id="${pCategory.categoryId}">${ pCategory.categoryName}
	                                        		<c:if test="${fn:length(pCategory.childCategoryList)>0}">
	                                        			<span class="fa fa-chevron-down"></span>
	                                        		</c:if>
	                                        	</a>
	                                        	<c:if test="${fn:length(pCategory.childCategoryList)>0}">
							                        <ul class="nav child_menu" >
							                        	<c:forEach items="${pCategory.childCategoryList}" var="cCategory" >
									                        <li >
									                        	<a class="blogManage" id="${cCategory.categoryId}">${cCategory.categoryName }</a>
									                        </li>
								                        </c:forEach>
							                        </ul>
						                        </c:if>
	                                        </li>
	                                     </c:forEach>
                                    </ul>
                                </li>
                                <li><a><i class="fa fa-align-left"></i> 分类管理 <span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <li><a href="#">新增分类</a></li>
                                        <li><a href="#">删除/修改分类</a></li>
                                    </ul>
                                </li>
                                <li><a><i class="fa fa-tag"></i>标签管理 <span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <li><a href="#">新增标签</a></li>
                                        <li><a href="#">删除/修改标签</a></li>
                                    </ul>
                                </li>
                                <li><a><i class="fa  fa-comment"></i> 评论管理<span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <li><a href="#">所有评论</a></li>
                                        <li><a href="#">文章评论</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- /sidebar menu -->
                </div>
            </div>
            <!-- top navigation -->
            <div class="top_nav">
                <div class="nav_menu">
                    <nav>
                        <div class="nav toggle">
                            <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                        </div>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="">
                                <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
	                    <img src="${user.userAvatar }" alt="">${user.userNickName }
	                    <span class=" fa fa-angle-down"></span>
	                  </a>
                                <ul class="dropdown-menu dropdown-usermenu pull-right">
                                    <li><a href="logout"><i class="fa fa-sign-out pull-right"></i> 退出登录</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <!-- /top navigation -->
            <!-- page content -->
            <div class="right_col" role="main" id="indexContent">
                <!-- top tiles -->
                <div class="row tile_count">
                    <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-user"></i> 总共的博文</span>
                        <div class="count" id="articleCount"></div>
                        <!-- <span class="count_bottom"> 超越<i class="green">100% </i>的用户</span> -->
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-clock-o"></i> 总访问量</span>
                        <div class="count" id="viewCount"></div>
                        <!-- <span class="count_bottom"> 超越<i class="green">100% </i>的用户</span> -->
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-thumbs-o-up"></i> 点赞</span>
                        <div class="count" id="likeCount"></div>
                        <!-- <span class="count_bottom"> 超越<i class="green">100% </i>的用户</span> -->
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-comment"></i> 总留言数</span>
                        <div class="count" id="commentCount"></div>
                        <!-- <span class="count_bottom"> 超越<i class="green">100% </i>的用户</span> -->
                    </div>
                </div>
                <!-- /top tiles -->
                <div>
                	<p class="h1">近期发表的博文：<p>
                </div>
                <!-- 最近文章 -->
                <div class="table-responsive">
                    <table class="table table-striped jambo_table bulk_action" id="tb">
                        <thead>
                            <tr class="headings">
                                <th class="column-title">文章Id </th>
                                <th class="column-title">文章名 </th>
                                <th class="column-title">作者</th>
                                <th class="column-title">内容摘要</th>
                                <th class="column-title">一级分类</th>
                                <th class="column-title">二级分类</th>
                                <th class="column-title">标签</th>
                                <th class="column-title">发布时间</th>
                                <th class="column-title">最后一次修改时间</th>
                                <th class="column-title no-link last"><span class="nobr">操作</span>
                                </th>
                                <th class="bulk-actions" colspan="7">
                                    <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                </th>
                            </tr>
                        </thead>
                        <tbody id="tbody">
                            <tr class="even pointer" id="hideRow">
                                <td class=" "></td>
                                <td class=" "></td>
                                <td class=" "></td>
                                <td class=" "></td>
                                <td class=" "></td>
                                <td class=" "></td>
                                <td class=" "></td>
                                <td class=" "></td>
                                <td class=" "</td>
                                <td class=" last"><a href="#">查看</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /page content -->
            <!-- footer content -->
            <footer>
                <div class="pull-right">
                    Copyright © 2018 氢博客 All rights reserved. <a href="#">站点地图</a>
                </div>
                <div class="clearfix"></div>
            </footer>
            <!-- /footer content -->
        </div>
    </div>
    <!-- jQuery -->
    <script src="vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="vendors/nprogress/nprogress.js"></script>
    <!-- Chart.js -->
    <script src="vendors/Chart.js/dist/Chart.min.js"></script>
    <!-- gauge.js -->
    <script src="vendors/gauge.js/dist/gauge.min.js"></script>
    <!-- bootstrap-progressbar -->
    <script src="vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- iCheck -->
    <script src="vendors/iCheck/icheck.min.js"></script>
    <!-- Skycons -->
    <script src="vendors/skycons/skycons.js"></script>
    <!-- Flot -->
    <script src="vendors/Flot/jquery.flot.js"></script>
    <script src="vendors/Flot/jquery.flot.pie.js"></script>
    <script src="vendors/Flot/jquery.flot.time.js"></script>
    <script src="vendors/Flot/jquery.flot.stack.js"></script>
    <script src="vendors/Flot/jquery.flot.resize.js"></script>
    <!-- Flot plugins -->
    <script src="vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
    <script src="vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
    <script src="vendors/flot.curvedlines/curvedLines.js"></script>
    <!-- DateJS -->
    <script src="vendors/DateJS/build/date.js"></script>
    <!-- JQVMap -->
    <script src="vendors/jqvmap/dist/jquery.vmap.js"></script>
    <script src="vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
    <script src="vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="vendors/moment/min/moment.min.js"></script>
    <script src="vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="build/js/custom.min.js"></script>
    <script src="statics/js/index.js"></script>
</body>

</html>