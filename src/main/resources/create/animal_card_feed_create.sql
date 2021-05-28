create table Animal_card_feed
(
    card_id     number        not null,
    feed_name   varchar2(200) not null,
    feed_amount varchar2(100) not null,
    feed_time   varchar2(5)   not null,
    constraint pk_animal_card_feed primary key (card_id, feed_name, feed_amount, feed_time)
)
--#