package com.example.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Employee;

@Repository
@Service
@Transactional    // when  doing dmls maintaining the state of it
public interface EmployeeRepository  extends JpaRepository<Employee,Integer>{
	

@Modifying

@Query("update Employee set employeeName = 'nagendra' where id=:id")
public int updateinfo(@Param(value = "id") int id);


@Query("from Employee where employeeName=:employeeName ")
public List<Employee> getEmployeeDetail(@Param(value = "employeeName") String employeeName);

}
