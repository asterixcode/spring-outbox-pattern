DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'orders_db') THEN
            -- Create the database
            CREATE DATABASE "orders_db";
        END IF;
    END $$;

\c "orders_db";