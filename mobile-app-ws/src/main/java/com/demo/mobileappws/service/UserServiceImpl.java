package com.demo.mobileappws.service;

import com.demo.mobileappws.model.UserDetailRequest;
import com.demo.mobileappws.model.UserRest;
import com.demo.mobileappws.Utilites.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    Map<String, UserRest> users;
    Utils utils;


    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailRequest userDetailRequest) {
        UserRest userRest = new UserRest();
        userRest.setFirstName(userDetailRequest.getFirstName());
        userRest.setLastName(userDetailRequest.getLastName());
        userRest.setEmail(userDetailRequest.getEmail());

        String userId = utils.generateUserId();
        userRest.setUserId(userId);

        if (users == null) users = new HashMap<>();
        users.put(userId, userRest);
        return userRest;
    }
}
