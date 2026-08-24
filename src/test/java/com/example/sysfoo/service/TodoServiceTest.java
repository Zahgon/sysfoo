package com.example.sysfoo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.sysfoo.model.Todo;
import com.example.sysfoo.repository.TodoRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class TodoServiceTest {

    @InjectMock
    TodoRepository todoRepository;

    @Inject
    TodoService todoService;

    @Test
    public void saveTodoTest() {
        Todo todo = new Todo("Test Todo");
        Mockito.doNothing().when(todoRepository).persist(todo);

        Todo savedTodo = todoService.save(todo);
        assertEquals("Test Todo", savedTodo.getText());
    }
}
