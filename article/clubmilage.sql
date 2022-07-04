#hibernate_sequence
-- auto-generated definition
create table hibernate_sequence
(
    next_val bigint null
);

# user
-- auto-generated definition
create table user
(
    user_no     bigint       not null
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    user_email  varchar(255) not null,
    user_id     varchar(255) null,
    constraint UK_a3imlf41l37utmxiquukk8ajc
        unique (user_id),
    constraint UK_j09k2v8lxofv2vecxu2hde9so
        unique (user_email)
);

create index i_userId
    on user (user_id);

# place
-- auto-generated definition
create table place
(
    place_no          bigint       not null
        primary key,
    created_at        datetime(6)  null,
    modified_at       datetime(6)  null,
    place_description varchar(255) null,
    place_id          varchar(255) null,
    place_name        varchar(255) null,
    constraint UK_lb7b53a9rp1g3me8ler9ci2eb
        unique (place_id)
);

create index i_placeId
    on place (place_id);

# review
-- auto-generated definition
create table review
(
    review_no      bigint       not null
        primary key,
    created_at     datetime(6)  null,
    modified_at    datetime(6)  null,
    review_content varchar(255) null,
    review_id      varchar(255) null,
    use_yn         varchar(255) null,
    place_no       bigint       null,
    user_no        bigint       null,
    constraint UK_r5l24vpi1wf35q8h3e7lejuar
        unique (review_id),
    constraint FKhctu9q3140kf2fsn2nfqr45ur
        foreign key (user_no) references user (user_no),
    constraint FKmkoyb9buxflbbm1vkl38u9hki
        foreign key (place_no) references place (place_no)
);

# review_hist
-- auto-generated definition
create table review_hist
(
    review_hist_no bigint       not null
        primary key,
    action_type    varchar(255) null,
    bonus_point    int          null,
    create_at      datetime(6)  null,
    review_point   int          null,
    review_no      bigint       null,
    constraint FKaa5hcn3o59sf67pjlroa7obb
        foreign key (review_no) references review (review_no)
);

create index i_createAt
    on review_hist (create_at);

create index i_reviewHistNo
    on review_hist (review_hist_no);

# photo
-- auto-generated definition
create table photo
(
    photo_no        bigint       not null
        primary key,
    photo_file_path varchar(255) null,
    photo_id        varchar(255) null,
    review_id       bigint       null,
    constraint UK_422i4mxf7udf8hnvl7bnkedgy
        unique (photo_id),
    constraint FKnx36dfpxbxmxifyiq7yvhiohn
        foreign key (review_id) references review (review_no)
);

