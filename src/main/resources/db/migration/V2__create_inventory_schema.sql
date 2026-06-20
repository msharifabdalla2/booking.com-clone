CREATE TABLE room_type_inventory (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    hotel_id UUID NOT NULL REFERENCES hotels(id),
    room_type_id UUID NOT NULL REFERENCES room_types(id),
    date DATE NOT NULL,
    total_count INTEGER NOT NULL CHECK (total_count >= 0),
    reserved_count INTEGER NOT NULL DEFAULT 0,

    CONSTRAINT chk_reserved_le_total CHECK (reserved_count <= total_count),

    CONSTRAINT uq_room_type_date UNIQUE (room_type_id, date)
);