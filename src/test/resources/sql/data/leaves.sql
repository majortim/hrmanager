CREATE TABLE IF NOT EXISTS leaves (
                                        leave_id BIGINT NOT NULL COMMENT '생성된 휴가/휴직 ID',
                                        parent_id BIGINT NULL COMMENT '부모 휴가/휴직 ID',
                                        emp_id BIGINT NOT NULL COMMENT '사원 ID',
                                        leave_ty VARCHAR(90) NOT NULL COMMENT '생성된 휴가/휴직 유형',
                                        leave_cnt BIGINT NOT NULL COMMENT '생성된 휴가/휴직 일수',
                                        paid BOOLEAN NOT NULL COMMENT '유급',
                                        marked_as_worked BOOLEAN NOT NULL COMMENT '연차생성시 출근으로 간주',
                                        create_dt DATETIME NULL COMMENT '생성일',
                                        PRIMARY KEY (leave_id)
);

truncate table leaves;

insert into leaves( leave_id
                  , emp_id
                  , leave_ty
                  , leave_cnt
                  , paid
                  , marked_as_worked
                  , create_dt)
values (
        1
        , 1
        , 'PERSONAL'
        , 1
        , true
        , true
        , '2022-07-01 10:00:00'
       );
