package com.accp.biz;

import com.accp.dao.DepartmentDao;
import com.accp.dao.UserDao;
import com.accp.pojo.Department;
import com.accp.pojo.DepartmentExample;
import com.accp.pojo.User;
import com.accp.pojo.UserExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentBiz {
    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private UserDao userDao;
    public List<Department>  findAll(){
        return departmentDao.selectByExample(null);
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    public void deleteUser(Integer id){
        try {
            userDao.deleteByPrimaryKey(id);
        }catch (Exception e){
            throw new RuntimeException("删除失败！");
        }
    }
    public Long findCountUser(Integer dempId){
        UserExample example=new UserExample();
        example.createCriteria().andDepartmentIdEqualTo(dempId);
        return userDao.countByExample(example);
    }
    public Department findDepartment(Integer id){
        UserExample example=new UserExample();
        example.createCriteria().andIdEqualTo(id);
        User user=userDao.selectByExample(example).get(0);
        DepartmentExample departmentExample=new DepartmentExample();
        departmentExample.createCriteria().andIdEqualTo(user.getDepartmentId());
        return departmentDao.selectByExample(departmentExample).get(0);
    }
}
