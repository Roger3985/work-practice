package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import com.roger.user.service.UserService;
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

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EditUserViewModel {

    private static final Logger logger = LoggerFactory.getLogger(EditUserViewModel.class);

    /**
     * 自動綁定 ID 為 editUserWin 的組件
     */
    @Wire("#editUserWin")
    private Window editUserWin;

    /**
     * 自動注入Spring管理的UserService bean
     */
    @WireVariable
    private UserService userService;

    private UserDto user = new UserDto();
    private UserDto tempUser = new UserDto(); // 臨時儲存物件

    /**
     * 在 ViewModel 初始化時調用，接收傳遞過來的使用者資料
     * @param user 使用者
     */
    @Init
    public void init(@ExecutionArgParam("user") UserDto user) {
        this.user = user;
        // 初始化臨時物件
        this.tempUser.setUsername(user.getUsername());
        this.tempUser.setPassword(user.getPassword());
        this.tempUser.setNickname(user.getNickname());
        this.tempUser.setEmail(user.getEmail());
    }

    /**
     * 保存使用者資料的方法
     */
    @Command
    @NotifyChange("users")
    public void saveUser() {

        // 將臨時對象的值複製到實際用戶對象
        user.setUsername(tempUser.getUsername());
        user.setPassword(tempUser.getPassword());
        user.setNickname(tempUser.getNickname());
        user.setEmail(tempUser.getEmail());

        // 更新使用者
        userService.upateUser(user);

        // 顯示保存成功的消息
        Clients.showNotification("保存成功", "info", null, "middle_center", 2000);

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
        if (editUserWin != null) {
            editUserWin.detach(); // 使用 detach() 方法關閉
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

    public UserDto getTempUser() {
        return tempUser;
    }

    public void setTempUser(UserDto tempUser) {
        this.tempUser = tempUser;
    }
}
