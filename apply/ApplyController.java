package com.demo.apply;

import com.demo.apply.entity.Apply;
import com.demo.config.rest.RestException;
import com.demo.config.rest.RestResponse;
import com.demo.db.apply.ApplyMapper;
import com.demo.db.school.SchoolMapper;
import com.demo.school.entity.School;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.demo.config.rest.RestErrorEnum.DUPLICATE_APPLY_MAJOR;

@Slf4j
@RestController
@RequestMapping("/apply")
@Api(tags = {"志愿管理"})
class ApplyController {

    @Autowired
    ApplyMapper applyMapper;

    @Autowired
    SchoolMapper schoolMapper;

    @ApiOperation(value = "新增志愿 (报考两部曲)")
    @PostMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "body", name = "apply", value = "志愿请求", required = true, dataType = "Apply"))
    public RestResponse createUser(@RequestBody @Valid Apply apply) {
        Apply result = applyMapper.selectByKey(apply.schoolId, apply.majorId, apply.userId);
        if (result != null) {
            throw new RestException(DUPLICATE_APPLY_MAJOR);
        }
//        int count = applyMapper.countByUserId(apply.userId);
//        apply.number = count + 1;
//        apply.number = apply.order;
        applyMapper.insert(apply);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "删除申请")
    @DeleteMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "query", name = "id", value = "apply id", required = true, dataTypeClass = Long.class))
    public RestResponse deleteApply(@RequestParam("id") Long id) {
        applyMapper.deleteByPrimaryKey(id);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "获取school信息 (高校信息模块)")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "limit", value = "数量（对应一页显示多少行）", required = true, defaultValue = "10", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "offset", value = "偏移量（对应第几页）", required = true, defaultValue = "1", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataTypeClass = Long.class)
    })
    @ResponseBody
    RestResponse<List<Apply>> find(@RequestParam(defaultValue = "10") Integer limit,
                                       @RequestParam(defaultValue = "1") Integer offset,
                                   @RequestParam("userId") Long userId) {
        Apply query = new Apply();
        query.userId = userId;
        List<Apply> select = applyMapper.select(query);
        return new RestResponse<>(select);
    }

}