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
        $("#writeBlog").hide();
        $("#updateBlog").hide();
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
                tds.eq(9).append($("<a href='javascript:;'>删除</a>|<a href='javascript:;'>修改</a>"));
                var delBtn = tds.eq(9).children().eq(0);
                delBtn.bind('click', delArticle);
                var updateBtn = tds.eq(9).children().eq(1);
                updateBtn.bind('click', updateArticle);
            }
        },
        error: function() {
            alert("网络错误");
        }
    });


    var E = window.wangEditor;
    var editor1 = new E('#div1', '#div2'); // 两个参数也可以传入 elem 对象，class 选择器
    editor1.customConfig.menus = [
        'head', // 标题
        'bold', // 粗体
        'fontSize', // 字号
        'fontName', // 字体
        'italic', // 斜体
        'underline', // 下划线
        'strikeThrough', // 删除线
        'foreColor', // 文字颜色
        'backColor', // 背景颜色
        'link', // 插入链接
        'list', // 列表
        'justify', // 对齐方式
        'quote', // 引用
        'emoticon', // 表情
        'image', // 插入图片
        'table', // 表格
        'code', // 插入代码
        'undo', // 撤销
        'redo' // 重复
    ];
    editor1.customConfig.uploadFileName = 'img';
    editor1.customConfig.uploadImgServer = 'uploadImg';
    editor1.customConfig.uploadImgTimeout = 600000;
    editor1.create();
    var E1 = window.wangEditor;
    var editor2 = new E1('#div11', '#div22'); // 两个参数也可以传入 elem 对象，class 选择器
    editor1.customConfig.menus = [
        'head', // 标题
        'bold', // 粗体
        'fontSize', // 字号
        'fontName', // 字体
        'italic', // 斜体
        'underline', // 下划线
        'strikeThrough', // 删除线
        'foreColor', // 文字颜色
        'backColor', // 背景颜色
        'link', // 插入链接
        'list', // 列表
        'justify', // 对齐方式
        'quote', // 引用
        'emoticon', // 表情
        'image', // 插入图片
        'table', // 表格
        'code', // 插入代码
        'undo', // 撤销
        'redo' // 重复
    ];
    editor2.customConfig.uploadFileName = 'img';
    editor2.customConfig.uploadImgServer = 'uploadImg';
    editor2.customConfig.uploadImgTimeout = 600000;
    editor2.create();


    $(".writeBlogMenu").click(function(event) {
        hideAll();
        $("#writeBlog").show();
    });
    $("#submitArticleBtn").click(function(event) {
        if ($("#newArticleTitle").val() == '') {
            alert("标题不能为空~！");
            return false;
        }
        if ($("#newPCategory").val() == '') {
            alert("一级分类不能为空");
            return false;
        }
        if ($("#newCCategory").val() == '') {
            alert("二级分类不能为空");
            return false;
        }
        $.ajax({
            url: 'addArticle',
            type: 'post',
            dataType: 'json',
            data: {
                "articleTitle": $("#newArticleTitle").val(),
                "pCategory": $("#newPCategory").val(),
                "articleTagIds": $("#newTagName").val(),
                "cCategory": $("#newCCategory").val(),
                "articleContent": editor1.txt.html()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("提交成功~!");
                    location.reload();
                } else {
                    alert("提交失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });
    $("#updateArticleBtn").click(function(event) {
        if ($("#updateArticleTitle").val() == '') {
            alert("标题不能为空~！");
            return false;
        }
        if ($("#updatePCategory").val() == '') {
            alert("一级分类不能为空");
            return false;
        }
        if ($("#updateCCategory").val() == '') {
            alert("二级分类不能为空");
            return false;
        }
        $.ajax({
            url: 'updateArticle',
            type: 'post',
            dataType: 'json',
            data: {
                "articleId": $("#articleId").val(),
                "articleTitle": $("#updateArticleTitle").val(),
                "pCategory": $("#updatePCategory").val(),
                "articleTagIds": $("#updateTagName").val(),
                "cCategory": $("#updateCCategory").val(),
                "articleContent": editor2.txt.html()
            },
            success: function(data) {
                if (data.res > 0) {
                    alert("修改成功~!");
                    location.reload();
                } else {
                    alert("修改失败~!");
                }
            },
            error: function() {
                alert("网络错误~!");
            }
        });
    });


    var pickId;
    $(".blogManage").click(function(event) {
        //将其它模块隐藏
        hideAll();
        //将自己显示出来
        $("#articleManageContent").show();
        $("#category").text('分类:' + $(this).text());
        pickId = $(this).attr("id");
        $.ajax({
            url: 'getPage',
            type: 'post',
            dataType: 'json',
            data: { "id": pickId },
            success: function(data) {
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
            url: 'getArticlesByCategory',
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
                    tds.eq(9).append($("<a href='javascript:;'>删除</a>|<a href='javascript:;'>修改</a>"));
                    var delBtn = tds.eq(9).children().eq(0);
                    delBtn.bind('click', delArticle);
                    var updateBtn = tds.eq(9).children().eq(1);
                    updateBtn.bind('click', updateArticle);
                }
            }
        });
    }
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
        hideAll();
        $("#updateBlog").show();

        var $tds = $(this).parent().parent().children();
        $("#articleId").val($tds.eq(0).text());
        $("#updateArticleTitle").val($tds.eq(1).text());
        $("#updatePCategory").val($tds.eq(4).text());
        $("#updateTagName").val($tds.eq(6).text());
        $("#updateCCategory").val($tds.eq(5).text());
        $.ajax({
            url: 'getArticleById',
            type: 'post',
            dataType: 'json',
            data: { "id": $tds.eq(0).text() },
            success: function(data) {
                editor2.txt.html(data.article.articleContent);
            },
            error: function() {

            }
        });

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
        if ($("#newCategoryName").val() == "") {
            alert("分类名不能为空~!");
            return;
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
});