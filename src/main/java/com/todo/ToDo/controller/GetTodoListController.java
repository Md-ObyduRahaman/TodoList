package com.todo.ToDo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/private")
public class GetTodoListController {
    private final Logger logger = LoggerFactory.getLogger(GetTodoListController.class);
    private final short API_VERSION = 1;

    @Autowired
    Environment environment;




    @GetMapping(value = "/v1/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTodoListController() throws JsonProcessingException {

        return null;

    }
}