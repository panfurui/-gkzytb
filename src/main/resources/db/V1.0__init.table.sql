
CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `address` varchar(64)  DEFAULT NULL COMMENT '地址',
  `phone` varchar(64) DEFAULT NULL COMMENT '联系方式',
  `role` int(4) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_school` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '学校名',
  `area` varchar(64)  DEFAULT NULL COMMENT '地区，各个省',
  `address` varchar(64)  DEFAULT NULL COMMENT '详细地址',
  `phone` varchar(64)  DEFAULT NULL COMMENT '咨询方式',
  `description` text DEFAULT NULL COMMENT '学校描述',
  `is211` int(4)  DEFAULT NULL COMMENT '是否211',
  `is985` int(4)  DEFAULT NULL COMMENT '是否985',
  `bachelor_count` int(4)  DEFAULT NULL COMMENT '本科学科点',
  `master_count` int(4)  DEFAULT NULL COMMENT '硕士学科点',
  `doctor_count` int(4)  DEFAULT NULL COMMENT '博士学科点',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_major` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` bigint(20) unsigned NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '专业名',
  `subject_category` varchar(64)  DEFAULT NULL COMMENT '科目类别',
  `degree_category` varchar(64)  DEFAULT NULL COMMENT '学位类别',
  `line_type` varchar(64)  DEFAULT NULL COMMENT '所属批次',
  `description` text DEFAULT NULL COMMENT '专业描述',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_major_history` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` bigint(20) unsigned NOT NULL,
  `school_name` varchar(64) DEFAULT NULL COMMENT '学校名',
  `area` varchar(64)  DEFAULT NULL COMMENT '地区，各个省',
  `major_id` bigint(20) unsigned NOT NULL COMMENT '专业id',
  `major_name` varchar(64) DEFAULT NULL COMMENT '专业名',
  `subject_category` varchar(64)  DEFAULT NULL COMMENT '科目类别',
  `line_type` varchar(64)  DEFAULT NULL COMMENT '所属批次',
  `year` int(4)  DEFAULT NULL COMMENT '年份',
  `count` int(4)  DEFAULT NULL COMMENT '录取人数',
  `highest` int(4)  DEFAULT NULL COMMENT '最高分',
  `lowest` int(4)  DEFAULT NULL COMMENT '最低分',
  `average` int(4)  DEFAULT NULL COMMENT '平均分',
  `rate` int(4)  DEFAULT NULL COMMENT '就业率',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_line_history` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `area` varchar(64)  DEFAULT NULL COMMENT '地区，各个省',
  `year` int(4)  DEFAULT NULL COMMENT '年份',
  `line1` int(4)  DEFAULT NULL COMMENT '理科第一批分数线',
  `line2` int(4)  DEFAULT NULL COMMENT '理科第二批分数线',
  `line3` int(4)  DEFAULT NULL COMMENT '理科第三批分数线',
  `line4` int(4)  DEFAULT NULL COMMENT '文科第一批分数线',
  `line5` int(4)  DEFAULT NULL COMMENT '文科第二批分数线',
  `line6` int(4)  DEFAULT NULL COMMENT '文科第三批分数线',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_favourite_major` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` bigint(20) unsigned NOT NULL,
  `school_name` varchar(64) DEFAULT NULL COMMENT '学校名',
  `major_id` bigint(20) unsigned NOT NULL COMMENT '专业id',
  `major_name` varchar(64) DEFAULT NULL COMMENT '专业名',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `name` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_apply` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` bigint(20) unsigned NOT NULL,
  `school_name` varchar(64) DEFAULT NULL COMMENT '学校名',
  `major_id` bigint(20) unsigned NOT NULL COMMENT '专业id',
  `major_name` varchar(64) DEFAULT NULL COMMENT '专业名',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `name` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `order` int(4)  DEFAULT NULL COMMENT '志愿排名',
  `can_adjust` int(4)  DEFAULT NULL COMMENT '是否调剂',
  `status` int(4)  DEFAULT NULL COMMENT '填报状态，0-未完成，1-已完成',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_message_board` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL,
  `img` varchar(128) DEFAULT NULL COMMENT '路径',
  `description` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
