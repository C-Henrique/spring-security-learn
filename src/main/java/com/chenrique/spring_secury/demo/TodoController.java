package com.chenrique.spring_secury.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

	private static final List<Todo> TODO_LIST = List.of(new Todo("admin", "Curso I"), new Todo("admin", "Curso II"));
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Metodo GET - hello-word
	 * 
	 * @return String
	 */
	@GetMapping("/todos")
	public List<Todo> retrieveAllTodo() {
		return TODO_LIST;
	}

	@GetMapping("/users/{user}/todos")
	public Todo retrieveTodoForSpecificUser(@PathVariable("user") String username) {
		return TODO_LIST.get(0);
	}

	@PostMapping("/users/{user}/todos")
	public void createTodoForSpecificUser(@PathVariable("user") String username, @RequestBody Todo todo) {
		logger.info("Create {} of {}", todo, username);
	}

	record Todo(String username, String description) {
	}
}
