// Item
INSERT INTO `shop`.`item`
(`id`,
 `name`,
 `price`)
VALUES
    (<{id: }>,
        <{name: }>,
        <{price: }>);

// Customer
INSERT INTO `shop`.`customer`
(`id`)
VALUES
(<{id: }>);

// Cart
INSERT INTO `shop`.`cart`
(`id`,
`customer_id`)
VALUES
(<{id: }>,
<{customer_id: }>);

// CartDetail
INSERT INTO `shop`.`cart_detail`
(`id`,
`date_added`,
`quantity`,
`cart_id`,
`item_id`)
VALUES
(<{id: }>,
<{date_added: }>,
<{quantity: }>,
<{cart_id: }>,
<{item_id: }>);

//orders
INSERT INTO `shop`.`orders`
(`id`,
`create_date`,
`customer_id`)
VALUES
(<{id: }>,
<{create_date: }>,
<{customer_id: }>);

// OrderDetail
INSERT INTO `shop`.`order_detail`
(`id`,
`quantity`,
`item_id`,
`order_id`)
VALUES
(<{id: }>,
<{quantity: }>,
<{item_id: }>,
<{order_id: }>);

