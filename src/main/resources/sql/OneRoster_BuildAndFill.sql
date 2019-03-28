DROP TABLE IF EXISTS 
	OneRosterV1P1_Enrollment, 
	OneRosterV1P1_Demographic, 
	OneRosterV1P1_UserAgent,
	OneRosterV1P1_UserIdentifier,
	OneRosterV1P1_UserOrg, 
	OneRosterV1P1_UserClass,
	OneRosterV1P1_User, 
	onerosterv1p1_classacademicsession, 
	onerosterv1p1_class, 
	onerosterv1p1_course, 
	onerosterv1p1_academicsessionchild,
	onerosterv1p1_academicsession,
	onerosterv1p1_orgchild,
	onerosterv1p1_org;
     


/* Org */
create table OneRosterV1P1_Org (
	SourcedId varchar(64),
	SourcedSchoolYear smallint(6), 
    Status varchar(20), 
    DateLastModified datetime,
	DistrictId varchar(30), 
	Type varchar(8),
	Name varchar(75), 
	Identifier varchar(50), 
	Line1 varchar(75), 
	Line2 varchar(80),
	City varchar(30), 
	State varchar(50), 
	PostCode varchar(50), 
	Country varchar(50), 
	OrgId varchar(64), 
	OrgSchoolYear smallint(6),
	PRIMARY KEY (SourcedId, SourcedSchoolYear),
	FOREIGN KEY (OrgId, OrgSchoolYear) REFERENCES OneRosterV1P1_Org(SourcedId, SourcedSchoolYear),
	INDEX (DistrictId)
);

insert into OneRosterV1P1_Org
(
	select distinct	
		l.LEARefId as SourcedId,
        l.LEASchoolYear as SourcedSchoolYear,
        'active', #Idk how we want to determine this value. As we delete records every night without marking them for future deletion (same for every top level table)
        now(), #This should probably come from staging. (same for every top level table)
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
        null as OrgId,
        null as OrgSchoolYear
	from lea as l
)
union all
(
	select distinct	 	
		sch.SchoolRefId, 
        sch.SchoolSchoolYear,
        'active',
        now(),
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


/* Org Children */
create table OneRosterV1P1_OrgChild (
	OrgChildId varchar(64),
	ChildId varchar(64), 
	ChildSchoolYear smallint(6), 
	OrgId varchar(64), 
	OrgSchoolYear smallint(6),
	PRIMARY KEY (OrgChildId),
    FOREIGN KEY (ChildId, ChildSchoolYear) REFERENCES OneRosterV1P1_Org(SourcedId, SourcedSchoolYear),
	FOREIGN KEY (OrgId, OrgSchoolYear) REFERENCES OneRosterV1P1_Org(SourcedId, SourcedSchoolYear)
);

insert into OneRosterV1P1_OrgChild
select distinct
	uuid() as OrgChildId,
	sch.SchoolRefId as ChildId,
    sch.SchoolSchoolYear as ChildSchoolYear,
	o.SourcedId as OrgId, 
	o.SourcedSchoolYear as OrgSchoolYear
from OneRosterV1P1_Org as o
join school sch 
	on 	sch.LEARefId = o.SourcedId
    and sch.LEASchoolYear = o.SourcedSchoolYear
where o.OrgId is null 
and  o.OrgSchoolYear is null;


/* Academic Session */
CREATE TABLE OneRosterV1P1_AcademicSession (
	SourcedId varchar(64) NOT NULL,
    SourcedSchoolYear smallint(6) NOT NULL,
    Status varchar(20), 
    DateLastModified datetime,
    DistrictId varchar(30),
	Title longtext, 
	Type varchar(10), 
	SchoolYear smallint(4),
	StartDate date,
	EndDate date,
	AcademicSessionId varchar(64), 
	AcademicSessionSchoolYear smallint(6),
    OrgId varchar(64), 
	OrgSchoolYear smallint(6),
    PRIMARY KEY (SourcedId, SourcedSchoolYear),
	FOREIGN KEY (AcademicSessionId, AcademicSessionSchoolYear) REFERENCES OneRosterV1P1_AcademicSession(SourcedId, SourcedSchoolYear),
    FOREIGN KEY (OrgId, OrgSchoolYear) REFERENCES OneRosterV1P1_Org(SourcedId, SourcedSchoolYear),
	INDEX (DistrictId) 
);

/* 
	We need to do something special for Academic Sessions...
*/

DROP TABLE IF EXISTS t1, t2, t3;

# Table 1 - Makes a fake uuid associated to each school. This will allow us to create an unique calendar per school
CREATE TEMPORARY TABLE t1 
select 	uuid() as uuid, 
		sch.SchoolSchoolYear as uuidSchoolYear, 
        sch.SchoolRefId, 
        sch.SchoolSchoolYear 
from school sch
where sch.SchoolSchoolYear = 2019;

# Table 2 - Associates all SchoolCalendars to a SchoolRefId
CREATE TEMPORARY TABLE t2 
select 	schc.SchoolCalendarRefId, 
		schc.SchoolCalendarSchoolYear, 
        schc.SchoolRefId, 
        schc.SchoolSchoolYear, 
        schc.CalendarYear
from schoolcalendar schc
where schc.SchoolCalendarSchoolYear = 2019;


# Table 3 - Merge T1 & T2 so that each SchoolCalendar with the same SchoolRefId is associated to the same UUID created in T2
CREATE TEMPORARY TABLE t3 
select 	t1.uuid, 
		t1.uuidSchoolYear, 
        t2.SchoolCalendarRefId, 
        t2.SchoolCalendarSchoolYear, 
        t2.SchoolRefId, 
        t2.SchoolSchoolYear, 
        t2.CalendarYear
from t2
join t1 
	on t1.schoolRefId = t2.schoolRefId
    and t1.SchoolSchoolYear = t2.SchoolSchoolYear;

insert into OneRosterV1P1_AcademicSession
(
	select distinct
		t3.uuid as SourcedId,
		sch.SchoolSchoolYear as SourcedSchoolYear,
		'active',
        now(),
		l.leaId as DistrictId,
		'Full Year' as Title, #These are made up full year calendars
		'schoolYear' as Type,
		(
			select schc.CalendarYear as SchoolYear
			from schoolcalendar as schc
			where schc.SchoolRefId = t3.SchoolRefId
			and schc.SchoolSchoolYear = sch.SchoolSchoolYear
			and schc.CalendarYear is not null
			limit 1
		) as SchoolYear,
		(
			select min(sub.BeginDate) as BeginDate
			from schoolcalendarsession as sub
			join schoolcalendar as schCal 
				on schCal.SchoolCalendarRefId = sub.SchoolCalendarRefId
				and schCal.SchoolCalendarSchoolYear = sub.SchoolCalendarSchoolYear
			where schCal.SchoolRefId = t3.SchoolRefId
			and schCal.SchoolSchoolYear = 2019
		) as BeginDate,
		(
			select max(sub.EndDate) as BeginDate
			from schoolcalendarsession as sub
			join schoolcalendar as schCal 
				on schCal.SchoolCalendarRefId = sub.SchoolCalendarRefId
				and schCal.SchoolCalendarSchoolYear = sub.SchoolCalendarSchoolYear
			where schCal.SchoolRefId = t3.SchoolRefId
			and schCal.SchoolSchoolYear = t3.SchoolSchoolYear
		) as EndDate,
		null as AcademicSessionId,
		null as AcademicSessionSchoolYear,
		sch.SchoolRefId as OrgId,
		sch.SchoolSchoolYear as OrgSchoolYear
	from t3
	join school sch 
		on sch.SchoolRefId = t3.SchoolRefId
		and sch.SchoolSchoolYear = t3.SchoolSchoolYear
	join lea as l 
		on l.LEARefId = sch.LEARefId 
		and l.LEASchoolYear = sch.LEASchoolYear
);


insert into OneRosterV1P1_AcademicSession
(
	select distinct
		schcs.SchoolCalendarSessionRefId as SourcedId, 
		schcs.SchoolCalendarSessionSchoolYear as SourcedSchoolYear,
        'active',
        now(),
        l.leaId as DistrictId,
		schcs.Description as Title,
		case when LOWER(schcs.SessionTypeCode) = 'semester' then 'semester' else 'term' end as Type, #Not sure this will cover every value that should be a semester
		t3.CalendarYear as SchoolYear,
		schcs.BeginDate,
		schcs.EndDate,
        t3.uuid as AcademicSessionId,
        t3.uuidSchoolYear as AcademicSessionSchoolYear,
        t3.SchoolRefId as OrgId,
        t3.SchoolSchoolYear as OrgSchoolYear
	from schoolcalendarsession as schcs
    join t3 as t3
		on schcs.SchoolCalendarRefId = t3.SchoolCalendarRefId
        and schcs.SchoolCalendarSchoolYear = t3.SchoolCalendarSchoolYear
	join school as sch 
		on sch.SchoolRefId = t3.SchoolRefId
        and sch.SchoolSchoolYear = t3.SchoolSchoolYear
    join lea as l 
		on l.LEARefId = sch.LEARefId 
        and l.LEASchoolYear = sch.LEASchoolYear
);
DROP TABLE IF EXISTS t1, t2, t3;


/* Academic Session Children */
CREATE TABLE OneRosterV1P1_AcademicSessionChild (
	AcademicSessionChildId varchar(64),
	ChildId varchar(64), 
	ChildSchoolYear smallint(6), 
	AcademicSessionId varchar(64), 
	AcademicSessionSchoolYear smallint(6),
	PRIMARY KEY (AcademicSessionChildId),
    FOREIGN KEY (ChildId, ChildSchoolYear) REFERENCES OneRosterV1P1_AcademicSession(SourcedId, SourcedSchoolYear),
	FOREIGN KEY (AcademicSessionId, AcademicSessionSchoolYear) REFERENCES OneRosterV1P1_AcademicSession(SourcedId, SourcedSchoolYear)
);


insert into OneRosterV1P1_AcademicSessionChild
select distinct
	uuid() as AcademicSessionChildId,
	scs.SchoolCalendarSessionRefId as ChildId,
    scs.SchoolCalendarSessionSchoolYear as ChildSchoolYear,
	asv.AcademicSessionId,
	asv.AcademicSessionSchoolYear
from schoolcalendarsession scs 
join OneRosterV1P1_AcademicSession as asv on asv.SourcedId = scs.SchoolCalendarSessionRefId;


/* Academic Session Course */
CREATE TABLE OneRosterV1P1_Course (
	SourcedId varchar(64),
	SourcedSchoolYear smallint(6), 
    Status varchar(20), 
    DateLastModified datetime,
	DistrictId varchar(30), 
	Title varchar(75), 
	CourseCode varchar(50), 
	Grades varchar(341), 
	Subjects varchar(341), 
	SubjectCodes varchar(341), 
	AcademicSessionId varchar(64), 
	AcademicSessionSchoolYear smallint(6), 
	OrgId varchar(64), 
	OrgSchoolYear smallint(6),
	PRIMARY KEY (SourcedId, SourcedSchoolYear),
	FOREIGN KEY (AcademicSessionId, AcademicSessionSchoolYear) REFERENCES OneRosterV1P1_AcademicSession(SourcedId, SourcedSchoolYear),
	FOREIGN KEY (OrgId, OrgSchoolYear) REFERENCES OneRosterV1P1_Org(SourcedId, SourcedSchoolYear),
	INDEX (DistrictId)
);

insert into OneRosterV1P1_Course
select distinct
	c.CourseRefId as SourcedId, 
	c.CourseSchoolYear as SourcedSchoolYear,
    'active',
	now(),
    l.leaId as DistrictId,
	c.Title as Title,
    ci.CourseId as CourseCode,
    (
		select GROUP_CONCAT(distinct replace(GradeLevelCode, ',', ''))
        from coursegrade as sub_cg 
		where sub_cg.CourseRefId = c.CourseRefId
        and sub_cg.CourseSchoolYear = c.CourseSchoolYear
        group by sub_cg.CourseRefId
    ) as Grades,
    (
		select GROUP_CONCAT(distinct replace(SubjectCode, ',', ''))
        from course as sub_c 
		where sub_c.CourseRefId = c.CourseRefId
        and sub_c.CourseSchoolYear = c.CourseSchoolYear
        group by sub_c.CourseRefId
    ) as Subjects,
	(
		select GROUP_CONCAT(distinct replace(SCEDCourseCode, ',', ''))
        from course as sub_c 
		where sub_c.CourseRefId = c.CourseRefId
        and sub_c.CourseSchoolYear = c.CourseSchoolYear
        group by sub_c.CourseRefId
    ) as SubjectCodes,
    asv.SourcedId as AcademicSessionId,
    asv.SourcedSchoolYear as AcademicSessionSchoolYear,
    sch.SchoolRefId as OrgId,
    sch.SchoolSchoolYear as OrgSchoolYear
from course as c
left join courseidentifier as ci 
	on c.CourseRefId = ci.CourseRefId
    and c.CourseSchoolYear = ci.CourseSchoolYear
    and ci.IdentificationSystemCode = 'School'
join school as sch
	on sch.SchoolRefId = c.SchoolRefId
    and sch.SchoolSchoolYear = c.SchoolSchoolYear
join lea as l 
	on l.LEARefId = sch.LEARefId 
	and l.LEASchoolYear = sch.LEASchoolYear  
left join OneRosterV1P1_AcademicSession asv
	on asv.OrgId = sch.SchoolRefId
	and asv.OrgSchoolYear = sch.SchoolSchoolYear
    and asv.Type = 'schoolYear'
    #and now() between scs.BeginDate and scs.EndDate
#where c.CourseSchoolYear = 2019
;

/* Class */
CREATE TABLE OneRosterV1P1_Class (
	SourcedId varchar(64),
	SourcedSchoolYear smallint(6),
    Status varchar(20), 
    DateLastModified datetime,
	DistrictId varchar(30), 
	Title varchar(75),
	ClassCode varchar(50), 
	ClassType varchar(9), 
	Location varchar(255),
	Grades varchar(500),
	Subjects varchar(500),
    SubjectCodes varchar(500),
	Periods varchar(500),
	CourseId varchar(64), 
	CourseSchoolYear smallint(6), 
	OrgId varchar(64), 
	OrgSchoolYear smallint(6),
	PRIMARY KEY (SourcedId, SourcedSchoolYear),
	FOREIGN KEY (CourseId, CourseSchoolYear) REFERENCES OneRosterV1P1_Course(SourcedId, SourcedSchoolYear),
	FOREIGN KEY (OrgId, OrgSchoolYear) REFERENCES OneRosterV1P1_Org(SourcedId, SourcedSchoolYear),
	INDEX (DistrictId)
);

insert into OneRosterV1P1_Class
select distinct
	cs.CourseSectionRefId as SourcedId, 
	cs.CourseSectionSchoolYear as SourcedSchoolYear,
    'active',
	now(),
	l.leaId as DistrictId,
	c.Title as Title,
    ci.CourseId as ClassCode,
    case #Assumption that this should even be done... Maybe everything should just be scheduled.
		when c.Title like '%Homeroom%' 
			or c.Title like '%Home room%'
            or c.Title like '%HMRM%'
            or c.Title like '%HM RM%'
            or ci.CourseId like '%Homeroom%' 
			or ci.CourseId like '%Home room%' 
			or ci.CourseId like '%HMRM%'
			or ci.CourseId like '%HM RM%'
            or (c.Title like 'HR%' and ci.CourseId like '%HR%')
            or (c.Title like 'HR%' and ci.CourseId like '%HMRM%')
            or (c.Title like 'HR%' and ci.CourseId like '%HM RM%')
			or (c.Title like 'HR%' and ci.CourseId like '%Homeroom%')
            or (c.Title like 'HR%' and ci.CourseId like '%Home room%')
		then 'homeroom'
		else 'scheduled'
	END as ClassType,
    (
		select GROUP_CONCAT(replace(css.ClassroomIdentifier, ',', ''))
		from coursesectionschedule as css 
		where css.CourseSectionRefId = cs.CourseSectionRefId
        and css.CourseSectionSchoolYear = cs.CourseSectionSchoolYear
        group by css.CourseSectionRefId
        order by css.CourseSectionScheduleRefId
    ) as Location, # Should we be listing these, OneRoster only displays 1 value. We could also distinct the grouping, eliminating values like 03,03,03,03
    (
		select GROUP_CONCAT( replace(GradeLevelCode, ',', ''))
        from coursegrade as sub_cg 
		where sub_cg.CourseRefId = c.CourseRefId
        and sub_cg.CourseSchoolYear = c.CourseSchoolYear
        group by sub_cg.CourseRefId
    ) as Grades,
    (
		select GROUP_CONCAT( replace(SubjectCode, ',', ''))
        from course as sub_c 
		where sub_c.CourseRefId = c.CourseRefId
        and sub_c.CourseSchoolYear = c.CourseSchoolYear
        group by sub_c.CourseRefId
    ) as Subjects,
    (
		select GROUP_CONCAT( replace(SCEDCourseCode, ',', ''))
        from course as sub_c 
		where sub_c.CourseRefId = c.CourseRefId
        and sub_c.CourseSchoolYear = c.CourseSchoolYear
        group by sub_c.CourseRefId
    ) as SubjectCodes,
    (
		select GROUP_CONCAT( replace(ClassPeriod, ',', ''))
        from coursesectionschedule as css 
		where css.CourseSectionRefId = cs.CourseSectionRefId
        and css.CourseSectionSchoolYear = cs.CourseSectionSchoolYear
        group by css.CourseSectionRefId
        order by css.CourseSectionScheduleRefId
    ) as Periods,
    c.CourseRefId as CourseId,
    c.CourseSchoolYear as CourseSchoolYear,
    sch.SchoolRefId as OrgId,
    sch.SchoolSchoolYear as OrgSchoolYear
from coursesection as cs
join course as c 
	on c.CourseRefId = cs.CourseRefId
    and c.CourseSchoolYear = cs.CourseSchoolYear
join school as sch
	on sch.SchoolRefId = c.SchoolRefId
    and sch.SchoolSchoolYear = c.SchoolSchoolYear
join lea as l 
		on l.LEARefId = sch.LEARefId 
        and l.LEASchoolYear = sch.LEASchoolYear   
left join courseidentifier as ci 
	on c.CourseRefId = ci.CourseRefId
    and c.CourseSchoolYear = ci.CourseSchoolYear
    and ci.IdentificationSystemCode = 'School';

/* Class Term */
CREATE TABLE OneRosterV1P1_ClassAcademicSession (
	ClassAcademicSessionId varchar(64), 
    ClassId varchar(64), 
	ClassSchoolYear smallint(6),
	AcademicSessionId varchar(64), 
	AcademicSessionSchoolYear smallint(6), 
	PRIMARY KEY (ClassAcademicSessionId),
    FOREIGN KEY (ClassId, ClassSchoolYear) REFERENCES OneRosterV1P1_Class(SourcedId, SourcedSchoolYear),
    FOREIGN KEY (AcademicSessionId, AcademicSessionSchoolYear) REFERENCES OneRosterV1P1_AcademicSession(SourcedId, SourcedSchoolYear)
	
);

insert into OneRosterV1P1_ClassAcademicSession
select distinct
	uuid() as ClassAcademicSessionId,
	cs.CourseSectionRefId as ClassId, 
	cs.CourseSectionSchoolYear as ClassSchoolYear,
	asv.SourcedId as AcademicSessionId,
	asv.SourcedSchoolYear as AcademicSessionSchoolYear
from coursesection as cs
join coursesectionschedule as css
	on css.CourseSectionRefId = cs.CourseSectionRefId
	and css.CourseSectionSchoolYear = cs.CourseSectionSchoolYear
join OneRosterV1P1_AcademicSession as asv 
	on css.SchoolCalendarSessionRefId = asv.SourcedId
	and css.SchoolCalendarSessionSchoolYear = asv.SourcedSchoolYear;


/* User */
CREATE TABLE OneRosterV1P1_User (
	SourcedId varchar(64), 
	SourcedSchoolYear smallint(6), 
    Status varchar(20), 
    DateLastModified datetime,
	Role varchar(16), 
	DistrictId varchar(30),
	EnabledUser bigint(20), 
	GivenName varchar(35),
	FamilyName varchar(35), 
	MiddleName varchar(35), 
	Identifier varchar(250), 
	Email varchar(128), 
	Phone varchar(24), 
	Sms varchar(24),
	Grades varchar(341),
	PRIMARY KEY (SourcedId, SourcedSchoolYear),
	INDEX (DistrictId),
	INDEX (Role)
);

insert into OneRosterV1P1_User
(
	select distinct
		s.StudentRefId as SourcedId, 
		s.StudentSchoolYear as SourcedSchoolYear,
        'active',
		now(),
		'student' as Role,
		l.leaId as DistrictId,
		true as EnabledUser,
		s.FirstName as GivenName, 
		s.LastName as FamilyName, 
		s.MiddleName as MiddleName,
		#si.StudentId as Identifier, -- I shouldn't have to do this, however there are duplicate primary telephone numbers
        ( 
			#Remove after duplcates are fixed...
			select StudentId
            from studentidentifier as sub_si
            where sub_si.StudentRefId = s.StudentRefId
            and sub_si.StudentSchoolYear = s.StudentSchoolYear
            and sub_si.IdentificationSystemCode = 'District'
            limit 1
        ) as Identifier,
		#sem.EmailAddress as Email, -- I shouldn't have to do this, however there are duplicate primary email addresses
        ( 
			#Remove after duplcates are fixed...
			select EmailAddress
            from studentemail as sub_sem
            where sub_sem.StudentRefId = s.StudentRefId
            and sub_sem.StudentSchoolYear = s.StudentSchoolYear
            and sub_sem.PrimaryEmailAddressIndicator = true
            limit 1
        ) as Email,
		#st.TelephoneNumber as Phone, -- I shouldn't have to do this, however there are duplicate primary telephone numbers
        ( 
			#Remove after duplcates are fixed...
			select TelephoneNumber
            from studenttelephone as sub_st
            where sub_st.StudentRefId = s.StudentRefId
            and sub_st.StudentSchoolYear = s.StudentSchoolYear
            and sub_st.PrimaryTelephoneNumberIndicator = true
            limit 1
        ) as Phone,
		null as Sms,
        (
			select GROUP_CONCAT( replace(CurrentGradeLevel, ',', ''))
			from studentenrollment as sub_se 
			where sub_se.StudentRefId = se.StudentRefId
			and sub_se.StudentSchoolYear = se.StudentSchoolYear
			group by sub_se.StudentRefId
		) as Grades
	from Student as s
	/*left join studentidentifier as si 
		on s.StudentRefId = si.StudentRefId 
        and s.StudentSchoolYear = si.StudentSchoolYear 
        and si.IdentificationSystemCode = 'District'*/
	/*left join studentemail as sem 
		on s.StudentRefId = sem.StudentRefId 
        and s.StudentSchoolYear = sem.StudentSchoolYear 
        and sem.PrimaryEmailAddressIndicator = true*/
	/*left join studenttelephone as st 
		on s.StudentRefId = st.StudentRefId 
        and s.StudentSchoolYear = st.StudentSchoolYear 
        and st.PrimaryTelephoneNumberIndicator = true*/
	join studentenrollment as se
		on se.StudentRefId = s.StudentRefId
        and se.StudentSchoolYear = s.StudentSchoolYear
	join school as sch
		on sch.SchoolRefId = se.SchoolRefId
        and sch.SchoolSchoolYear = se.SchoolSchoolYear
	join lea as l 
		on l.LEARefId = sch.LEARefId 
		and l.LEASchoolYear = sch.LEASchoolYear 
	where s.StudentSchoolYear = 2019
)
union all
(
	select distinct
		t.StaffRefId, 
		t.StaffSchoolYear,
        'active',
		now(),
		/*case 
			when ta.PositionTitle like '%admin%' 
				or ta.PositionTitle like '%principal%'
				then 'administrator'
			when ta.PositionTitle = 'aide' 
				or ta.PositionTitle = 'TA' 
				or ta.PositionTitle like '%aide%' 
				or ta.PositionTitle like '%teach%ass%'
				then 'aide'
			when ta.PositionTitle = 'proctor' 
				then 'proctor'
			when ta.PositionTitle = 'teacher' 
				or ta.PositionTitle like '%tchr%'
				or ta.PositionTitle like '%sub%teach%'
				or ta.PositionTitle like '%teacher%'
				then 'teacher'
			else 'other'
		END as Role,*/
        'teacher' as Role,#This isn't right, as busdrivers, cleaners, maintenance, mechanic, etc... will need to be handled in ingestion -- SELECT distinct positiontitle FROM core3.staffassignment;
        l.leaId as DistrictId,
		true,
		t.FirstName, 
		t.LastName, 
		t.MiddleName,
		#ti.StaffId,  -- I shouldn't have to do this, however there are duplicate primary telephone numbers
        ( 
			#Remove after duplcates are fixed...
			select sub_ti.StaffId
            from staffidentifier as sub_ti
            where sub_ti.StaffRefId = t.StaffRefId
            and sub_ti.StaffSchoolYear = t.StaffSchoolYear
            and sub_ti.IdentificationSystemCode = 'District'
            limit 1
        ) as Identifier,
		#tem.EmailAddress, -- I shouldn't have to do this, however there are duplicate primary telephone numbers
        ( 
			#Remove after duplcates are fixed...
			select EmailAddress
            from staffemail as sub_tem
            where sub_tem.StaffRefId = t.StaffRefId
            and sub_tem.StaffSchoolYear = t.StaffSchoolYear
            and sub_tem.PrimaryEmailAddressIndicator = true
            limit 1
        ) as Email,
		null as Phone,
		null as Sms, 
        null as Grades
	from Staff as t
	/*left join staffidentifier as ti 
		on t.StaffRefId = ti.StaffRefId 
        and t.StaffSchoolYear = ti.StaffSchoolYear 
        and ti.IdentificationSystemCode = 'District'*/
	/*left join staffemail as tem 
		on t.StaffRefId = tem.StaffRefId 
        and t.StaffSchoolYear = tem.StaffSchoolYear 
        and tem.PrimaryEmailAddressIndicator = true*/
	join staffassignment ta
		on ta.StaffRefId = t.StaffRefId
        and ta.StaffSchoolYear = t.StaffSchoolYear
	join school as sch
		on sch.SchoolRefId = ta.SchoolRefId
        and sch.SchoolSchoolYear = ta.SchoolSchoolYear
	join lea as l 
		on l.LEARefId = sch.LEARefId 
		and l.LEASchoolYear = sch.LEASchoolYear 
	where t.StaffSchoolYear = 2019
)
union all
(
	select distinct
		sc.StudentContactRefId, 
		sc.StudentContactSchoolYear,
        'active',
		now(),
		/*case 
			when scr.RelationshipCode = 'Father' 
				or scr.RelationshipCode = 'Mother'
				or scr.RelationshipCode like '%parent%' 
			then 'parent'
			when scr.RelationshipCode like '%Uncle%' 
				or scr.RelationshipCode like '%Aunt%'
				or scr.RelationshipCode like '%Sibling%'
				or scr.RelationshipCode like '%Brother%'
				or scr.RelationshipCode like '%Sister%'
				or scr.RelationshipCode like 'step%'
				or scr.RelationshipCode like 'grand%'
			then 'relative'
			when scr.RelationshipCode like '%Guardian%' then 'Guardian'
			else 'other'
		END as Role,*/ #Needs to be figured out by ingestion, right now this is just not enough
        'parent' as Role,
        l.leaId as DistrictId,
		true,
		sc.FirstName, 
		sc.LastName, 
		sc.MiddleName,
		#sci.StudentContactId, -- I shouldn't have to do this, however there are duplicate identifier
        ( 
			#Remove after duplcates are fixed...
			select sub_sci.StudentContactId
            from studentcontactidentifier as sub_sci
            where sub_sci.StudentContactRefId = sc.StudentContactRefId 
            and sub_sci.StudentContactSchoolYear = sc.StudentContactSchoolYear
            and sub_sci.IdentificationSystemCode = 'District'
            limit 1
        ) as Identifier,
		#scem.EmailAddress, -- I shouldn't have to do this, however there are duplicate primary email address
        ( 
			#Remove after duplcates are fixed...
			select sub_scem.EmailAddress
            from studentcontactemail as sub_scem
            where sub_scem.StudentContactRefId = sc.StudentContactRefId 
            and sub_scem.StudentContactSchoolYear = sc.StudentContactSchoolYear
            and sub_scem.PrimaryEmailAddressIndicator = true
            limit 1
        ) as Email,
		#sct.TelephoneNumber, -- I shouldn't have to do this, however there are duplicate primary telephone numbers
         ( 
			#Remove after duplcates are fixed...
			select sub_sct.TelephoneNumber
            from studentcontacttelephone as sub_sct
            where sub_sct.StudentContactRefId = sc.StudentContactRefId 
            and sub_sct.StudentContactSchoolYear = sc.StudentContactSchoolYear
            and sub_sct.PrimaryTelephoneNumberIndicator = true
            limit 1
        ) as Email,
		null as Sms,
        null as Grades
	from StudentContact as sc
	/*left join studentcontactidentifier as sci 
		on sc.StudentContactRefId = sci.StudentContactRefId 
        and sc.StudentContactSchoolYear = sci.StudentContactSchoolYear 
        and sci.IdentificationSystemCode = 'District'*/
	/*left join studentcontactemail as scem 
		on sc.StudentContactRefId = scem.StudentContactRefId 
        and sc.StudentContactSchoolYear = scem.StudentContactSchoolYear 
        and scem.PrimaryEmailAddressIndicator = true*/
	/*left join studentcontacttelephone as sct 
		on sc.StudentContactRefId = sct.StudentContactRefId 
        and sc.StudentContactSchoolYear = sct.StudentContactSchoolYear 
        and sct.PrimaryTelephoneNumberIndicator = true*/
	join studentcontactrelationship as scr
		on scr.StudentContactRefId = sc.StudentContactRefId
        and scr.StudentContactSchoolYear = sc.StudentContactSchoolYear
	join student as s
		on s.StudentRefId = scr.StudentRefId
        and s.StudentSchoolYear = scr.StudentSchoolYear
	left join studentenrollment as se
		on se.StudentRefId = s.StudentRefId
        and se.StudentSchoolYear = s.StudentSchoolYear
	join school as sch
		on sch.SchoolRefId = se.SchoolRefId
        and sch.SchoolSchoolYear = se.SchoolSchoolYear
	join lea as l 
		on l.LEARefId = sch.LEARefId 
		and l.LEASchoolYear = sch.LEASchoolYear 
	where sc.StudentContactSchoolYear = 2019
);

/* User Class */
CREATE TABLE OneRosterV1P1_UserClass (
	UserClassId varchar(64),
    UserId varchar(64), 
	UserSchoolYear smallint(6), 
	ClassId varchar(64), 
	ClassSchoolYear smallint(6), 
    OrgId varchar(64), 
	OrgSchoolYear smallint(6), 
	PRIMARY KEY (UserClassId),
    FOREIGN KEY (UserId, UserSchoolYear) REFERENCES OneRosterV1P1_User(SourcedId, SourcedSchoolYear),
    FOREIGN KEY (ClassId, ClassSchoolYear) REFERENCES OneRosterV1P1_Class(SourcedId, SourcedSchoolYear),
    FOREIGN KEY (OrgId, OrgSchoolYear) REFERENCES OneRosterV1P1_Org(SourcedId, SourcedSchoolYear)
);

insert into OneRosterV1P1_UserClass
(
	select distinct
		scs.StudentCourseSectionRefId as UserClassId,
        u.SourcedId as UserId,
		u.SourcedSchoolYear as UserSchoolYear,
		scs.CourseSectionRefId as ClassId,
		scs.CourseSectionSchoolYear as ClassSchoolYear,
        c.SchoolRefId as OrgId,
        c.SchoolSchoolYear as OrgSchoolYear
	from onerosterv1p1_user as u  
    join studentcoursesection as scs
		on scs.StudentRefId = u.SourcedId
        and scs.StudentSchoolYear = u.SourcedSchoolYear
	join coursesection cs 
		on cs.CourseSectionRefId = scs.CourseSectionRefId
        and cs.CourseSectionSchoolYear= scs.CourseSectionSchoolYear
	join course as c 
		on c.CourseRefId = cs.CourseRefId
        and c.CourseSchoolYear = cs.CourseSchoolYear
	where u.SourcedSchoolYear = 2019
    and u.Role = 'student'
)
union all
(
	select distinct
		scs.StaffCourseSectionRefId,
		u.SourcedId,
		u.SourcedSchoolYear,
		scs.CourseSectionRefId,
		scs.CourseSectionSchoolYear,
        c.SchoolRefId,
        c.SchoolSchoolYear
	from onerosterv1p1_user as u
    join staffcoursesection as scs
		on scs.StaffRefId = u.SourcedId
        and scs.StaffSchoolYear = u.SourcedSchoolYear
	join coursesection cs 
		on cs.CourseSectionRefId = scs.CourseSectionRefId
        and cs.CourseSectionSchoolYear= scs.CourseSectionSchoolYear
	join course as c 
		on c.CourseRefId = cs.CourseRefId
        and c.CourseSchoolYear = cs.CourseSchoolYear
	where u.SourcedSchoolYear = 2019
    and (u.Role = 'administrator' or u.Role = 'aide' or u.Role = 'proctor' or u.Role = 'teacher')
);


/* User Agent */
CREATE TABLE OneRosterV1P1_UserAgent (
	UserAgentId varchar(64), 
    UserId varchar(64), 
	UserSchoolYear smallint(6), 
	AgentId varchar(64), 
	AgentSchoolYear smallint(6), 
	PRIMARY KEY (UserAgentId),
	FOREIGN KEY (UserId, UserSchoolYear) REFERENCES OneRosterV1P1_User(SourcedId, SourcedSchoolYear),
    FOREIGN KEY (AgentId, AgentSchoolYear) REFERENCES OneRosterV1P1_User(SourcedId, SourcedSchoolYear)
);

insert into OneRosterV1P1_UserAgent
(
	select distinct
		uuid() as UserAgentId,
        u.SourcedId as UserId,
		u.SourcedSchoolYear as SourcedSchoolYear,
		u2.SourcedId as AgentId,
		u2.SourcedSchoolYear as AgentSchoolYear
	from onerosterv1p1_user as u
	join studentcontactrelationship as scr 
		on scr.StudentRefId = u.SourcedId 
        and scr.StudentSchoolYear = u.SourcedSchoolYear
	join onerosterv1p1_user u2
		on u2.SourcedId = scr.StudentContactRefId
        and u2.SourcedSchoolYear = scr.StudentContactSchoolYear
	where u.Role = 'student'
    and u.SourcedSchoolYear = 2019
    and scr.RelationshipCode is not null
)
union all
(
	select distinct
		uuid() as UserAgentId,
        u.SourcedId as UserId,
		u.SourcedSchoolYear as SourcedSchoolYear,
		u2.SourcedId,
		u2.SourcedSchoolYear
	from onerosterv1p1_user as u
	join studentcontactrelationship as scr 
		on scr.StudentContactRefId = u.SourcedId 
		and scr.StudentContactSchoolYear = u.SourcedSchoolYear
	join onerosterv1p1_user u2
		on u2.SourcedId = scr.StudentRefId
        and u2.SourcedSchoolYear = scr.StudentSchoolYear
	where (u.Role = 'guardian' or u.Role = 'parent' or u.Role = 'relative')
    and u.SourcedSchoolYear = 2019
    and scr.RelationshipCode is not null
);


/* User Org */
CREATE TABLE OneRosterV1P1_UserOrg (
	UserOrgId varchar(64),
    UserId varchar(64), 
	UserSchoolYear smallint(6), 
	OrgId varchar(64), 
	OrgSchoolYear smallint(6), 
	PRIMARY KEY (UserOrgId),
    FOREIGN KEY (UserId, UserSchoolYear) REFERENCES OneRosterV1P1_User(SourcedId, SourcedSchoolYear),
    FOREIGN KEY (OrgId, OrgSchoolYear) REFERENCES OneRosterV1P1_Org(SourcedId, SourcedSchoolYear)
);


insert into OneRosterV1P1_UserOrg
(
	select distinct
		uuid() as UserOrgId,
        u.SourcedId as UserId,
		u.SourcedSchoolYear as UserSchoolYear,
		sen.SchoolRefId as OrgId,
		sen.SchoolSchoolYear as OrgSchoolYear
	from onerosterv1p1_user as u 
    join studentenrollment as sen
		on sen.StudentRefId = u.SourcedId
        and sen.StudentSchoolYear = u.SourcedSchoolYear
    where u.SourcedSchoolYear = 2019
    and u.Role = 'student'
)
union all
(
	select distinct
		uuid() as UserOrgId,
        u.SourcedId as UserId,
		u.SourcedSchoolYear as UserSchoolYear,
		sch.LEARefId,
		sch.LEASchoolYear
	from onerosterv1p1_user as u 
    join studentenrollment as sen
		on sen.StudentRefId = u.SourcedId
        and sen.StudentSchoolYear = u.SourcedSchoolYear
	join school as sch 
		on sch.SchoolRefId = sen.SchoolRefId 
        and sch.SchoolSchoolYear = sen.SchoolSchoolYear
	where u.SourcedSchoolYear = 2019
    and u.Role = 'student'
)
union all
(
	select distinct
		uuid() as UserOrgId,
        u.SourcedId as UserId,
		u.SourcedSchoolYear as UserSchoolYear,
		ta.SchoolRefId,
		ta.SchoolSchoolYear
	from onerosterv1p1_user as u 
    join staffassignment as ta
		on ta.StaffRefId = u.SourcedId
        and ta.StaffSchoolYear = u.SourcedSchoolYear
	where u.SourcedSchoolYear = 2019
    and (u.Role = 'administrator' or u.Role = 'aide' or u.Role = 'proctor' or u.Role = 'teacher')
)
union all
(
	select distinct
		uuid() as UserOrgId,
        u.SourcedId as UserId,
		u.SourcedSchoolYear as UserSchoolYear,
		sch.LEARefId,
		sch.LEASchoolYear
	from onerosterv1p1_user as u 
    join staffassignment as ta
		on ta.StaffRefId = u.SourcedId
        and ta.StaffSchoolYear = u.SourcedSchoolYear
	join school as sch 
		on sch.SchoolRefId = ta.SchoolRefId 
        and sch.SchoolSchoolYear = ta.SchoolSchoolYear
	where u.SourcedSchoolYear = 2019
    and (u.Role = 'administrator' or u.Role = 'aide' or u.Role = 'proctor' or u.Role = 'teacher')
)
union all
(
	select distinct
		uuid() as UserOrgId,
		u.SourcedId as UserId,
		u.SourcedSchoolYear as UserSchoolYear,
        se.SchoolRefId,
		se.SchoolSchoolYear
	from onerosterv1p1_user as u 
	join studentcontactrelationship as scr
		on scr.StudentContactRefId = u.SourcedId
        and scr.StudentContactSchoolYear = u.SourcedSchoolYear
	join onerosterv1p1_user as u2 
		on u2.SourcedId = scr.StudentRefId
        and u2.SourcedSchoolYear = scr.StudentSchoolYear
	join student s 
		on s.StudentRefId = u2.SourcedId
        and s.StudentSchoolYear = u2.SourcedSchoolYear
	join studentenrollment se 
		on se.StudentRefId = s.StudentRefId 
        and se.StudentSchoolYear = s.StudentSchoolYear
	where u.SourcedSchoolYear = 2019
    and (u.Role = 'guardian' or u.Role = 'parent' or u.Role = 'relative')
)
union all
(
	select 
		uuid() as UserOrgId,
        u.SourcedId as UserId,
		u.SourcedSchoolYear as UserSchoolYear,
		sch.LEARefId,
		sch.LEASchoolYear
	from onerosterv1p1_user as u 
	join studentcontactrelationship as scr
		on scr.StudentContactRefId = u.SourcedId
        and scr.StudentContactSchoolYear = u.SourcedSchoolYear
	join onerosterv1p1_user as u2 
		on u2.SourcedId = scr.StudentRefId
        and u2.SourcedSchoolYear = scr.StudentSchoolYear
	join student s 
		on s.StudentRefId = u2.SourcedId
        and s.StudentSchoolYear = u2.SourcedSchoolYear
	join studentenrollment se 
		on se.StudentRefId = s.StudentRefId 
        and se.StudentSchoolYear = s.StudentSchoolYear
	join school as sch 
		on sch.SchoolRefId = se.SchoolRefId 
        and sch.SchoolSchoolYear = se.SchoolSchoolYear
	where u.SourcedSchoolYear = 2019
    and (u.Role = 'guardian' or u.Role = 'parent' or u.Role = 'relative')
);

/* User Identifier */
CREATE TABLE OneRosterV1P1_UserIdentifier(
	UserIdentifierId varchar(64),
    UserId varchar(64), 
	UserSchoolYear smallint(6), 
	Code varchar(50),
	Id varchar(250),
	PRIMARY KEY (UserIdentifierId),
    FOREIGN KEY (UserId, UserSchoolYear) REFERENCES OneRosterV1P1_User(SourcedId, SourcedSchoolYear)
);

insert into OneRosterV1P1_UserIdentifier
(
	select
		si.StudentIdentifierRefId as UserIdentifierId,
        u.SourcedId as SourcedId,
		u.SourcedSchoolYear as SourcedSchoolYear,
		si.IdentificationSystemCode as Code,
		si.StudentId as Id
	from onerosterv1p1_user as u 
	join studentidentifier as si
		on si.StudentRefId = u.SourcedId
        and si.StudentSchoolYear = u.SourcedSchoolYear
    where u.Role = 'student'
    and u.SourcedSchoolYear = 2019
    and si.IdentificationSystemCode != 'SchoolToolRefId'
)
union all
(
	select
		si.StaffIdentifierRefId as UserIdentifierId,
		u.SourcedId as SourcedId,
		u.SourcedSchoolYear as SourcedSchoolYear,
		si.IdentificationSystemCode,
		si.StaffId
	from onerosterv1p1_user as u 
	join staffidentifier as si
		on si.StaffRefId = u.SourcedId
        and si.StaffSchoolYear = u.SourcedSchoolYear
	where (u.Role = 'administrator' or u.Role = 'aide' or u.Role = 'proctor' or u.Role = 'teacher')
    and u.SourcedSchoolYear = 2019
    and si.IdentificationSystemCode != 'SchoolToolRefId'
)
union all
(
	select
		si.StudentContactIdentifierRefId as UserIdentifierId,
		u.SourcedId as SourcedId,
		u.SourcedSchoolYear as SourcedSchoolYear,
		si.IdentificationSystemCode,
		si.StudentContactId
	from onerosterv1p1_user as u 
	join studentcontactidentifier as si
		on si.StudentContactRefId = u.SourcedId
        and si.StudentContactSchoolYear = u.SourcedSchoolYear
	where (u.Role = 'guardian' or u.Role = 'parent' or u.Role = 'relative')
    and u.SourcedSchoolYear = 2019
    and si.IdentificationSystemCode != 'SchoolToolRefId'
);


/* Demographic */
CREATE TABLE OneRosterV1P1_Demographic (
	SourcedId varchar(64), 
	SourcedSchoolYear smallint(6),
    Status varchar(20), 
    DateLastModified datetime,
	DistrictId varchar(30), 
	BirthDate date,
	Sex varchar(50) ,
	AmericanIndianOrAlaskaNative tinyint(1),
	Asian tinyint(1),
	BlackOrAfricanAmerican tinyint(1),
	NativeHawaiianOrOtherPacificIslander tinyint(1), 
	White tinyint(1),
	DemographicRaceTwoOrMoreRaces tinyint(1), 
	HispanicOrLatinoEthnicity tinyint(1), 
	CountryOfBirthCode varchar(50), 
	StateOfBirthAbbreviation varchar(2), 
	CityOfBirth varchar(100),
	PublicSchoolResidenceStatus binary(0),
	PRIMARY KEY (SourcedId, SourcedSchoolYear),
	INDEX (DistrictId)
);

insert into OneRosterV1P1_Demographic
(
	select distinct
		s.StudentRefId as SourcedId, 
		s.StudentSchoolYear as SourcedSchoolYear,
        'active',
		now(),
        l.leaId as DistrictId,
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
	from onerosterv1p1_user as u 
	join Student as s
		on s.StudentRefId = u.SourcedId
        and s.StudentSchoolYear = u.SourcedSchoolYear
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
	join studentenrollment as se
		on se.StudentRefId = s.StudentRefId
        and se.StudentSchoolYear = s.StudentSchoolYear
	join school as sch
		on sch.SchoolRefId = se.SchoolRefId
        and sch.SchoolSchoolYear = se.SchoolSchoolYear
	join lea as l 
		on l.LEARefId = sch.LEARefId 
		and l.LEASchoolYear = sch.LEASchoolYear  
	where u.SourcedSchoolYear = 2019
		and u.Role = 'student'
 );
 
/* Enrollment */
CREATE TABLE OneRosterV1P1_Enrollment (
	SourcedId varchar(64),
	SourcedSchoolYear smallint(6),
    Status varchar(20), 
    DateLastModified datetime,
	DistrictId varchar(30), 
	UserId varchar(64), 
	UserSchoolYear smallint(6), 
	ClassId varchar(64), 
	ClassSchoolYear smallint(6), 
	`Primary` tinyint(4), 
	BeginDate binary(0), 
	EndDate binary(0), 
	OrgId varchar(64), 
	OrgSchoolYear smallint(6),
	PRIMARY KEY (SourcedId, SourcedSchoolYear),
    FOREIGN KEY (UserId, UserSchoolYear) REFERENCES OneRosterV1P1_User(SourcedId, SourcedSchoolYear),
	FOREIGN KEY (ClassId, ClassSchoolYear) REFERENCES OneRosterV1P1_Class(SourcedId, SourcedSchoolYear),
	FOREIGN KEY (OrgId, OrgSchoolYear) REFERENCES OneRosterV1P1_Org(SourcedId, SourcedSchoolYear),
	INDEX (DistrictId)
);

insert into OneRosterV1P1_Enrollment
(
	select distinct
		scs.StudentCourseSectionRefId as SourcedId,
        scs.StudentCourseSectionSchoolYear as SourcedSchoolYear,
        'active',
		now(),
        l.leaId as DistrictId,
        scs.StudentRefId as UserId,
		scs.StudentSchoolYear as UserSchoolYear,
		scs.CourseSectionRefId as ClassId,
		scs.CourseSectionSchoolYear as ClassSchoolYear,
        null as `Primary`,
        null as BeginDate,
        null as EndDate,
        c.SchoolRefId as OrgId,
        c.SchoolSchoolYear as OrgSchoolYear
	from onerosterv1p1_user as u 
	join studentcoursesection as scs
		on scs.StudentRefId = u.SourcedId
        and scs.StudentSchoolYear = u.SourcedSchoolYear
	join coursesection as cs 
		on scs.CourseSectionRefId = cs.CourseSectionRefId
        and scs.CourseSectionSchoolYear= cs.CourseSectionSchoolYear
	left join coursesectionschedule as css
		on cs.CourseSectionRefId = css.CourseSectionRefId
        and cs.CourseSectionSchoolYear = css.CourseSectionSchoolYear
	left join schoolcalendarsession as schcs 
		on css.SchoolCalendarSessionRefId = schcs.SchoolCalendarSessionRefId
        and css.SchoolCalendarSessionSchoolYear = schcs.SchoolCalendarSessionSchoolYear
	join course as c 
		on cs.CourseRefId = c.CourseRefId
        and cs.CourseSchoolYear = c.CourseSchoolYear
	join school as sch
		on sch.SchoolRefId = c.SchoolRefId
        and sch.SchoolSchoolYear = c.SchoolSchoolYear
	join lea as l 
		on l.LEARefId = sch.LEARefId 
		and l.LEASchoolYear = sch.LEASchoolYear 
	where u.SourcedSchoolYear = 2019
		and u.Role = 'student'
)
union all
(
	select distinct
		scs.StaffCourseSectionRefId,
        scs.StaffCourseSectionSchoolYear,
        'active',
		now(),
        l.leaId as DistrictId,
        scs.StaffRefId,
		scs.StaffSchoolYear,
		scs.CourseSectionRefId,
		scs.CourseSectionSchoolYear,
        scs.TeacherOfRecord,
        null, 
        null,
        c.SchoolRefId,
        c.SchoolSchoolYear
	from onerosterv1p1_user as u 
	join staffcoursesection as scs
		on scs.StaffRefId = u.SourcedId
        and scs.StaffSchoolYear = u.SourcedSchoolYear
	join coursesection cs 
		on scs.CourseSectionRefId = cs.CourseSectionRefId
        and scs.CourseSectionSchoolYear= cs.CourseSectionSchoolYear
	join course as c 
		on cs.CourseRefId = c.CourseRefId
        and cs.CourseSchoolYear = c.CourseSchoolYear
	join school as sch
		on sch.SchoolRefId = c.SchoolRefId
        and sch.SchoolSchoolYear = c.SchoolSchoolYear
	join lea as l 
		on l.LEARefId = sch.LEARefId 
		and l.LEASchoolYear = sch.LEASchoolYear  
	where u.SourcedSchoolYear = 2019
		and (u.Role = 'administrator' or u.Role = 'aide' or u.Role = 'proctor' or u.Role = 'teacher')
);