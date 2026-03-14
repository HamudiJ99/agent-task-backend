package com.example.agent_task;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Person> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return repository.save(person);
    }
}