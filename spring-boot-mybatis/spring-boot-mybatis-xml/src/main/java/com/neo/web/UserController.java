package com.neo.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neo.mapper.UserMapper;
import com.neo.model.User;
import com.neo.util.PageRequest;
import com.neo.util.PageResult;
import com.neo.util.PageUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 无分页查询
     *
     * @param
     */
    @ApiOperation(value = "无分页查询")
    @GetMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = userMapper.getAll();
        return users;
    }

    /**
     * 单条记录查询
     *
     * @param id
     */
    @ApiOperation(value = "单条记录查询")
    @GetMapping("/getUser")
    public User getUser(Long id) {
        User user = userMapper.getOne(id);
        return user;
    }

    /**
     * 新增
     *
     * @param user
     */
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public void save(User user) {
        userMapper.insert(user);
    }

    /**
     * 更新
     *
     * @param user
     */
    @ApiOperation(value = "更新")
    @PutMapping(value = "update")
    public void update(User user) {
        userMapper.update(user);
    }

    /**
     * 删除
     *
     * @param id
     */
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userMapper.delete(id);
    }

    /**
     * 分页
     *
     * @param pageRequest
     * @return
     */
    @ApiOperation(value = "分页")
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