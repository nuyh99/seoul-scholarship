alter table scholarship.member
    add column is_done boolean default false;

alter table scholarship.member
    add column total_recommended_scholarship_count int default 0;
