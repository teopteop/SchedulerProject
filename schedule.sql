create table schedule
(
    scheduleId bigint auto_increment primary key,
    authorId bigint not null comment "Refers to user.userId",
    password varchar(20) not null,
    task varchar(200) not null,
    createDate timestamp default current_timestamp,
    lastModifiedDate timestamp default current_timestamp on update  current_timestamp,
    foreign key (authorId) references user(userId)
);

create table user
(
    userId bigint auto_increment primary key,
    name varchar(20) not null,
    email varchar(50) not null,
    createDate timestamp default current_timestamp,
    lastModifiedDate timestamp default current_timestamp on update  current_timestamp
);

insert into user (name, email) values ('김자바','java2233@gmail.com');
insert into user (name, email) values ('이자바','java1212@gmail.com');
insert into user (name, email) values ('최자바','java3333@gmail.com');
insert into user (name, email) values ('유자바','java0707@gmail.com');
insert into user (name, email) values ('김봄','spring0514@gmail.com');
insert into user (name, email) values ('이봄','spring2232@gmail.com');
