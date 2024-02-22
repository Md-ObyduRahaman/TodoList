package com.todo.ToDo.repository;

import com.todo.ToDo.model.response.EmployeeDetails;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<EmployeeDetails, Integer> {
}
