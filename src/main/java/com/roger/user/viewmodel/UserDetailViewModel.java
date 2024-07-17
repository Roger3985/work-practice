package com.roger.user.viewmodel;

import com.roger.user.dto.UserDto;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserDetailViewModel {

    private UserDto user = new UserDto();

    public void Init() {
        // 獲取傳遞的會員參數
        Execution execution = Executions.getCurrent();
        user = (UserDto) execution.getArg().get("user");
    }

    // Getter and Setter

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
