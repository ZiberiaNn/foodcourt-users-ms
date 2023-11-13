-- Inserts all roles needed.
insert
	into
	role_table (role_id,
	name)
values(1,
"ADMIN"),
(2,
"OWNER"),
(3,
"EMPLOYEE"),
(4,
"CLIENT");

-- Inserts admin user with email "admin@admin.com" and password "admin" encrypted with bcrypt.
insert
	into
	foodcourt_users.user_table (birth_date,
	email,
	identity_document,
	last_name,
	name,
	password,
	phone)
values (null,
'admin@admin.com',
0,
null,
null,
'$2y$10$cU9geSUKorAHVCbVtzHDLuTQi3Smk5sZ47bCUFdrJ0i0oWmeuyf7q',
null);
-- Links admin user with role "ADMIN" (which has id "1")
set
@admin_user_id = LAST_INSERT_ID();

insert
	into
	foodcourt_users.user_table_roles (user_entity_user_id,
	roles_role_id)
values (@admin_user_id,
1);