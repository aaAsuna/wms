package org.ricardo.wms.util;

import java.lang.reflect.Method;

public class PermissionUtil {
    public static String buildExpreesion(Method m){
        String className = m.getDeclaringClass().getName();
        return className + ":" + m.getName();
    }
}
