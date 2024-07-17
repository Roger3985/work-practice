package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import com.roger.user.service.UserService;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;
import java.util.Map;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EditUserViewModel {

    @WireVariable
    private UserService userService;

    private UserDto user = new UserDto();

    @Init
    public void Init(@ExecutionArgParam("user") UserDto user) {
        this.user = user;
    }

    @Command
    public void saveUser() {

        // 更新會員
        userService.upateUser(user);

        // 重新加載會員列表
        List<UserDto> users = userService.findAllUsers();

        // 將更新後的會員列表傳遞到會員列表頁面
        Executions.getCurrent().getDesktop().setAttribute("users", users);

        // 保存後跳轉會員詳情頁面或包含頁面
        Executions.sendRedirect("~./zul/user/usersPage.zul");
    }
    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
