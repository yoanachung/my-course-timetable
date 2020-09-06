drop table if exists course CASCADE;
drop table if exists section CASCADE;
drop table if exists section_time CASCADE;
drop table if exists time_table CASCADE;
drop table if exists time_table_cell CASCADE;

create table course (
   id bigint generated by default as identity,
    credit tinyint not null,
    title varchar(255) not null,
    primary key (id)
);

create table section (
   id bigint generated by default as identity,
    course_id bigint not null,
    primary key (id)
);

create table section_time (
   id bigint generated by default as identity,
    period varchar(5) not null,
    period_count tinyint not null,
    weekday varchar(10) not null,
    section_id bigint not null,
    primary key (id)
);

create table time_table (
   id bigint generated by default as identity,
    primary key (id)
);

create table time_table_cell (
   id bigint generated by default as identity,
    section_id bigint not null,
    time_table_id bigint,
    primary key (id)
);

alter table section
   add constraint FKoy8uc0ftpivwopwf5ptwdtar0
   foreign key (course_id)
   references course;

alter table section_time
   add constraint FK7sckthqgaq94ywxt4q2q2rooi
   foreign key (section_id)
   references section;

alter table time_table_cell
   add constraint FKrxl4nxi2o5a1ybkrhyshf03sl
   foreign key (time_table_id)
   references time_table;