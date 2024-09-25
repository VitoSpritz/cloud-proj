package com.example.dbcon.dbcon.Services.Interfaces;

public interface GroupService {
    
    void assignGroup(String userId, String groupId);

    void deleteGroupFromUser(String userId, String groupId);
}
