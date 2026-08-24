package com.example.sysfoo.controller;

import com.example.sysfoo.model.Todo;
import com.example.sysfoo.service.TodoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/todos")
public class TodoController {

    @Inject
    TodoService todoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Todo addTodo(Todo todo) {
        return todoService.save(todo);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }
}
