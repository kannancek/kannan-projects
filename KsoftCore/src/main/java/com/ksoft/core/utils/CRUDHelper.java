package com.ksoft.core.utils;

public class CRUDHelper {

	public static final int NOLIMIT = -1;
	
	private String[] arrFields;
	private String tableName;
	private String whereClause;
	private String orderBy;
	private String groupBy;
	private String limit;
	


	public CRUDHelper() {

	}

	public void setArrFields(String[] arrFields) {

		this.arrFields = arrFields;

	}

	public String[] getArrFields() {

		return (this.arrFields);

	}

	public void setTableName(String tableName) {

		this.tableName = tableName;

	}

	public String getTableName() {

		return (this.tableName);

	}

	public void setWhereClause(String whereClause) {

		this.whereClause = whereClause;

	}

	public String getWhereClause() {

		return (this.whereClause);

	}

	public void setOrderBy(String orderBy) {

		this.orderBy = orderBy;

	}

	public String getOrderBy() {

		return (this.orderBy);

	}

	public void setGroupBy(String groupBy) {

		this.groupBy = groupBy;

	}

	public String getGroupBy() {

		return (this.groupBy);

	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(int start, int limit) {
		
		if(start != NOLIMIT && limit != NOLIMIT)			
			this.limit = " limit " + start + " , " + limit;
	}
	
	public void reset() {

		this.arrFields = null;
		this.tableName = null;
		this.whereClause = null;
		this.orderBy = null;
		this.groupBy = null;

	}

	public String getInsertQuery() {

		if (arrFields == null || tableName == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();

		sb.append("insert into " + tableName + " (");

		for (int i = 0; i < arrFields.length; i++) {

			if (i != 0) {

				sb.append(",");

			}

			sb.append(arrFields[i]);
		}

		sb.append(")");
		sb.append(" values (");

		for (int i = 0; i < arrFields.length; i++) {

			if (i != 0) {
				sb.append(",");
			}
			sb.append("?");

		}

		sb.append(")");

		return sb.toString();
	}

	public String getUpdateQuery() {
		
		if (arrFields == null || tableName == null){
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("update " + tableName + " set ");
		
		for (int i = 0; i < arrFields.length; i++) {
			
			if (i != 0) {
				sb.append(",");
			}
			
			sb.append(arrFields[i] + " = ?");

		}
		sb.append(" where 1 = 1 ");

		if (whereClause != null) {
			sb.append(whereClause);
		}

		return sb.toString();
	}

	public String getDeleteQuery() {

		if (tableName == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		sb.append("delete from " + tableName);
		sb.append(" where 1 = 1 ");

		if (whereClause != null) {
			sb.append(whereClause);
		}

		return sb.toString();
	}

	/*
	 * Return a <code>String</code> SQL query which can be used for database
	 * select operations. as input are equal.
	 */
	public String getSelectQuery() {
		if (arrFields == null || tableName == null)
			return null;
		StringBuffer sb = new StringBuffer();
		
		sb.append("select ");		

		for (int i = 0; i < arrFields.length; i++) {
			if (i != 0)
				sb.append(",");
			sb.append(arrFields[i]);
		}
		
		sb.append(" from " + tableName);
		sb.append(" where 1 = 1 ");
		
		if (whereClause != null){
			sb.append(whereClause);
		}

		if (groupBy != null)
			sb.append(groupBy);

		if (orderBy != null)
			sb.append(orderBy);
		
		if (limit != null)
			sb.append(limit);
		
		return sb.toString();
	}

	public String getCountQuery() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select count(*) ");
		sb.append(" from " + tableName);
		sb.append(" where 1 = 1 ");
		
		if (whereClause != null){
			sb.append(whereClause);
		}
		
		return sb.toString();
	}

}
