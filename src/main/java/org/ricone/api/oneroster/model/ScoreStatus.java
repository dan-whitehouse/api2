package org.ricone.api.oneroster.model;

public enum ScoreStatus {
	exempt, //The result is exempt i.e. this score does NOT contribute to any summative assessment.
	fullyGraded, //The result is fully graded.
	notSubmitted, //The result is not submitted.
	partiallyGraded, //The result is partially graded. Further scoring will be undertaken and this score must NOT be used in summative assessment i.e. it must become 'fully graded'.
	submitted //The result is submitted. This is a FINAL score and can only be changed as part of a formal review process
}
