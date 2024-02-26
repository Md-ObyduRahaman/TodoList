package com.todo.ToDo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.ToDo.model.response.BaseResponse;
import com.todo.ToDo.model.response.EmployeeDetails;
import com.todo.ToDo.repository.EmployeeRepo;
import com.todo.ToDo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/private")
public class GetTodoListController {
    private final Logger logger = LoggerFactory.getLogger(GetTodoListController.class);
    private final short API_VERSION = 1;

    @Autowired
    Environment environment;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EmployeeService employeeService;


    @GetMapping(value = "/v1/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTodoListController() throws JsonProcessingException {

        BaseResponse baseResponse = new BaseResponse();
        List<EmployeeDetails> employeeDetails= employeeService.getAllBooks();

        if (!employeeDetails.isEmpty()) {
            baseResponse.setApiName("Employee Insert");
            baseResponse.setStatus(true);
            baseResponse.setData(employeeDetails);
        } else {
            baseResponse.setApiName("Employee Insert");
            baseResponse.setStatus(false);
            baseResponse.setData("Data not added");
        }



        return ResponseEntity.ok().body(objectMapper.writeValueAsString(baseResponse));
    }
    @PostMapping(value = "/v1/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertTodoList(@RequestBody EmployeeDetails employeeDetails) throws JsonProcessingException {

        BaseResponse baseResponse = new BaseResponse();
        if (employeeService.saveOrUpdate(employeeDetails)) {
            baseResponse.setApiName("Employee Insert");
            baseResponse.setStatus(true);
            baseResponse.setData("Data added successfully");
        } else {
            baseResponse.setApiName("Employee Insert");
            baseResponse.setStatus(false);
            baseResponse.setData("Data not added");
        }


        return ResponseEntity.ok().body(objectMapper.writeValueAsString(baseResponse));
    }
}