package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import com.roger.user.service.UserService;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.Map;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EditUserViewModel {

    @WireVariable
    private UserService userService;

    private UserDto user;

    @Init
    public void Init(@ExecutionArgParam("user") UserDto user) {
        this.user = user;
    }

    @Command
    public void saveUser() {
        userService.upateUser(user);
        // 保存後跳轉會員詳情頁面或包含頁面
        Executions.createComponents("~./zul/user/userDetailPage.zul", null, Map.of("user", user));
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
