CREATE VIEW DemographicView AS
(
	select
		s.StudentRefId as SourcedId, 
		s.StudentSchoolYear as SourcedSchoolYear,
		s.Birthdate as BirthDate,
		s.SexCode as Sex,
		case when sr_native.raceCode is null then false else true end as AmericanIndianOrAlaskaNative, 
		case when sr_asian.raceCode is null then false else true end as Asian, 
		case when sr_black.raceCode is null then false else true end as BlackOrAfricanAmerican,
		case when sr_islander.raceCode is null then false else true end as NativeHawaiianOrOtherPacificIslander,
		case when sr_white.raceCode is null then false else true end as White,
        null as DemographicRaceTwoOrMoreRaces,
		s.HispanicLatinoEthnicity as HispanicOrLatinoEthnicity,
        s.CountryOfBirth as CountryOfBirthCode,
        null as StateOfBirthAbbreviation,
        null as CityOfBirth,
        null as PublicSchoolResidenceStatus
	from Student as s
	left join studentrace as sr_native
		on s.StudentRefId = sr_native.StudentRefId 
		and s.StudentSchoolYear = sr_native.StudentSchoolYear 
		and sr_native.RaceCode = 'American Indian or Alaska Native'
		
	left join studentrace as sr_asian 
		on s.StudentRefId = sr_asian.StudentRefId 
		and s.StudentSchoolYear = sr_asian.StudentSchoolYear 
		and sr_asian.RaceCode = 'Asian' 
		
	left join studentrace as sr_black
		on s.StudentRefId = sr_black.StudentRefId 
		and s.StudentSchoolYear = sr_black.StudentSchoolYear 
		and sr_black.RaceCode = 'Black or African American'
	
	left join studentrace as sr_islander 
		on s.StudentRefId = sr_islander.StudentRefId 
		and s.StudentSchoolYear = sr_islander.StudentSchoolYear 
		and sr_islander.RaceCode = 'Native Hawaiian or Other Pacific Islander'    
	
	left join studentrace as sr_white 
		on s.StudentRefId = sr_white.StudentRefId 
		and s.StudentSchoolYear = sr_white.StudentSchoolYear 
		and sr_white.RaceCode = 'White'
 )       
order by SourcedId, SourcedSchoolYear;	