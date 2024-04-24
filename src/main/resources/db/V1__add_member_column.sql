alter table member
    add column military_service varchar(100) comment '군필여부';

alter table member
    add column sibling_exists boolean default false comment '형제자매여부';

create table member_detailed_condition
(
    member_id          varchar(255) not null comment '회원 아이디',
    detailed_condition varchar(255) comment '세부 자격'
);

create index IDX_member_detailed_condition
    on member_detailed_condition (member_id);
