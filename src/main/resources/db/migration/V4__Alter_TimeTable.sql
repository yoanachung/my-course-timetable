alter table time_table
    add column time_table_list_id bigint not null;

alter table time_table
    add constraint time_table__time_table_list_FK
    foreign key (time_table_list_id)
    references time_table_list
    on update cascade on delete restrict;
