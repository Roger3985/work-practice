package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import com.roger.user.pojo.User;
import com.roger.user.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;


import java.util.*;

@Getter
@Setter
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserViewModel2 {

    private static final Logger logger = LoggerFactory.getLogger(UserViewModel2.class);

    @WireVariable
    private UserService userService;

    private List<UserDto> users = new ArrayList<>();
    private UserDto selectedUser = new UserDto();
    private User user;

    // 分頁相關屬性
    private int pageSize = 100; // 每頁顯示的數量
    private int pageNumber = 1; // 當前頁碼
    private int totalUserCount; // 總會員數量
    private int totalPage; // 總頁數

    /**
     * 初始化
     */
    @Init
    public void init() {
        // 加載初始資料
        loadUsers();
    }

    /**
     * 加載會員數量
     */
    @NotifyChange({"users", "totalUserCount", "pageNumber", "totalPage"})
    private void loadUsers() {
        try {
            users = userService.findUsersByPage(pageNumber, pageSize); // 傳入資料限制每頁資料
            totalUserCount = userService.countAllUsers(); // 獲取總資料數量
            totalPage = (int) Math.ceil((double) totalUserCount / pageSize); // 計算總頁數
            logger.info("Loaded users: {} Total users: {} Total pages: {}", users.size(), totalUserCount, totalPage);
        } catch (Exception e) {
            logger.error("Failed to load users", e);
        }
    }

    /**
     * 翻頁
     */
    @Command
    @NotifyChange({"users", "totalUserCount","pageNumber", "totalPage"})
    public void navigatePage(@BindingParam("page") int page) {
        System.out.println("navigatePage called with page: " + page);
        // 如果目標頁碼大於0且小於等於總頁數，則進行頁碼切換
        if (page > 0 && page <= totalPage()) {
            // 設置當前頁碼為使用者點擊的目標頁碼
            pageNumber = page;
            // 加載對應頁碼的使用者數據
            loadUsers();
        }
    }

    /**
     * 獲取總頁數
     */
    private int totalPage() {
        return (int) Math.ceil((double) totalUserCount / pageSize);
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
    @Command
    @NotifyChange({"users", "user"})
    public void registerUser() {
        Map<String, Object> args = new HashMap<>();
        Executions.createComponents("~./zul/user/registerUserPage.zul", null, args);
    }

    /**
     * 刪除選擇的使用者
     * @param user 使用者
     */
    @Command
    @NotifyChange({"users", "user"})
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
    @NotifyChange({"users", "user"})
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
}
