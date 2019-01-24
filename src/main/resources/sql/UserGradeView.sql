CREATE VIEW UserGradeView AS
(
	select 
		se.StudentRefId as SourcedId,
		se.StudentSchoolYear as SourcedSchoolYear,
		'student' as SourcedRole,
		se.CurrentGradeLevel as GradeLevel
	from studentenrollment se
)
order by SourceId, SourceSchoolYear;


select * from UserGradeView;
