alter table applied_scholarship
    add column created_at datetime(6) not null default current_timestamp(6);

alter table applied_scholarship
    add column updated_at datetime(6) not null default current_timestamp(6);

alter table stored_scholarship
    add column created_at datetime(6) not null default current_timestamp(6);

alter table stored_scholarship
    add column updated_at datetime(6) not null default current_timestamp(6);

alter table scholarship
    add index idx_scholarship_1 (start_date);
