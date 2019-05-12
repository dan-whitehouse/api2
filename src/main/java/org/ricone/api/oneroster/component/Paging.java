package org.ricone.api.oneroster.component;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.component.error.exception.InvalidPagingException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Paging {
	private Logger logger = LogManager.getLogger(this.getClass());
	private final String OFFSET = "offset";
	private final String LIMIT = "limit";

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Integer offset = null;
	private Integer limit = null;

	Paging(HttpServletRequest request, HttpServletResponse response) throws InvalidPagingException {
		this.request = request;
		this.response = response;

		if(request.getParameter(LIMIT) != null || request.getParameter(OFFSET) != null) {
			buildLimit(request.getParameter(LIMIT));
			buildOffset(request.getParameter(OFFSET));

			response.setHeader(OFFSET, String.valueOf(offset));
			response.setHeader(LIMIT, String.valueOf(limit));
		}
	}

	private void buildLimit(String limit) throws InvalidPagingException {
		if(NumberUtils.isCreatable(limit)) {
			this.limit = Integer.parseInt(limit);
			if(this.limit < 0) {
				throw new InvalidPagingException("Pagination limit value must be a positive number");
			}
		}
		else if(limit == null) {
			this.limit = 100;
		}
		else {
			//If the value is a letter or symbol
			throw new InvalidPagingException("Pagination limit value must be a valid number");
		}
	}

	private void buildOffset(String offset) throws InvalidPagingException {
		if(NumberUtils.isCreatable(offset)) {
			this.offset = Integer.parseInt(offset);
			if(this.offset < 0) {
				throw new InvalidPagingException("Pagination offset value must be a positive number");
			}
		}
		else if(offset == null){
			this.offset = 0;
		}
		else {
			//If the value is a letter or symbol
			throw new InvalidPagingException("Pagination offset value must be a valid number");
		}
	}

	public void setPagingHeaders(int totalRecords) {
		response.setHeader("X-Total-Count", String.valueOf(totalRecords));

		if(isPaged()) {
			/*  Reference: https://stackoverflow.com/questions/27992413/how-do-i-calculate-the-offsets-for-pagination
				It is RECOMMENDED that implementers pass back next, previous, first and last links in the HTTP Link Header.

				Example: 503 student resources exist in the collection. Pagination is on the second page, in units of 10.
				Link:
					<https://imsglobal.org/ims/oneroster/v1p1/students?limit=10&offset=20>; rel="next",
					<https://imsglobal.org/ims/oneroster/v1p1/students?limit=3&offset=500>; rel="last",
					<https://imsglobal.org/ims/oneroster/v1p1/students?limit=10&offset=0>; rel="first",
					<https://imsglobal.org/ims/oneroster/v1p1/students?limit=10&offset=0>; rel="prev"
				NOTE: Pagination must be supported for ALL endpoints that return a collection.
			 */

			int currentPage = (int)Math.floor((double)offset / limit);
			int totalPages = (int)Math.ceil((double)totalRecords / limit);
			int last_offset = (totalPages - 1) * limit;
			int previous_offset = (currentPage - 1) * limit;
			int next_offset = (currentPage + 1) * limit;

			List<String> list = new ArrayList<>();
			if(next_offset <= last_offset) {
				list.add(buildLink(limit, next_offset, "next"));
			}
			list.add(buildLink(limit, last_offset, "last"));
			list.add(buildLink(limit, 0, "first"));
			if(previous_offset >= 0) {
				list.add(buildLink(limit, previous_offset, "prev"));
			}

			response.setHeader(HttpHeaders.LINK, String.join(", ", list));
		}
	}

	private String buildLink(int limit, int offset, String rel) {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		builder.path(request.getRequestURL().toString());
		builder.queryParam(LIMIT, limit);
		builder.queryParam(OFFSET, offset);
		return "<" + builder.build().toString() + ">; rel=\"" + rel + "\"";
	}

	public boolean isPaged() {
		return limit != null && offset != null;
	}

	public Integer getOffset() {
		return offset;
	}

	public Integer getLimit() {
		return limit;
	}

	@Override
	public String toString() {
		return "Paging{" + "offset=" + offset + ", limit=" + limit + '}';
	}
}
