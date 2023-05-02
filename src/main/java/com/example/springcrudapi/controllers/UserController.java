package com.example.springcrudapi.controllers;

import com.example.springcrudapi.models.User;
import com.example.springcrudapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/welcome")
    public String welcomePage(){
        return "Hello EveryBody Welcome...";
    }

    @GetMapping(value = "/allUsers")
    public List<User> getUser(){
        return userRepo.findAll();
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    public ResponseEntity<User> addUser(@RequestBody User user){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(user != null) {

            userRepo.save(user);
            return new ResponseEntity<>(user, headers, HttpStatus.OK);}
        else{
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }
        //return "User Added Successfully...";
    }
    @PutMapping(value = "update/{id}")
    public  String updateUser(@PathVariable long id,@RequestBody User user){
        //get user by his id to update his data
        User updatedUser = userRepo.findById(id).get();
        //set new data
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setOccupation(user.getOccupation());
        updatedUser.setAge(user.getAge());

        //save the new data in the database
        userRepo.save(updatedUser);
        return "User Updated Successfully...";


    }

    @DeleteMapping(value = "delete/{id}")
    public  String deleteUser(@PathVariable long id){
        //get user by his id to delete his data
        User deleteUser = userRepo.findById(id).get();

        //delete the user from the database
        userRepo.delete(deleteUser);
        return "User with id of "+id+" Deleted Successfully...";


    }
}
