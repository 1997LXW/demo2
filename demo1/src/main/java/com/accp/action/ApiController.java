package com.accp.action;

import com.accp.biz.DepartmentBiz;
import com.accp.pojo.Department;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ApiController {
    @Resource
    private DepartmentBiz departmentBiz;
    @GetMapping("load")
    public List<Department> load(){
        return departmentBiz.findAll();
    }
    @DeleteMapping("delet/{id}")
    public Map<String,String> delet(@PathVariable Integer id){
        Map<String,String> map=new HashMap<String, String>();
        try {
            Department department=departmentBiz.findDepartment(id);
            departmentBiz.deleteUser(id);
            map.put("code","200");
            map.put("msg","删除成功"+department.getName()+"还剩"+departmentBiz.findCountUser(department.getId())+"人");
        }catch (Exception e){
            map.put("code","500");
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
