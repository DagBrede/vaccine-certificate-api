
create sequence hibernate_sequence start with 1 increment by 1;

create table certificate(
                          id int not null,
                          first_name varchar(100) not null,
                          last_name varchar(100) not null,
                          national_identity_number varchar(11) not null
);

create table vaccination(
    id int not null,
    dose int,
    date date,
    type varchar(100),
    CERTIFICATE_ID int not null,
    foreign key (CERTIFICATE_ID) references certificate(id)
)

/*insert into certificate(id, first_name, last_name, is_vaccinated)
values (1, 'Bruce', 'Wayne', true);

insert into certificate(id, first_name, last_name, is_vaccinated)
values (2, 'Clark', 'Kent', false);*/