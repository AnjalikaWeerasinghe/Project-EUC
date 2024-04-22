select m.id as id, ms.status as status, count(ms.status) as status_count
from beargallery.material as m, beargallery.materialstatus as ms
where m.materialstatus_id = ms.id
group by ms.id
order by status_count;