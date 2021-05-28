create table Supply
(
    supply_id number primary key,
    feed_supplier_name varchar2(200) not null,
    feed_name          varchar2(200) not null,
    amount varchar2(100) not null,
    price number(*, 2) not null,
    supply_date date not null,
    CONSTRAINT fk_feed_name FOREIGN KEY (feed_name) REFERENCES Feed (feed_name),
    CONSTRAINT fk_feed_supplier_name FOREIGN KEY (feed_supplier_name)
        REFERENCES Feed_supplier (feed_supplier_name)
)
--#