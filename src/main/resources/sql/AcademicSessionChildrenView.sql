CREATE VIEW CourseGradeView AS
select 
	#cg.CourseGradeRefId as ChildId,
    #cg.CourseGradeSchoolYear as ChildSchoolYear,
	asv.SourcedId as SourcedId, 
	asv.SourcedSchoolYear as SourcedSchoolYear
from AcademicSessionView as asv
where asv.AcademicSessionId is null 
and  asv.AcademicSessionSchoolYear is null

