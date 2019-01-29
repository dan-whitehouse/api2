CREATE VIEW OrgView AS
(
	select 	
		l.LEARefId as SourcedId,
        l.LEASchoolYear as SourcedSchoolYear,
        l.leaId as DistrictId,
		'district' as Type, 
		l.LEAName as Name, 
		l.LEAId as Identifier,
		l.StreetNumberAndName as Line1,
		l.Line2 as Line2,
		l.City as City,
		l.StateCode as State,
		l.PostalCode as PostCode,
		l.CountryCode as Country,
        null as ParentId,
        null as ParentSchoolYear
	from lea as l
)
union all
(
	select 	
		sch.SchoolRefId, 
        sch.SchoolSchoolYear,
        l.leaId,
		'school', 
		sch.SchoolName, 
		schi.SchoolId,
		sch.StreetNumberAndName,
		sch.Line2,
		sch.City,
		sch.StateCode,
		sch.PostalCode,
		sch.CountryCode,
        l.LEARefId,
        l.LEASchoolYear
	from school sch
    join lea as l 
		on l.LEARefId = sch.LEARefId 
		and l.LEASchoolYear = sch.LEASchoolYear 
	left join schoolidentifier schi 
		on schi.SchoolRefId = sch.SchoolRefId 
		and schi.IdentificationSystemCode = 'SEA'
		and sch.SchoolSchoolYear = schi.SchoolSchoolYear
	
);

select * from OrgView;