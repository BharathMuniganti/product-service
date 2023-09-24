insert into categories_prod_serv(category_name ) values ('Bakery Bread') ;
insert into categories_prod_serv(category_name ) values ('In Store Bakery') ;
insert into categories_prod_serv(category_name ) values ('Cheese') ;
insert into categories_prod_serv(category_name ) values ('Cream or Creamer') ;
insert into categories_prod_serv(category_name ) values ('Cultured') ;
insert into categories_prod_serv(category_name ) values ('Refrigerated Baking') ;
insert into categories_prod_serv(category_name ) values ('Self Service Deli Cold') ;
insert into categories_prod_serv(category_name ) values ('Bouquets and Cut Flowers') ;
insert into categories_prod_serv(category_name ) values ('Gifts') ;
insert into categories_prod_serv(category_name ) values ('Plants') ;
insert into categories_prod_serv(category_name ) values ('Frozen Bake') ;
insert into categories_prod_serv(category_name ) values ('Frozen Breakfast') ;


insert into sub_categories_prod_serv(sub_cat_name ,category_id ) values ('Bagels',1) ;

insert into departments_prod_serv(department_name ,sub_category_id ) values ('Bakery',1) ;

insert into locations_prod_serv(location_name ,department_id ) values ('Perimeter',1) ;

insert into products_prod_serv(sku,name ,location_id ) values (14,'SKUDESC14',1) ;
