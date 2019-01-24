CREATE VIEW OrgView AS
(
	select 	
		l.LEARefId as LEARefId, 
		l.LEASchoolYear as LEASchoolYear, 
		l.LEARefId as SourceId, 
		'district' as Type, 
		l.LEAName as Name, 
		l.LEAId as Identifier,
		l.StreetNumberAndName as Line1,
		l.Line2 as Line2,
		l.City as City,
		l.StateCode as State,
		l.PostalCode as PostCode,
		l.CountryCode as Country
	from lea as l
)
union all
(
	select 	
		sch.LEARefId as LEARefId, 
		sch.SchoolSchoolYear as LEASchoolYear, 
		sch.SchoolRefId as SourceId, 
		'school' as Type, 
		sch.SchoolName as Name, 
		schi.SchoolId as Identifier,
		sch.StreetNumberAndName as Line1,
		sch.Line2 as Line2,
		sch.City as City,
		sch.StateCode as State,
		sch.PostalCode as PostCode,
		sch.CountryCode as Country
	from school sch
	left join schoolidentifier schi 
		on schi.SchoolRefId = sch.SchoolRefId 
		and schi.IdentificationSystemCode = 'SEA'
		and sch.SchoolSchoolYear = schi.SchoolSchoolYear
);

select * from OrgView;