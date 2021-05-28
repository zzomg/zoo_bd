--таблица типов корма
create table Feed_type
(
    feed_type varchar2(80) primary key
)
--#

--таблица кормов
create table Feed
(
    feed_name varchar2(200) primary key,
    feed_type varchar2(80) not null,
    CONSTRAINT fk_feed_type FOREIGN KEY (feed_type) REFERENCES Feed_type (feed_type)
)
--#
