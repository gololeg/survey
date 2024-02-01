-- Column: survey.tasks.create_date

-- ALTER TABLE IF EXISTS survey.tasks DROP COLUMN IF EXISTS create_date;

ALTER TABLE IF EXISTS survey.tasks
    ADD COLUMN create_date time with time zone;

    -- Table: survey.settings

    -- DROP TABLE IF EXISTS survey.settings;

    CREATE TABLE IF NOT EXISTS survey.settings
    (
        name character varying COLLATE pg_catalog."default" NOT NULL,
        low_level_task_count integer,
        middle_level_task_count integer,
        high_level_task_count integer,
        low_level_task_time integer,
        middle_level_task_time integer,
        high_level_task_time integer,
        CONSTRAINT settings_pkey PRIMARY KEY (name)
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS survey.settings
        OWNER to postgres;