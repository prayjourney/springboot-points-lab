CREATE TABLE `file_chunk_record` (
                                     `id`            bigint(20)   NOT NULL AUTO_INCREMENT COMMENT 'id',
                                     `md5`           varchar(255) NOT NULL COMMENT '文件md5值',
                                     `chunk`         int(11)      NOT NULL COMMENT '分片值',
                                     `upload_status` tinyint(4)   NOT NULL COMMENT '存储状态, 0-error, 1-ok',
                                     PRIMARY KEY (`id`) USING BTREE,
                                     UNIQUE KEY `uni_md5_chunk` (`md5`, `chunk`) USING BTREE COMMENT 'md5和chunk唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


CREATE TABLE `file_record` (
                               `id`            bigint(20)   NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `file_name`     varchar(255) NOT NULL COMMENT '文件名称 ',
                               `file_md5`      varchar(255) NOT NULL DEFAULT '' COMMENT '文件md5',
                               `file_path`     varchar(255) NOT NULL COMMENT '文件存放路径',
                               `file_size`     varchar(128)          DEFAULT NULL COMMENT '文件大小',
                               `upload_status` tinyint(4)            DEFAULT 0 COMMENT '文件上传状态',
                               `create_time`   datetime              DEFAULT current_timestamp() COMMENT '创建时间',
                               `update_time`   datetime              DEFAULT current_timestamp() COMMENT '更新时间',
                               PRIMARY KEY (`id`),
                               KEY `idx_md5` (`file_md5`) USING BTREE COMMENT '文件md5值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;