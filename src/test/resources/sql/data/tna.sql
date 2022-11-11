CREATE TABLE IF NOT EXISTS tna
(
    tna_id   BIGINT      NOT NULL COMMENT '근무 기록 ID',
    emp_id   BIGINT      NOT NULL COMMENT '사원 ID',
    leave_id BIGINT      NULL COMMENT '휴가/휴직 ID',
    tna_ty   VARCHAR(90) NOT NULL COMMENT '근무기록 유형',
    start_dt DATETIME    NULL COMMENT '시작일시',
    end_dt   DATETIME    NULL COMMENT '종료일시',
    PRIMARY KEY (tna_id)
);

TRUNCATE TABLE tna;

INSERT INTO tna( tna_id
               , emp_id
               , leave_id
               , tna_ty
               , start_dt
               , end_dt)
VALUES ( 1
       , 1
       , 1
       , 'LEAVE_OF_ABSENCE'
       , '2022-07-01 10:00:00'
       , '2022-07-22 10:00:00');