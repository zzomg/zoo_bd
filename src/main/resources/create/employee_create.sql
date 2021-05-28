--таблица сотрудников
create table Employee
(
    emp_id     number primary key,
    first_name varchar2(80)  not null,
    last_name  varchar2(80)  not null,
    hire_date  date          not null,
    sex        varchar2(40)  not null,
    age        number    not null,
    salary     number(*, 2)  not null,
    job_type   varchar2(80)  not null,
    cage_allow number(1) not null,

    CONSTRAINT emp_sex_chk CHECK (sex = 'М' or sex = 'Ж'),
    CONSTRAINT cage_allow_chk CHECK (cage_allow = 0 or cage_allow = 1),
    CONSTRAINT salary_chk CHECK (salary >= 0),
    CONSTRAINT emp_age_chk CHECK (age >= 18)
)
--#

create table job_cage_allow (
    job_type Varchar2(40) not null primary key
)
--#
