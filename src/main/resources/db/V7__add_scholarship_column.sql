ALTER TABLE scholarship
    ADD COLUMN formatted_last_semester_grade  FLOAT COMMENT '정제된 직전 학기 성적 기준',
    ADD COLUMN formatted_total_semester_grade FLOAT COMMENT '정제된 전체 학기 성적 기준',
    ADD COLUMN formatted_income               INT COMMENT '정제된 소득 구간 기준',
    ADD COLUMN formatted_support_details      VARCHAR(100) COMMENT '정제된 지원 내역',
    ADD COLUMN effort_level                   INT COMMENT '노력지수 레벨',
    ADD COLUMN effort_label                   VARCHAR(10) COMMENT '노력지수 레이블 상 중 하';

ALTER TABLE scholarship.member
    ADD COLUMN grade_standard FLOAT COMMENT '학교 최대 성적 기준',
    CHANGE COLUMN income_range income_range INT COMMENT '소득 구간';
