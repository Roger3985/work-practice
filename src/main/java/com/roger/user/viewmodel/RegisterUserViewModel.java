package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import com.roger.user.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class RegisterUserViewModel {

    private static final Logger logger = LoggerFactory.getLogger(RegisterUserViewModel.class);

    /**
     * 自動綁定 ID 為 registerUserWin 的組件
     */
    @Wire("#registerUserWin")
    private Window registerUserWin;

    /**
     * 自動注入 Spring 管理的 UserService bean
     */
    @WireVariable
    private UserService userService;

    private UserDto user = new UserDto();;

    /**
     * 新增使用者的方法
     */
    @Command
    @NotifyChange({"users", "user"})
    public void registerUser() {

        // System.out.println(user.getUsername());

        // 新增使用者
        userService.register_zk(user);

        // 顯示添加成功的消息
        Clients.showNotification("添加成功", "info", null, "middle_center", 2000);

        closeDialog();
    }

    /**
     * 在元件初始化和綁定完成後調用的方法
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        // 確保所有帶有 @Wire 註解的元件被正確綁定
        Selectors.wireComponents(view, this, false);
    }

    /**
     * 關閉對話框的方法
     */
    @Command
    public void closeDialog() {
        if (registerUserWin != null) {
            registerUserWin.detach(); // 使用 detach() 方法關閉
        } else {
            Clients.showNotification("Window component is null!", "error", null, "middle_center", 2000);
        }
    }

    // Getter and Setter
    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
