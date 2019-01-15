package com.greenfoxacademy.projectweek1restapi.controller;

import com.greenfoxacademy.projectweek1restapi.repository.AssigneeServiceImpl;
import com.greenfoxacademy.projectweek1restapi.repository.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
public class TodoRestController {

    private TodoServiceImpl todoSvc;
    private AssigneeServiceImpl assSvc;

    @Autowired
    public TodoRestController (TodoServiceImpl todoSvc, AssigneeServiceImpl assSvc) {
        this.todoSvc = todoSvc;
        this.assSvc = assSvc;
    }

    @GetMapping("/api/todo")
    public ArrayList<Object> list(Model model, @RequestParam(required=false) boolean isActive) {

        ArrayList<Object> todos = new ArrayList<>();

        if(isActive) {

            todoSvc.getAll()
                    .stream()
                    .filter(todo -> !todo.isDone())
                    .collect(Collectors.toList()).forEach(todos::add);
        }
        else {
            todoSvc.getAll().forEach(todos::add);
        }
        return todos;

    }

    @PostMapping("/api/todo/add")
    public ResponseEntity addNewTodoJson(){@RequestBody }
}
