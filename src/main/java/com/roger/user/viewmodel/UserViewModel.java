package com.roger.user.viewmodel;

import com.roger.user.mapper.UserMapper;
import com.roger.user.pojo.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.springframework.dao.DuplicateKeyException;

@Data
@Component
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserViewModel {

    @WireVariable
    private UserMapper userMapper;

    @WireVariable
    private PasswordEncoder passwordEncoder;

    private String username;
    private String password;
    private String response;
    private String nickname;
    private String email;

    /**
     * Go to Index Page
     */
    public void indexPage() {
        Executions.sendRedirect("/");
    }

    /**
     * Go to Register Page
     */
    public void registerPage() {
        Executions.sendRedirect("/zk/registerUser");
    }

    /**
     * Register User
     */
    @Command
    @NotifyChange("response")
    public void registerUser() {
        try {
            User user = new User();
            user.setUsername(username);
            password = passwordEncoder.encode(password);
            user.setPassword(password);
            userMapper.addUser(user);
            response = "User " + username + " with password " + password + " has been saved.";
            System.out.println("Registration successful: " + response);
        } catch (DuplicateKeyException e) {
            response = "This user is already registered";
            System.out.println("User is already registered: " + response);
        } catch (Exception e) {
            response = "An error occurred during registration";
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Go to the Delete Page.
     */
    public void deletePage() {
        Executions.sendRedirect("/zk/deleteUser");
    }

    /**
     * Delete User
     */
    @Command
    @NotifyChange("response")
    public void deleteUser() {
        User user = userMapper.findByUserName(username);
        if (user != null) {
            userMapper.deleteUser(user);
            response = "User " + username + " is delete success";
        } else {
            response = "Can not delete, Because user is no existed";
        }
    }

    /**
     * Go to Update Page
     */
    public void updatePage() {
        Executions.sendRedirect("/zk/updateUser");
    }

    /**
     * Update User
     */
    @Command
    @NotifyChange("response")
    public void updateUser() {
        User user = userMapper.findByUserName(username);
        if (user != null) {
            user.setUsername(username);
            password = passwordEncoder.encode(password);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setEmail(email);
            userMapper.updateUser(user);
            response = "The " + username + " is update success.";
        } else {
            response = "The user is no exist.";
        }
    }

    /**
     * Go to Search Page
     */
    public void searchPage() {
        Executions.sendRedirect("/zk/searchUser");
    }

    /**
     * Search User
     */
    @Command
    @NotifyChange("response")
    public void searchUser() {
        User user = userMapper.findByUserName(username);
        if (user != null) {
            response = "User finds and the username: " + username;
        } else {
            response = "User is not found. Please try again";
        }
    }
}

