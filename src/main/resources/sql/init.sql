CREATE TABLE position_type (
                               id BIGSERIAL PRIMARY KEY,
                               name VARCHAR(150) NOT NULL
);

CREATE TABLE purchase_type (
                               id BIGSERIAL PRIMARY KEY,
                               name VARCHAR(150) NOT NULL
);

CREATE TABLE electro_type (
                              id BIGSERIAL PRIMARY KEY,
                              name VARCHAR(150) NOT NULL
);

CREATE TABLE shop (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(150) NOT NULL
);

CREATE TABLE employee (
                          id BIGSERIAL PRIMARY KEY,
                          last_name VARCHAR(100) NOT NULL,
                          first_name VARCHAR(100) NOT NULL,
                          patronymic VARCHAR(100),
                          birth_date DATE,
                          position_id BIGINT,
                          shop_id BIGINT,
                          gender BOOLEAN,
                          FOREIGN KEY (position_id) REFERENCES position_type(id),
                          FOREIGN KEY (shop_id) REFERENCES shop(id)
);

CREATE TABLE electro_item (
                              id BIGSERIAL PRIMARY KEY,
                              name VARCHAR(150) NOT NULL,
                              price BIGINT,
                              count INT,
                              archive BOOLEAN,
                              description TEXT,
                              etype_id BIGINT,
                              FOREIGN KEY (etype_id) REFERENCES electro_type(id)
);

CREATE TABLE purchase (
                          id BIGSERIAL PRIMARY KEY,
                          electro_id BIGINT,
                          employee_id BIGINT,
                          shop_id BIGINT,
                          type_id BIGINT,
                          purchase_date TIMESTAMP,
                          FOREIGN KEY (electro_id) REFERENCES electro_item(id),
                          FOREIGN KEY (employee_id) REFERENCES employee(id),
                          FOREIGN KEY (shop_id) REFERENCES shop(id),
                          FOREIGN KEY (type_id) REFERENCES purchase_type(id)
);

CREATE TABLE electro_shop (
                              id BIGSERIAL PRIMARY KEY,
                              shop_id BIGINT,
                              electro_item_id BIGINT,
                              count INT,
                              FOREIGN KEY (shop_id) REFERENCES shop(id),
                              FOREIGN KEY (electro_item_id) REFERENCES electro_item(id)
);

CREATE TABLE electro_employee (
                                  employee_id BIGINT,
                                  electro_type_id BIGINT,
                                  PRIMARY KEY (employee_id, electro_type_id),
                                  FOREIGN KEY (employee_id) REFERENCES employee(id),
                                  FOREIGN KEY (electro_type_id) REFERENCES electro_type(id)
);


-- Inserting data into position_type
INSERT INTO position_type (name) VALUES
                                     ('Manager'),
                                     ('Salesperson'),
                                     ('Technician'),
                                     ('Cashier');

-- Inserting data into purchase_type
INSERT INTO purchase_type (name) VALUES
                                     ('Online'),
                                     ('In-Store'),
                                     ('Phone Order'),
                                     ('Mail Order');

-- Inserting data into electro_type
INSERT INTO electro_type (name) VALUES
                                    ('Laptop'),
                                    ('Smartphone'),
                                    ('Tablet'),
                                    ('Desktop');

-- Inserting data into shop
INSERT INTO shop (name) VALUES
                            ('ElectroWorld'),
                            ('TechHub'),
                            ('GadgetStore'),
                            ('DeviceMart');

-- Inserting data into employee
INSERT INTO employee (last_name, first_name, patronymic, birth_date, position_id, shop_id, gender) VALUES
                                                                                                       ('Smith', 'John', 'A', '1985-01-15', 1, 1, TRUE),
                                                                                                       ('Doe', 'Jane', 'B', '1990-07-22', 2, 2, FALSE),
                                                                                                       ('Brown', 'Charlie', 'C', '1988-11-30', 3, 3, TRUE),
                                                                                                       ('Johnson', 'Emily', 'D', '1992-05-14', 4, 4, FALSE);

-- Inserting data into electro_item
INSERT INTO electro_item (name, price, count, archive, description, etype_id) VALUES
                                                                                  ('Compact and powerful laptop', 1000, 10, FALSE, 'A lightweight, high-performance laptop.', 1),
                                                                                  ('Latest smartphone', 800, 15, FALSE, 'A sleek smartphone with the latest features.', 2),
                                                                                  ('High-resolution tablet', 600, 20, FALSE, 'A tablet with a stunning display.', 3),
                                                                                  ('Gaming desktop', 1200, 5, FALSE, 'A powerful desktop for gaming.', 4),
                                                                                  ('Ultrabook laptop', 1500, 8, FALSE, 'A slim and powerful ultrabook.', 1),
                                                                                  ('Budget smartphone', 300, 30, FALSE, 'An affordable smartphone with good features.', 2);

-- Inserting data into purchase
INSERT INTO purchase (electro_id, employee_id, shop_id, type_id, purchase_date) VALUES
                                                                                    (1, 1, 1, 1, '2023-07-01 10:30:00'),
                                                                                    (2, 2, 2, 2, '2023-07-02 14:45:00'),
                                                                                    (3, 3, 3, 3, '2023-07-03 09:20:00'),
                                                                                    (4, 4, 4, 4, '2023-07-04 11:10:00'),
                                                                                    (5, 1, 1, 2, '2023-07-05 12:00:00'),
                                                                                    (6, 2, 2, 3, '2023-07-06 13:30:00');

-- Inserting data into electro_shop
INSERT INTO electro_shop (shop_id, electro_item_id, count) VALUES
                                                               (1, 1, 5),
                                                               (2, 2, 7),
                                                               (3, 3, 10),
                                                               (4, 4, 3),
                                                               (1, 5, 4),
                                                               (2, 6, 15);

-- Inserting data into electro_employee
INSERT INTO electro_employee (employee_id, electro_type_id) VALUES
                                                                (1, 1),
                                                                (2, 2),
                                                                (3, 3),
                                                                (4, 4),
                                                                (1, 2),
                                                                (2, 3);
