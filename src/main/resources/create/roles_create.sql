create role zoo_manager
--#
create role zoo_vet
--#

grant connect to zoo_manager
--#
grant alter session to zoo_manager
--#
grant select any table to zoo_manager
--#
grant select on EMPLOYEE_SEQUENCE to zoo_manager
--#
grant insert, update, delete on EMPLOYEE to zoo_manager
--#
grant insert, update, delete on EMPLOYEE_ANIMAL to zoo_manager
--#
grant insert, update, delete on EMPLOYEE_ANIMAL_CARD to zoo_manager
--#
grant insert, update, delete on MANAGER to zoo_manager
--#
grant insert, update, delete on VET to zoo_manager
--#
grant insert, update, delete on CLEANER to zoo_manager
--#
grant insert, update, delete on CONSTRUCTOR to zoo_manager
--#
grant insert, update, delete on TRAINER to zoo_manager
--#
grant insert, update, delete on FEED to zoo_manager
--#
grant insert, update, delete on FEED_SUPPLIER to zoo_manager
--#
grant insert, update, delete on SUPPLY to zoo_manager
--#

grant connect to zoo_vet
--#
grant alter session to zoo_vet
--#
grant select any table to zoo_vet
--#
grant select on ANIMAL_CARD_SEQUENCE to zoo_vet
--#
grant insert, update, delete on ANIMAL_CARD to zoo_vet
--#
grant insert, update, delete on ANIMAL_ILL to zoo_vet
--#
grant insert, update, delete on ANIMAL_CARD_FEED to zoo_vet
--#
grant insert, update, delete on ANIMAL_OFFSPRING to zoo_vet
--#
grant insert, update, delete on ANIMAL_VACCINE to zoo_vet
--#
