CREATE VIEW UserAgentView AS
(
	select 
		scr.StudentContactRefId as AgentId,
		scr.StudentContactSchoolYear as AgentSchoolYear,
		scr.RelationshipCode as AgentType,
		s.StudentRefId as SourcedId,
		s.StudentSchoolYear as SourcedSchoolYear,
		'student' as SourcedRole
	from student as s
	join studentcontactrelationship as scr 
		on scr.StudentRefId = s.StudentRefId 
        and scr.StudentSchoolYear = s.StudentSchoolYear
	where scr.RelationshipCode is not null
)
union all
(
	select 
		scr.StudentRefId,
		scr.StudentSchoolYear,
		'student',
		sc.StudentContactRefId,
		sc.StudentContactSchoolYear,
		'contact'
	from studentcontact as sc
	join studentcontactrelationship as scr 
		on scr.StudentContactRefId = sc.StudentContactRefId 
		and scr.StudentContactSchoolYear = sc.StudentContactSchoolYear
	where scr.RelationshipCode is not null
)
order by SourcedId, SourcedSchoolYear;

select * from UserAgentView;