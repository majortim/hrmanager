CREATE TABLE IF NOT EXISTS tna
(
    tna_id   BIGINT      NOT NULL COMMENT '근무 기록 ID',
    emp_id   BIGINT      NOT NULL COMMENT '사원 ID',
    base_year INTEGER NOT NULL COMMENT '기준연도',
    tna_ty   VARCHAR(90) NOT NULL COMMENT '근무기록 유형',
    leave_ty  VARCHAR(90) NULL COMMENT '휴가/휴직 유형',
    start_dt DATETIME    NULL COMMENT '시작일시',
    end_dt   DATETIME    NULL COMMENT '종료일시',
    PRIMARY KEY (tna_id)
);

TRUNCATE TABLE tna;

INSERT INTO tna( tna_id
               , emp_id
               , base_year
               , tna_ty
               , leave_ty
               , start_dt
               , end_dt)
VALUES ( 1
       , 1
       , 2022
       , 'LEAVE_OF_ABSENCE'
       , 'PERSONAL'
       , '2022-07-01 00:00:00'
       , '2022-07-22 23:59:59');

INSERT INTO tna( tna_id
               , emp_id
               , base_year
               , tna_ty
               , start_dt
               , end_dt)
VALUES ( 2
       , 1
       , 2022
       , 'ABSENCE_WITHOUT_LEAVE'
       , '2022-09-13 00:00:00'
       , '2022-09-14 23:59:59');