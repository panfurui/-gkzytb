package com.demo.school;

import com.demo.apply.entity.Apply;
import com.demo.config.rest.RestException;
import com.demo.config.rest.RestResponse;
import com.demo.db.apply.ApplyMapper;
import com.demo.db.major.FavouriteMajorMapper;
import com.demo.db.major.MajorHistoryMapper;
import com.demo.db.major.MajorMapper;
import com.demo.db.school.SchoolMapper;
import com.demo.major.entity.FavouriteMajor;
import com.demo.major.entity.Major;
import com.demo.major.entity.MajorHistory;
import com.demo.school.entity.School;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.demo.config.rest.RestErrorEnum.SCHOOL_DUPLICATE_NAME;

@Slf4j
@RestController
@RequestMapping("/school")
@Api(tags = {"学校管理"})
class SchoolController {

    @Value("${server.port}")
    String port;
    @Value("${server.ip}")
    String ip;
    @Value("${file.path}")
    String rootPath;
    @Autowired
    SchoolMapper schoolMapper;
    @Autowired
    MajorMapper majorMapper;
    @Autowired
    MajorHistoryMapper majorHistoryMapper;
    @Autowired
    FavouriteMajorMapper favouriteMajorMapper;
    @Autowired
    ApplyMapper applyMapper;

    @ApiOperation(value = "新增学校")
    @PostMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "body", name = "school", value = "学校", required = true, dataType = "SchoolEntity"))
    public RestResponse createUser(School entity) throws IOException {
        School result = schoolMapper.selectByName(entity.name);
        if (result != null) {
            throw new RestException(SCHOOL_DUPLICATE_NAME);
        }
        MultipartFile file = entity.file;
        if (!file.isEmpty()) {
            String oldFileName = file.getOriginalFilename();
            String path = rootPath+"/school";
            String randomStr = UUID.randomUUID().toString();
            String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            File temp = new File(path, newFileName);
            if (!temp.getParentFile().exists()) {
                temp.getParentFile().mkdirs();
            }
            file.transferTo(temp);
            entity.img = "school/"+newFileName;
        }

        schoolMapper.insert(entity);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "修改学校")
    @PostMapping("update")
    @ApiImplicitParams(@ApiImplicitParam(paramType = "body", name = "school", value = "学校", required = true, dataType = "SchoolEntity"))
    public RestResponse updateUser(School entity) throws IOException {
        School result = schoolMapper.selectByName(entity.name);
        if (result != null && entity.id.longValue() != result.id.longValue()) {
            throw new RestException(SCHOOL_DUPLICATE_NAME);
        }
        MultipartFile file = entity.file;
        if (!file.isEmpty()) {
            String oldFileName = file.getOriginalFilename();
            String path = rootPath+"/school";
            String randomStr = UUID.randomUUID().toString();
            String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            File temp = new File(path, newFileName);
            if (!temp.getParentFile().exists()) {
                temp.getParentFile().mkdirs();
            }
            file.transferTo(temp);
            entity.img = "school/"+newFileName;
        }
        schoolMapper.updateByPrimaryKey(entity);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "删除学校")
    @DeleteMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "query", name = "id", value = "user id", required = true, dataTypeClass = Long.class))
    public RestResponse deleteschool(@RequestParam("id") Long id) {
        schoolMapper.deleteByPrimaryKey(id);
        Apply apply = new Apply();
        apply.schoolId = id;
        applyMapper.delete(apply);
        FavouriteMajor favouriteMajor = new FavouriteMajor();
        favouriteMajor.schoolId = id;
        favouriteMajorMapper.delete(favouriteMajor);
        MajorHistory majorHistory = new MajorHistory();
        majorHistory.schoolId = id;
        majorHistoryMapper.delete(majorHistory);
        Major major = new Major();
        major.schoolId = id;
        majorMapper.delete(major);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "获取school信息 (高校信息模块)")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "limit", value = "获取school的数量（对应一页显示多少行）", required = true, defaultValue = "10", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "offset", value = "获取school的偏移量（对应第几页）", required = true, defaultValue = "1", dataTypeClass = Integer.class)
    })
    @ResponseBody
    RestResponse<PageInfo<School>> find(@RequestParam(defaultValue = "10") Integer limit,
                                        @RequestParam(defaultValue = "1") Integer offset) {
        PageHelper.offsetPage(offset, limit);
        List<School> schools = schoolMapper.selectAll();
        schools.forEach(obj -> {
            obj.img = "http://" + ip + ":" + port +"/show?name=" + obj.img;
        });
        Page<School> page = (Page<School>) schools;
        PageInfo<School> pageInfo = page.toPageInfo();
        return new RestResponse<>(pageInfo);
    }

}