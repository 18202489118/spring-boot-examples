package com.neo.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neo.mapper.UserMapper;
import com.neo.model.User;
import com.neo.util.PageRequest;
import com.neo.util.PageResult;
import com.neo.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = userMapper.getAll();
        return users;
    }

    @GetMapping("/getUser")
    public User getUser(Long id) {
        User user = userMapper.getOne(id);
        return user;
    }

    @PostMapping("/add")
    public void save(User user) {
        userMapper.insert(user);
    }

    @PutMapping(value = "update")
    public void update(User user) {
        userMapper.update(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userMapper.delete(id);
    }

    @GetMapping("/page")
    public PageResult selectPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     *
     * @param pageRequest
     * @return
     */
    private PageInfo<User> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectPage();
        return new PageInfo<User>(userList);
    }


}