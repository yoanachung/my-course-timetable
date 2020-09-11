alter table time_table_cell
    add column course_id bigint not null;

alter table time_table_cell
    add constraint time_table_cell__section_FK
    foreign key (section_id)
    references section
    on update cascade on delete restrict;

alter table time_table_cell
    add constraint time_table_cell__course_FK
    foreign key (course_id)
    references section
    on update cascade on delete restrict;
