create table animal_ill
(
    card_id number not null,
    illness varchar2(100) not null,
    treatment varchar2(400) not null,
    date_start date not null,
    date_end date,
    PRIMARY KEY (card_id, illness, treatment, date_start),
    CONSTRAINT fk_card_ill FOREIGN KEY (card_id) REFERENCES ANIMAL_CARD (card_id)
)
--#