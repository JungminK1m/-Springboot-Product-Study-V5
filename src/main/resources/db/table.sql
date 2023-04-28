CREATE TABLE user_tb(
	user_id INT PRIMARY KEY auto_increment,
	user_name VARCHAR(20) NOT null,
	user_password VARCHAR(20) NOT null,
	user_email VARCHAR(20) NOT null,
	role VARCHAR(20) NOT null,
	created_at TIMESTAMP NOT null
);

CREATE TABLE product_tb(
	product_id INT PRIMARY KEY auto_increment,
	product_name VARCHAR(20) NOT null,
	product_price INT NOT null,
	product_qty INT NOT null,
	created_at TIMESTAMP NOT null
);

CREATE TABLE orders_tb(
    orders_id INT PRIMARY KEY auto_increment,
    orders_name varchar(20) NOT null,
    orders_price INT NOT null,
    orders_qty INT NOT null,
    product_id INT NOT null,
    user_id INT NOT null,
    created_at TIMESTAMP
);