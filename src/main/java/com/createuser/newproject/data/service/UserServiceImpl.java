package com.createuser.newproject.data.service;

import com.createuser.newproject.data.model.User;
import com.createuser.newproject.data.repository.UserRepository;
import com.createuser.newproject.dtos.exception.EmailAlreadyExistException;
import com.createuser.newproject.dtos.request.RegisterUserRequests;
import com.createuser.newproject.dtos.responses.RegisterUserResponses;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public RegisterUserResponses registerUser(RegisterUserRequests requests) {
        if (userRepository.existsByEmail(requests.getEmail().toLowerCase().strip())) {
            throw new EmailAlreadyExistException("Email already exists, use another email address to sign up");
        }
        User user = mapper.map(requests, User.class);
        user.setFirstName(requests.getFirstName().toLowerCase().strip());
        user.setLastName(requests.getLastName().toLowerCase().strip());
        user.setPassword(requests.getPassword());
        user.setPassword(requests.getPassword());
        user.setPhoneNumber(requests.getPhoneNumber());
        userRepository.save(user);
        RegisterUserResponses responses = mapper.map(user, RegisterUserResponses.class);
        responses.setMessage("Your account has been created successfully");
        return responses;
    }
}
