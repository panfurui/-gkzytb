package com.demo.major;

import com.alibaba.fastjson.JSONObject;
import com.demo.config.rest.RestException;
import com.demo.config.rest.RestResponse;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.demo.config.rest.RestErrorEnum.*;

@Slf4j
@RestController
@RequestMapping("/major")
@Api(tags = {"专业管理"})
class MajorController {

    @Autowired
    MajorMapper majorMapper;
    @Autowired
    MajorHistoryMapper majorHistoryMapper;
    @Autowired
    FavouriteMajorMapper favouriteMajorMapper;
    @Autowired
    SchoolMapper schoolMapper;

    @ApiOperation(value = "新增专业")
    @PostMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "body", name = "major", value = "专业", required = true, dataType = "Major"))
    public RestResponse createUser(@RequestBody @Valid Major major) {
        Major result = majorMapper.selectByName(major.name, major.schoolId, major.subjectCategory, major.degreeCategory);
        if (result != null) {
            throw new RestException(DUPLICATE_SCHOOL_MAJOR);
        }
        majorMapper.insert(major);
        return new RestResponse<>(true);
    }


    @ApiOperation(value = "新增/修改专业分数线")
    @PostMapping("history")
    @ApiImplicitParams(@ApiImplicitParam(paramType = "body", name = "majorHistory", value = "专业", required = true, dataType = "MajorHistory"))
    public RestResponse createMajorHistory(@RequestBody @Valid MajorHistory majorHistory) {
        MajorHistory query = new MajorHistory();
        query.majorId = majorHistory.majorId;
        query.year = majorHistory.year;
        MajorHistory history = majorHistoryMapper.selectOne(query);
        if (history != null) {
            query.count = majorHistory.count;
            query.highest = majorHistory.highest;
            query.lowest = majorHistory.lowest;
            query.average = majorHistory.average;
            query.rate = majorHistory.rate;
            majorHistoryMapper.updateByPrimaryKey(query);
        } else {
            majorHistoryMapper.insertSelective(majorHistory);
        }
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "修改专业")
    @PutMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "body", name = "major", value = "专业", required = true, dataType = "Major"))
    public RestResponse updateUser(@RequestBody @Valid Major major) {
        Major result = majorMapper.selectByName(major.name, major.schoolId, major.subjectCategory, major.degreeCategory);
        if (result != null && major.id.longValue() != result.id.longValue()) {
            throw new RestException(DUPLICATE_SCHOOL_MAJOR);
        }
        majorMapper.updateByPrimaryKey(major);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "删除专业")
    @DeleteMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "query", name = "id", value = "major id", required = true, dataTypeClass = Long.class))
    public RestResponse deleteMajor(@RequestParam("id") Long id) {
        majorMapper.deleteByPrimaryKey(id);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "删除喜欢的专业")
    @DeleteMapping("/favourite")
    @ApiImplicitParams(@ApiImplicitParam(paramType = "query", name = "id", value = "id", required = true, dataTypeClass = Long.class))
    public RestResponse deleteFavouriteMajor(@RequestParam("id") Long id) {
        favouriteMajorMapper.deleteByPrimaryKey(id);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "某学校各专业历史数据 (高校信息模块)")
    @RequestMapping(method = RequestMethod.GET, value = "/getMajorHistoryBySchoolId")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "schoolId", value = "学校id", required = true, dataTypeClass = Long.class)
    })
    @ResponseBody
    RestResponse<?> getMajorHistoryBySchoolId(@RequestParam("schoolId") Long schoolId) {
        List<MajorHistory> majorHistories = majorHistoryMapper.selectBySchoolId(schoolId);
        Map<Long, JSONObject> majors = new HashMap<>();
        majorHistories.forEach(obj -> {
            JSONObject major = majors.getOrDefault(obj.majorId, new JSONObject());
            major.put("majorId", obj.majorId);
            major.put("majorName", obj.majorName);
            major.put(obj.year + "count", obj.count);
            major.put(obj.year + "highest", obj.highest);
            major.put(obj.year + "lowest", obj.lowest);
            major.put(obj.year + "average", obj.average);
            majors.put(obj.majorId, major);
        });

        return new RestResponse<>(majors.values());
    }

    @ApiOperation(value = "新增感兴趣专业 (高校信息模块,报考预测)")
    @PostMapping("/addFavouriteMajor")
    @ApiImplicitParams(@ApiImplicitParam(paramType = "body", name = "major", value = "专业", required = true, dataType = "FavouriteMajor"))
    public RestResponse addFavouriteMajor(@RequestBody @Valid FavouriteMajor major) {
        FavouriteMajor favouriteMajor = favouriteMajorMapper.selectByKey(major.schoolId, major.majorId, major.userId);
        if (favouriteMajor != null) {
            throw new RestException(DUPLICATE_FAVOURITE_MAJOR);
        }
        major.id = null;
        favouriteMajorMapper.insert(major);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "获取已选的感兴趣专业 (高校信息模块)")
    @GetMapping("/getFavouriteMajor")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataTypeClass = Long.class)
    })
    @ResponseBody
    RestResponse<?> getFavouriteMajor(@RequestParam("userId") Long userId) {
        List<FavouriteMajor> favouriteMajors = favouriteMajorMapper.selectByUserId(userId);
        favouriteMajors.forEach(obj -> {
            School school = schoolMapper.selectByPrimaryKey(obj.schoolId);
            obj.school = school;
        });
        return new RestResponse<>(favouriteMajors);
    }

    @ApiOperation(value = "某省某专业符合学生分数的相关信息")
    @RequestMapping(method = RequestMethod.GET, value = "/getMajorHistoryByScore")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "area", value = "地区", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "majorName", value = "专业名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "year", value = "年份", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "score", value = "学生分数", required = true, dataTypeClass = Integer.class)
    })
    @ResponseBody
    RestResponse<?> getMajorHistoryByScore(@RequestParam("area") String area,
                                         @RequestParam("majorName") String majorName,
                                         @RequestParam("year") Integer year,
                                         @RequestParam("score") Integer score) {
        List<MajorHistory> majorHistories = majorHistoryMapper.selectByKey(area, majorName,year, score);
        return new RestResponse<>(majorHistories);
    }

    @ApiOperation(value = "专业信息")
    @RequestMapping(method = RequestMethod.GET, value = "/getMajorByKey")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "keyword", value = "关键字", required = true, dataTypeClass = Integer.class)
    })
    @ResponseBody
    RestResponse<?> getMajorByKey(@RequestParam("keyword") String keyword) {
        List<Major> majorHistories = majorMapper.selectByKey(keyword);
        return new RestResponse<>(majorHistories);
    }


    @ApiOperation(value = "某科类某年的所有学校专业 (报考两部曲)")
    @RequestMapping(method = RequestMethod.GET, value = "/getMajorHistoryByKey")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "limit", value = "获取school的数量（对应一页显示多少行）", required = true, defaultValue = "10", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "offset", value = "获取school的偏移量（对应第几页）", required = true, defaultValue = "1", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "subjectCategory", value = "科类", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "year", value = "年份", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "keyword", value = "输入拟报考地区、院校及专业", required = false, dataTypeClass = String.class)
    })
    @ResponseBody
    RestResponse<PageInfo<MajorHistory>> getMajorHistoryByKey(
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "1") Integer offset,
            @RequestParam("subjectCategory") String subjectCategory,
            @RequestParam("year") Integer year,
            @RequestParam("keyword") String keyword) {

        PageHelper.offsetPage(offset, limit);
        Page<MajorHistory> page = (Page<MajorHistory>) majorHistoryMapper.selectByKeyword(subjectCategory, year, keyword);
        majorMapper.selectAll();
        PageInfo<MajorHistory> pageInfo = page.toPageInfo();
        return new RestResponse<>(pageInfo);
    }


    @ApiOperation(value = "获取信息")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "limit", value = "数量（对应一页显示多少行）", required = true, defaultValue = "10", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "offset", value = "偏移量（对应第几页）", required = true, defaultValue = "1", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "schoolId", value = "学校id", required = true, dataTypeClass = Long.class)
    })
    @ResponseBody
    RestResponse<PageInfo<Major>> find(@RequestParam(defaultValue = "10") Integer limit,
                                       @RequestParam(defaultValue = "1") Integer offset,
                                       @RequestParam Long schoolId) {
        PageHelper.offsetPage(offset, limit);
        List<Major> majors = majorMapper.selectAllBySchoolId(schoolId);
        majors.forEach(obj -> {
            MajorHistory query = new MajorHistory();
            query.majorId = obj.id;
            query.year = 2018;
            MajorHistory history2018 = majorHistoryMapper.selectOne(query);
            obj.history2018 = history2018;

            query.year = 2017;
            MajorHistory history2017 = majorHistoryMapper.selectOne(query);
            obj.history2017 = history2017;

            query.year = 2016;
            MajorHistory history2016 = majorHistoryMapper.selectOne(query);
            obj.history2016 = history2016;
        });
        Page<Major> page = (Page<Major>) majors;
        PageInfo<Major> pageInfo = page.toPageInfo();
        return new RestResponse<>(pageInfo);
    }

    @ApiOperation(value = "图表")
    @GetMapping("/rate")
    @ResponseBody
    RestResponse<List<JSONObject>> countNeedHelpArea(@RequestParam Long majorId,
                                                     @RequestParam Long schoolId) {
        List<MajorHistory> orders = majorHistoryMapper.selectBySchoolIdMajorId(schoolId, majorId);
        List<JSONObject> object = new ArrayList<>();
        orders.forEach(order -> {
            JSONObject json = new JSONObject();
            json.put("year", order.year);
            json.put("count", order.count);
            object.add(json);
        });
        return new RestResponse<>(object);
    }
}