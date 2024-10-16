package com.createuser.newproject.data.service;

import com.createuser.newproject.dtos.request.RegisterUserRequests;
import com.createuser.newproject.dtos.responses.RegisterUserResponses;

public interface UserService {
    RegisterUserResponses registerUser(RegisterUserRequests requests);
}
