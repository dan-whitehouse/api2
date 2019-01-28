CREATE VIEW AcademicSessionView AS
(
	select distinct
		schc.SchoolCalendarRefId as SourcedId, 
		schc.SchoolCalendarSchoolYear as SourcedSchoolYear,
		schc.CalendarDescription as Title,
		'schoolYear' as Type,
		schc.CalendarYear as SchoolYear,
		(
			select min(sub.BeginDate) as BeginDate
			from schoolcalendarsession as sub
			where sub.SchoolCalendarRefId = schc.SchoolCalendarRefId
			and sub.SchoolCalendarSchoolYear = schc.SchoolCalendarSchoolYear
		) as BeginDate,
		(
			select max(sub.EndDate) as EndDate
			from schoolcalendarsession as sub
			where sub.SchoolCalendarRefId = schc.SchoolCalendarRefId
			and sub.SchoolCalendarSchoolYear = schc.SchoolCalendarSchoolYear
		) as EndDate,
        null as AcademicSessionId,
        null as AcademicSessionSchoolYear
	from schoolcalendar as schc
)
union all
(
	select distinct
		schcs.SchoolCalendarSessionRefId, 
		schcs.SchoolCalendarSessionSchoolYear,
		schcs.Description,
		case when LOWER(schcs.SessionTypeCode) = 'semester' then 'semester' else 'term' end as Type, #TODO - Maybe a case statement based on some value
		schc.CalendarYear,
		schcs.BeginDate,
		schcs.EndDate,
        schcs.SchoolCalendarRefId,
        schcs.SchoolCalendarSchoolYear
	from schoolcalendarsession as schcs
    join schoolcalendar as schc
		on schc.SchoolCalendarRefId = schcs.SchoolCalendarRefId
        and schc.SchoolCalendarSchoolYear = schcs.SchoolCalendarSchoolYear
)
order by SourcedId, SourcedSchoolYear;
