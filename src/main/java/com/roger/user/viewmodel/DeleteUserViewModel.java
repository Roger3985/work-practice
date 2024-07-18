package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import com.roger.user.service.UserService;
import lombok.Getter;
import lombok.Setter;
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
public class DeleteUserViewModel {

    /**
     * 自動綁定 ID 為 deleteUserWin 的組件
     */
    @Wire("#deleteUserWin")
    private Window deleteUserWin;

    /**
     * 自動注入 Spring 管理的 UserService bean
     */
    @WireVariable
    private UserService userService;

    private UserDto user = new UserDto();

    /**
     * 在 viewModel 初始化時調用，接收傳遞過來的使用者資料
     * @param user 使用者
     */
    @Init
    public void init(@ExecutionArgParam("user") UserDto user) {
        this.user = user; // 將當前載入的使用者給這個 viewmodel 內的資料使用
    }

    @Command
    @NotifyChange("users")
    public void deleteUser() {
        // 刪除使用者
        userService.deleteUser(user);

        // 重新加載會員列表
        List<UserDto> users = userService.findAllUsers();

        // 關閉彈窗
        closeDialog();
    }

    /**
     * 在組件初始化和綁定完成後調用的方法
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        // 確保所有帶有 @Wire 註解的組件被正確綁定
        Selectors.wireComponents(view, this, false);
    }

    /**
     * 關閉對話框的方法
     */
    @Command
    public void closeDialog() {
        if (deleteUserWin != null) {
            deleteUserWin.detach(); // 使用 detach() 方法關閉
        } else {
            Clients.showNotification("Window component is null!", "error", null, "middle_center", 2000);
        }
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
