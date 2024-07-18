package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserDetailViewModel {

    /**
     * 自動綁定 ID 為 userDetailWin
     */
    @Wire("#userDetailWin")
    private Window userDetailWin;

    private UserDto user = new UserDto();

    /**
     * 初始化方法，從參數中取得使用者資訊
     * @param user 使用者
     */
    public void Init(@ExecutionArgParam("user") UserDto user) {
        // 獲取傳遞的會員參數
        this.user = user;
    }

    /**
     * 在元件初始化和綁定完成後調用的方法
     */
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        // 確保所有帶有 @Wire 註解的元件被正確綁定
        Selectors.wireComponents(view, this, false);
    }

    /**
     * 關閉對話框的方式
     */
    public void closeDialog() {
        if (userDetailWin != null) {
            userDetailWin.detach(); // 使用 detach() 方法關閉
        } else {
            Clients.showNotification("Window component is null", "error", null, "middle_center", 2000);
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
