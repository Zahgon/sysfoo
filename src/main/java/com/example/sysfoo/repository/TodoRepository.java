package com.example.sysfoo.repository;

import com.example.sysfoo.model.Todo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TodoRepository implements PanacheRepository<Todo> {
}
