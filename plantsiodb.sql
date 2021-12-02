CREATE DATABASE plantsiotdb;
USE plantsiotdb;

CREATE TABLE plantsiottable (id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,plant_moisture INT(11) NOT NULL,plant_temp INT(11) NOT NULL, plant_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP);


