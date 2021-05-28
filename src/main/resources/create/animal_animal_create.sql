--таблица животных, которых можно селить рядом
CREATE TABLE animal_animal
(
    animal_name_first   varchar2(160) NOT NULL,
    animal_name_second   varchar2(160) not null,
    PRIMARY KEY (animal_name_first, animal_name_second),
    FOREIGN KEY (animal_name_first) REFERENCES Animal (animal_name),
    FOREIGN KEY (animal_name_second) REFERENCES Animal (animal_name)
)
--#