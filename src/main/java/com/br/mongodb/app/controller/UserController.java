package com.br.mongodb.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.mongodb.app.dto.UserDTO;
import com.br.mongodb.app.model.User;
import com.br.mongodb.app.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Api(value = "Service CRUD using MongoDB")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	@ApiOperation(value = "Search all users register")
	public ResponseEntity<List<User>> getTodosProfessores() {
		List<User> users = new ArrayList<>();
		try {
			users = userService.getAllUsers();
			if (users.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/showUser/{id}")
	@ApiOperation(value = "Search for a particular user by Id")
	public ResponseEntity<User> getProfessorPorId(@PathVariable("id") String id) {
		Optional<User> userData = userService.getUserOptional(id);
		if (userData.isPresent())
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/saveUser")
	@ApiOperation(value = "Save new user")
	public ResponseEntity<User> salvarProfessor(@RequestBody UserDTO userDTO) {
		try {
			return new ResponseEntity<>(userService.saveUser(userDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateUser")
	@ApiOperation(value = "Update respective user")
	public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO) {

		Optional<User> userData = userService.getUserOptional(userDTO.getId());
		if (userData.isPresent()) {
			return new ResponseEntity<>(userService.updateUser(userData, userDTO), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteUser/{id}")
	@ApiOperation(value = "Delete user by Id")
	public ResponseEntity<HttpStatus> deletarProfessor(@PathVariable("id") String id) {
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteAllUsers")
	@ApiOperation(value = "Delete all users")
	public ResponseEntity<HttpStatus> deletarProfessores() {
		try {
			userService.deleteAllUsers();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
