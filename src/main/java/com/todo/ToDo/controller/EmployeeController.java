package com.todo.ToDo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.ToDo.model.response.BaseResponse;
import com.todo.ToDo.model.response.EmployeeDetails;
import com.todo.ToDo.repository.EmployeeRepo;
import com.todo.ToDo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Tag(name = "EmployeeController", description = "Task management APIs")
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/private")
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final short API_VERSION = 1;

    @Autowired
    Environment environment;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EmployeeService employeeService;


    @GetMapping(value = "/v1/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllEmployeeDetails() throws JsonProcessingException {

        BaseResponse baseResponse = new BaseResponse();
        List<EmployeeDetails> employeeDetails = employeeService.getAllBooks();

        if (!employeeDetails.isEmpty()) {
            baseResponse.setApiName("getAllEmployeeDetails");
            baseResponse.setStatus(true);
            baseResponse.setData(employeeDetails);
        } else {
            baseResponse.setApiName("getAllEmployeeDetails");
            baseResponse.setStatus(false);
            baseResponse.setData("Data not found");
        }


        return ResponseEntity.ok().body(objectMapper.writeValueAsString(baseResponse));
    }

    @PostMapping(value = "/v1/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertEmployeeDetails(@RequestBody EmployeeDetails employeeDetails) throws JsonProcessingException {

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

    @PutMapping(value = "/v1/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEmployeeDetails(@RequestBody EmployeeDetails employeeDetails) throws JsonProcessingException {

        BaseResponse baseResponse = new BaseResponse();
        if (employeeService.saveOrUpdate(employeeDetails)) {
            baseResponse.setApiName("Employee update");
            baseResponse.setStatus(true);
            baseResponse.setData("Data updated successfully");
        } else {
            baseResponse.setApiName("Employee update");
            baseResponse.setStatus(false);
            baseResponse.setData("Data not updated");
        }


        return ResponseEntity.ok().body(objectMapper.writeValueAsString(baseResponse));
    }

    @DeleteMapping(value = "/v1/deleteEmployeeDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> deleteEmployeeDetails(@PathVariable("id") int id) throws JsonProcessingException {

        BaseResponse baseResponse = new BaseResponse();
        if (employeeService.deleteEmployeeDetailsById(id)) {
            baseResponse.setApiName("Employee delete");
            baseResponse.setStatus(true);
            baseResponse.setData("Data deleted successfully");
        } else {
            baseResponse.setApiName("Employee delete");
            baseResponse.setStatus(false);
            baseResponse.setData("Data not deleted");
        }
        return ResponseEntity.ok().body(objectMapper.writeValueAsString(baseResponse));

    }

    @Operation(summary = "Get a EmployeeDetails by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the EmployeeDetails",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDetails.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "EmployeeDetails not found",
                    content = @Content)})
    @GetMapping(value = "/v1/getEmployeeDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> getEmployeeDetailsById(@PathVariable("id") int id) throws JsonProcessingException {
        BaseResponse baseResponse = new BaseResponse();
        EmployeeDetails employeeDetails = employeeService.getEmployeeDetailsById(id);
        if (employeeDetails != null) {
            baseResponse.setApiName("getEmployeeDetails");
            baseResponse.setStatus(true);
            baseResponse.setData(employeeDetails);
        } else {
            baseResponse.setApiName("getEmployeeDetails");
            baseResponse.setStatus(false);
            baseResponse.setData(employeeDetails);
        }
        return ResponseEntity.ok().body(objectMapper.writeValueAsString(baseResponse));
    }
}