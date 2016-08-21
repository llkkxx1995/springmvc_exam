package com.hand.webapp.mapper;

import java.util.List;

import com.hand.webapp.pojo.Address;
import com.hand.webapp.pojo.Customer;
import com.hand.webapp.pojo.Page;

public interface CustomerMapper {

	public Customer selectCustomerByName(Customer customer)throws Exception;
	
	public int countCustomerNum() throws Exception;
	
	public List<Customer> selectCustomerByPage(Page page)throws Exception;
	
	public void deleteCustomerById(int id)throws Exception;
	
	public List<Address> selectAddress()throws Exception;
	
	public Customer selectCustomerById(int id)throws Exception;
	
	public void updateCustomer(Customer customer)throws Exception;
	
	public void insertCustomer(Customer customer)throws Exception;
}
