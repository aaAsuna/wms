package org.ricardo.wms.service.impl;

import org.ricardo.wms.domain.Department;
import org.ricardo.wms.domain.Employee;
import org.ricardo.wms.mapper.EmployeeMapper;
import org.ricardo.wms.mapper.PermissionMapper;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IEmployeeService;
import org.ricardo.wms.util.MD5;
import org.ricardo.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public void delete(Long id) {
        //先删除关系
        employeeMapper.deleteRelation(id);
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(Employee record, Long[] ids) {
        record.setPassword(MD5.encode(record.getPassword()));
        employeeMapper.insert(record);
        //保存新的关系
        if(ids!=null){
            for (Long id : ids) {
                employeeMapper.saveRelation(record.getId(),id);
            }
        }
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> list() {
        return employeeMapper.selectAll();
    }

    @Override
    public void update(Employee record, Long[] ids) {
        //先删除关系
        employeeMapper.deleteRelation(record.getId());
        employeeMapper.updateByPrimaryKey(record);
        //保存新的关系
        if(ids!=null){
            for (Long id : ids) {
                employeeMapper.saveRelation(record.getId(),id);
            }
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows = employeeMapper.queryForCount(qo);
        if(rows==0){
            return PageResult.EMPTY_PAGE;
        }
        List<Department> data = employeeMapper.queryForList(qo);
        return new PageResult(data,rows,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public void batchDelete(Long[] ids) {
            employeeMapper.batchDelete(ids);
    }

    @Override
    public void login(String username, String password) {
        //1.根据用户名和密码查询员工
        Employee emp = employeeMapper.selectByUsernameAndPassword(username,MD5.encode(password));
        //2.如果为空,抛出异常提示用户名或密码错误
        if (emp == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        //存入session
        UserContext.setCurrentUser(emp);
        //3.将当前用户保存到session
        //4.判断如果不是admin查询出所有的权限
        if(!emp.getAdmin()) {
            //5.将该用户的权限存入session中
            UserContext.setCurrentPerms(permissionMapper.selectAllExpressionByEmployeeId(emp.getId()));
        }
    }
}
