CREATE VIEW ClassTermView AS
(
	select 
		scs.SchoolCalendarSessionRefId as TermId,
		scs.SchoolCalendarSessionSchoolYear as TermSchoolYear,
		'term' as Type,
		cs.CourseSectionRefId as SourcedId, 
		cs.CourseSectionSchoolYear as SourcedSchoolYear
	from coursesection as cs
	join coursesectionschedule as css
		on css.CourseSectionRefId = cs.CourseSectionRefId
		and css.CourseSectionSchoolYear = cs.CourseSectionSchoolYear
	join schoolcalendarsession as scs 
		on scs.SchoolCalendarSessionRefId = css.SchoolCalendarSessionRefId
		and scs.SchoolCalendarSessionSchoolYear = css.SchoolCalendarSessionSchoolYear
)
union all
(
	select
		scs.SchoolCalendarRefId as TermId,
		scs.SchoolCalendarSchoolYear as TermSchoolYear,
		'academicSession',
		cs.CourseSectionRefId as SourcedId, 
		cs.CourseSectionSchoolYear as SourcedSchoolYear
	from coursesection as cs
	join coursesectionschedule as css
		on css.CourseSectionRefId = cs.CourseSectionRefId
		and css.CourseSectionSchoolYear = cs.CourseSectionSchoolYear
	join schoolcalendarsession as scs 
		on scs.SchoolCalendarSessionRefId = css.SchoolCalendarSessionRefId
		and scs.SchoolCalendarSessionSchoolYear = css.SchoolCalendarSessionSchoolYear
)