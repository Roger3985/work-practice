package com.roger.user.viewmodel;

import com.roger.user.mapper.UserMapper;
import com.roger.user.pojo.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

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
     * Go to register Page
     */
    public void registerPage() {
        Executions.sendRedirect("/");
    }

    @Command
    @NotifyChange("response")
    public void register() {
        User user = new User();
        user.setUsername(username);
        password = passwordEncoder.encode(password);
        user.setPassword(password);
        userMapper.addUser(user);
        response = "User " + username + " with password " + password + " has been saved.";
    }

    /**
     * Go to the delete Page.
     */
    public void deletePage() {
        Executions.sendRedirect("/zk/deleteUser");
    }

    @Command
    @NotifyChange("response")
    public void deleteUser() {

        User user = new User();
        user = userMapper.findByUserName(username);
        if (user != null) {
            userMapper.deleteUser(user);
            response = "User " + username + " is delete success";
        } else {
            response = "Can not delete, Because user is no existed";
        }
    }

    /**
     * Go to update Page
     */
    public void updatePage() {
        Executions.sendRedirect("/zk/updateUser");
    }

    @Command
    @NotifyChange("response")
    public void updateUser() {

        User user = new User();
        user = userMapper.findByUserName(username);
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
     * Go to search Page
     */
    public void searchPage() {
        Executions.sendRedirect("/zk/searchUser");
    }

    @Command
    @NotifyChange("response")
    public void searchUser() {

        User user = new User();
        user = userMapper.findByUserName(username);
        if (user != null) {
            response = "User finds and the username: " + username;
        } else {
            response = "User is not found. Please try again";
        }
    }

    // Getters and setters for username, password, email, and response
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

