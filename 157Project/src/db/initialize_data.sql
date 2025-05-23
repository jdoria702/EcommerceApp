INSERT INTO Customer (email, password, address, first_name, last_name) VALUES
('alice.smith@example.com', 'password123', '123 Main St, NY', 'Alice', 'Smith'),
('bob.jones@example.com', 'passw0rd', '456 Elm St, LA', 'Bob', 'Jones'),
('carol.danvers@example.com', 'carol456', '789 Oak St, TX', 'Carol', 'Danvers'),
('dave.miller@example.com', 'securepwd', '321 Pine St, WA', 'Dave', 'Miller'),
('eve.adams@example.com', 'qwerty123', '654 Maple St, FL', 'Eve', 'Adams'),
('frank.moore@example.com', 'frankie!', '987 Cedar St, IL', 'Frank', 'Moore'),
('grace.lee@example.com', 'gracepwd', '111 Birch St, NV', 'Grace', 'Lee'),
('henry.wilson@example.com', 'henry456', '222 Cherry St, OH', 'Henry', 'Wilson'),
('isla.kim@example.com', 'isla123', '333 Walnut St, MI', 'Isla', 'Kim'),
('jack.brown@example.com', 'jackpass', '444 Spruce St, AZ', 'Jack', 'Brown');

INSERT INTO Seller (name, address, description) VALUES
('TechWorld', '12 Tech Park, CA', 'Electronics and gadgets'),
('HomeComforts', '45 Cozy Lane, OR', 'Furniture and home goods'),
('FashionFinds', '89 Style Blvd, NY', 'Trendy fashion and accessories'),
('BookBarn', '67 Reader Rd, MA', 'Books and stationery'),
('PetPalace', '90 Paw Ave, CO', 'Pet supplies and accessories');

INSERT INTO Product (seller_id, name, category, description, price, rating, quantity) VALUES
(1, 'Wireless Earbuds', 'Electronics', 'Noise-cancelling earbuds', 49.99, 4.5, 100),
(1, 'Smartphone Case', 'Electronics', 'Shockproof phone case', 12.99, 4.2, 250),
(1, 'Gaming Mouse', 'Electronics', 'RGB gaming mouse', 29.99, 4.6, 80),
(2, 'Modern Sofa', 'Furniture', '3-seat leather sofa', 799.99, 4.7, 20),
(2, 'Coffee Table', 'Furniture', 'Wooden coffee table', 199.99, 4.1, 35),
(2, 'Office Chair', 'Furniture', 'Ergonomic office chair', 149.99, 4.3, 50),
(3, 'Denim Jacket', 'Fashion', 'Unisex blue denim jacket', 59.99, 4.8, 70),
(3, 'Sneakers', 'Fashion', 'White casual sneakers', 69.99, 4.6, 100),
(3, 'Leather Belt', 'Fashion', 'Genuine leather belt', 24.99, 4.0, 120),
(4, 'Fantasy Novel', 'Books', 'Bestselling fantasy book', 14.99, 4.9, 200),
(4, 'Notebook Set', 'Books', '5-pack ruled notebooks', 9.99, 4.3, 300),
(4, 'Ballpoint Pens', 'Books', 'Pack of 10 pens', 5.99, 3.9, 500),
(5, 'Cat Toy', 'Pet Supplies', 'Interactive cat toy', 7.99, 4.7, 150),
(5, 'Dog Bed', 'Pet Supplies', 'Large plush dog bed', 49.99, 4.5, 40),
(5, 'Bird Feeder', 'Pet Supplies', 'Outdoor bird feeder', 19.99, 4.1, 60);

INSERT INTO Review (product_id, customer_id, rating, review, reviewer_name) VALUES
(1, 1, 5, 'Amazing sound quality!', 'Alice Smith'),
(1, 2, 4, 'Very good but battery could be better.', 'Bob Jones'),
(2, 3, 5, 'Fits my phone perfectly.', 'Carol Danvers'),
(3, 4, 4, 'Responsive and cool lights.', 'Dave Miller'),
(4, 5, 5, 'Sofa is super comfy and stylish.', 'Eve Adams'),
(4, 6, 4, 'Delivery took long but great quality.', 'Frank Moore'),
(5, 7, 3, 'Scratches easily, looks good though.', 'Grace Lee'),
(6, 8, 4, 'Chair is supportive and adjustable.', 'Henry Wilson'),
(7, 9, 5, 'Love the fit and look!', 'Isla Kim'),
(8, 10, 5, 'Great shoes for walking.', 'Jack Brown'),
(9, 1, 3, 'Bit overpriced.', 'Alice Smith'),
(10, 2, 5, 'Could not stop reading!', 'Bob Jones'),
(11, 3, 4, 'Good paper quality.', 'Carol Danvers'),
(12, 4, 2, 'Some pens didn’t work.', 'Dave Miller'),
(13, 5, 5, 'Cat went crazy for it!', 'Eve Adams'),
(14, 6, 5, 'Dog loves it!', 'Frank Moore'),
(15, 7, 4, 'Works fine, birds love it.', 'Grace Lee'),
(5, 8, 4, 'Nice table for the price.', 'Henry Wilson'),
(6, 9, 5, 'Fixed my back pain.', 'Isla Kim'),
(3, 10, 5, 'Amazing precision and grip.', 'Jack Brown');

INSERT INTO Orders (customer_id, product_id, quantity) VALUES
(1, 1, 1),
(2, 1, 1),
(3, 2, 2),
(4, 3, 1),
(5, 4, 1),
(6, 4, 1),
(7, 5, 1),
(8, 6, 1),
(9, 7, 1),
(10, 8, 1),
(1, 9, 1),
(2, 10, 2),
(3, 11, 1),
(4, 12, 3),
(5, 13, 1),
(6, 14, 1),
(7, 15, 2),
(8, 5, 1),
(9, 6, 1),
(10, 3, 1);