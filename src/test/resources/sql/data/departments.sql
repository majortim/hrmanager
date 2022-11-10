CREATE TABLE IF NOT EXISTS departments (
                                             dept_id BIGINT NOT NULL COMMENT '부서 ID',
                                             manager_id BIGINT NULL,
                                             dept_name VARCHAR(100) NOT NULL COMMENT '부서명',
                                             PRIMARY KEY (dept_id)
);

TRUNCATE TABLE departments;

INSERT INTO departments(dept_id, dept_name)
VALUES(1, '경영지원팀');