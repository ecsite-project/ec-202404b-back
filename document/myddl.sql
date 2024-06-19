-- UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ユーザー(users)
drop table if exists users cascade;
CREATE TABLE users (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4()
 , firstname text not null
 , lastname text not null
 , email text not null unique
 , password text not null
 , zipcode text not null
 , prefecture text not null
 , municipalities text not null
 , address text not null
 , telephone text not null
 , created_at timestamp default CURRENT_TIMESTAMP
 , updated_at timestamp default CURRENT_TIMESTAMP
) ;

-- 注文(orders)
drop table if exists orders cascade;
CREATE TABLE orders (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4()
 , user_id UUID
 , status INTEGER not null
 , total_price INTEGER not null
 , order_date text not null
 , destination_name text default ''
 , destination_email text not null
 , destination_zipcode text not null
 , destination_prefecture text not null
 , destination_municipalities text not null
 , destination_address text not null
 , destination_tel text not null
 , destination_method text not null
 , created_at timestamp default CURRENT_TIMESTAMP
 , updated_at timestamp default CURRENT_TIMESTAMP
) ;

--------------------------------------------------------
-- 犬種(breeds)
drop table if exists breeds cascade;
CREATE TABLE breeds (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name text not null
);

-- 色(colors)
drop table if exists colors cascade;
CREATE TABLE colors (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name text not null
);

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
) ;

-- オプショングループ(option_groups)
drop table if exists option_groups cascade;
CREATE TABLE option_groups (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name text not null,
    input_type text not null
);

-- オプション(options)
drop table if exists options cascade;
CREATE TABLE options (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name text not null,
    price Integer not null,
    option_group_id UUID,
    FOREIGN KEY (option_group_id) REFERENCES option_groups(id)
);

-------------------------------------------------------------

-- 注文された商品(order_items)
drop table if exists order_items cascade;
CREATE TABLE order_items (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    item_id UUID,
    order_id UUID,
    FOREIGN KEY (item_id) REFERENCES items(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- 注文された商品のオプション(order_item_options)
drop table if exists order_item_options cascade;
CREATE TABLE order_item_options(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    order_item_id UUID,
    option_id UUID,
    FOREIGN KEY (order_item_id) REFERENCES order_items(id),
    FOREIGN KEY (option_id) REFERENCES options(id)
);