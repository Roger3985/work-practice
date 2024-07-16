package com.roger.zk.viewmodel;

import com.roger.user.pojo.User;
import com.roger.zk.service.UserService;
import org.zkoss.zk.ui.annotation.Command;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LoginViewModel {

    @WireVariable
    private UserService userService;

    private String username;
    private String password;
    private String message;

    // Getters and Setters

    @Command
    public void login() {
        User user = userService.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            message = "Invalid username or password!";
        } else {
            message = "Login successful!";
        }
    }
}
