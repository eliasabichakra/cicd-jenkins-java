package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;

@SpringBootApplication
public class MainClass {
    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);
    }
}

// Entity class for User, mapped to the 'users' table
@Entity
@Table(name = "users") // Specify the table name as 'users'
class User {
    @Id
    private String user; // Assuming 'user' is the column name for the ID
    private int age;     // Assuming 'age' is another column in the table

    // Getters and setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

// Repository to interact with the 'users' table in the database
interface UserRepository extends JpaRepository<User, String> {
    // JpaRepository will handle the interaction with the 'users' table
}

// REST Controller to expose the endpoint
@RestController
class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Fetch all users from the 'users' table
    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll(); // Fetches all records from the 'users' table
    }
}
