package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import com.roger.user.pojo.User;
import com.roger.user.service.UserService;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;


import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserViewModel2 {

    @WireVariable
    private UserService userService;

    private List<UserDto> allUsers; // 獲取所有使用者
    private List<User> currentPageUsers;
    private int pageSize = 10;
    private int activePage = 0;

    @Init
    public void init() {
        allUsers = userService.findAllUsers();
    }



    private void updateCurrentPageUsers() {
        int start = activePage * pageSize;
        int end = Math.min(start + pageSize, allUsers.size());
    }

    @Command
    @NotifyChange("currentPageUsers")
    public void changePageSize(int pageSize) {
        this.pageSize = pageSize;
        updateCurrentPageUsers();
    }

    @Command
    @NotifyChange("currentPageUsers")
    public void changeActivePage(int activePage) {
        this.activePage = activePage;
        updateCurrentPageUsers();
    }

    public int getActivePage() {
        return activePage;
    }

    public void setActivePage(int activePage) {
        this.activePage = activePage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<User> getCurrentPageUsers() {
        return currentPageUsers;
    }

    public void setCurrentPageUsers(List<User> currentPageUsers) {
        this.currentPageUsers = currentPageUsers;
    }

    public List<UserDto> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<UserDto> allUsers) {
        this.allUsers = allUsers;
    }
}
