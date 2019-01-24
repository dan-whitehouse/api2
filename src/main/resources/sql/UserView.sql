CREATE VIEW UserView AS
(
	select
		s.StudentRefId as SourcedId, 
		s.StudentSchoolYear as SourcedSchoolYear,
		'student' as Role,
		true as EnabledUser,
		s.FirstName as GivenName, 
		s.LastName as FamilyName, 
		s.MiddleName as MiddleName,
		si.StudentId as Identifier,
		sem.EmailAddress as Email,
		st.TelephoneNumber as Phone,
		null as Sms
	from Student as s
	left join studentidentifier as si 
		on s.StudentRefId = si.StudentRefId 
        and s.StudentSchoolYear = si.StudentSchoolYear 
        and si.IdentificationSystemCode = 'District'
	left join studentemail as sem 
		on s.StudentRefId = sem.StudentRefId 
        and s.StudentSchoolYear = sem.StudentSchoolYear 
        and sem.PrimaryEmailAddressIndicator = true
	left join studenttelephone as st 
		on s.StudentRefId = st.StudentRefId 
        and s.StudentSchoolYear = st.StudentSchoolYear 
        and st.PrimaryTelephoneNumberIndicator = true
)
union all
(
	select
		t.StaffRefId, 
		t.StaffSchoolYear,
		'teacher',
		true,
		t.FirstName, 
		t.LastName, 
		t.MiddleName,
		ti.StaffId,
		tem.EmailAddress,
		null,
		null
	from Staff as t
	left join staffidentifier as ti 
		on t.StaffRefId = ti.StaffRefId 
        and t.StaffSchoolYear = ti.StaffSchoolYear 
        and ti.IdentificationSystemCode = 'District'
	left join staffemail as tem 
		on t.StaffRefId = tem.StaffRefId 
        and t.StaffSchoolYear = tem.StaffSchoolYear 
        and tem.PrimaryEmailAddressIndicator = true
)
union all
(
	select
		sc.StudentContactRefId, 
		sc.StudentContactSchoolYear,
		'contact',
		true,
		sc.FirstName, 
		sc.LastName, 
		sc.MiddleName,
		sci.StudentContactId,
		scem.EmailAddress,
		sct.TelephoneNumber,
		null
	from StudentContact as sc
	left join studentcontactidentifier as sci 
		on sc.StudentContactRefId = sci.StudentContactRefId 
        and sc.StudentContactSchoolYear = sci.StudentContactSchoolYear 
        and sci.IdentificationSystemCode = 'District'
	left join studentcontactemail as scem 
		on sc.StudentContactRefId = scem.StudentContactRefId 
        and sc.StudentContactSchoolYear = scem.StudentContactSchoolYear 
        and scem.PrimaryEmailAddressIndicator = true
	left join studentcontacttelephone as sct 
		on sc.StudentContactRefId = sct.StudentContactRefId 
        and sc.StudentContactSchoolYear = sct.StudentContactSchoolYear 
        and sct.PrimaryTelephoneNumberIndicator = true
)
order by SourcedId, SourcedSchoolYear;

select * from UserView;