CREATE VIEW OrgChildrenView AS
select 
	sch.SchoolRefId as ChildId,
    sch.SchoolSchoolYear as ChildSchoolYear,
	ov.SourcedId as SourcedId, 
	ov.SourcedSchoolYear as SourcedSchoolYear
from OrgView as ov
join school sch 
	on 	sch.LEARefId = ov.SourcedId
    and sch.LEASchoolYear = ov.SourcedSchoolYear
where ov.ParentId is null 
and  ov.ParentSchoolYear is null