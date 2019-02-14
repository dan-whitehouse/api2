package org.ricone.api.oneroster.component.springfox;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.oneroster.model.ErrorResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface SwaggerOperation {

	@interface AcademicSessions {
		@ApiOperation(value = "getAcademicSession", tags = {"AcademicSession"})
		@interface GetAcademicSession {}

		@ApiOperation(value = "getAllAcademicSessions", tags = {"AcademicSession"})
		@interface GetAllAcademicSessions {}

		@ApiOperation(value = "getTerm", tags = {"AcademicSession"})
		@interface GetTerm {}

		@ApiOperation(value = "getAllTerms", tags = {"AcademicSession"})
		@interface GetAllTerms {}

		@ApiOperation(value = "getGradingPeriod", tags = {"AcademicSession"})
		@interface GetGradingPeriod {}

		@ApiOperation(value = "getAllGradingPeriods", tags = {"AcademicSession"})
		@interface GetAllGradingPeriods {}

		@ApiOperation(value = "getGradingPeriodsForTerm", tags = {"AcademicSession"})
		@interface GetGradingPeriodsForTerm {}
	}

	@interface Classes {

	}


	@interface Courses {

	}


	@interface Demographics {

	}

	@interface EnrollmentsResponse {

	}

	@interface Orgs {

	}

	@interface User {}
}