package com.example.PhotoAppApiUser.controller;

import com.example.PhotoAppApiUser.dto.UserDto;
import com.example.PhotoAppApiUser.model.CreateUserRequestModel;
import com.example.PhotoAppApiUser.model.CreateUserResponseModel;
import com.example.PhotoAppApiUser.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    UserService userService;

    @GetMapping("/status")
    public String status(){
        return "Photo App User Working on port " +env.getProperty("local.server.port");
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel createUserRequestModel){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto= modelMapper.map(createUserRequestModel, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);

        CreateUserResponseModel returnValue = modelMapper.map(createdUser,CreateUserResponseModel.class);

        return  ResponseEntity.status( HttpStatus.CREATED).body(returnValue);
    }
}
