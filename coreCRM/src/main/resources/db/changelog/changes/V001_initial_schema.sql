CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE buildings (
    id UUID PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    last_contact_date TIMESTAMP
);

CREATE TABLE tasks (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    user_id UUID,
    building_id UUID,
    version BIGINT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_building FOREIGN KEY (building_id) REFERENCES buildings(id)
);

CREATE TABLE shedlock (
    name VARCHAR(255) PRIMARY KEY,
    lock_until TIMESTAMP NOT NULL,
    locked_at TIMESTAMP NOT NULL,
    locked_by VARCHAR(255) NOT NULL
);