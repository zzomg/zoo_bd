--таблица карточек особей животных в зоопарке
create table Animal_card
(
    card_id        number primary key,
    animal_name    varchar2(160) not null,
    cage_number    number(*)     not null,
    weight         number(*, 2)  not null,
    height         number(*, 2)  not null,
    sex            varchar2(60)  not null,
    birth_date     date          not null,
    admission_date date          not null,
    ill            number(1)     not null,
    isolated       number(1)     not null,
    req_warm_cage  number(1)     not null,
    CONSTRAINT fk_card_animal_name FOREIGN KEY (animal_name) REFERENCES Animal (animal_name),
    CONSTRAINT ill_chk CHECK (ill = 0 or ill = 1),
    CONSTRAINT animal_sex_chk CHECK (sex = 'самец' or sex = 'самка'),
    CONSTRAINT isolated_chk CHECK (isolated = 0 or isolated = 1)
)
--#