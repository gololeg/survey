-- Database: postgres

-- DROP DATABASE IF EXISTS postgres;

CREATE DATABASE postgres
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Belarus.1251'
    LC_CTYPE = 'Russian_Belarus.1251'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE postgres
    IS 'default administrative connection database';

    -- SCHEMA: survey

    -- DROP SCHEMA IF EXISTS survey ;

    CREATE SCHEMA IF NOT EXISTS survey
        AUTHORIZATION postgres;

        -- Table: survey.answers

        -- DROP TABLE IF EXISTS survey.answers;

        CREATE TABLE IF NOT EXISTS survey.answers
        (
            id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
            name character varying COLLATE pg_catalog."default",
            value character varying COLLATE pg_catalog."default",
            is_right boolean NOT NULL DEFAULT false,
            task_id bigint,
            text character varying(100) COLLATE pg_catalog."default",
            CONSTRAINT answers_pkey PRIMARY KEY (id),
            CONSTRAINT task_id_fk FOREIGN KEY (task_id)
                REFERENCES survey.tasks (id) MATCH SIMPLE
                ON UPDATE NO ACTION
                ON DELETE NO ACTION
                NOT VALID
        )

        TABLESPACE pg_default;

        ALTER TABLE IF EXISTS survey.answers
            OWNER to postgres;

            -- Table: survey.client_answers

            -- DROP TABLE IF EXISTS survey.client_answers;

            CREATE TABLE IF NOT EXISTS survey.client_answers
            (
                id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
                survey_id character varying COLLATE pg_catalog."default" NOT NULL,
                task_id bigint NOT NULL,
                answer_id bigint NOT NULL,
                CONSTRAINT client_answers_pkey PRIMARY KEY (id),
                CONSTRAINT client_ans_ans_fk FOREIGN KEY (answer_id)
                    REFERENCES survey.answers (id) MATCH SIMPLE
                    ON UPDATE NO ACTION
                    ON DELETE NO ACTION
                    NOT VALID,
                CONSTRAINT client_answer_task_fk FOREIGN KEY (task_id)
                    REFERENCES survey.tasks (id) MATCH SIMPLE
                    ON UPDATE NO ACTION
                    ON DELETE NO ACTION
                    NOT VALID
            )

            TABLESPACE pg_default;

            ALTER TABLE IF EXISTS survey.client_answers
                OWNER to postgres;

                -- Table: survey.levels

                -- DROP TABLE IF EXISTS survey.levels;

                CREATE TABLE IF NOT EXISTS survey.levels
                (
                    id integer NOT NULL,
                    name character varying COLLATE pg_catalog."default" NOT NULL,
                    CONSTRAINT level_pkey PRIMARY KEY (id)
                )

                TABLESPACE pg_default;

                ALTER TABLE IF EXISTS survey.levels
                    OWNER to postgres;

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

                        -- Table: survey.tasks

                        -- DROP TABLE IF EXISTS survey.tasks;

                        CREATE TABLE IF NOT EXISTS survey.tasks
                        (
                            id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
                            name character varying COLLATE pg_catalog."default",
                            img bytea,
                            level_id integer,
                            type_id integer,
                            description character varying COLLATE pg_catalog."default",
                            CONSTRAINT tasks_pkey PRIMARY KEY (id),
                            CONSTRAINT level_id_fk FOREIGN KEY (level_id)
                                REFERENCES survey.levels (id) MATCH SIMPLE
                                ON UPDATE NO ACTION
                                ON DELETE NO ACTION
                                NOT VALID,
                            CONSTRAINT type_id_fk FOREIGN KEY (type_id)
                                REFERENCES survey.types (id) MATCH SIMPLE
                                ON UPDATE NO ACTION
                                ON DELETE NO ACTION
                                NOT VALID
                        )

                        TABLESPACE pg_default;

                        ALTER TABLE IF EXISTS survey.tasks
                            OWNER to postgres;

                        COMMENT ON COLUMN survey.tasks.id
                            IS 'id';

                        COMMENT ON COLUMN survey.tasks.name
                            IS 'name';

                        COMMENT ON COLUMN survey.tasks.img
                            IS 'image';

                            -- Table: survey.types

                            -- DROP TABLE IF EXISTS survey.types;

                            CREATE TABLE IF NOT EXISTS survey.types
                            (
                                id integer NOT NULL,
                                name character varying COLLATE pg_catalog."default" NOT NULL,
                                CONSTRAINT types_pkey PRIMARY KEY (id)
                            )

                            TABLESPACE pg_default;

                            ALTER TABLE IF EXISTS survey.types
                                OWNER to postgres;