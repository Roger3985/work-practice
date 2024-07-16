package com.roger.zk.viewmodel;

import com.roger.user.pojo.User;
import com.roger.zk.service.UserService;
import org.zkoss.zk.ui.annotation.Command;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class RegisterViewModel {

    @WireVariable
    private UserService userService;

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String message;

    // Getters and Setters

    @Command
    public void register() {
        if (!password.equals(confirmPassword)) {
            message = "Passwords do not match!";
            return;
        }
        if (userService.findByUsername(username) != null) {
            message = "Username already exists!";
            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userService.register(user);
        message = "Registration successful!";
    }
}
