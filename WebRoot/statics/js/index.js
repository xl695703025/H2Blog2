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
    hideAll = function() {
        $("#indexContent").hide();
        $("#articleManageContent").hide();
        $("#categoryManageContent").hide();
        $("#tagManageContent").hide();
        $("#commentManageContent").hide();
        $("#addTag").hide();
        $("#updateTag").hide();
        $("#addCategory").hide();
        $("#updateCategory").hide();
    }
    hideAll();
    $("#indexContent").show();

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
        hideAll();
        //将自己显示出来
        $("#articleManageContent").show();
        $("#category").text('分类:' + $(this).text());
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
                    tds.eq(9).text('').append($("<a href='javascript:;'>删除</a>|<a href='javascript:;'>修改</a>"));
                    var delBtn = tds.eq(9).children().eq(0);
                    delBtn.bind('click', delArticle);
                    var updateBtn = tds.eq(9).children().eq(1);
                    updateBtn.bind('click', updateArticle);
                }
            }
        });
    });
    delArticle = function() {
        $this = $(this);
        $.ajax({
            url: 'delArticle',
            type: 'post',
            dataType: 'json',
            data: {
                'articleId': $(this).parent().parent().children().eq(0).text()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("删除成功~!");
                    $this.parent().parent().remove();
                } else {
                    alert("删除失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    }
    updateArticle = function() {

    }
    $(".categoryManage").click(function(event) {
        //将其它模块隐藏
        hideAll();
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
                    tds.eq(4).text('').append($("<a href='javascript:;'>删除</a>|<a href='javascript:;'>修改</a>"));
                    var delBtn = tds.eq(4).children().eq(0);
                    delBtn.bind('click', delCategory);
                    var updateBtn = tds.eq(4).children().eq(1);
                    updateBtn.bind('click', updateCategory);
                    childCategoryList = categoryList[i].childCategoryList;
                    for (var j = 0; j < childCategoryList.length; j++) {
                        var tr = $hideRow2.clone();
                        tr.appendTo("#tbody3").show();
                        tds = tr.children();
                        tds.eq(0).text(childCategoryList[j].categoryId);
                        tds.eq(2).text(childCategoryList[j].categoryName);
                        tds.eq(3).text(childCategoryList[j].categoryDescription);
                        tds.eq(4).text('').append($("<a href='javascript:;'>删除</a>|<a href='javascript:;'>修改</a>"));
                        var delBtn = tds.eq(4).children().eq(0);
                        delBtn.bind('click', delCategory);
                        var updateBtn = tds.eq(4).children().eq(1);
                        updateBtn.bind('click', updateCategory);
                    }
                }
            }
        });

    });
    $(".categoryAddMenu").click(function(event) {
        hideAll();
        $("#addCategory").show();
    });
    $("#addCategoryBtn").click(function(event) {
        if ($("#pCategoryName").val() == "") {
            alert('一级分类不能为空~！');
            return false;
        }
        $.ajax({
            url: 'addCategory',
            type: 'post',
            dataType: 'json',
            data: {
                "pCategoryName": $("#pCategoryName").val(),
                "cCategoryName": $("#cCategoryName").val(),
                "categoryDescription": $("#categoryDescription").val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("添加成功~!");
                    $(".categoryManage").click();
                } else {
                    alert("添加失败，一级分类或二级分类已经存在~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
    /*delCategory*/
    delCategory = function(event) {
        var $this = $(this);
        $.ajax({
            url: 'delCategory',
            type: 'post',
            dataType: 'json',
            data: {
                'categoryId': $(this).parent().parent().children().eq(0).text()
            },
            success: function(data) {
                if (data.res > 0) {

                    alert("删除成功~!");
                    for (var k = 0; k < data.res - 1; k++) {
                        $this.parent().parent().next().remove();
                    }
                    $this.parent().parent().remove();
                } else {
                    alert("删除失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });

    }

    updateCategory = function(event) {
        hideAll();
        $("#updateCategory").show();
        $tr = $(this).parent().parent();
        $('#categoryId').val($tr.children().eq(0).text());
        if ($tr.children().eq(1).text() != '') {
            $('#newCategoryName').val($tr.children().eq(1).text())
        } else {
            $('#newCategoryName').val($tr.children().eq(2).text())
        }
        $('#newCategoryDescription').val($tr.children().eq(3).text())
    }
    $("#updateCategoryBtn").click(function(event) {
    	if($("#newCategoryName").val()==""){
    		alert("分类名不能为空~!");
    		return ;
    	}
        $.ajax({
            url: 'updateCategory',
            type: 'post',
            dataType: 'json',
            data: {
                "categoryId": $("#categoryId").val(),
                "categoryName": $("#newCategoryName").val(),
                "categoryDescription": $("#newCategoryDescription").val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("修改成功~!");
                    $(".categoryManage").click();
                } else {
                    alert("分类名已经存在，修改失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });


    $(".tagManage").click(function(event) {
        //将其它模块隐藏
        hideAll();
        //将自己显示出来
        $("#tagManageContent").show();
        $.ajax({
            url: 'getTagByUserName',
            type: 'post',
            dataType: 'json',
            success: function(data) {
                var tagList = data.tagList;
                $("#tbody4").empty();
                for (var i = 0; i < tagList.length; i++) {
                    var tr = $hideRow3.clone();
                    tr.appendTo("#tbody4").show();
                    tds = tr.children();
                    tds.eq(0).text(tagList[i].tagId);
                    tds.eq(1).text(tagList[i].tagName);
                    tds.eq(2).text(tagList[i].tagDescription);
                    var delBtn = tds.eq(3).children().eq(0);
                    delBtn.bind('click', delTag);
                    var updateBtn = tds.eq(3).children().eq(1);
                    updateBtn.bind('click', updateTag);
                }
            }
        });

    });
    delTag = function(event) {
        var $this = $(this);
        $.ajax({
            url: 'delTag',
            type: 'post',
            dataType: 'json',
            data: {
                'tagId': $(this).parent().parent().children().eq(0).text()
            },
            success: function(data) {
                if (data.res) {
                    alert("删除成功~!");
                    $this.parent().parent().hide();
                } else {
                    alert("删除失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });

    }
    updateTag = function(event) {
        hideAll();
        $("#updateTag").show();
        $tr = $(this).parent().parent();
        $('#tagId').val($tr.children().eq(0).text());
        $('#newtagName').val($tr.children().eq(1).text())
        $('#newtagDescription').val($tr.children().eq(2).text())
    }
    $('#updateTagBtn').click(function(event) {
        $.ajax({
            url: 'updateTag',
            type: 'post',
            dataType: 'json',
            data: {
                "tagId": $('#tagId').val(),
                "tagName": $('#newtagName').val(),
                "tagDescription": $('#newtagDescription').val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("修改成功~!");
                    $(".tagManage").click();
                } else {
                    alert("修改失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });

    });
    $(".tagAddMenu").click(function(event) {
        hideAll();
        $("#addTag").show();
    });
    $("#addTagBtn").bind('click', function(event) {
        $.ajax({
            url: 'addTag',
            type: 'post',
            dataType: 'json',
            data: {
                "tagName": $("#tagName").val(),
                "tagDescription": $("#tagDescription").val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("添加成功~!");
                    $("#tagName").val("");
                    $("#tagDescription").val("");
                    $(".tagManage").click();
                } else {
                    alert("标签已经存在，添加失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
    $(".commentManage").click(function(event) {
        //将其它模块隐藏
        hideAll();
        //将自己显示出来
        $("#commentManageContent").show();
        $.ajax({
            url: 'getCommentList',
            type: 'post',
            dataType: 'json',
            success: function(data) {
                var commentList = data.commentList;
                $("#tbody5").empty();
                for (var i = 0; i < commentList.length; i++) {
                    var tr = $hideRow4.clone();
                    tr.appendTo('#tbody5').show();
                    tds = tr.children();
                    tds.eq(0).text(commentList[i].commentId);
                    tds.eq(1).text(commentList[i].commentAuthorName);
                    tds.eq(2).text(commentList[i].commentArticleTitle);
                    tds.eq(3).text(commentList[i].commentAuthorEmail);
                    tds.eq(4).text(commentList[i].commentContent);
                    tds.eq(5).text(commentList[i].commentIp);
                    tds.eq(6).text(commentList[i].commentCreateTime);
                    var $delBtn = $("<a href='javascript:;'>删除</a>");
                    $delBtn.bind('click', function(event) {
                        var $this = $(this);
                        $.ajax({
                            url: 'delComment',
                            type: 'post',
                            dataType: 'json',
                            data: {
                                'commentId': $(this).parent().parent().children().eq(0).text()
                            },
                            success: function(data) {
                                if (data.res) {
                                    alert('删除成功~!');
                                    $this.parent().parent().hide();
                                } else {
                                    alert('删除失败~!');
                                }
                            },
                            error: function(data) {
                                alert('网络错误~!');
                            }
                        });

                    });
                    tds.eq(7).append($delBtn);
                }
            }
        });

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