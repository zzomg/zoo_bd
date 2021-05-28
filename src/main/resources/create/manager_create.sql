create table manager
(
    emp_id    number primary key,
    education varchar2(100) not null,
    foreign key (emp_id) references EMPLOYEE (EMP_ID)
)