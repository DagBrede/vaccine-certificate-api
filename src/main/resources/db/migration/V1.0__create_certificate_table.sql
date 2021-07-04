
create sequence hibernate_sequence start with 3 increment by 1;

create table certificate(
                          id int not null,
                          first_name varchar(100) not null,
                          last_name varchar(100) not null,
                          is_vaccinated boolean not null,
);

insert into certificate(id, first_name, last_name, is_vaccinated)
values (1, 'Bruce', 'Wayne', true);

insert into certificate(id, first_name, last_name, is_vaccinated)
values (2, 'Clark', 'Kent', false);