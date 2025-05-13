create table schedule
(
    scheduleId bigint auto_increment primary key,
    authorId bigint not null comment "Refers to user.userId",
    password varchar(20) not null,
    task varchar(500) not null,
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
