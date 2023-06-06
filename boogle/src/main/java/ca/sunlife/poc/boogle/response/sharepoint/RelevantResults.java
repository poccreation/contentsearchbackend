package ca.sunlife.poc.boogle.response.sharepoint;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelevantResults {
	
	@JsonProperty("RowCount")
	private Long rowCount;
	@JsonProperty("TotalRows")
	private Long totalRows;
	@JsonProperty("Table")
	private Table table;
	
	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
	}
	
	public void setTable(Table table) {
		this.table = table;
	}
	
	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}
	
	public Long getRowCount() {
		return rowCount;
	}
	
	public Long getTotalRows() {
		return totalRows;
	}
	public Table getTable() {
		return table;
	}
}
