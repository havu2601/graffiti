drop database if exists graffitidb;
create database if not exists graffitidb;
use graffitidb;
---------- ACCOUNT ----------->
create table user_role(
    role_id int auto_increment,
    role_name varchar(50) not null,
    primary key(role_id)
);

create table user_account(
    user_id integer auto_increment,
    user_name varchar(50) not null,
    user_email varchar(100) not null,
    password varchar(30) not null,
    user_contact varchar(11) not null,
    user_address varchar(250) not null,
    role_id integer default 1,
    primary key(user_id),
    constraint FK_user_role foreign key(role_id) references user_role(role_id)
);

---------- CATEGORY --------------->
create table category(
    category_id integer auto_increment,
    category_name varchar(50) not null,
    primary key(category_id)
);

create table sub_category(
    subcat_id integer auto_increment,
    subcat_name varchar(50) not null,
    category_id integer not null,
    primary key(subcat_id),
    constraint FK_subcat_cat foreign key(category_id) references category(category_id)
);

---------- PRODUCT --------------->
create table color(
    color_id integer auto_increment,
    color_hexcode varchar(30) not null,
    color_name varchar(200) not null,
    color_pigment varchar(30),
    color_lightfastness varchar(30),
    color_coverage varchar(30),
    color_RGB varchar(30),
    primary key(color_id)
);

create table brand(
    brand_id integer auto_increment,
    brand_name varchar(50) not null,
    brand_description varchar(250),
    primary key(brand_id)
);

create table product(
    product_id integer auto_increment,
    product_name varchar(50) not null,
    product_stock integer not null,
    product_price double not null,
    product_capacity integer not null,
    product_desc varchar(250) not null,
    product_status int default 0,
    color_id integer not null,
    brand_id integer not null,
    subcat_id integer not null,
    primary key(product_id),
    constraint FK_product_subcat foreign key(subcat_id) references sub_category(subcat_id),
    constraint FK_product_brand foreign key(brand_id) references brand(brand_id),
    constraint FK_product_color foreign key(color_id) references color(color_id)
);

create table image(
    image_id integer auto_increment,
    image_path varchar(250) not null,
    image_title varchar(200),
    product_id integer not null,
    primary key(image_id),
    constraint FK_image_product foreign key(product_id) references product(product_id)
);


---------- ORDER --------------->
create table orders(
    order_id integer auto_increment,
    order_date timestamp not null default current_timestamp,
    user_id integer not null,
    status varchar(50) default 'N',
    primary key(order_id),
    constraint FK_order_user foreign key(user_id) references user_account(user_id)
);

create table order_detail(
    order_detail_id integer auto_increment,
    order_id integer not null,
    product_id integer not null,
    product_qty integer not null,
    primary key(order_detail_id),
    constraint FK_order_orderdetail foreign key(order_id) references orders(order_id),
    constraint FK_order_product foreign key(product_id) references product(product_id)
);

----------- VIDEO --------->
create table video(
    video_id integer auto_increment,
    video_path varchar(250) not null,
    video_title varchar(200),
    primary key(video_id)
);

----------- SUPPLIER --------->
create table supplier(
    supplier_id integer auto_increment,
    supplier_name varchar(50) not null,
    supplier_email varchar(250) not null,
    supplier_contact varchar(250) not null,
    supplier_address varchar(250) not null,
    primary key(supplier_id)
);

----------- IMPORT --------->
create table import(
    import_id integer auto_increment,
    import_date timestamp not null default current_timestamp,
    supplier_id integer not null,
    user_id integer not null,
    import_status varchar(50) default 'N',
    primary key(import_id),
    constraint FK_import_user foreign key(user_id) references user_account(user_id),
    constraint FK_import_supplier foreign key(supplier_id) references supplier(supplier_id)
);

create table import_detail(
    import_detail_id integer auto_increment,
    import_id integer not null,
    product_id integer not null,
    product_qty integer not null,
    primary key(import_detail_id),
    constraint FK_import_importdetail foreign key(import_id) references import(import_id),
    constraint FK_import_product foreign key(product_id) references product(product_id)
);

----------- FAQ --------->
/*
create table faq(
    faq_id integer auto_increment,
    faq_question varchar(250) not null,
    faq_answer varchar(250) not null,
    primary key(faq_id)
);
*/
----------- FEEDBACK --------->
create table feedback(
    feedback_id integer auto_increment,
    feedback_date timestamp not null default current_timestamp,
    feedback_content varchar(250) not null,
    user_id integer not null,
    primary key(feedback_id),
    constraint FK_feedback_user foreign key(user_id) references user_account(user_id)
);

create table rep_feedback(
    rep_id integer auto_increment,
    feedback_id integer not null,
    rep_date timestamp not null default current_timestamp,
    rep_content varchar(250) not null,
    user_id integer not null,
    primary key(rep_id),
    constraint FK_rep_user foreign key(user_id) references user_account(user_id),
    constraint FK_rep_feedback foreign key(feedback_id) references feedback(feedback_id)
);
