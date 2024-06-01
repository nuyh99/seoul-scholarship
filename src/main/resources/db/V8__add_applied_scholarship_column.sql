ALTER TABLE scholarship.applied_scholarship
    ADD COLUMN supported_amount INT(11) DEFAULT 0 COMMENT '지원 받은 금액';

update scholarship.applied_scholarship
set supported_amount = 0;
