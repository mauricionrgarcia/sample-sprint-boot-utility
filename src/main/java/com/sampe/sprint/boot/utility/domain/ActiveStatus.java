package com.sampe.sprint.boot.utility.domain;

/**
 * Enumerator to represent status active / inactive and respective code in
 * database
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 21/10/2018 21:46:01
 */
public enum ActiveStatus {

	ACTIVE("A"), INACTIVE("I");

	private String dbCode;

	ActiveStatus(String dbCode) {
		this.dbCode = dbCode;
	}

	/**
	 * @return the dbCode
	 */
	public String getDbCode() {
		return dbCode;
	}

	/**
	 * Retrive ActiveStatus from database code
	 * 
	 * @param dbCode database code
	 * @return ActiveStatus
	 */
	public static ActiveStatus getFromDb(String dbCode) {
		for (ActiveStatus status : ActiveStatus.values()) {
			if (status.getDbCode().equals(dbCode)) {
				return status;
			}
		}
		return null;
	}

}
