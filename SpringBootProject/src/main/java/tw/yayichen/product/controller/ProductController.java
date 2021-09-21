package tw.yayichen.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.yayichen.product.model.Product;
import tw.yayichen.product.model.ProductService;

@Controller
@RequestMapping(path = "/product")
@SessionAttributes(names = {"totalPages", "totalElements"})
public class ProductController {
	
	@Autowired
	private ProductService pService;

	@GetMapping(path = "/productmainpage.controller")
	public String processQueryMainPage() {
		return "product/productQueryAll";
	}
	
	@PostMapping(path = "/query/{pid}")
	@ResponseBody
	public Product processQueryById(@PathVariable("pid") int pid) {
		return pService.findById(pid);
	}
	
	@PostMapping(path = "/add")
	@ResponseBody
	public Product processAddAction(@RequestBody Product p) {
		return pService.insertProduct(p);
	}
	
	@PostMapping(path = "/queryallbypage/{pageNo}")
	@ResponseBody
	public List<Product> processQueryByPageAction(@PathVariable("pageNo") int pageNo, Model m){
		
		int pageSize = 3; //每頁顯示的筆數
		Pageable pageable = PageRequest.of(pageNo-1, pageSize); //PageRequest.of(顯示頁碼, 每頁筆數);
		Page<Product> page = pService.findAllByPage(pageable);
		
		int totalPages = page.getTotalPages();        //取得資料總頁數
		long totalElements = page.getTotalElements(); //取得全部資料筆數
		m.addAttribute("totalPages", totalPages);
		m.addAttribute("totalElements", totalElements);
		
		return page.getContent(); //getContent()回傳該頁{pageNo}資料內容
	}
}
