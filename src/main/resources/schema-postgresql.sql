-- UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ユーザー(users)
drop table if exists users cascade;
CREATE TABLE users (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4()
 , first_name text not null
 , last_name text not null
 , email text not null unique
 , password text not null
 , zipcode text not null
 , prefecture text not null
 , municipalities text not null
 , address text not null
 , telephone text not null
 , created_at timestamp default CURRENT_TIMESTAMP
 , updated_at timestamp default CURRENT_TIMESTAMP
);

INSERT INTO users (first_name, last_name, email, password, zipcode, prefecture, municipalities, address, telephone)
VALUES
('Taro', 'Yamada', 'taro.yamada@example.com', 'password123', '123-4567', 'Tokyo', 'Shibuya', 'Shibuya 1-1-1', '090-1234-5678'),
('Hanako', 'Suzuki', 'hanako.suzuki@example.com', 'password123', '765-4321', 'Osaka', 'Naniwa', 'Naniwa 2-2-2', '080-8765-4321');

-- 配達時間(delivery_time_ranges)
drop table if exists delivery_time_ranges cascade;
CREATE TABLE delivery_time_ranges(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4()
    , time_range text not null
);

INSERT INTO delivery_time_ranges (time_range)
VALUES
('8:00-10:00'),
('10:00-12:00'),
('12:00-14:00'),
('14:00-16:00'),
('16:00-18:00');

-- 犬種(breeds)
drop table if exists breeds cascade;
CREATE TABLE breeds (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name text not null
);

INSERT INTO breeds (name)
VALUES
('Shiba Inu'),
('Labrador Retriever'),
('German Shepherd'),
('Golden Retriever'),
('Bulldog'),
('Poodle'),
('Beagle'),
('Chihuahua'),
('Dachshund');

-- 色(colors)
drop table if exists colors cascade;
CREATE TABLE colors (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name text not null
);

INSERT INTO colors (name)
VALUES
('Black'),
('White'),
('Brown'),
('Golden');

-- 商品(items)
drop table if exists items cascade;
CREATE TABLE items (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4()
  , description text not null
  , price integer not null
  , image text not null
  , gender text not null
  , birth_day DATE not null
  , deleted boolean default false not null
  , breed_id UUID
  , color_id UUID
  , created_at timestamp default CURRENT_TIMESTAMP
  , updated_at timestamp default CURRENT_TIMESTAMP
  , FOREIGN KEY (breed_id) REFERENCES breeds(id)
  , FOREIGN KEY (color_id) REFERENCES colors(id)
);

INSERT INTO items (description, price, image, gender, birth_day, breed_id, color_id)
VALUES
('Friendly Shiba Inu', 300000, 'shiba_inu.png', 'male', '2023-01-01', (SELECT id FROM breeds WHERE name='Shiba Inu'), (SELECT id FROM colors WHERE name='Brown')),
('Loyal Labrador', 250000, 'labrador.png', 'female', '2023-02-01', (SELECT id FROM breeds WHERE name='Labrador Retriever'), (SELECT id FROM colors WHERE name='Black')),
('Brave German Shepherd', 280000, 'german_shepherd.png', 'male', '2023-03-01', (SELECT id FROM breeds WHERE name='German Shepherd'), (SELECT id FROM colors WHERE name='Black')),
('Gentle Golden Retriever', 270000, 'golden_retriever.png', 'female', '2023-04-01', (SELECT id FROM breeds WHERE name='Golden Retriever'), (SELECT id FROM colors WHERE name='Golden')),
('Playful Bulldog', 260000, 'bulldog.png', 'male', '2023-05-01', (SELECT id FROM breeds WHERE name='Bulldog'), (SELECT id FROM colors WHERE name='White')),
('Smart Poodle', 240000, 'poodle.png', 'female', '2023-06-01', (SELECT id FROM breeds WHERE name='Poodle'), (SELECT id FROM colors WHERE name='Brown')),
('Energetic Beagle', 230000, 'beagle.png', 'male', '2023-07-01', (SELECT id FROM breeds WHERE name='Beagle'), (SELECT id FROM colors WHERE name='Black')),
('Tiny Chihuahua', 220000, 'chihuahua.png', 'female', '2023-08-01', (SELECT id FROM breeds WHERE name='Chihuahua'), (SELECT id FROM colors WHERE name='Brown')),
('Long Dachshund', 210000, 'dachshund.png', 'male', '2023-09-01', (SELECT id FROM breeds WHERE name='Dachshund'), (SELECT id FROM colors WHERE name='Black'));

-- オプショングループ(option_groups)
drop table if exists option_groups cascade;
CREATE TABLE option_groups (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name text not null,
    input_type text not null
);

-- エサグループ
INSERT INTO option_groups (name, input_type) VALUES ('エサ', 'radio');

-- おもちゃグループ
INSERT INTO option_groups (name, input_type) VALUES ('おもちゃ', 'checkbox');

-- トイレグループ
INSERT INTO option_groups (name, input_type) VALUES ('トイレ', 'radio');

-- ケージグループ
INSERT INTO option_groups (name, input_type) VALUES ('ケージ', 'radio');

-- 首輪グループ
INSERT INTO option_groups (name, input_type) VALUES ('首輪', 'radio');

-- ベッドグループ
INSERT INTO option_groups (name, input_type) VALUES ('ベッド', 'radio');

-- お手入れ用品グループ
INSERT INTO option_groups (name, input_type) VALUES ('お手入れ用品', 'checkbox');

-- オプション(options)
drop table if exists options cascade;
CREATE TABLE options (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name text not null,
    price Integer not null,
    option_group_id UUID,
    FOREIGN KEY (option_group_id) REFERENCES option_groups(id)
);

-- エサオプション
INSERT INTO options (name, price, option_group_id)
VALUES
('エサA', 500, (SELECT id FROM option_groups WHERE name='エサ')),
('エサB', 600, (SELECT id FROM option_groups WHERE name='エサ')),
('エサC', 700, (SELECT id FROM option_groups WHERE name='エサ'));

-- おもちゃオプション
INSERT INTO options (name, price, option_group_id)
VALUES
('おもちゃA', 1500, (SELECT id FROM option_groups WHERE name='おもちゃ')),
('おもちゃB', 2000, (SELECT id FROM option_groups WHERE name='おもちゃ')),
('おもちゃC', 2500, (SELECT id FROM option_groups WHERE name='おもちゃ'));

-- トイレオプション
INSERT INTO options (name, price, option_group_id)
VALUES
('トイレA', 2500, (SELECT id FROM option_groups WHERE name='トイレ')),
('トイレB', 3000, (SELECT id FROM option_groups WHERE name='トイレ')),
('トイレC', 3500, (SELECT id FROM option_groups WHERE name='トイレ'));

-- ケージオプション
INSERT INTO options (name, price, option_group_id)
VALUES
('ケージA', 8000, (SELECT id FROM option_groups WHERE name='ケージ')),
('ケージB', 10000, (SELECT id FROM option_groups WHERE name='ケージ')),
('ケージC', 12000, (SELECT id FROM option_groups WHERE name='ケージ'));

-- 首輪オプション
INSERT INTO options (name, price, option_group_id)
VALUES
('首輪A', 1000, (SELECT id FROM option_groups WHERE name='首輪')),
('首輪B', 1200, (SELECT id FROM option_groups WHERE name='首輪')),
('首輪C', 1400, (SELECT id FROM option_groups WHERE name='首輪'));

-- ベッドオプション
INSERT INTO options (name, price, option_group_id)
VALUES
('ベッドA', 3000, (SELECT id FROM option_groups WHERE name='ベッド')),
('ベッドB', 3500, (SELECT id FROM option_groups WHERE name='ベッド')),
('ベッドC', 4000, (SELECT id FROM option_groups WHERE name='ベッド'));

-- お手入れ用品オプション
INSERT INTO options (name, price, option_group_id)
VALUES
('ブラシ', 500, (SELECT id FROM option_groups WHERE name='お手入れ用品')),
('シャンプー', 800, (SELECT id FROM option_groups WHERE name='お手入れ用品')),
('爪切り', 600, (SELECT id FROM option_groups WHERE name='お手入れ用品'));

-- 注文(orders)
drop table if exists orders cascade;
CREATE TABLE orders (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  user_id UUID,
  status INTEGER not null,
  total_price INTEGER not null,
  order_date text not null,
  destination_name text default '',
  destination_email text not null,
  destination_zipcode text not null,
  destination_prefecture text not null,
  destination_municipalities text not null,
  destination_address text not null,
  destination_tel text not null,
  delivery_date DATE not null,
  delivery_time UUID not null,
  payment_method text not null,
  created_at timestamp default CURRENT_TIMESTAMP,
  updated_at timestamp default CURRENT_TIMESTAMP,
  FOREIGN KEY (delivery_time) REFERENCES delivery_time_ranges(id)
);

INSERT INTO orders (user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_prefecture, destination_municipalities, destination_address, destination_tel, delivery_date, delivery_time, payment_method)
VALUES
((SELECT id FROM users WHERE email='taro.yamada@example.com'), 1, 320000, '2024-06-20', 'Taro Yamada', 'taro.yamada@example.com', '123-4567', 'Tokyo', 'Shibuya', 'Shibuya 1-1-1', '090-1234-5678', '2024-06-27', (SELECT id FROM delivery_time_ranges WHERE time_range='8:00-10:00'), 'Credit Card'),
((SELECT id FROM users WHERE email='hanako.suzuki@example.com'), 1, 270000, '2024-06-20', 'Hanako Suzuki', 'hanako.suzuki@example.com', '765-4321', 'Osaka', 'Naniwa', 'Naniwa 2-2-2', '080-8765-4321', '2024-06-27', (SELECT id FROM delivery_time_ranges WHERE time_range='10:00-12:00'), 'Credit Card');

-- 注文された商品(order_items)
drop table if exists order_items cascade;
CREATE TABLE order_items (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    item_id UUID,
    order_id UUID,
    FOREIGN KEY (item_id) REFERENCES items(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

INSERT INTO order_items (item_id, order_id)
VALUES
((SELECT id FROM items WHERE description='Friendly Shiba Inu'), (SELECT id FROM orders WHERE destination_email='taro.yamada@example.com')),
((SELECT id FROM items WHERE description='Loyal Labrador'), (SELECT id FROM orders WHERE destination_email='hanako.suzuki@example.com'));

-- 注文された商品のオプション(order_item_options)
drop table if exists order_item_options cascade;
CREATE TABLE order_item_options(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    order_item_id UUID,
    option_id UUID,
    FOREIGN KEY (order_item_id) REFERENCES order_items(id),
    FOREIGN KEY (option_id) REFERENCES options(id)
);

INSERT INTO order_item_options (order_item_id, option_id)
VALUES
((SELECT id FROM order_items WHERE item_id=(SELECT id FROM items WHERE description='Friendly Shiba Inu')), (SELECT id FROM options WHERE name='エサA')),
((SELECT id FROM order_items WHERE item_id=(SELECT id FROM items WHERE description='Friendly Shiba Inu')), (SELECT id FROM options WHERE name='おもちゃA')),
((SELECT id FROM order_items WHERE item_id=(SELECT id FROM items WHERE description='Loyal Labrador')), (SELECT id FROM options WHERE name='エサB')),
((SELECT id FROM order_items WHERE item_id=(SELECT id FROM items WHERE description='Loyal Labrador')), (SELECT id FROM options WHERE name='おもちゃB'));
