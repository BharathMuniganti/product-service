

-- roles
CREATE TABLE roles_prod_serv
(
role_id INT AUTO_INCREMENT PRIMARY KEY,
role_name VARCHAR(100),
);

-- b2b_users
CREATE TABLE b2b_users_prod_serv (
     user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     mobile_number varchar(10) NOT NULL,
     password varchar(255) NOT NULL,
     user_name varchar(255) NOT NULL UNIQUE,
     role_id INT NOT NULL,
     FOREIGN KEY (role_id) REFERENCES roles_prod_serv (role_id)
);

-- categories_prod_serv
CREATE TABLE categories_prod_serv
(
category_id INT AUTO_INCREMENT PRIMARY KEY,
category_name VARCHAR(100),

);

-- sub_categories_prod_serv
CREATE TABLE sub_categories_prod_serv
(
sub_category_id INT AUTO_INCREMENT PRIMARY KEY,
sub_cat_name VARCHAR(100),
category_id INT ,
FOREIGN KEY (category_id) REFERENCES categories_prod_serv (category_id)
);

-- departments_prod_serv
CREATE TABLE departments_prod_serv
(
department_id INT AUTO_INCREMENT PRIMARY KEY,
department_name VARCHAR(100),
sub_category_id INT ,
FOREIGN KEY (sub_category_id) REFERENCES sub_categories_prod_serv (sub_category_id)
);

-- locations_prod_serv
CREATE TABLE locations_prod_serv
(
location_id INT AUTO_INCREMENT PRIMARY KEY,
location_name VARCHAR(100),
department_id INT ,
FOREIGN KEY (department_id) REFERENCES departments_prod_serv (department_id)
);


-- products_prod_serv
CREATE TABLE products_prod_serv
(
sku INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100),
location_id INT ,
FOREIGN KEY (location_id) REFERENCES locations_prod_serv (location_id)
);



