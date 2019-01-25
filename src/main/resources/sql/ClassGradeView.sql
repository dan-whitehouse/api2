CREATE VIEW ClassGradeView AS
select 
	cg.CourseGradeRefId as GradeId,
    cg.CourseGradeSchoolYear as GradeSchoolYear,
    cg.GradeLevelCode as GradeLevel,
	cs.CourseSectionRefId as SourcedId, 
	cs.CourseSectionSchoolYear as SourcedSchoolYear
from coursesection as cs
join coursegrade as cg 
	on cg.CourseRefId = cs.CourseRefId
    and cg.CourseSchoolYear = cs.CourseSchoolYear
where cg.GradeLevelCode is not null;


