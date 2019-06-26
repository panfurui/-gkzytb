package com.demo.area;

import com.demo.area.entity.LineHistory;
import com.demo.config.rest.RestException;
import com.demo.config.rest.RestResponse;
import com.demo.db.area.LineHistoryMapper;
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

import static com.demo.config.rest.RestErrorEnum.*;

@Slf4j
@RestController
@RequestMapping("/line")
@Api(tags = {"分数线管理"})
class LineHistoryController {

    @Autowired
    LineHistoryMapper lineHistoryMapper;

    @ApiOperation(value = "新增分数线")
    @PostMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "body", name = "lineHistory", value = "分数线", required = true, dataType = "LineHistory"))
    public RestResponse createUser(@RequestBody @Valid LineHistory lineHistory) {
        LineHistory result = lineHistoryMapper.selectByKey(lineHistory.area, lineHistory.year);
        if (result != null) {
            result.line1 = lineHistory.line1;
            result.line2 = lineHistory.line2;
            result.line3 = lineHistory.line3;
            result.line4 = lineHistory.line4;
            result.line5 = lineHistory.line5;
            result.line6 = lineHistory.line6;
            lineHistoryMapper.updateByPrimaryKeySelective(result);
        } else {
            lineHistoryMapper.insert(lineHistory);
        }
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "删除分数线")
    @DeleteMapping
    @ApiImplicitParams(@ApiImplicitParam(paramType = "query", name = "id", value = "line id", required = true, dataTypeClass = Long.class))
    public RestResponse deleteLine(@RequestParam("id") Long id) {
        lineHistoryMapper.deleteByPrimaryKey(id);
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "获取分数线")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "limit", value = "数量（对应一页显示多少行）", required = true, defaultValue = "10", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "offset", value = "偏移量（对应第几页）", required = true, defaultValue = "1", dataTypeClass = Integer.class)
    })
    @ResponseBody
    RestResponse<PageInfo<LineHistory>> find(@RequestParam(defaultValue = "10") Integer limit,
                                             @RequestParam(defaultValue = "1") Integer offset) {
        PageHelper.offsetPage(offset, limit);
        Page<LineHistory> page = (Page<LineHistory>) lineHistoryMapper.selectAll();
        PageInfo<LineHistory> pageInfo = page.toPageInfo();
        return new RestResponse<>(pageInfo);
    }


    @ApiOperation(value = "获取某个分数线")
    @RequestMapping(method = RequestMethod.GET, value = "queryByYearArea")
    @ResponseBody
    RestResponse<Integer> find(@RequestParam("year") Integer year,
                                   @RequestParam("score") Integer score,
                                             @RequestParam("area") String area) {
        LineHistory query = new LineHistory();
        query.area = area;
        query.year = year;
        LineHistory lineHistory = lineHistoryMapper.selectOne(query);
        if (lineHistory == null) {
            throw new RestException(ERROR_LINE);
        }
        query.year = 2019;
        LineHistory lineHistory1 = lineHistoryMapper.selectOne(query);
        if (lineHistory == null) {
            throw new RestException(ERROR_LINE1);
        }
        Integer result = lineHistory.line1 + score - lineHistory1.line1;
        return new RestResponse<>(result);
    }

}