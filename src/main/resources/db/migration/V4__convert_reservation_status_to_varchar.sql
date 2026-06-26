DO $$ BEGIN
    IF EXISTS (SELECT 1 FROM pg_type WHERE typname = 'reservation_status') THEN
        ALTER TABLE reservations ALTER COLUMN status DROP DEFAULT;
        ALTER TABLE reservations ALTER COLUMN status TYPE VARCHAR(20) USING status::VARCHAR;
        DROP TYPE reservation_status;
    END IF;
END $$;
ALTER TABLE reservations ALTER COLUMN status SET DEFAULT 'PENDING';