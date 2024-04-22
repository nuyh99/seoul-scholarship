alter table member
    add column military_service varchar(100);

alter table member
    add column sibling_exists boolean default false;

create table member_significant
(
    member_id   varchar(255) not null,
    significant varchar(255)
);

create index IDX_member_significant_1
    on member_significant (member_id);