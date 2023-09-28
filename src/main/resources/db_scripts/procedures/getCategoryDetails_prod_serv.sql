CREATE PROCEDURE `getCategoryDetails_prod_serv`(IN location_id INT , IN department_id INT )
BEGIN

select
 l.location_id ,l.location_name
, d.department_id ,d.department_name
, c.category_id , c.category_name
from  locations_prod_serv l
  , departments_prod_serv d, categories_prod_serv c
  where  l.location_id = d.location_id    and d.department_id = c.department_id
  and l.location_id = location_id
  and d.department_id = department_id
   ;

END