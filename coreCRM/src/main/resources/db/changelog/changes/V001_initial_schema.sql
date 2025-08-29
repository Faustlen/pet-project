CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE buildings (
    id UUID PRIMARY KEY,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE tasks (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL,
    building_id UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_building FOREIGN KEY (building_id) REFERENCES buildings(id)
);