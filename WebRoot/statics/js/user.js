$(document).ready(function() {
    $.ajax({
        url: '../getBlogInfo',
        type: 'post',
        dataType: 'json',
        success: function(data) {
            $("#articleCount").text(data.articleCount);
            $("#likeCount").text(data.likeCount);
            $("#viewCount").text(data.viewCount);
            $("#commentCount").text(data.commentCount);
            var p = 0;
            if (parseInt(data.articleCount) % 10 == 0) {
                p = parseInt(parseInt(data.articleCount) / 10);
            } else {
                p = parseInt(parseInt(data.articleCount) / 10) + 1;
            }
            $("#total1").text(p);
            $("#now1").text(1);
        },
        error: function() {
            alert("网络错误");
        }
    });
    hideAll = function() {
        $("#indexContent").hide();
        $("#articleCategory").hide();
        $("#allBlog").hide();
        $("#articleContent").hide();
    }
    hideAll();
    $("#indexContent").show();

    $("#hideRow").hide();

    var $hideRow = $("#hideRow").clone();
    $.ajax({
        url: '../getArticles',
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
                tds.eq(9).children().eq(0).bind('click', function(event) {
                    view($(this).parent().parent().children().eq(0).text());
                });
            }
        },
        error: function() {
            alert("网络错误");
        }
    });

    $(".allBlogMenu").click(function(event) {
        hideAll();
        $("#allBlog").show();
        if ($("#now1").text() <= 1) {
            $("#pPage1").hide();
        } else {
            $("#pPage1").show();
        }
        if ($("#now1").text() >= $("#total1").text()) {
            $("#nPage1").hide();
        } else {
            $("#nPage1").show();
        }
        getAllArticle(1, 10);
    });
    $("#pPage1").click(function(event) {
        $("#now1").text(parseInt($("#now1").text()) - 1);
        if ($("#now1").text() <= 1) {
            $("#pPage1").hide();
        } else {
            $("#pPage1").show();
        }
        if ($("#now1").text() >= $("#total1").text()) {
            $("#nPage1").hide();
        } else {
            $("#nPage1").show();
        }
        $("#tbody3").empty();
        getAllArticle($("#now1").text(), 10);
    });
    $("#nPage1").click(function(event) {
        $("#now1").text(parseInt($("#now1").text()) + 1);
        if ($("#now1").text() <= 1) {
            $("#pPage1").hide();
        } else {
            $("#pPage1").show();
        }
        if ($("#now1").text() >= $("#total1").text()) {
            $("#nPage1").hide();
        } else {
            $("#nPage1").show();
        }
        $("#tbody3").empty();
        getAllArticle($("#now1").text(), 10);
    });
    getAllArticle = function(pageNow, pageSize) {
        $.ajax({
            url: '../getArticles',
            type: 'post',
            dataType: 'json',
            data: {
                "pageNow": pageNow,
                "pageSize": pageSize
            },
            success: function(data) {
                $("#tbody3").empty();
                var articleList = data.articleList;
                for (var i = 0; i < articleList.length; i++) {
                    var tr = $hideRow.clone();
                    tr.appendTo("#tbody3").show();
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
                    tds.eq(9).children().eq(0).bind('click', function(event) {
                        view($(this).parent().parent().children().eq(0).text());
                    });
                }
            },
            error: function() {
                alert("网络错误");
            }
        });
    }

    var pickId;
    $(".blogManage").click(function(event) {
        //将其它模块隐藏
        hideAll();
        //将自己显示出来
        $("#articleCategory").show();
        $("#category").text('分类:' + $(this).text());
        pickId = $(this).attr("id");
        $.ajax({
            url: '../getPage',
            type: 'post',
            dataType: 'json',
            data: { "id": pickId },
            success: function(data) {
                $("#tbody3").empty();
                $("#now").text(1);
                $("#total").text(data.res);
                if ($("#now").text() <= 1) {
                    $("#pPage").hide();
                } else {
                    $("#pPage").show();
                }
                if ($("#now").text() >= $("#total").text()) {
                    $("#nPage").hide();
                } else {
                    $("#nPage").show();
                }
                getArticles(pickId, $("#now").text(), 10);
            },
            error: function() {
                alert("网络错误~！");
            }
        });
    });
    $("#pPage").click(function(event) {
        $("#now").text(parseInt($("#now").text()) - 1);
        if ($("#now").text() <= 1) {
            $("#pPage").hide();
        } else {
            $("#pPage").show();
        }
        if ($("#now").text() >= $("#total").text()) {
            $("#nPage").hide();
        } else {
            $("#nPage").show();
        }
        $("#tbody2").empty();
        getArticles(pickId, $("#now").text(), 10);
    });
    $("#nPage").click(function(event) {
        $("#now").text(parseInt($("#now").text()) + 1);
        if ($("#now").text() <= 1) {
            $("#pPage").hide();
        } else {
            $("#pPage").show();
        }
        if ($("#now").text() >= $("#total").text()) {
            $("#nPage").hide();
        } else {
            $("#nPage").show();
        }
        $("#tbody2").empty();
        getArticles(pickId, $("#now").text(), 10);
    });
    getArticles = function(id, pageNow, pageSize) {
        $.ajax({
            url: '../getArticlesByCategory',
            type: 'post',
            dataType: 'json',
            data: {
                "id": id,
                "pageNow": pageNow,
                "pageSize": pageSize
            },
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
                    tds.eq(9).children().eq(0).bind('click', function(event) {
                        view($(this).parent().parent().children().eq(0).text());
                    });

                }
            }
        });
    }
    view = function(id) {
        hideAll();
        $("#articleContent").show();
        $.ajax({
            url: '../getArticleById',
            type: 'post',
            dataType: 'json',
            data: { "id": id },
            success: function(data) {
                $("#articleId").val(data.article.articleId);
                $("#articleTitle").text(data.article.articleTitle);
                $("#articleText").empty().append(data.article.articleContent);
                getComment(data.article.articleId);
            },
            error: function() {
                alert("网络错误~!");
            }
        });

    }
    $commentHide = $("#comment").clone();
    var k = 0;
    getComment = function(articleId) {
        $("#comments").empty();
        $.ajax({
            url: '../getCommentByArticleId',
            type: 'post',
            dataType: 'json',
            data: { "articleId": articleId },
            success: function(data) {
                commentList = data.commentList;
                for (k = 0; k < commentList.length; k++) {
                    $comment = $commentHide.clone();
                    $comment.appendTo('#comments').show();
                    $comment.children().eq(0).children().eq(0).text(k + 1);
                    $comment.children().eq(0).children().eq(1).text(commentList[k].commentCreateTime);
                    $comment.children().eq(0).children().eq(2).text(commentList[k].commentAuthorName);
                    $comment.children().eq(1).text(commentList[k].commentContent);
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });

    }
    $("#userIndex").click(function(event) {
        window.location.reload(true);
        return false;
    });

    $("#submitComment").click(function(event) {
        if ($("#nickName").val() == "") {
            alert("昵称不能为空~！");
            return false;
        }
        if ($("#email").val() == "") {
            alert("邮箱不能为空~！");
            return false;
        }
        if ($("#commentContent").val() == "") {
            alert("评论不能为空~！");
            return false;
        }
        var re = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
        if (!re.test($("#email").val())) {
            alert("邮箱格式不正确~!");
            return false;
        }
        $.ajax({
            url: '../addComment',
            type: 'post',
            dataType: 'json',
            data: {
                "articleId": $("#articleId").val(),
                "nickName": $("#nickName").val(),
                "email": $("#email").val(),
                "commentContent": $("#commentContent").val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("评论成功~！");
                    $comment = $commentHide.clone();
                    $comment.children().eq(0).children().eq(0).text(k++ + 1);
                    $comment.children().eq(0).children().eq(1).text(data.date);
                    $comment.children().eq(0).children().eq(2).text($("#nickName").val());
                    $comment.children().eq(1).text($("#commentContent").val());
                    $comment.appendTo('#comments').show();
                } else {
                    alert("评论失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
    $("#likeBtn").click(function(event) {
        $.ajax({
            url: '../like',
            type: 'post',
            dataType: 'json',
            data: {
                "articleId": $("#articleId").val()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("点赞成功~!");
                } else {
                    alert("已经点赞了~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
});