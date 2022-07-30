USE shop;

TRUNCATE TABLE purchase_order;
TRUNCATE TABLE order_line;
TRUNCATE TABLE category;
TRUNCATE TABLE product_category;
TRUNCATE TABLE product;
TRUNCATE TABLE image;
TRUNCATE TABLE member;
TRUNCATE TABLE member_authorities;
TRUNCATE TABLE article;
TRUNCATE TABLE article_content;
TRUNCATE TABLE event_entry;

INSERT INTO member (member_id, name, password, blocked) VALUES ('user1', '사용자1', '1234', false);
INSERT INTO member (member_id, name, password, blocked) VALUES ('user2', '사용자2', '5678', false);
INSERT INTO member (member_id, name, password, blocked) VALUES ('admin', '운영자', 'admin1234', false);
INSERT INTO member_authorities VALUES ('user1', 'ROLE_USER');
INSERT INTO member_authorities VALUES ('user2', 'ROLE_USER');
INSERT INTO member_authorities VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO category VALUES (1001, '전자제품');
INSERT INTO category VALUES (2001, '필기구');

INSERT INTO product VALUES ('prod-001', '라즈베리파이3 모델B', 56000, '모델B');
INSERT INTO image (product_id, list_idx, image_type, image_path, upload_time) VALUES
    ('prod-001', 0, 'II', 'rp13.jpg', NOW());
INSERT INTO image (product_id, list_idx, image_type, image_path, upload_time) VALUES
    ('prod-001', 1, 'EI', 'http://external/image/path', NOW());

INSERT INTO product_category VALUES ('prod-001', 1001);

INSERT INTO product VALUES ('prod-002', '어프로치 휴대용 화이트보드 세트', 11920, '화이트보드');
INSERT INTO image (product_id, list_idx, image_type, image_path, upload_time) VALUES
    ('prod-002', 0, 'II', 'wbp.png', NOW());

INSERT INTO product_category VALUES ('prod-002', 2001);

INSERT INTO product VALUES ('prod-003', '볼펜 겸용 터치펜', 9000, '볼펜과 터치펜을 하나로!');
INSERT INTO image (product_id, list_idx, image_type, image_path, upload_time) VALUES
    ('prod-003', 0, 'II', 'pen.jpg', NOW());
INSERT INTO image (product_id, list_idx, image_type, image_path, upload_time) VALUES
    ('prod-003', 1, 'II', 'pen2.jpg', NOW());

INSERT INTO product_category VALUES ('prod-003', 1001);
INSERT INTO product_category VALUES ('prod-003', 2001);