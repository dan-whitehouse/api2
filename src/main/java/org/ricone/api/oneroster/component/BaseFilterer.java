package org.ricone.api.oneroster.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.error.exception.InvalidDataException;
import org.ricone.api.oneroster.error.exception.InvalidFilterFieldException;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseFilterer {
	protected Logger logger = LogManager.getLogger(this.getClass());
	protected Root from;
	private List<Join> joinList = new ArrayList<>();
	protected static final String INVALID_DATA_EXCEPTION = "The filter parameter ";

	public void addJoins(Root<?> from, Join... joins) {
		this.from = from;
		Collections.addAll(joinList, joins);
	}

	protected abstract Path getPath(String field) throws InvalidFilterFieldException, InvalidDataException;

	protected Join getJoin(String alias) {
		return joinList.stream().filter(join -> alias.equalsIgnoreCase(join.getAlias())).findFirst().get();
	}

	protected Root getFrom() {
		return from;
	}

	protected String buildInvalidDataException(String invalidField) {
		return "The filter parameter [" + invalidField + "] in a non-filterable field.";
	}
}
