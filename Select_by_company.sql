select c.name, p.name from person p join company c on p.company_id = c.id where c.id != 5 order by c.name, p.name asc;

select c.name, count(*) from company c join person p on p.company_id = c.id 
group by c.name 
having count(*) = (select count(*) from person as p group by p.company_id order by 1 desc limit 1);
