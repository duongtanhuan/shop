drop table if exists item;
create table item
(
    id    int          not null auto_increment primary key,
    name  varchar(100) not null,
    price double       not null

);

create table customer
(
    id       int          not null auto_increment,
    email    varchar(100) not null,
    username varchar(100) not null,
    password varchar(100) not null,
    primary key (id)
);

drop table if exists cart;
create table cart
(
    id          int not null auto_increment primary key,
    customer_id int not null,
    constraint fk_customer_cart
        foreign key (customer_id) references customer (id)
);

drop table if exists orders;
create table orders
(
    id          int     not null auto_increment primary key,
    customer_id int     not null,
    create_date date,
    total_price double,
    status      boolean not null default 1,
    constraint fk_customer_orders
        foreign key (customer_id) references customer (id)
);

drop table if exists order_detail;
create table order_detail
(
    id       int not null auto_increment primary key,
    order_id int not null,
    item_id  int not null,
    quantity int not null,
    constraint fk_item_order_detail
        foreign key (item_id) references item (id),
    constraint fk_orders_order_detail
        foreign key (order_id) references orders (id)
);

drop table if exists cart_detail;
create table cart_detail
(
    id         int not null auto_increment primary key,
    cart_id    int not null,
    item_id    int not null,
    date_added date,
    quantity   int not null,
    constraint fk_item_cart_detail
        foreign key (item_id) references item (id),
    constraint fk_cart_cart_detail
        foreign key (cart_id) references cart (id)
);

drop table if exists role;
create table role
(
    id   int          not null auto_increment primary key,
    name varchar(100) not null
);

drop table if exists user_role;
create table user_role
(
    customer_id int not null,
    role_id     int not null,
    constraint fk_customer_role
        foreign key (customer_id) references customer (id),
    constraint fk_role_customer
        foreign key (role_id) references role (id)
);
