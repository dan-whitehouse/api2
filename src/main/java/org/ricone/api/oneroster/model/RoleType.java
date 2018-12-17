package org.ricone.api.oneroster.model;

public enum RoleType {
	administrator, //Administrator in the organization (e.g. School). May be used for enrollment.
	aide, //Someone who provides appropriate aide to the user but NOT also one of the other roles.
	guardian, //Guardian of the user and NOT the Mother or Father. May also be a Relative.
	parent, //Mother or father of the user.
	proctor, //Exam proctor. Added in V1.1. May be used for enrollment.
	relative, //A relative of the user and NOT the Mother or Father. May also be the Guardian.
	student, //A student at a organization (e.g. School). May be used for enrollment.
	teacher //A Teacher at organization (e.g. School). May be used for enrollment.
}
