package com.auth.backendserver.service;

import com.auth.backendserver.model.Users;
import com.auth.backendserver.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {
    private final UsersRepo usersRepo;
    @Autowired
    public UsersService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }
    //create users
    public Users saveUser(Users user) {
        return usersRepo.save(user);
    }
    public Optional<Users> login(String email, String password) {
        Optional<Users> user = usersRepo.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
    //all users
    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }
    // get user by id
    public Optional<Users> getUserById(UUID id){
        return usersRepo.findById(id);
    }
    //get user by email
    public Optional<Users> getUserByEmail(String email) {
        return usersRepo.findByEmail(email);
    }
    //delete user
    public void deleteUser(UUID id) {
        usersRepo.deleteById(id);
    }
}
