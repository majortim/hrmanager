CREATE TABLE IF NOT EXISTS employees (
                                           emp_id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY COMMENT '사원 ID' ,
                                           username VARCHAR(50) COMMENT '계정',
                                           dept_id BIGINT NULL COMMENT '사원 부서 ID',
                                           job_id BIGINT NULL COMMENT '직무 ID',
                                           emp_name VARCHAR(100) NOT NULL COMMENT '사원 이름',
                                           email VARCHAR(254) NULL COMMENT '전자우편',
                                           phone_number VARCHAR(50) NULL COMMENT '전화번호',
                                           hire_dt DATETIME NULL COMMENT '입사일',
                                           retire_dt DATETIME NULL COMMENT '퇴사일',
                                           PRIMARY KEY (emp_id));

truncate table employees;

insert into employees(emp_name)
values('관리자');