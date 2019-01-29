CREATE VIEW UserGradeView AS
(
	select 
		se.StudentEnrollmentRefId as GradeId,
        se.StudentEnrollmentSchoolYear as GradeSchoolYear,
		se.StudentRefId as SourcedId,
		se.StudentSchoolYear as SourcedSchoolYear,
		'student' as SourcedRole,
		se.CurrentGradeLevel as GradeLevel
	from studentenrollment se
    where se.CurrentGradeLevel is not null
)
order by SourcedId, SourcedSchoolYear;


select * from UserGradeView;
