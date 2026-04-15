create database getcat_db;

CREATE TABLE roles(
    role_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_label VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE ref_animals(
    animal_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    species VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE ref_services(
    service_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    service_type VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE addresses(
    address_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    building_num VARCHAR(10),
    street VARCHAR(150),
    city VARCHAR(100),
    postal_code VARCHAR(20)
);

CREATE TABLE users(
    user_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(254) UNIQUE NOT NULL, 
    phone VARCHAR(20) UNIQUE,
    user_photo VARCHAR(500),
    password_hash VARCHAR(255) NOT NULL, -- supports all algorithms
    
    is_online BOOLEAN DEFAULT false,
    is_deleted BOOLEAN DEFAULT false,
    is_blocked BOOLEAN DEFAULT false,

    address_id INT,
    role_id INT,

    CONSTRAINT fk_users_address 
    FOREIGN KEY(address_id) 
    REFERENCES addresses(address_id)
    ON DELETE SET null,

    CONSTRAINT fk_users_role
    FOREIGN KEY(role_id)
    REFERENCES roles(role_id)
    ON DELETE SET NULL
);

CREATE TABLE posts(
    post_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    description TEXT,
    animal_photo VARCHAR(500),
    start_date DATE,
    end_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- time and date
    is_deleted BOOLEAN DEFAULT false,

    user_id INT,
    service_id INT,
    animal_id INT,

    CONSTRAINT fk_posts_user
    FOREIGN KEY(user_id)
    REFERENCES users(user_id)
    ON DELETE SET NULL,

    CONSTRAINT fk_posts_service
    FOREIGN KEY(service_id)
    REFERENCES ref_services(service_id)
    ON DELETE SET NULL,

    CONSTRAINT fk_posts_animal
    FOREIGN KEY(animal_id)
    REFERENCES ref_animals(animal_id)
    ON DELETE SET NULL
);

CREATE TYPE status_type AS ENUM ('pending', 'confirmed', 'cancelled');

CREATE TABLE bookings(
   booking_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   booking_status status_type DEFAULT 'pending',

   user_id INT,
   post_id INT,

   CONSTRAINT fk_bookings_booker 
   FOREIGN KEY(user_id) 
   REFERENCES users(user_id)
   ON DELETE SET NULL,

   CONSTRAINT fk_bookings_post
   FOREIGN KEY(post_id) 
   REFERENCES posts(post_id)
   ON DELETE SET NULL,

   CONSTRAINT unique_user_post
   UNIQUE (user_id, post_id)     
);
