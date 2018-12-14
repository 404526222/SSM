package controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
import pojo.Category;
import service.CategoryService;
import util.Page;
 
// 告诉spring mvc这是一个控制器类
@Controller
@RequestMapping("")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@RequestMapping("listCategory")
	public ModelAndView listCategory(Page page) {
		ModelAndView mav= new ModelAndView();
		List<Category> cs=categoryService.list(page);
		int total=categoryService.total();
		page.caculateLast(total);
		mav.addObject("cs",cs);
		mav.setViewName("listCategory");
		return mav;
	}
	
	@RequestMapping("addCategory")
	public ModelAndView addCategory(Category category) {
		categoryService.add(category);
		ModelAndView mav=new ModelAndView("redirect:/listCategory");
		return mav;
	}
	
	@RequestMapping("updateCategory")
	public ModelAndView updateCategory(Category category) {
		categoryService.update(category);
		ModelAndView mav=new ModelAndView("redirect:/listCategory");
		return mav;
	}
	
	@RequestMapping("deleteCategory")
	public ModelAndView deleteCategory(Category category) {
		categoryService.delete(category);
		ModelAndView mav=new ModelAndView("redirect:/listCategory");
		return mav;
	}
	
	@RequestMapping("editCategory")
	public ModelAndView editCategory(Category category) {
		categoryService.get(category.getId());
		ModelAndView mav=new ModelAndView("editCategory");
		return mav;
	}
}