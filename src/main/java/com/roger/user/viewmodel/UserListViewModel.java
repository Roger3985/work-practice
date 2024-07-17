package com.roger.user.viewmodel;

import com.roger.user.pojo.User;
import com.roger.user.service.UserService;
import com.roger.user.dto.UserDto;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserListViewModel {

    @WireVariable
    private UserService userService;

    private List<UserDto> users = new ArrayList<>();
    private UserDto selectedUser = new UserDto();

    @Init
    public void init() {
        users = userService.findAllUsers();
    }

    @Command
    public void editUser(@BindingParam("user") UserDto user) {
        System.out.println("user:" + user.getUsername());
        Map<String, Object> args = new HashMap<>();
        args.put("user", user);
        // 將選擇的會員傳遞到編輯頁面，使用正確的ZUL文件路徑
        Executions.createComponents("~./zul/user/editUserPage.zul", null, args);
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public UserDto getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserDto selectedUser) {
        this.selectedUser = selectedUser;
    }
}
