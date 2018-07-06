$(document).ready(function() {
    $.ajax({
        url: 'getBlogInfo',
        type: 'post',
        dataType: 'json',
        success: function(data) {
            $("#articleCount").text(data.articleCount);
            $("#likeCount").text(data.likeCount);
            $("#viewCount").text(data.viewCount);
            $("#commentCount").text(data.commentCount);
        },
        error: function() {
            alert("网络错误");
        }
    });
    $("#hideRow").hide();
    $("#hideRow2").hide();
    $("#hideRow3").hide();
    $("#hideRow4").hide();
    var $hideRow = $("#hideRow").clone();
    var $hideRow2 = $("#hideRow2").clone();
    var $hideRow3 = $("#hideRow3").clone();
    var $hideRow4 = $("#hideRow4").clone();
    $.ajax({
        url: 'getArticles',
        type: 'post',
        dataType: 'json',
        data: {
            "pageNow": 1,
            "pageSize": 10
        },
        success: function(data) {
            var articleList = data.articleList;
            for (var i = 0; i < articleList.length; i++) {
                var tr = $hideRow.clone();
                tr.appendTo("#tbody1").show();
                tds = tr.children();
                tds.eq(0).text(articleList[i].articleId);
                tds.eq(1).text(articleList[i].articleTitle);
                tds.eq(2).text(articleList[i].userNickName);
                tds.eq(3).text(articleList[i].articleContent.substring(0, 10));
                tds.eq(4).text(articleList[i].parentCategory);
                tds.eq(5).text(articleList[i].childCategory);
                tds.eq(6).text(articleList[i].articleTagIds);
                tds.eq(7).text(articleList[i].articlePostTime);
                tds.eq(8).text(articleList[i].articleUpdateTime);
            }
        },
        error: function() {
            alert("网络错误");
        }
    });
    $(".blogManage").click(function(event) {
        //将其它模块隐藏
        $("#indexContent").hide();
        $("#categoryManageContent").hide();
        $("#tagManageContent").hide();
        //将自己显示出来
        $("#articleManageContent").show();
        var id = $(this).attr("id");
        $.ajax({
            url: 'getArticlesByCategory',
            type: 'post',
            dataType: 'json',
            data: { "id": id },
            success: function(data) {
                $("#tbody2").empty();
                var articleList = data.articleList;
                for (var i = 0; i < articleList.length; i++) {
                    var tr = $hideRow.clone();
                    tr.appendTo("#tbody2").show();
                    tds = tr.children();
                    tds.eq(0).text(articleList[i].articleId);
                    tds.eq(1).text(articleList[i].articleTitle);
                    tds.eq(2).text(articleList[i].userNickName);
                    tds.eq(3).text(articleList[i].articleContent.substring(0, 10));
                    tds.eq(4).text(articleList[i].parentCategory);
                    tds.eq(5).text(articleList[i].childCategory);
                    tds.eq(6).text(articleList[i].articleTagIds);
                    tds.eq(7).text(articleList[i].articlePostTime);
                    tds.eq(8).text(articleList[i].articleUpdateTime);
                    tds.eq(9).text('').append($("<a href>删除</a>|<a href>修改</a>"));
                }
            }
        });

    });
    $(".categoryManage").click(function(event) {
        //将其它模块隐藏
        $("#indexContent").hide();
        $("#articleManageContent").hide();
        $("#tagManageContent").hide();
        //将自己显示出来
        $("#categoryManageContent").show();
        $.ajax({
            url: 'getCategory',
            type: 'post',
            dataType: 'json',
            success: function(data) {
                $("#tbody3").empty();
                var categoryList = data.categoryList;
                for (var i = 0; i < categoryList.length; i++) {
                    var tr = $hideRow2.clone();
                    tr.appendTo("#tbody3").show();
                    tds = tr.children();
                    tds.eq(0).text(categoryList[i].categoryId);
                    tds.eq(1).text(categoryList[i].categoryName);
                    if (categoryList[i].categoryPid == '0') {
                        tds.eq(2).text("");
                    } else {
                        tds.eq(2).text("");
                    }
                    tds.eq(3).text(categoryList[i].categoryDescription);
                    tds.eq(4).text('').append($("<a href>删除</a>|<a href>修改</a>"));
                    childCategoryList = categoryList[i].childCategoryList;
                    for (var j = 0; j < childCategoryList.length; j++) {
                        var tr = $hideRow2.clone();
                        tr.appendTo("#tbody3").show();
                        tds = tr.children();
                        tds.eq(0).text(childCategoryList[j].categoryId);
                        tds.eq(2).text(childCategoryList[j].categoryName);
                        tds.eq(3).text(childCategoryList[j].categoryDescription);
                        tds.eq(4).text('').append($("<a href>删除</a>|<a href>修改</a>"));
                    }
                }
            }
        });

    });
    $(".tagManage").click(function(event) {
        //将其它模块隐藏
        $("#indexContent").hide();
        $("#articleManageContent").hide();
        $("#categoryManageContent").hide();
        //将自己显示出来
        $("#tagManageContent").show();
        $.ajax({
            url: 'getTagByUserName',
            type: 'post',
            dataType: 'json',
            success: function(data) {
                var tagList = data.tagList;
                $("#tbody4").empty();
                for (var i = 0;i < tagList.length;i++) {
                    var tr = $hideRow3.clone();
                    tr.appendTo("#tbody4").show();
                    tds = tr.children();
                    tds.eq(0).text(tagList[i].tagId);
                    tds.eq(1).text(tagList[i].tagName);
                    tds.eq(2).text(tagList[i].tagDescription);
                }
            }
        });

    });
    $(".commentManage").click(function(event) {
        //将其它模块隐藏
        $("#indexContent").hide();
        $("#articleManageContent").hide();
        $("#categoryManageContent").hide();
        $("#tagManageContent").hide();
        //将自己显示出来
        $("#commentManageContent").show();
    });
    /*$.ajax({
    	url: 'getCategory',
    	type: 'post',
    	dataType: 'json',
    	success:function(data){
    		var categoryList=data.categoryList;
    		for(var i=0;i<categoryList.length;i++){
    			var $li=$('<li><a href="#">'+categoryList[i].categoryName+'</a></li>');
    			childList=categoryList[i].childCategoryList;
    			var $ul=$('<ul class="nav child_menu" style="display: block"></ul>');
    			for(var j=0;j<childList.length;j++){
    				$ul.append('<li><a>'+childList[j].categoryName+'</a></li>');
    			}
    			if(childList.length>=1){
    				$li.children('a').append('<span class="fa fa-chevron-down"></span>');
    				alert($ul.html());
    				$li.bind('click', function(event) {
    					alert(1);
    					$ul.css("display","block");
    				});
    				$li.append($ul);
    			}
    			$("#articleManagement").append($li);
    		}
    	},
        error: function() {
            alert("网络错误");
        }
    });*/

});