package org.ricardo.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemMenu extends BaseDomain {
    private String name; // 当前菜单的名称
    private String url; // 点击该菜单访问的路径
    private String sn; //用于查询sn对应的子菜单
    private SystemMenu parent; //父菜单
}
