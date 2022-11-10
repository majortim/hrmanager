CREATE TABLE IF NOT EXISTS non_working_days_weekly
(
    weekly_id      BIGINT      NOT NULL,
    `day_of_week`  VARCHAR(50) NOT NULL,
    weekly_holiday BOOLEAN     NOT NULL,
    paid           BOOLEAN     NOT NULL,
    create_dt      DATETIME    NOT NULL,
    enabled        BOOLEAN     NOT NULL,
    PRIMARY KEY (weekly_id)
);
TRUNCATE TABLE non_working_days_weekly;

INSERT INTO non_working_days_weekly(weekly_id,
                                    `day_of_week`,
                                    weekly_holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES (1,
        'SUNDAY',
        TRUE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

INSERT INTO non_working_days_weekly(weekly_id,
                                    `day_of_week`,
                                    weekly_holiday,
                                    paid,
                                    create_dt,
                                    enabled)
VALUES (2,
        'SATURDAY',
        FALSE,
        TRUE,
        '2022-01-01 10:00:00',
        TRUE);

