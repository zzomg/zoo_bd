CREATE USER MANAGER IDENTIFIED BY manager
--#
GRANT zoo_manager TO MANAGER
--#
CREATE USER VET IDENTIFIED BY vet
--#
GRANT zoo_vet TO VET
--#
insert into zoo_user(username, user_role) values('MANAGER', 'zoo_manager')
--#
insert into zoo_user(username, user_role) values('VET', 'zoo_vet')
--#