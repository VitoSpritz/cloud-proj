package com.example.dbcon.dbcon.Services;

import com.example.dbcon.dbcon.entities.NewUser;

public interface NewUserService {
    
    public void createUser(NewUser newUser);

    public void sendVerificationEmail(String clientId);
}
