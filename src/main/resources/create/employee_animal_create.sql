--таблица сотрудников и видов животных, за которых они ответственны
CREATE TABLE Employee_Animal
(
    emp_id      number        NOT NULL,
    animal_name varchar2(160) NOT NULL,
    date_start date,
    date_end date,
    PRIMARY KEY (emp_id, animal_name),
    FOREIGN KEY (emp_id) REFERENCES Employee (emp_id),
    FOREIGN KEY (animal_name) REFERENCES Animal (animal_name)
)
--#

--вью для получения имени, фамилии сотрудника
create forms Employee_animal_view as
    select emp_id, FIRST_NAME, LAST_NAME, animal_name, date_start, date_end
from Employee_Animal join Employee using (emp_id)
--#
