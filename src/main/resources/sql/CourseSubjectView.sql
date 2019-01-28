CREATE VIEW CourseSubjectView AS
select 
	c.CourseRefId as SubjectId,
    c.CourseSchoolYear as SubjectSchoolYear,
    c.SubjectCode as Subject,
    c.SCEDCourseCode as SubjectCode,
	c.CourseRefId as SourcedId, 
	c.CourseSchoolYear as SourcedSchoolYear
from course as c
where c.SubjectCode is not null 
or c.SCEDCourseCode is not null;

