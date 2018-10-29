package com.sampe.sprint.boot.utility.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sampe.sprint.boot.utility.domain.Customer;
import com.sampe.sprint.boot.utility.repository.CustomerRepository;

/**
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 23/09/2018 21:41:36
 */
@Service
public class CustomerService {

	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * get a {@link Customer} from:
	 * 
	 * @param id Customer
	 * @return Customer
	 */
	public Optional<Customer> getCustomer(Long id) {
		log.info("Service to find 'Customer' from code: {} ", id);
		return this.customerRepository.findById(id);
	}

	/**
	 * get all customer
	 * 
	 * @return all customer
	 */
	public List<Customer> findCustomer() {
		log.info("Service to find  all 'Customer' from filter: {} ", "");
		return this.customerRepository.findAll();
	}

	/**
	 * find customer with
	 * 
	 * @param pageable Pageable
	 * @return Page of customer
	 */
	public Page<Customer> findCustomer(Pageable pageable) {
		log.info("Service to find  pageable 'Customer' from filter: {} ", "");
		return this.customerRepository.findAll(pageable);
	}

}