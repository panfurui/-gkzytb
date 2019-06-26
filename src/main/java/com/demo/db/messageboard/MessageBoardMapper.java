package com.demo.db.messageboard;

import com.demo.messageboard.entity.MessageBoardEntity;
import com.demo.util.TkMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MessageBoardMapper extends TkMapper<MessageBoardEntity> {

    @Select("select * from t_message_board order by create_time desc")
    List<MessageBoardEntity> selectAllByTime();
}
