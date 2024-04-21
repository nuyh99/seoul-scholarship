create table member
(
    id                  varchar(255) not null primary key,
    birth               date         null,
    enrolled            varchar(255) null,
    income_range        varchar(255) null,
    last_semester_grade double       null default 0,
    major_category      varchar(255) null,
    name                varchar(255) null,
    school_name         varchar(255) null,
    semester            varchar(255) null,
    sex                 varchar(255) null,
    total_grade         double       null default 0
);

create table scholarship
(
    id                              bigint       not null primary key,
    eligibility_restriction_details text         null comment '자격제한',
    end_date                        date         null comment '모집종료일',
    grade                           varchar(255) null comment '학년구분',
    grade_details                   text         null comment '성적기준',
    home_page_url                   varchar(255) null comment '홈페이지 주소',
    income_details                  text         null comment '소득기준',
    local_residency_details         text         null comment '지역거주여부',
    major_category                  varchar(255) null comment '학과구분',
    organization                    varchar(255) null comment '운영기관명',
    organization_type               varchar(255) null comment '운영기관구분',
    product_name                    varchar(255) null comment '상품명',
    recommendation_required_details text         null comment '추천필요여부',
    required_document_details       text         null comment '제출서류',
    scholarship_type                varchar(255) null comment '학자금유형구분',
    selection_count_details         text         null comment '선발인원',
    selection_method_details        text         null comment '선발방법',
    specific_qualification_details  text         null comment '특정자격',
    start_date                      date         null comment '모집시작일',
    support_details                 text         null comment '지원내역',
    university_category             varchar(255) null comment '대학구분',
    view_count                      bigint       null comment '조회수' default 0
);
