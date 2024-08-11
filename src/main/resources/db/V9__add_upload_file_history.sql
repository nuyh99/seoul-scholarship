create table upload_file_history
(
    id           bigint primary key,
    file_name    varchar(255) not null comment '파일명',
    created_date datetime(6)  not null comment '생성일',
    status       varchar(50)  not null comment '처리 상태'
);
