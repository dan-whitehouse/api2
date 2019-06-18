package org.ricone.security.acl;

public class PathPermissionMapper {
	public PathPermission map(String appId, Service service) {
		boolean get = false, post = false, put = false, delete = false;
		for (Right right : service.getRights().getRight()) {
			switch(right.getType().toUpperCase()) {
				case "QUERY": {
					if("APPROVED".equalsIgnoreCase(right.getValue())) {
						get = true;
						break;
					}
				}
				case "CREATE": {
					if("APPROVED".equalsIgnoreCase(right.getValue())) {
						post = true;
						break;
					}
				}
				case "UPDATE": {
					if("APPROVED".equalsIgnoreCase(right.getValue())) {
						put = true;
						break;
					}
				}
				case "DELETE": {
					if("APPROVED".equalsIgnoreCase(right.getValue())) {
						delete = true;
						break;
					}
				}
				default: break;
			}
		}
		return new PathPermission(appId,"/requests/" + service.getName(), get, post, put, delete);
	}
}
