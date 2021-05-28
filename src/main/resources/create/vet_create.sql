create table vet
(
    emp_id       number primary key,
    animal_group varchar2(200) not null,
    education    varchar2(100) not null,
    foreign key (emp_id) references EMPLOYEE (EMP_ID)
)
--#