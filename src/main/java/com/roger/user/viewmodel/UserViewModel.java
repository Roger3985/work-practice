package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;
import com.roger.user.pojo.User;
import com.roger.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserViewModel {

    @WireVariable
    private UserService userService;

    @WireVariable
    private PasswordEncoder passwordEncoder;

    private UserDto userDto = new UserDto();

    private Result result = new Result();

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
        Executions.sendRedirect("~./zul/user/registerUser.zul");
    }

    @Command
    @NotifyChange("result")
    public void registerUser() {
        result = userService.register_zk(userDto);
    }

    /**
     * Go to the Delete Page.
     */
    @Command
    public void deletePage() {
        Executions.sendRedirect("~./zul/user/deleteUser.zul");
    }

    /**
     * Delete User
     */
    @Command
    @NotifyChange("result")
    public void deleteUser() {
        result = userService.deleteUser(userDto);
    }

    /**
     * Go to Update Page
     */
    @Command
    public void updatePage() {
        Executions.sendRedirect("~./zul/user/updateUser.zul");
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
        Executions.sendRedirect("~./zul/user/searchUser.zul");
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

    /**
     * Go to AllUsersPage
     */
    public void allUsersPage() {
        Executions.sendRedirect("~./zul/user/usersPage.zul");
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

