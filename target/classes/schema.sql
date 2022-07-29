drop table if exists currencies;

create table currencies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ticker VARCHAR2(200) NOT NULL,
    name VARCHAR2(200) NOT NULL,
    number_of_coins VARCHAR2(200) NOT NULL,
    market_cap VARCHAR2(200) NOT NULL
);