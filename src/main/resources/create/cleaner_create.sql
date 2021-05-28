create table cleaner
(
    emp_id       number primary key,
    cleaner_type varchar2(100) not null,
    foreign key (emp_id) references EMPLOYEE (EMP_ID)
)