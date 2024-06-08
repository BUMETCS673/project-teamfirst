CREATE TABLE UM_USER_ROLE ( 
	id              int UNSIGNED NOT NULL,
	name 			varchar(50)  NOT NULL,
	created_at      timestamp,
	updated_at      timestamp,
	CONSTRAINT pk_user_role PRIMARY KEY (id),
	CONSTRAINT user_role_name_unique UNIQUE (name) 
 );

CREATE TABLE UM_USER( 
	id                   bigint UNSIGNED NOT NULL AUTO_INCREMENT,
	identity_provider_id varchar(255) NOT NULL,
	firstname            varchar(200),
	lastname             varchar(200),
	email                varchar(255) NOT NULL,
	username             varchar(200),
	phone                varchar(50),
	city                 varchar(70),
	state                varchar(70),
	postal_code          varchar(15),
	country              varchar(70),
	user_role_id         int UNSIGNED,
	team_id              bigint UNSIGNED,
	created_at           timestamp,
	updated_at           timestamp,
	CONSTRAINT pk_user PRIMARY KEY (id),
	CONSTRAINT fk_user_role FOREIGN KEY(user_role_id) REFERENCES UM_USER_ROLE(id),
	CONSTRAINT user_email_unique UNIQUE (email) ,
	CONSTRAINT identity_provider_id_unique UNIQUE (identity_provider_id) ,
	CONSTRAINT user_username_unique UNIQUE (username) 
);

CREATE INDEX user_team_id_foreign ON UM_USER ( team_id );
CREATE INDEX users_user_role_id_foreign ON UM_USER ( user_role_id );
INSERT INTO UM_USER_ROLE(id, name, created_at, updated_at) VALUES ( 1, 'ADMIN', '2022-03-26 17:54:38.0', '2022-03-26 17:54:38.0' ); 
INSERT INTO UM_USER_ROLE(id, name, created_at, updated_at) VALUES ( 2, 'STUDENT', '2022-03-26 17:54:38.0', '2022-03-26 17:54:38.0' ); 
INSERT INTO UM_USER_ROLE(id, name, created_at, updated_at) VALUES ( 3, 'INSTRUCTOR', '2022-03-26 17:54:38.0', '2022-03-26 17:54:38.0' );
INSERT INTO UM_USER_ROLE(id, name, created_at, updated_at) VALUES ( 4, 'ADMINISTRATOR', '2022-03-26 17:54:38.0', '2022-03-26 17:54:38.0' ); 