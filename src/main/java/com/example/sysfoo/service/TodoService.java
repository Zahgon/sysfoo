package com.example.sysfoo.service;

import com.example.sysfoo.model.Todo;
import com.example.sysfoo.repository.TodoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TodoService {

    @Inject
    TodoRepository todoRepository;

    @Transactional
    public Todo save(Todo todo) {
        // Replicate Spring Data save() upsert: persist() is insert-only, so merge when id is present.
        if (todo.getId() == null) {
            todoRepository.persist(todo);
            return todo;
        }
        return todoRepository.getEntityManager().merge(todo);
    }

    public List<Todo> findAll() {
        return todoRepository.listAll();
    }
}
