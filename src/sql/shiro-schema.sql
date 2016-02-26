drop table if exists sys_user_role;
drop table if exists sys_resource_role;
drop table if exists sys_user;
drop table if exists sys_role;
drop table if exists sys_organization;
drop table if exists sys_resource;

create table sys_organization (
  id bigint auto_increment,
  name varchar(100),
  parent_id bigint,
  parent_ids varchar(100),
  available bool default false,
  constraint pk_sys_organization primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_organization_parent_id on sys_organization(parent_id);

create table sys_user (
  id bigint auto_increment,
  organization_id bigint not null,
  username varchar(100),
  password varchar(100),
  salt varchar(100),
  locked bool default false,
  constraint pk_sys_user primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_user_username on sys_user(username);
create index idx_sys_user_organization_id on sys_user(organization_id);
alter table sys_user add constraint fk_user_organization foreign key(organization_id) references sys_organization(id) ;

create table sys_resource (
  id bigint auto_increment,
  name varchar(100),
  type varchar(50),
  url varchar(200),
  parent_id bigint,
  parent_ids varchar(100),
  permission varchar(100),
  available bool default false,
  constraint pk_sys_resource primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_resource_parent_id on sys_resource(parent_id);
create index idx_sys_resource_parent_ids on sys_resource(parent_ids);

create table sys_role (
  id bigint auto_increment,
  role varchar(100),
  description varchar(100),
  available bool default false,
  constraint pk_sys_role primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table sys_resource_role (
  resource_id bigint not null,
  role_id bigint not null,
  PRIMARY KEY(resource_id,role_id)
) charset=utf8 ENGINE=InnoDB;
alter table sys_resource_role add constraint fk_resource_role_resource foreign key(resource_id) references sys_resource(id) ;
alter table sys_resource_role add constraint fk_resource_role_role foreign key(role_id) references sys_role(id) ;

create table sys_user_role (
  user_id bigint not null, 
  role_id bigint not null,
   PRIMARY KEY(user_id,role_id)
) charset=utf8 ENGINE=InnoDB;
alter table sys_user_role add constraint fk_user_role_user foreign key(user_id) references sys_user(id) ;
alter table sys_user_role add constraint fk_user_role_role foreign key(role_id) references sys_role(id) ;
