package test;




import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.Category;
import com.yuxia.blog.entity.Comment;
import com.yuxia.blog.entity.Tag;
import com.yuxia.blog.other.Page;
import com.yuxia.blog.service.ArticleService;
import com.yuxia.blog.service.CategoryService;
import com.yuxia.blog.service.CommentService;
import com.yuxia.blog.service.TagService;
import com.yuxia.blog.service.UserService;



public class Test1 {

	ApplicationContext applicationContext;
	@Before
	public void init(){
		String xml="applicationContext-mybatis.xml";
		applicationContext = new ClassPathXmlApplicationContext(xml);
	}
	@Test
	public void test1(){
		CategoryService cs=(CategoryService) applicationContext.getBean("categoryService");
		ArticleService as=(ArticleService) applicationContext.getBean("articleService");
		TagService ts=(TagService) applicationContext.getBean("tagService");
		CommentService cos=(CommentService) applicationContext.getBean("commentService");
		String userName="yuxia";
		Page page =new Page(1, 5, 10);
		//as.getArticle(userName, page);
//		for(Category c:cs.getCategoryList(userName)){
//			System.out.println(c);
//		}
//		for(Tag t:ts.getTagList(userName)){
//			System.out.println(t);
//		}
//		for(Comment c:cos.getComment(userName)){
//			System.out.println(c);
//		}
		for(Category c:cs.getCategoryList("yuxia")){
			System.out.println(c);
		}
	}

}
