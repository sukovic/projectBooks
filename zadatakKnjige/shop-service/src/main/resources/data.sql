INSERT INTO user (id, username, password, role) VALUES
  (1, 'pera', 'pera123', 'kupac'),
  (2, 'joca', 'joca123', 'admin');


INSERT INTO basket (id, username) VALUES
  (1, 'pera'),
  (2, 'pera');


INSERT INTO purchase_order (id, username, total) VALUES
  (1, 'pera', 800),
  (2, 'pera', 1500);


INSERT INTO bought_book (id, book_id, bought_price, quantity, purchase_order_id, basket_id) VALUES
  (1, 1, 800, 2, 1, null),
  (2, 2, 500, 1, 2, null),
  (3, 2, 1000, 2, 2, null),
  (4, 3, 1400, 2, null, 1),
  (5, 1, 1600, 4, null, 2);
