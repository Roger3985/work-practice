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

    @WireVariable
    private User user;

    private String username;
    private String password;
    private String response;

    @Command
    @NotifyChange("response")
    public void register() {
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
        user = userMapper.findByUserName(username);
        userMapper.deleteUser(user);
        response = "User " + username + " is delete success";
    }

    /**
     * Go to update Page
     */
    public void updatePage() {
        Executions.sendRedirect("/zk/updateUser");
    }

    /**
     * Go to search Page
     */
    public void searchPage() {
        Executions.sendRedirect("/zk/searchUser");
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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

