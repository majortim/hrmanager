-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `jobs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobs` (
                                      `job_id` BIGINT NOT NULL COMMENT '직무 ID',
                                      `job_title` VARCHAR(100) NOT NULL COMMENT '직무',
                                      PRIMARY KEY (`job_id`));


-- -----------------------------------------------------
-- Table `employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employees` (
                                           `emp_id` BIGINT NOT NULL COMMENT '사원 ID',
                                           `username` VARCHAR(50) COMMENT '계정',
                                           `dept_id` BIGINT NULL COMMENT '사원 부서 ID',
                                           `job_id` BIGINT NULL COMMENT '직무 ID',
                                           `emp_name` VARCHAR(100) NOT NULL COMMENT '사원 이름',
                                           `email` VARCHAR(254) NULL COMMENT '전자우편',
                                           `phone_number` VARCHAR(50) NULL COMMENT '전화번호',
                                           `hire_dt` DATETIME NULL COMMENT '입사일',
                                           `retire_dt` DATETIME NULL COMMENT '퇴사일',
                                           PRIMARY KEY (`emp_id`));
-- -----------------------------------------------------
-- Table `employee_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee_status` (
                                                 `es_id` BIGINT NOT NULL COMMENT '사원 상태 ID',
                                                 `emp_id` BIGINT NOT NULL COMMENT '사원 ID',
                                                 `es_ty` VARCHAR(90) COMMENT '상태 유형',
                                                 `create_dt` DATETIME NULL COMMENT '생성일',
                                                 PRIMARY KEY (`es_id`)
);

-- -----------------------------------------------------
-- Table `departments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `departments` (
                                             `dept_id` BIGINT NOT NULL COMMENT '부서 ID',
                                             `manager_id` BIGINT NULL,
                                             `dept_name` VARCHAR(100) NOT NULL COMMENT '부서명',
                                             PRIMARY KEY (`dept_id`)
);


-- -----------------------------------------------------
-- Table `tna`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tna` (
                                     `tna_id` BIGINT NOT NULL COMMENT '근무 기록 ID',
                                     `emp_id` BIGINT NOT NULL COMMENT '사원 ID',
                                     `tna_ty` VARCHAR(90) NOT NULL COMMENT '근무기록 유형',
                                     `start_dt` DATETIME NULL COMMENT '시작일시',
                                     `end_dt` DATETIME NULL COMMENT '종료일시',
                                     PRIMARY KEY (`tna_id`)
);

CREATE TABLE IF NOT EXISTS `leaves` (
                                        `leave_id` BIGINT NOT NULL COMMENT '생성된 휴가/휴직 ID',
                                        'parent_id' BIGINT COMMENT '부모 휴가/휴직 ID',
                                        `emp_id` BIGINT NOT NULL COMMENT '사원 ID',
                                        `leave_ty` VARCHAR(90) NOT NULL COMMENT '생성된 휴가/휴직 유형',
                                        `leave_cnt` BIGINT NOT NULL COMMENT '생성된 휴가/휴직 일수',
                                        `paid` BOOLEAN NOT NULL COMMENT '유급',
                                        `marked_as_worked` BOOLEAN NOT NULL COMMENT '연차생성시 출근으로 간주',
                                        `create_dt` DATETIME NULL COMMENT '생성일',
                                        PRIMARY KEY (`leave_id`)
);

-- -----------------------------------------------------
-- Table `salary_particulars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salary_particulars` (
                                                    `particular_id` BIGINT NOT NULL COMMENT '급여 항목 ID',
                                                    `particular` VARCHAR(300) NULL COMMENT '항목명',
                                                    `salary_ty` CHAR(1) NULL COMMENT '항목 유형',
                                                    PRIMARY KEY (`particular_id`)
);


-- -----------------------------------------------------
-- Table `salaries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salaries` (
                                          `salary_id` BIGINT NOT NULL COMMENT '급여 ID',
                                          `emp_id` BIGINT NOT NULL COMMENT '사원 ID',
                                          `salary_dt` DATETIME NOT NULL COMMENT '지급일',
                                          `particular_id` BIGINT NOT NULL COMMENT '급여 항목 ID',
                                          `salary` BIGINT NOT NULL COMMENT '급여',
                                          PRIMARY KEY (`salary_id`)
);


-- -----------------------------------------------------
-- Table `annual_basic_salaries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `annual_basic_salaries` (
                                                       `abs_id` BIGINT NOT NULL COMMENT '연봉 ID',
                                                       `emp_id` BIGINT NOT NULL COMMENT '사원 ID',
                                                       `year` BIGINT NOT NULL COMMENT '연도',
                                                       `salary` BIGINT NOT NULL COMMENT '연봉',
                                                       `increase` BIGINT NULL COMMENT '상승분',
                                                       PRIMARY KEY (`abs_id`)
);

CREATE TABLE IF NOT EXISTS users(
                                    username VARCHAR(50) NOT NULL PRIMARY KEY,
                                    password VARCHAR(100) NOT NULL,
                                    enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS authorities (

                                           auth_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
                                           username VARCHAR(50) NOT NULL,
                                           authority VARCHAR(50) NOT NULL
);