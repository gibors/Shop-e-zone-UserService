package com.shopezone.userservice.repository;

import com.shopezone.userservice.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import java.util.Optional;

@EnableScan
public interface UserRepository extends DynamoDBCrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}
