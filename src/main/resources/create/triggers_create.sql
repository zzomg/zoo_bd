CREATE SEQUENCE employee_sequence START WITH 1
--#

CREATE SEQUENCE animal_card_sequence START WITH 1
--#

CREATE SEQUENCE supply_sequence START WITH 1
--#

--триггер для автоинкремента айди сотрудников
CREATE TRIGGER employee_on_insert
    BEFORE INSERT
    ON Employee
    FOR EACH ROW
BEGIN
    SELECT employee_sequence.nextval
    INTO :new.emp_id
    FROM dual;
END;
--#

--триггер для автоинкремента айди поставок
CREATE TRIGGER supplies_on_insert
    BEFORE INSERT
    ON Supply
    FOR EACH ROW
BEGIN
    SELECT supply_sequence.nextval
    INTO :new.supply_id
    FROM dual;
END;
--#

--триггер для установки доступа к клеткам животных у сотрудников
create trigger EMPLOYEE_CAGE_ALLOW_TRIGGER
    before insert
    on EMPLOYEE
    for each row
DECLARE
    rowcnt number;
BEGIN
    SELECT COUNT(*)
    INTO rowcnt
    FROM JOB_CAGE_ALLOW
    WHERE JOB_TYPE like :new.job_type;
    IF rowcnt = 0 THEN
        SELECT 0
        INTO :new.cage_allow
        from dual;
    ELSE
        SELECT 1
        INTO :new.cage_allow
        from dual;
    END IF;
END;
--#

--триггер для автоинкремента айди карт животных
CREATE TRIGGER animal_card_on_insert
    BEFORE INSERT
    ON Animal_card
    FOR EACH ROW
BEGIN
    SELECT animal_card_sequence.nextval
    INTO :new.card_id
    FROM dual;
END;
--#

create trigger animal_card_warm_cage
    before insert
    on Animal_card
    for each row
BEGIN
    SELECT REQ_WARM_CAGE
    INTO :new.req_warm_cage
    FROM ANIMAL
    WHERE ANIMAL.ANIMAL_NAME = :new.animal_name;
END;
--#

--триггер для вставки во вью
CREATE OR REPLACE trigger emp_animal_view_on_insert
    instead of insert
    on EMPLOYEE_ANIMAL_VIEW
    for each row
begin
    insert into Employee_Animal
    VALUES (:new.emp_id, :new.animal_name, :new.DATE_START, :new.DATE_END);
end;
--#

--триггер для вставки во вью
CREATE OR REPLACE trigger emp_animal_card_view_on_insert
    instead of insert
    on EMPLOYEE_ANIMAL_card_VIEW
    for each row
begin
    insert into Employee_Animal_card
    VALUES (:new.emp_id, :new.card_id, :new.DATE_START, :new.DATE_END);
end;
--#

--триггер для пометки больного животного
CREATE OR REPLACE trigger animal_card_ill_trigger
    after insert
    on ANIMAL_ILL
    for each row
begin
    update ANIMAL_CARD
        set ill = 1
    where ANIMAL_CARD.CARD_ID = :new.card_id;
end;
--#
