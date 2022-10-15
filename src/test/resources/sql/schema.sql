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
  `dept_id` BIGINT NULL COMMENT '사원 부서 ID',
  `job_id` BIGINT NULL COMMENT '직무 ID',
  `emp_name` VARCHAR(100) NOT NULL COMMENT '사원 이름',
  `email` VARCHAR(254) NULL COMMENT '전자우편',
  `phone_number` VARCHAR(50) NULL COMMENT '전화번호',
  `hire_date` DATETIME NULL COMMENT '입사일',
  `retire_date` DATETIME NULL COMMENT '퇴사일',
  PRIMARY KEY (`emp_id`));


-- -----------------------------------------------------
-- Table `departments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `departments` (
  `dept_id` BIGINT NOT NULL COMMENT '부서 ID',
  `manager_id` BIGINT NULL,
  `dept_name` VARCHAR(100) NOT NULL COMMENT '부서명',
  PRIMARY KEY (`dept_id`));


-- -----------------------------------------------------
-- Table `tna`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tna` (
  `tna_id` BIGINT NOT NULL COMMENT '근무 기록 ID',
  `emp_id` BIGINT NOT NULL COMMENT '사원 ID',
  `tna_ty` VARCHAR(90) NOT NULL COMMENT '근무기록 유형',
  `start_dt` DATETIME NULL COMMENT '시작일시',
  `end_dt` DATETIME NULL COMMENT '종료일시',
  PRIMARY KEY (`tna_id`));


-- -----------------------------------------------------
-- Table `salary_particulars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salary_particulars` (
  `particular_id` BIGINT NOT NULL COMMENT '급여 항목 ID',
  `particular` VARCHAR(300) NULL COMMENT '항목명',
  `salary_ty` CHAR(1) NULL COMMENT '항목 유형',
  PRIMARY KEY (`particular_id`));


-- -----------------------------------------------------
-- Table `salaries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salaries` (
  `salary_id` BIGINT NOT NULL COMMENT '급여 ID',
  `emp_id` BIGINT NOT NULL COMMENT '사원 ID',
  `salary_dt` DATETIME NOT NULL COMMENT '지급일',
  `particular_id` BIGINT NOT NULL COMMENT '급여 항목 ID',
  `salary` BIGINT NOT NULL COMMENT '급여',
  PRIMARY KEY (`salary_id`));


-- -----------------------------------------------------
-- Table `annual_basic_salaries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `annual_basic_salaries` (
  `abs_id` BIGINT NOT NULL COMMENT '연봉 ID',
  `emp_id` BIGINT NOT NULL COMMENT '사원 ID',
  `year` BIGINT NOT NULL COMMENT '연도',
  `salary` BIGINT NOT NULL COMMENT '연봉',
  `increase` BIGINT NULL COMMENT '상승분',
  PRIMARY KEY (`abs_id`));

CREATE TABLE IF NOT EXISTS users(
                      username varchar_ignorecase(50) not null primary key,
                      password varchar_ignorecase(50) not null,
                      enabled boolean not null
);

CREATE TABLE IF NOT EXISTS authorities (
                             username varchar_ignorecase(50) not null,
                             authority varchar_ignorecase(50) not null,
                             constraint fk_authorities_users foreign key(username) references users(username)
);