create table applied_scholarship
(
    id             bigint auto_increment primary key,
    member_id      varchar(255) not null,
    scholarship_id bigint       not null,
    status         varchar(30)  not null,
    index idx_applied_scholarship_1 (member_id),
    index idx_applied_scholarship_2 (scholarship_id),
    index idx_applied_scholarship_3 (scholarship_id, member_id)
);

create table stored_scholarship
(
    id             bigint auto_increment primary key,
    member_id      varchar(255) not null,
    scholarship_id bigint       not null,
    index idx_stored_scholarship_1 (member_id),
    index idx_stored_scholarship_2 (scholarship_id),
    index idx_stored_scholarship_3 (scholarship_id, member_id)
);
