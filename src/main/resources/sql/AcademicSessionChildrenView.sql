CREATE VIEW AcademicSessionChildrenView AS
select 
	scs.SchoolCalendarSessionRefId as ChildId,
    scs.SchoolCalendarSessionSchoolYear as ChildSchoolYear,
	asv.SourcedId as SourcedId, 
	asv.SourcedSchoolYear as SourcedSchoolYear
from AcademicSessionView as asv
join schoolcalendarsession scs 
	on 	scs.SchoolCalendarRefId = asv.SourcedId
    and scs.SchoolCalendarSchoolYear = asv.SourcedSchoolYear
where asv.AcademicSessionId is null 
and  asv.AcademicSessionSchoolYear is null

