package com.source.serviceImpl;

import com.source.dao.AuthenticationRepository;
import com.source.model.User;
import com.source.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    AuthenticationRepository authrepo;
    @Override
    public List<User> getAdminCredentials() {

        return authrepo.findAll();
    }
}
