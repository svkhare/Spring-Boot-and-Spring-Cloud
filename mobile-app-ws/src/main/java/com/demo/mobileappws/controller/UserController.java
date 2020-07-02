package com.demo.mobileappws.controller;

import com.demo.mobileappws.exceptions.UserServiceException;
import com.demo.mobileappws.model.UserDetailRequest;
import com.demo.mobileappws.model.UpdateUserDetailRequest;
import com.demo.mobileappws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.mobileappws.model.UserRest;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    Map<String, UserRest> users;

    //Request Param
    @GetMapping()
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit,
                           @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        return "get users was called with page= " + page + " and limit= " + limit + " and sort= " + sort + ".";
    }

    //Path Variable
    @GetMapping(value = "/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){

        if(true) throw new UserServiceException("A user service exception is thrown");

        if(users.containsKey(userId)){
            return new ResponseEntity<>(users.get(userId),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createtUser(@Valid @RequestBody UserDetailRequest userDetailRequest) {
        UserRest userRest = userService.createUser(userDetailRequest);
        return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailRequest updateUserDetailRequest) {
        UserRest storedDetails = users.get(userId);
        storedDetails.setFirstName(updateUserDetailRequest.getFirstName());
        storedDetails.setLastName(updateUserDetailRequest.getLastName());
        users.put(userId, storedDetails);
        return storedDetails;
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        users.remove(userId);
        return ResponseEntity.noContent().build();
    }
}
