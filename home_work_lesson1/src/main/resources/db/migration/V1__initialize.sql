drop table if exists categories cascade;
create table categories (id bigserial, title varchar(255), primary key(id));
insert into categories
(title) values
('Fruits'),
('Vegetables'),
('Other'),
('Berries');

drop table if exists products cascade;
create table products (id bigserial, title varchar(255), description varchar(5000), price int, primary key(id));
insert into products
(title, description, price) values
('Cheese', 'Fresh cheese', 320),
('Milk', 'Fresh milk', 80),
('Apples', 'Fresh apples', 80),
('Bread', 'Fresh bread', 30),
('Cabbage', '', 1),
('Strawberry', '', 2),
('Raspberry', '', 3),
('Oranges', '', 4),
('Anion', '', 5),
('A6', '', 6),
('A7', '', 7),
('A8', '', 8),
('A9', '', 9),
('A10', '', 10),
('A11', '', 11),
('A12', '', 12),
('A13', '', 13),
('A14', '', 14),
('A15', '', 15),
('A16', '', 16),
('A17', '', 17),
('A18', '', 18),
('A19', '', 19),
('A20', '', 20);


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