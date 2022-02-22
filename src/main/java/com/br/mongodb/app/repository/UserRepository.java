package com.br.mongodb.app.repository;

import org.springframework.stereotype.Repository;

import com.br.mongodb.app.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
