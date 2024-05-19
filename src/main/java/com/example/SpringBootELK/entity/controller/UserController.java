package com.example.SpringBootELK.entity.controller;

import com.example.SpringBootELK.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public User getUserById( @PathVariable String id)
    {
       logger.info("User id : "+id);
        User user= getUsers().stream().filter(x->x.getId().equals(id)).findAny().orElse(null);

       if(user!=null)
       {
           logger.info("User found : {} "+user);
           return user;
       }
       else {
           try{
               throw  new Exception();
           }
           catch (Exception ae)
           {
               logger.error("User not found with ID "+id);
           }
           return  new User();
       }
    }

    public List<User> getUsers()
    {
        return Stream.of(new User("1","Vishal"),
                new User("2","Vaibhav"),
                new User("3","Aditya"),
                new User("4","Sachin")).collect(Collectors.toList());
    }
}
