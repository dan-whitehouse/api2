CREATE VIEW UserIdentifierView AS
(
	select
		si.StudentIdentifierRefId IdentifierId,
		si.StudentIdentifierSchoolYear as IdentifierSchoolYear,
		si.IdentificationSystemCode as Code,
		si.StudentId as Id,
		si.StudentRefId as SourcedId,
		si.StudentSchoolYear as SourcedSchoolYear,
		'student' as SourcedRole
	from studentidentifier as si
    where si.IdentificationSystemCode != 'SchoolToolRefId'
)
union all
(
	select
		si.StaffIdentifierRefId IdentifierId,
		si.StaffIdentifierSchoolYear as IdentifierSchoolYear,
		si.IdentificationSystemCode,
		si.StaffId,
		si.StaffRefId,
		si.StaffSchoolYear,
		'teacher'
	from staffidentifier as si
    where si.IdentificationSystemCode != 'SchoolToolRefId'
)
union all
(
	select
		si.StudentContactIdentifierRefId,
		si.StudentContactIdentifierSchoolYear,
		si.IdentificationSystemCode,
		si.StudentContactId,
		si.StudentContactRefId,
		si.StudentContactSchoolYear,
		'contact' as role
	from studentcontactidentifier as si
    where si.IdentificationSystemCode != 'SchoolToolRefId'
)
order by SourcedId, SourcedSchoolYear;


select * from UserIdentifierView;