package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import com.roger.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import java.util.List;
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

    /**
     * 在 ViewModel 初始化時調用，接收傳遞過來的使用者資料
     * @param user 使用者
     */
    @Init
    public void init(@ExecutionArgParam("user") UserDto user) {
        this.user = user;
    }

    /**
     * 保存使用者資料的方法
     */
    @Command
    @NotifyChange("users")
    public void saveUser() {

        // 更新會員
        userService.upateUser(user);

        // 重新加載會員列表
        List<UserDto> users = userService.findAllUsers();

        // 將更新後的會員列表傳遞到會員列表頁面
        // Executions.getCurrent().getDesktop().setAttribute("users", users);

        // 保存後跳轉會員詳情頁面或包含頁面
        // Executions.sendRedirect("~./zul/user/usersPage.zul");

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
}
