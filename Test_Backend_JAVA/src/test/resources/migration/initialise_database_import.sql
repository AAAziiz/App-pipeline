CREATE DATABASE IF NOT EXISTS arwa_backend;

USE arwa_backend;

CREATE TABLE IF NOT EXISTS cards (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     card_cvc VARCHAR(255) NULL,
    card_number VARCHAR(255) NULL,
    month_expir VARCHAR(255) NULL,
    year_expir VARCHAR(255) NULL
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS users (
                                     card_id BIGINT NULL,
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     email VARCHAR(255) NULL,
    name VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT UK_qvssa6h1me5k6ohvd67jexrh8 UNIQUE (card_id),
    CONSTRAINT UKe67ryb8s35ckhfnb17qe4wlgp UNIQUE (id, name, email),
    CONSTRAINT FKrxplbc3h7tax4h43tfg5qgc1l FOREIGN KEY (card_id) REFERENCES cards(id)
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS hotels (
                                      adults INT NULL,
                                      children INT NULL,
                                      rooms INT NULL,
                                      star_rating INT NULL,
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      arrival_date VARCHAR(255) NULL,
    destination VARCHAR(255) NULL,
    hotel_type VARCHAR(255) NULL,
    leave_date VARCHAR(255) NULL
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS flights (
                                       add_nearby_airports_from BIT NULL,
                                       add_nearby_airports_to BIT NULL,
                                       adults INT NULL,
                                       children INT NULL,
                                       direct_flight BIT NULL,
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       departure_date VARCHAR(255) NULL,
    flight_type VARCHAR(255) NULL,
    fromcity VARCHAR(255) NULL,
    return_date VARCHAR(255) NULL,
    tocity VARCHAR(255) NULL,
    travel_class VARCHAR(255) NULL
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS cars (
                                    location_return BIT NULL,
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    awd VARCHAR(255) NULL,
    carclass VARCHAR(255) NULL,
    cartype VARCHAR(255) NULL,
    drop_date VARCHAR(255) NULL,
    passengers VARCHAR(255) NULL,
    pick_date VARCHAR(255) NULL
    ) ENGINE=InnoDB;
