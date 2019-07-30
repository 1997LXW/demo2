package com.accp.biz;

import com.accp.dao.DetailsDao;
import com.accp.dao.UserDao;
import com.accp.pojo.User;
import com.accp.pojo.UserExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PunishmentBiz {
    @Resource
    private DetailsDao detailsDao;
    @Resource
    private UserDao userDao;
    public User login(String name,String psw){
        User user=new User();
        try {
            UserExample example=new UserExample();
            example.createCriteria().andNameEqualTo(name);
            example.createCriteria().andPswEqualTo(psw);
            user= userDao.selectByExample(example).get(0);
        }catch (Exception e){
            throw new RuntimeException("登录失败！");
        }
        return user;
    }
    public PageInfo<User> pageInfoUser(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(userDao.selectByExample(null));
        return pageInfo;
    }

}
