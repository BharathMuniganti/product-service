CREATE  PROCEDURE `getProductDetails_prod_serv`(IN location_name VARCHAR(100) , IN department_name VARCHAR(100) ,
 IN category_name VARCHAR(100) , IN sub_cat_name VARCHAR(100) )
BEGIN

-- start getProductDetails
select  p.sku , p.name
, l.location_id ,l.location_name
, d.department_id ,d.department_name
, s.sub_category_id , s.sub_cat_name
, c.category_id , c.category_name
from products_prod_serv  p, locations_prod_serv l
  , departments_prod_serv d, sub_categories_prod_serv s , categories_prod_serv c
  where p.sub_category_id = s.sub_category_id
  and s.category_id = c.category_id
  and c.department_id = d.department_id
  and d.location_id = l.location_id
  and l.location_name = location_name
  and d.department_name = department_name
  and s.sub_cat_name  = sub_cat_name
  and c.category_name = category_name ;

END