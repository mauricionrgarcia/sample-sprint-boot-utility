package com.sampe.sprint.boot.utility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sampe.sprint.boot.utility.domain.Customer;

/**
 * Repository Customer
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 28/10/2018 17:41:50
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CrudRepository<Customer, Long> {

}
