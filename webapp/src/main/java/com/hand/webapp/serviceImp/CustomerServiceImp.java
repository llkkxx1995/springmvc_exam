package com.hand.webapp.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hand.webapp.mapper.CustomerMapper;
import com.hand.webapp.pojo.Address;
import com.hand.webapp.pojo.Customer;
import com.hand.webapp.pojo.Page;
import com.hand.webapp.service.CustomerService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Service
public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;
	private static final int size = 10;
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public boolean loginCustomerCheck(Customer customer) throws Exception {
		boolean result = false;
		Customer customerIn = customerMapper.selectCustomerByName(customer);
		if (customerIn!=null&&customerIn.getCustomer_id()>=0) {
			result = true;
		}
		return result;
	}

	@Transactional
	public int getCustomerNumber() throws Exception {
		int total =  customerMapper.countCustomerNum();
		int remain = total%size;
		int pages = total/size;
		
		if (pages==0) {
			pages++;
		}else if (remain>0) {
			pages++;
		}
		
		return pages;
	}
	

	@Transactional
	public List<Customer> getCustomerByPage(Page page) throws Exception {
		// TODO Auto-generated method stub
		return customerMapper.selectCustomerByPage(page);
	}

	public boolean deleteCustomerById(int id) {
		try {
			customerMapper.deleteCustomerById(id);
			return true;
		}catch(MySQLIntegrityConstraintViolationException e){
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	public List<Address> selectAddress() throws Exception {
		// TODO Auto-generated method stub
		return customerMapper.selectAddress();
	}

	public Customer selectCustomerById(int id) throws Exception {
		// TODO Auto-generated method stub
		return customerMapper.selectCustomerById(id);
	}

	@Transactional
	public void updateCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		customerMapper.updateCustomer(customer);
	}

	@Transactional
	public void insertCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		customerMapper.insertCustomer(customer);
	}

}
