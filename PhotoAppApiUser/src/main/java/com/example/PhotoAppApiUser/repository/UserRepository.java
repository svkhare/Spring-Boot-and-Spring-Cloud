package com.example.PhotoAppApiUser.repository;

import com.example.PhotoAppApiUser.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
