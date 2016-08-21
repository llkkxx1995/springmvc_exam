package com.hand.webapp.controller;




import java.util.Date;
import java.util.List;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hand.webapp.pojo.Address;
import com.hand.webapp.pojo.Customer;
import com.hand.webapp.pojo.Page;
import com.hand.webapp.pojo.Result;
import com.hand.webapp.service.CustomerService;

@Controller
@SessionAttributes("inUser")
public class CustomerContoller {

	@Autowired
	private CustomerService customerService;
	private static final int size = 10;
	
	@RequestMapping("/loginCheck")
	public String loginCheck(Customer customer,Model model)throws Exception{
		
		if (customerService.loginCustomerCheck(customer)) {
		   model.addAttribute("inUser", customer);
		    
			return "index";   
		}else{
			model.addAttribute("loginError", "登陆失败，密码或用户名错误");
			return "login";
		}
		
	}
	
	
	@RequestMapping("/getCustomers")
	@ResponseBody
	public List<Customer> getCustomers(Page page)throws Exception{
		System.out.println(page.getPage());
		System.out.println("进入customers");
		if (page.getPage()==0) {
			page.setPage(1);
		}
		if (page.getSize()==0) {
			page.setSize(size);
		}
		System.out.println(page.getPage());
		return customerService.getCustomerByPage(page);
	}
	
	@RequestMapping("/getPages")
	@ResponseBody
	public int getPages()throws Exception{
		System.out.println("进入pages");
		System.out.println( customerService.getCustomerNumber());
		return customerService.getCustomerNumber();
	}
	
	
	@RequestMapping("/deleteCustomer")
	@ResponseBody
	public Result deleteCustomer(Customer customer) throws Exception{
		Result result = new Result();
		int page = (customer.getCustomer_id())/size;
		int remain = (customer.getCustomer_id())%size;
		
		if (remain>0) {
			page++;
		}
		if (page>customerService.getCustomerNumber()) {
			page = customerService.getCustomerNumber();
		}
		System.out.println(page);
		result.setPage(page);
		if (customerService.deleteCustomerById(customer.getCustomer_id())) {
			result.setResult(true);
		}else{
			result.setResult(false);
		}
		return result;
	}
	
	@RequestMapping("/getAddresses")
	@ResponseBody
	public List<Address> getAddresses()throws Exception{
		return customerService.selectAddress();
	}
	
	
	@RequestMapping("/editItem")
	public String editItem(int customer_id,Model model)throws Exception{
		System.out.println("进入edit");
		Customer customer = customerService.selectCustomerById(customer_id);
		model.addAttribute("customer", customer);
		return "edit";
	}
	
	
	@RequestMapping("/saveUpdate")
	public String saveUpdate(Customer customer,Model model)throws Exception{
		int page = (customer.getCustomer_id())/size;
		int remain = (customer.getCustomer_id())%size;
		
		if (remain>0) {
			page++;
		}
		if (page>customerService.getCustomerNumber()) {
			page = customerService.getCustomerNumber();
		}
		System.out.println(page);
		model.addAttribute("pageIndex", page);
		customerService.updateCustomer(customer);
		return "index";
	}
	
	
	@RequestMapping("/saveInsert")
	public String saveInsert(Customer customer,Model model)throws Exception{
		customer.setLast_update(new Date());
		customer.setStore_id(1);
		customer.setCreate_date(new Date());
		customerService.insertCustomer(customer);
		
		int page = (customer.getCustomer_id())/size;
		int remain = (customer.getCustomer_id())%size;
		
		if (remain>0) {
			page++;
		}
		if (page>customerService.getCustomerNumber()) {
			page = customerService.getCustomerNumber();
		}
		
		System.out.println(page);
		model.addAttribute("pageIndex", page);
		customerService.updateCustomer(customer);
		return "index";
	}
	
	
	@RequestMapping("/logOut")
	public String logOut(HttpSession session)throws Exception{
		session.invalidate();
		return "login";
	}
}
