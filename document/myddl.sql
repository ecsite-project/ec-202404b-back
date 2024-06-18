-- UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ユーザー(users)
drop table if exists users cascade;
CREATE TABLE users (
 id serial PRIMARY KEY
 , name varchar(100) not null
 , email varchar(100) not null unique
 , password text not null
 , zipcode varchar(8) not null
 , prefecture varchar(10) not null
 , municipalities varchar(10) not null
 , address varchar(20) not null
 , telephone varchar(15) not null
) ;

-- 注文(orders)
drop table if exists orders cascade;
CREATE TABLE orders (
 id serial PRIMARY KEY
 , user_id INTEGER
 , status INTEGER not null
 , total_price INTEGER not null
 , order_date text not null
 , destination_name varchar(20) default ''
 , destination_email varchar(255) not null
 , destination_zipcode varchar(20) not null
 , destination_prefecture varchar(20) not null
 , destination_municipalities varchar(20) not null
 , destination_address varchar(20) not null
 , destination_tel varchar(20)
 , destination_method varchar(15) not null
 , FOREIGN KEY (user_id) REFERENCES users(id)
) ;

--------------------------------------------------------
-- 犬種(breeds)
drop table if exists breeds cascade;
CREATE TABLE breeds (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) not null
);

-- 色(colors)
drop table if exists colors cascade;
CREATE TABLE colors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) not null
);

-- 商品(items)
drop table if exists items cascade;
CREATE TABLE items (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4()
  , description text not null
  , price integer not null
  , image text not null
  , gender text not null
  , deleted boolean default false not null
  , breed_id INTEGER
  , color_id INTEGER
  , FOREIGN KEY (breed_id) REFERENCES breeds(id)
  , FOREIGN KEY (color_id) REFERENCES colors(id)
) ;

-- オプショングループ(option_groups)
drop table if exists option_groups cascade;
CREATE TABLE option_groups (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) not null,
    input_type text not null
);

-- オプション(options)
drop table if exists option cascade;
CREATE TABLE options (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) not null,
    price Integer not null,
    option_group_id INTEGER,
    FOREIGN KEY (option_group_id) REFERENCES option_groups(id)
);

-------------------------------------------------------------

-- 注文された商品(order_items)
drop table if exists order_items cascade;
CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    item_id UUID,
    order_id INTEGER,
    FOREIGN KEY (item_id) REFERENCES items(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- 注文された商品のオプション(order_item_options)
drop table if exists order_item_options cascade;
CREATE TABLE order_item_options(
    id SERIAL PRIMARY KEY,
    order_item_option INTEGER,
    option_id INTEGER,
    FOREIGN KEY (order_item_option) REFERENCES order_items(id),
    FOREIGN KEY (option_id) REFERENCES options(id)
);