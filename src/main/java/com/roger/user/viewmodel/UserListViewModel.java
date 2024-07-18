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

    // 分頁相關屬性
    private int pageSize = 10; // 每頁顯示的數量
    private int pageNumber = 1; // 當前頁碼
    private int totalUserCount; // 總會員數量

    @Init
    public void init() {
        loadUsers();
    }

    // 加載會員數量
    private void loadUsers() {
        users = userService.findUsersByPage(pageNumber, pageSize);
        totalUserCount = userService.countAllUsers(); // 獲取總資料數量
    }

    /**
     * Go Back to index Page
     */
    @Command
    public void indexPage() {
        Executions.sendRedirect("/");
    }

    /**
     * 新增使用者
     */
    public void registerUser() {
        Map<String, Object> args = new HashMap<>();
        Executions.createComponents("~./zul/user/registerUserPage.zul", null, args);
    }

    /**
     * 刪除選擇的使用者
     * @param user 使用者
     */
    @Command
    public void deleteUser(@BindingParam("user") UserDto user) {
        System.out.println("user: " + user.getUsername());
        Map<String, Object> args = new HashMap<>();
        args.put("user", user);
        // 將選擇的會員傳遞到刪除頁面，且使用正確的 zul 文件路徑
        Executions.createComponents("~./zul/user/deleteUserPage.zul", null, args);
    }

    /**
     * 編輯使用者並且帶參數過去
     * @param user 使用者
     */
    @Command
    public void editUser(@BindingParam("user") UserDto user) {
        System.out.println("user:" + user.getUsername());
        Map<String, Object> args = new HashMap<>();
        args.put("user", user);
        // 將選擇的會員傳遞到編輯頁面，使用正確的ZUL文件路徑
        Executions.createComponents("~./zul/user/editUserPage.zul", null, args);
    }

    /**
     * 查看使用者詳細資訊並且帶參數過去
     * @param user 使用者
     */
    @Command
    public void userDetail(@BindingParam("user") UserDto user) {
        System.out.println("user: " + user.getUsername());
        Map<String, Object> args = new HashMap<>();
        args.put("user", user);
        // 將選擇的會員傳遞到詳細頁面，且使用正確的 zul 文件路徑
        Executions.createComponents("~./zul/user/userDetailPage.zul", null, args);
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalUserCount() {
        return totalUserCount;
    }

    public void setTotalUserCount(int totalUserCount) {
        this.totalUserCount = totalUserCount;
    }
}
