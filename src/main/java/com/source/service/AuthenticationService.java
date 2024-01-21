package com.source.service;

import com.source.model.User;

import java.util.List;

public interface AuthenticationService {

    List<User> getAdminCredentials();
}
