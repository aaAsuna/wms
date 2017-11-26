package org.ricardo.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class Employee extends BaseDomain {
    private String name;

    private String password;

    private String email;

    private Integer age;

    private Boolean admin = false;

    private Long deptId;

    private Department dept;

    //many2many
    private List<Role> roles = new ArrayList<>();

    public String getRoleNames() {
        if(admin != null && admin) {
            return "[超级管理员]";
        }
        if(roles.size() == 0) {
            return "[未分配角色]";
        }

        StringBuilder sb = new StringBuilder(80);
        sb.append("[");
        for (Role role : roles) {
            sb.append(role.getName()).append(",");
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }
}