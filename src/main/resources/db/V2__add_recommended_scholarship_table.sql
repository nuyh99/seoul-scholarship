create table recommended_scholarship
(
    id             bigint auto_increment primary key,
    member_id      varchar(255) not null,
    scholarship_id bigint       not null,
    index idx_recommended_scholarship_1 (member_id, scholarship_id)
);
