CREATE VIEW CourseGradeView AS
select 
	cg.CourseGradeRefId as GradeId,
    cg.CourseGradeSchoolYear as GradeSchoolYear,
    cg.GradeLevelCode as GradeLevel,
	c.CourseRefId as SourcedId, 
	c.CourseSchoolYear as SourcedSchoolYear
from course as c
join coursegrade as cg 
	on cg.CourseRefId = c.CourseRefId
    and cg.CourseSchoolYear = c.CourseSchoolYear
where cg.GradeLevelCode is not null;
