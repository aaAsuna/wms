package org.ricardo.wms.util;

import org.ricardo.wms.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserContext {
    public static final String USER_IN_SESSION = "user_in_session";
    public static final String PERMS_IN_SESSION = "perms_in_session";

    public static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }
    public static void setCurrentUser(Employee emp) {
        if(emp == null) {
            getSession().invalidate();
        }
        getSession().setAttribute(USER_IN_SESSION, emp);
    }

    public static Employee getCurrentUser() {
        return (Employee) getSession().getAttribute(USER_IN_SESSION);
    }

    public static void setCurrentPerms(List<String> perms) {
        getSession().setAttribute(PERMS_IN_SESSION, perms);
    }

    public static List<String> getCurrentPerms() {
        return (List<String>) getSession().getAttribute(PERMS_IN_SESSION);
    }
}
