CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(19,2) NOT NULL,
    category VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE inventories (
    product_id BIGINT PRIMARY KEY,
    available_quantity INTEGER NOT NULL,
    reserved_quantity INTEGER NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_inventory_product
        FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE carts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_cart_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE cart_items (
    id BIGSERIAL PRIMARY KEY,
    cart_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,

    CONSTRAINT fk_cart_item_cart
        FOREIGN KEY (cart_id) REFERENCES carts(id),

    CONSTRAINT fk_cart_item_product
        FOREIGN KEY (product_id) REFERENCES products(id),

    CONSTRAINT uq_cart_product
        UNIQUE (cart_id, product_id)
);

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_amount NUMERIC(19, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_order_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    unit_price NUMERIC(19, 2) NOT NULL,
    quantity INTEGER NOT NULL,
    subtotal NUMERIC(19, 2) NOT NULL,

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id) REFERENCES orders(id),

    CONSTRAINT fk_order_item_product
        FOREIGN KEY (product_id) REFERENCES products(id)
);