package com.backend.customerDetailMysql.repository;

import com.backend.customerDetailMysql.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c.id, c.name, r.roleName FROM Customer c JOIN c.role r")
    List<Object[]> findCustomerWithRoleName();
}
