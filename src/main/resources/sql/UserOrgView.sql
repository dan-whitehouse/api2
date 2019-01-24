CREATE VIEW UserOrgView AS
(
	select 
		sen.SchoolRefId as OrgId,
		sen.SchoolSchoolYear as OrgSchoolYear,
		'school' as OrgType,
		sen.StudentRefId as SourcedId,
		sen.StudentSchoolYear as SourcedSchoolYear,
		'student' as SourcedRole
	from studentenrollment as sen
)
union all
(
	select 
		sch.LEARefId,
		sch.LEASchoolYear,
		'district',
		sen.StudentRefId,
		sen.StudentSchoolYear,
		'student'
	from studentenrollment as sen
	left join school as sch 
		on sen.SchoolRefId = sch.SchoolRefId 
        and sen.SchoolSchoolYear = sch.SchoolSchoolYear
)
union all
(
	select 
		ta.SchoolRefId,
		ta.SchoolSchoolYear,
		'school' as OrgType,
		ta.StaffRefId,
		ta.StaffSchoolYear,
		'teacher'
	from staffassignment as ta
)
union all
(
	select 
		sch.LEARefId,
		sch.LEASchoolYear,
		'district',
		ta.StaffRefId,
		ta.StaffSchoolYear,
		'teacher'
	from staffassignment as ta
	left join school as sch 
		on sch.SchoolRefId = ta.SchoolRefId 
        and sch.SchoolSchoolYear = ta.SchoolSchoolYear
)
union all
(
	select 
		se.SchoolRefId,
		se.SchoolSchoolYear,
		'school',
		scr.StudentContactRefId,
		scr.StudentContactSchoolYear,
		'contact'
	from studentcontactrelationship as scr
	left join student s 
		on s.StudentRefId = scr.StudentRefId 
        and s.StudentSchoolYear = scr.StudentSchoolYear
	left join studentenrollment se 
		on se.StudentRefId = s.StudentRefId 
        and se.StudentSchoolYear = s.StudentSchoolYear
)
union all
(
	select 
		sch.LEARefId,
		sch.LEASchoolYear,
		'district',
		scr.StudentContactRefId,
		scr.StudentContactSchoolYear,
		'contact'
	from studentcontactrelationship as scr
	left join student s 
		on s.StudentRefId = scr.StudentRefId 
        and s.StudentSchoolYear = scr.StudentSchoolYear
	left join studentenrollment se 
		on se.StudentRefId = s.StudentRefId 
        and se.StudentSchoolYear = s.StudentSchoolYear
	left join school as sch 
		on sch.SchoolRefId = se.SchoolRefId 
        and sch.SchoolSchoolYear = se.SchoolSchoolYear
)
order by SourcedId, SourcedSchoolYear;

select * from UserOrgView;