package com.hand.webapp.service;

import java.util.List;

import com.hand.webapp.pojo.Address;
import com.hand.webapp.pojo.Customer;
import com.hand.webapp.pojo.Page;

public interface CustomerService {

	public boolean loginCustomerCheck(Customer customer)throws Exception;
	
	public int getCustomerNumber()throws Exception;
	
	public List<Customer> getCustomerByPage(Page page)throws Exception;
	
	public boolean deleteCustomerById(int id)throws Exception;
	
	public List<Address> selectAddress()throws Exception;
	
	public Customer selectCustomerById(int id)throws Exception;
	
	public void updateCustomer(Customer customer)throws Exception;
	
	public void insertCustomer(Customer customer)throws Exception;
}
