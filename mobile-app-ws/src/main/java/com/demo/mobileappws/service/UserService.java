package com.demo.mobileappws.service;

import com.demo.mobileappws.model.UserDetailRequest;
import com.demo.mobileappws.model.UserRest;

public interface UserService {

    UserRest createUser(UserDetailRequest userDetailRequest);
}
