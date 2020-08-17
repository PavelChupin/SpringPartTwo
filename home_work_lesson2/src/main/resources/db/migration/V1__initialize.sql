drop table if exists categories cascade;
create table categories (id bigserial, title varchar(255), primary key(id));
insert into categories
(title) values
('Fruits'),
('Vegetables'),
('Other'),
('Berries');

drop table if exists products cascade;
create table products (id bigserial, title varchar(255), description varchar(5000), price numeric(8,2), primary key(id));
insert into products
(title, description, price) values
('Cheese', 'Fresh cheese', 320.0),
('Milk', 'Fresh milk', 80.0),
('Apples', 'Fresh apples', 80.0),
('Bread', 'Fresh bread', 30.0),
('Cabbage', '', 1.0),
('Strawberry', '', 2.0),
('Raspberry', '', 3.0),
('Oranges', '', 4.0),
('Anion', '', 5.0),
('A6', '', 6.0),
('A7', '', 7.0),
('A8', '', 8.0),
('A9', '', 9.0),
('A10', '', 10.0),
('A11', '', 11.0),
('A12', '', 12.0),
('A13', '', 13.0),
('A14', '', 14.0),
('A15', '', 15.0),
('A16', '', 16.0),
('A17', '', 17.0),
('A18', '', 18.0),
('A19', '', 19.0),
('A20', '', 20.0);


drop table if exists incl_prod_in_categ;
create table incl_prod_in_categ (id_product bigserial, id_category bigserial);
insert into incl_prod_in_categ
(id_product, id_category)
select p.id
      ,case p.title when 'Strawberry' then (select c.id from categories c where c.title = 'Berries')
                    when 'Raspberry' then (select c.id from categories c where c.title = 'Berries')
                    when 'Apples' then (select c.id from categories c where c.title = 'Fruits')
                    when 'Oranges' then (select c.id from categories c where c.title = 'Fruits')
                    when 'Cabbage' then (select c.id from categories c where c.title = 'Vegetables')
                    when 'Anion' then (select c.id from categories c where c.title = 'Vegetables')
                    else (select c.id from categories c where c.title = 'Other')
        end
  from products p;

insert into incl_prod_in_categ
(id_product, id_category)
select p.id
      ,case p.title when 'A6' then (select c.id from categories c where c.title = 'Berries')
                    when 'A7' then (select c.id from categories c where c.title = 'Vegetables')
        end
  from products p
  where p.title in ('A6','A7');