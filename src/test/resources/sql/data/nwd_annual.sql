CREATE TABLE IF NOT EXISTS non_working_days_annual
(
    annual_id     BIGINT       NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    annual_ty     VARCHAR(50)  NOT NULL,
    annual_nm     VARCHAR(300) NULL,
    annual_offset INTEGER      NOT NULL DEFAULT 0,
    annual_cnt    INTEGER      NOT NULL DEFAULT 1,
    `month`       INTEGER      NOT NULL,
    day_of_month  INTEGER      NOT NULL,
    lunar         BOOLEAN      NOT NULL,
    holiday       BOOLEAN      NOT NULL,
    paid          BOOLEAN      NOT NULL,
    create_dt     DATETIME     NOT NULL,
    enabled       BOOLEAN      NOT NULL,
    PRIMARY KEY (annual_id)
);

TRUNCATE TABLE non_working_days_annual;

INSERT INTO non_working_days_annual(annual_ty,
                                    annual_nm,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('STATUTORY_HOLIDAY',
        '근로자의 날',
        5,
        1,
        FALSE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_annual(
                                    annual_ty,
                                    annual_nm,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('PUBLIC_HOLIDAY',
        '1월 1일',
        1,
        1,
        FALSE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_annual(
                                    annual_ty,
                                    annual_nm,
                                    annual_offset,
                                    annual_cnt,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('PUBLIC_HOLIDAY',
        '설날',
        -1,
        3,
        1,
        1,
        TRUE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_annual(
                                    annual_ty,
                                    annual_nm,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('PUBLIC_HOLIDAY',
        '3·1절',
        3,
        1,
        FALSE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_annual(annual_ty,
                                    annual_nm,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('PUBLIC_HOLIDAY',
        '부처님오신날',
        4,
        8,
        TRUE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_annual(annual_ty,
                                    annual_nm,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('PUBLIC_HOLIDAY',
        '어린이날',
        5,
        5,
        FALSE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_annual(annual_ty,
                                    annual_nm,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('PUBLIC_HOLIDAY',
        '현충일',
        6,
        6,
        FALSE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_annual(annual_ty,
                                    annual_nm,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('PUBLIC_HOLIDAY',
        '광복절',
        8,
        15,
        FALSE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_annual(annual_ty,
                                    annual_nm,
                                    annual_offset,
                                    annual_cnt,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('PUBLIC_HOLIDAY',
        '추석',
        -1,
        3,
        8,
        15,
        TRUE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_annual(annual_ty,
                                    annual_nm,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('PUBLIC_HOLIDAY',
        '개천절',
        10,
        3,
        FALSE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_annual(annual_ty,
                                    annual_nm,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('PUBLIC_HOLIDAY',
        '한글날',
        10,
        9,
        FALSE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_annual(annual_ty,
                                    annual_nm,
                                    `month`,
                                    day_of_month,
                                    lunar,
                                    holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES ('PUBLIC_HOLIDAY',
        '크리스마스',
        12,
        25,
        FALSE,
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);
/*
    STATUTORY_HOLIDAY("법정휴일"),
    PUBLIC_HOLIDAY("공휴일"),
    CONTRACTUAL_HOLIDAY("약정휴일");
*/