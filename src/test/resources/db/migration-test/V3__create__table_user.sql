CREATE TABLE user_entity (
    id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(255),
    password VARCHAR(255),
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    address VARCHAR(255),
    birthday DATE,
    activity_permission BOOLEAN,
    accepted_terms BOOLEAN
);

INSERT INTO user_entity (id, username, email, role, password, firstname, lastname, address, birthday, activity_permission, accepted_terms)
VALUES
    ('3f6879ef-8b7f-4c42-a4a6-4c5f5c6f2813', 'user1', 'user1@example.com', 'USER', 'P4SSW0RD_', 'User', 'One', 'Address 1', '2000-01-01', true, true),
    ('9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d', 'user2', 'user2@example.com', 'USER', '_P4SSW0RD', 'User', 'Two', 'Address 2', '2000-01-02', true, true);