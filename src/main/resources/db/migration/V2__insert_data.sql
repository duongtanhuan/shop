insert into item (name, price)
values ("item1", 20.0),
       ("item2", 10.0),
       ("item3", 25.0),
       ("item4", 22.0),
       ("item5", 16.0);

insert into customer (email, username, password)
values ("user1@gmail.com", "user1", "u1234"),
       ("useradmin2@gmail.com", "useradmin2", "u1234"),
       ("user3@gmail.com", "user3", "u1234"),
       ("user4@gmail.com", "user4", "u1234"),
       ("user5@gmail.com", "user5", "u1234");

insert into cart (customer_id)
values (1),
       (2),
       (3),
       (4);

insert into orders (customer_id, create_date, total_price, status)
values (2, null, null, 1),
       (2, null, null, 1),
       (2, null, null, 0),
       (2, null, null, 0);

insert into order_detail (order_id, item_id, quantity)
values (2, 1, 4),
       (2, 1, 4),
       (1, 2, 2),
       (3, 4, 5);

insert into cart_detail (cart_id, item_id, date_added, quantity)
values (2, 1, null, 4),
       (2, 1, null, 2),
       (2, 2, null, 1),
       (3, 4, null, 6);

insert into role (name)
values ("user"),
       ("admin");

insert into user_role (customer_id, role_id)
values (1, 1),
       (2, 1),
       (2, 2);
