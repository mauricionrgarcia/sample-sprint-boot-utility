package com.sampe.sprint.boot.utility.resource;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sampe.sprint.boot.utility.domain.Customer;
import com.sampe.sprint.boot.utility.service.CustomerService;
import com.sampe.sprint.boot.utility.web.method.support.FilterParam;

/**
 * REST controller - Customer
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 23/09/2018 21:41:09
 */
@RestController
@RequestMapping("/customer")
public class CustomerResource {

	private static final Logger log = LoggerFactory.getLogger(CustomerResource.class);

	@Autowired
	private CustomerService customerService;

	/**
	 * get a {@link Customer} from:
	 * 
	 * @param id identifier
	 * @return CustomerPadraop
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
		log.info("Request to find 'Customer' from identifier: {} ", id);
		Optional<Customer> customer = this.customerService.getCustomer(id);
		return ResponseEntity.ok(customer.get());
	}

	/**
	 * get all {@link Customer}
	 * 
	 * @return CustomerPadraop
	 */
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomer() {
		log.info("Request to find  all 'Customer'");
		List<Customer> customers = this.customerService.findCustomer();
		return ResponseEntity.ok(customers);
	}

	/**
	 * get page {@link Customer}
	 * 
	 * @return CustomerPadraop
	 */
	@GetMapping(params = { "page", "size" })
	public ResponseEntity<Page<Customer>> findCustomer(Pageable page, @RequestParam("name") String name2,
			@FilterParam("name") String name) {
		log.info("Request to find  all 'Customer'");
		Page<Customer> customers = this.customerService.findCustomer(page);
		return ResponseEntity.ok(customers);
	}

}