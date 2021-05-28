CREATE USER mng IDENTIFIED BY manager
--#
GRANT zoo_manager TO MANAGER
--#
CREATE USER vet IDENTIFIED BY vet
--#
GRANT zoo_vet TO VET
--#
insert into zoo_user(username, user_role) values('mng', 'zoo_manager')
--#
insert into zoo_user(username, user_role) values('vet', 'zoo_vet')
--#