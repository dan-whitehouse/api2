package org.ricone.api.oneroster.model;

public enum SessionType {
	gradingPeriod, //Denotes a period over which some grade/result is to be awarded.
	semester, //Denotes a semester period. Typically there a two semesters per schoolYear.
	schoolYear, //Denotes the school year.
	term //Denotes a term period. Typically there a three terms per schoolYear.
}
