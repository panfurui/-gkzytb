package com.demo.messageboard;

import com.demo.config.rest.RestResponse;
import com.demo.db.messageboard.MessageBoardMapper;
import com.demo.db.user.UserMapper;
import com.demo.messageboard.entity.MessageBoardEntity;
import com.demo.user.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/messageboard")
@Api(tags = {"留言板管理"})
class MessageBoardController {

    @Value("${server.port}")
    String port;
    @Value("${server.ip}")
    String ip;
    @Value("${file.path}")
    String rootPath;
    @Autowired
    UserMapper userMapper;
    @Autowired
    MessageBoardMapper messageBoardMapper;

    /**
     * @param entity
     * @return
     */
    @ApiOperation(value = "新增留言")
    @PostMapping
    public RestResponse createArticle(MessageBoardEntity entity) throws IOException {
        MultipartFile file = entity.file;
        if (!file.isEmpty()) {
            String oldFileName = file.getOriginalFilename();
            String path = rootPath+"/messageboard";
            String randomStr = UUID.randomUUID().toString();
            String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            File temp = new File(path, newFileName);
            if (!temp.getParentFile().exists()) {
                temp.getParentFile().mkdirs();
            }
            file.transferTo(temp);
            entity.img = "messageboard/"+newFileName;
        }
        entity.createTime = new Date();
        int row = messageBoardMapper.insertSelective(entity);
        if (row == 0) {
            return new RestResponse<>(false);
        }
        return new RestResponse<>(true);
    }

    @ApiOperation(value = "获取留言板信息，后续考虑适用websocket，去掉这个接口")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    RestResponse<List<MessageBoardEntity>> find() {
        List<MessageBoardEntity> list = messageBoardMapper.selectAllByTime();
//        InetAddress addr = null;
//        String ip = "";
//        try {
//            addr = InetAddress.getLocalHost();
//            ip = addr.getHostAddress();
//        } catch (UnknownHostException e) {
//        }
//        String finalIp = ip;
        list.forEach(obj -> {
            UserEntity owner = userMapper.selectByPrimaryKey(obj.userId);
            if (owner != null) {
                obj.userName = owner.name;
            } else {
                obj.userName = "匿名";
            }

            obj.img = "http://" + ip + ":" + port +"/show?name=" + obj.img;
        });
        return new RestResponse<>(list);
    }
}