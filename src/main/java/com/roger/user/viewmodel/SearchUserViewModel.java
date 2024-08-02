package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;
import com.roger.user.pojo.User;
import com.roger.user.service.UserService;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SearchUserViewModel {

    /**
     * 自動注入 Spring 管理的 UserService bean
     */
    @WireVariable
    private UserService userService;

    private UserDto user = new UserDto();

    /**
     * 在 ViewModel 初始化時調用，接收傳遞過來的使用者資料
     * @param user 使用者
     */
    @Init
    public void init(@ExecutionArgParam("user") UserDto user) {
        this.user = user;
    }

    public void searchUser() {
        // 查詢使用者
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
