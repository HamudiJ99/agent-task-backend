package com.example.agent_task;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    // Alle Tasks
    @GetMapping
    public List<Task> getAll() {
        return repository.findAll();
    }

    // Einzelnen Task
    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // Task erstellen
    @PostMapping
    public Task create(@RequestBody Task task) {
        return repository.save(task);
    }

    // Task löschen
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Task als erledigt markieren
    @PutMapping("/{id}/complete")
    public Task complete(@PathVariable Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setCompleted(true);
        return repository.save(task);
    }
}