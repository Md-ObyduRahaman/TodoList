package com.todo.ToDo.service;

import com.todo.ToDo.controller.GetTodoListController;
import com.todo.ToDo.exceptionHandler.AppCommonException;
import com.todo.ToDo.model.response.EmployeeDetails;
import com.todo.ToDo.repository.EmployeeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;
    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public List<EmployeeDetails> getAllBooks()
    {
        List<EmployeeDetails> employeeDetails = new ArrayList<EmployeeDetails>();
        employeeRepo.findAll().forEach(employeeDetails1 -> employeeDetails.add(employeeDetails1));
        return employeeDetails;
    }


    public boolean saveOrUpdate(EmployeeDetails employeeDetails) {
        boolean flag = false;
        try {
            employeeRepo.save(employeeDetails);
            flag = true;
        } catch (BadSqlGrammarException e) {
            logger.trace("No Data found with profileId is {}  Sql Grammar Exception", employeeDetails.getId());
            throw new AppCommonException(4001 + "##Sql Grammar Exception##" + 1 + "##" + 1);
        } catch (TransientDataAccessException f) {
            logger.trace("No Data found with Employee id is {} network or driver issue or db is temporarily unavailable  ", employeeDetails.getId());
            throw new AppCommonException(4002 + "##Network or driver issue or db is temporarily unavailable##" + 1 + "##" + 1);
        } catch (CannotGetJdbcConnectionException g) {
            logger.trace("No Data found with employee Id is {} could not acquire a jdbc connection  ", employeeDetails.getId());
            throw new AppCommonException(4003 + "##A database connection could not be obtained##" + 1 + "##" + 1);
        }
        return flag;


    }
}
