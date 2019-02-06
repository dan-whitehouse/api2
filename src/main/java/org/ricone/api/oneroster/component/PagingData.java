package org.ricone.api.oneroster.component;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class PagingData {
	private Logger logger = LogManager.getLogger(this.getClass());
	private final String OFFSET = "offset";
	private final String LIMIT = "limit";

	HttpServletRequest request;
	private HttpServletResponse response;
	private Integer offset = null;
	private Integer limit = null;

	PagingData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.request = request;
		this.response = response;

		if(NumberUtils.isDigits(request.getParameter(OFFSET))) {
			offset = Integer.parseInt(request.getParameter(OFFSET));
		}

		if(NumberUtils.isDigits(request.getParameter(LIMIT))) {
			limit = Integer.parseInt(request.getParameter(LIMIT));
		}

		if(isPaged()) {
			if(offset == null) offset = 0;
			if(limit == null) limit = 100;

			response.setHeader(OFFSET, String.valueOf(offset));
			response.setHeader(LIMIT, String.valueOf(limit));
		}
	}

	public boolean isPaged() {
		return limit != null;
	}

	public Integer getOffset() {
		return offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setPagingHeaders(int totalRecords) {
		/*  TODO: Should this only be returned when paging is applicable? Meaning, move the method call in DAO's to outside of the paging block.
			It is RECOMMENDED that implementations pass the total resource count in collection back to the requester.
			This MUST be provided in the custom HTTP header: X-Total-Count
		 */
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
		builder.queryParam("limit", limit);
		builder.queryParam("offset", offset);
		return "<" + builder.build().toString() + ">; rel=\"" + rel + "\"";
	}

	@Override
	public String toString() {
		return "Paging{" + "offset=" + offset + ", limit=" + limit + '}';
	}
}
