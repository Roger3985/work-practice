package com.roger.user.viewmodel;

import com.roger.master.service.MasterService;
import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;
import com.roger.user.pojo.User;
import com.roger.user.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@Getter
@Setter
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserViewModel {

    @WireVariable
    private UserService userService;

    @WireVariable
    private MasterService masterService;

    @WireVariable
    private PasswordEncoder passwordEncoder;

    private UserDto userDto;
    private Result result;

    /**
     * Go to Index Page
     */
    @Command
    public void indexPage() {
        Executions.sendRedirect("/");
    }

    /**
     * Go to Register Page
     */
    @Command
    public void registerPage() {
        Executions.sendRedirect("/zk/registerUser");
    }

    @Command
    @NotifyChange("result")
    public Result registerUser() {
        try {
            result = masterService.UserWithDepartment(userDto);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result = new Result();
    }

    /**
     * Go to the Delete Page.
     */
    @Command
    public void deletePage() {
        Executions.sendRedirect("/zk/deleteUser");
    }

    /**
     * Delete User
     */
    @Command
    @NotifyChange("result")
    public Result deleteUser() {
        try {
            result = userService.deleteUser(userDto);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result = new Result();
    }

    /**
     * Go to Update Page
     */
    @Command
    public void updatePage() {
        Executions.sendRedirect("/zk/updateUser");
    }

    /**
     * Update User
     */
    @Command
    @NotifyChange("result")
    public void updateUser() {
        result = userService.upateUser(userDto);
    }

    /**
     * Go to Search Page
     */
    @Command
    public void searchPage() {
        Executions.sendRedirect("/zk/searchUser");
    }

    /**
     * Search User
     */
    @Command
    @NotifyChange("result")
    public void searchUser() {
        User user = new User(userDto.getUsername(), userDto.getNickname(), userDto.getEmail());
        result = userService.findUserUnion(user);
    }

    public UserService getUserService() {
        return userService;
    }
}

