package com.example.RestControllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.User;
import com.example.Repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ClientRestController {

	@Autowired
	private UserRepository userRepository;

	@ResponseBody
	@JsonView(User.Basic.class)
	@RequestMapping(value = "/api/clients/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> addClient(@RequestBody User user, HttpSession session) {
		session.setMaxInactiveInterval(-1);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/api/clients/", method = RequestMethod.GET)
	public ResponseEntity<Page<User>> getClients(HttpSession session, Pageable page) {
		session.setMaxInactiveInterval(-1);
		Page<User> users = userRepository.findAll(page);
		if (users != null) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/api/clients/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getClient(@PathVariable long id, HttpSession session) {
		session.setMaxInactiveInterval(-1);
		User user = userRepository.findById(id);
		if (user != null) {
			return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
