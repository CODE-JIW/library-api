create table tb_author(
    id uuid not null primary key,
    name varchar(100) not null,
    biography varchar(1000) not null,
    birth_date date not null
)