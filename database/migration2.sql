-- Column: survey.tasks.create_date

-- ALTER TABLE IF EXISTS survey.tasks DROP COLUMN IF EXISTS create_date;

ALTER TABLE IF EXISTS survey.tasks
    ADD COLUMN create_date timestamp with time zone;

    -- Table: survey.sessions

    -- DROP TABLE IF EXISTS survey.sessions;

    CREATE TABLE IF NOT EXISTS survey.sessions
    (
        session_id character varying COLLATE pg_catalog."default" NOT NULL,
        login character varying COLLATE pg_catalog."default" NOT NULL,
        last_active_date timestamp with time zone,
        CONSTRAINT sessions_pkey PRIMARY KEY (session_id)
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS survey.sessions
        OWNER to postgres;

        -- Table: survey.users

        -- DROP TABLE IF EXISTS survey.users;

        CREATE TABLE IF NOT EXISTS survey.users
        (
            login character varying COLLATE pg_catalog."default" NOT NULL,
            password character varying COLLATE pg_catalog."default" NOT NULL,
            CONSTRAINT users_pkey PRIMARY KEY (login)
        )

        TABLESPACE pg_default;

        ALTER TABLE IF EXISTS survey.users
            OWNER to postgres;

            INSERT INTO survey.users(
            	login, password)
            	VALUES ('admin2', 'admin');