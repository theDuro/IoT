

DROP TABLE IF EXISTS users;
CREATE TABLE users (
   user_id INT(6) UNSIGNED AUTO_INCREMENT ,
   first_name VARCHAR (20),
   pasword VARCHAR(256),
   token VARCHAR(24)

);

DROP TABLE IF EXISTS comands;
CREATE TABLE comands (
     comand_id INT(6) UNSIGNED AUTO_INCREMENT ,
     led_limit_value FLOAT (1),
     led_frequency FLOAT (1),
     engine_power FLOAT(1),
     user_id  INT(6),
);







