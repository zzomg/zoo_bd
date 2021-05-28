--таблица сотрудников и отдельных особей животных, за которых они ответственны
CREATE TABLE Employee_Animal_card
(
    emp_id  number NOT NULL,
    card_id number NOT NULL,
    date_start date,
    date_end date,
    PRIMARY KEY (emp_id, card_id),
    FOREIGN KEY (emp_id) REFERENCES Employee (emp_id),
    FOREIGN KEY (card_id) REFERENCES Animal_card (card_id)
)
--#

create forms Employee_animal_card_view as
select emp_id, FIRST_NAME, LAST_NAME, card_id, animal_name, cage_number, date_start, date_end
from Employee_Animal_card join Employee using (emp_id)
join ANIMAL_CARD using (card_id)
--#
