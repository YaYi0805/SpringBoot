package tw.yayichen.order.controller;

import java.util.Date;
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

import tw.yayichen.order.model.PurchaseOrder;
import tw.yayichen.order.model.PurchaseOrderService;

@Controller
@RequestMapping("/order")
@SessionAttributes(names = { "totalPages", "totalElements" })
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@GetMapping(path = "/purchaseOrderProductList.controller")
	public String processPurchaseOrderMainAction() {
		return "order/purchaseOrderProductList";
	}

	@GetMapping(path = "/purchaseOrderQuery.controller")
	public String processPurchaseOrderQueryMainAction() {
		return "order/purchaseOrderQuery";
	}
	
	@GetMapping(path = "/purchaseOrderProduct.controller")
	public String processPurchaseOrderProductAction(@RequestParam(name = "pid") int pid, Model m) {
		m.addAttribute("pid", pid);
		return "order/purchaseOrderProduct";
	}

	@PostMapping(path = "/purchaseOrderInsert.controller")
	@ResponseBody
	public PurchaseOrder processPurchaseOrderInsert(@RequestBody PurchaseOrder po) {
		po.setOdate(new Date());
		return purchaseOrderService.insert(po);
	}

	@PostMapping(path = "/purchaseOrderQuery.controller/{oid}")
	@ResponseBody
	public PurchaseOrder processPurchaseOrderfindById(@PathVariable(name = "oid") int oid) {
		return purchaseOrderService.findById(oid);
	}

	@PostMapping(path = "/purchaseOrderQueryAll.controller")
	@ResponseBody
	public List<PurchaseOrder> processPurchaseOrderfindAll() {
		return purchaseOrderService.findAll();
	}

	@PostMapping(path = "/purchaseOrderQueryByPage/{pageNo}")
	@ResponseBody
	public List<PurchaseOrder> processActionQueryByPage(@PathVariable(name = "pageNo") int pageNo, Model m) {
		System.out.println("pageNo:" + pageNo);
		int pageSize = 2;
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<PurchaseOrder> page = purchaseOrderService.findAllByPage(pageable);
		m.addAttribute("totalPages", page.getTotalPages());
		m.addAttribute("totalElements", page.getTotalElements());
		return page.getContent();
	}

}
