--таблица видов животных
create table Animal
(
    animal_name   varchar2(160) primary key,
    animal_type   varchar2(80) not null,
    req_warm_cage number(1)    not null,
    CONSTRAINT req_warm_cage_chk CHECK (req_warm_cage = 0 or req_warm_cage = 1)
)
--#



