CREATE database shop CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'shopuser'@'localhost' IDENTIFIED BY 'shoppass';
CREATE USER 'shopuser'@'%' IDENTIFIED BY 'shoppass';

GRANT ALL PRIVILEGES ON shop.* TO 'shopuser'@'localhost';
GRANT ALL PRIVILEGES ON shop.* TO 'shopuser'@'%';

USE shop;

CREATE TABLE shop.purchase_order {
    order_number VARCHAR(50) NOT NULL PRIMARY KEY,
    version BIGINT,
    orderer_id VARCHAR(50),
    orderer_name VARCHAR(50),
    total_amounts INT,
    shipping_zip_code VARCHAR(6),
    shipping_addr1 VARCHAR(100),
    shipping_addr2 VARCHAR(100),
    shipping_message VARCHAR(200),
    receiver_name VARCHAR(50),
    receiver_phone VARCHAR(50),
    state VARCHAR(20),
    order_date DATETIME
} CHARACTER SET utf8mb4;

CREATE TABLE shop.order_line {
    order_number VARCHAR(50) NOT NULL,
    line_idx INT NOT NULL,
    product_id VARCHAR(50) NOT NULL,
    price INT,
    quantity INT,
    amounts INT
} CHARACTER SET utf8mb4;

CREATE index order_line_idx ON order_line (order_number, line_idx);

CREATE TABLE shop.category (
    category_id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(100)
) CHARACTER SET utf8mb4;

CREATE TABLE shop.product (
    product_id VARCHAR(50) NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    price INT,
    detail TEXT
) CHARACTER SET utf8mb4;

CREATE TABLE shop.product_category (
    product_id VARCHAR(50) NOT NULL,
    category_id BIGINT NOT NULL,
    CONSTRAINT PRIMARY KEY (product_id, category_id)
) CHARACTER SET utf8mb4;

CREATE TABLE shop.image (
    image_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(50),
    list_idx INT,
    image_type VARCHAR(10),
    image_path VARCHAR(255),
    upload_time DATETIME
) CHARACTER SET utf8mb4;

CREATE TABLE shop.member (
    member_id VARCHAR(50) NOT NULL PRIMARY KEY,
    name VARCHAR(50),
    password VARCHAR(255),
    blocked BOOLEAN,
    emails  VARCHAR(200)
) CHARACTER SET utf8mb4;

CREATE TABLE shop.member_authorities (
    member_id VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY (member_id, authority)
) CHARACTER SET utf8mb4;

CREATE TABLE shop.article (
    id INT NOT NULL AUTH_INCREMENT PRIMARY KEY,
    title VARCHAR(255)
) CHARACTER SET utf8mb4;

CREATE TABLE shop.article_content (
    id INT NOT NULL PRIMARY KEY,
    content VARCHAR(255),
    content_type VARCHAR(255)
) CHARACTER SET utf8mb4;

CREATE TABLE shop.event_entry (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `type` VARCHAR(255),
    `content_type` VARCHAR(255),
    payload MEDIUMTEXT,
    `timestamp` DATETIME
) CHARACTER SET utf8mb4;

CREATE TABLE shop.locks (
    `type` VARCHAR(255),
    id VARCHAR(255),
    lockid VARCHAR(255),
    expiration_time DATETIME,
    PRIMARY KEY (`type`, id)
) CHARACTER SET utf8mb4;

CREATE UNIQUE INDEX locks_idx ON shop.locks (lockid);