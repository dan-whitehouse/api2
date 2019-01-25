CREATE VIEW ClassPeriodView AS
select
	css.CourseSectionScheduleRefId as PeriodId,
	css.CourseSectionScheduleSchoolYear as PeriodSchoolYear,
	css.ClassPeriod as Period,
	cs.CourseSectionRefId as SourcedId, 
	cs.CourseSectionSchoolYear as SourcedSchoolYear
from coursesection as cs
join coursesectionschedule as css
	on css.CourseSectionRefId = cs.CourseSectionRefId
	and css.CourseSectionSchoolYear = cs.CourseSectionSchoolYear
where css.ClassPeriod is not null