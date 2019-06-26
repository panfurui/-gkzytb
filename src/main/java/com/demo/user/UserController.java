package com.demo.user;

import com.demo.config.rest.RestException;
import com.demo.db.user.UserMapper;
import com.demo.user.entity.UserEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.demo.config.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.demo.config.rest.RestErrorEnum.SCHOOL_DUPLICATE_NAME;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = {"用户管理"})
class UserController {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams(@ApiImplicitParam(paramType = "body", name = "user", value = "用户", required = true, dataType = "UserEntity"))
    public RestResponse login(@RequestBody @Valid UserEntity user) {
        UserEntity result = userMapper.selectByUsernamePassword(user.username, user.password);
        return new RestResponse<>(result);
    }

    @ApiOperation(value = "新增用户")
    @PostMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "body", name = "user", value = "用户", required = true, dataType = "UserEntity"))
    public RestResponse createUser(@RequestBody @Valid UserEntity user) {
        UserEntity result = userMapper.selectByUsername(user.username);
        if (result != null) {
            throw new RestException(SCHOOL_DUPLICATE_NAME);
        }
        userMapper.insert(user);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "修改用户")
    @PutMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "body", name = "user", value = "用户", required = true, dataType = "UserEntity"))
    public RestResponse updateUser(@RequestBody @Valid UserEntity user) {
        UserEntity result = userMapper.selectByUsername(user.username);
        if (result != null && user.id.longValue() != result.id.longValue()) {
            throw new RestException(SCHOOL_DUPLICATE_NAME);
        }
        userMapper.updateByPrimaryKey(user);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "query", name = "id", value = "user id", required = true, dataTypeClass = Long.class))
    public RestResponse deleteUser(@RequestParam("id") Long id) {
        userMapper.deleteByPrimaryKey(id);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "获取user信息")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "limit", value = "获取user的数量（对应一页显示多少行）", required = true, defaultValue = "10", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "offset", value = "获取user的偏移量（对应第几页）", required = true, defaultValue = "1", dataTypeClass = Integer.class)
    })
    @ResponseBody
    RestResponse<PageInfo<UserEntity>> find(@RequestParam(defaultValue = "10") Integer limit,
                                      @RequestParam(defaultValue = "1") Integer offset) {
        PageHelper.offsetPage(offset, limit);
        Page<UserEntity> page = (Page<UserEntity>) userMapper.selectAll();
        PageInfo<UserEntity> pageInfo = page.toPageInfo();
        return new RestResponse<>(pageInfo);
    }


    @ApiOperation(value = "获取user信息")
    @RequestMapping(method = RequestMethod.GET, value = "/notadmin")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "limit", value = "获取user的数量（对应一页显示多少行）", required = true, defaultValue = "10", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "offset", value = "获取user的偏移量（对应第几页）", required = true, defaultValue = "1", dataTypeClass = Integer.class)
    })
    @ResponseBody
    RestResponse<PageInfo<UserEntity>> findNotAdmin(@RequestParam(defaultValue = "10") Integer limit,
                                            @RequestParam(defaultValue = "1") Integer offset) {
        PageHelper.offsetPage(offset, limit);
        Page<UserEntity> page = (Page<UserEntity>) userMapper.selectAllNotAdmin();
        PageInfo<UserEntity> pageInfo = page.toPageInfo();
        return new RestResponse<>(pageInfo);
    }
}