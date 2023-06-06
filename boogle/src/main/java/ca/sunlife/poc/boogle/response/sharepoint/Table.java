package ca.sunlife.poc.boogle.response.sharepoint;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Table {
	@JsonProperty("Rows")
	private List<Rows> rows;

	public void setRows(List<Rows> rows) {
		this.rows = rows;
	}

	public List<Rows> getRows() {
		return rows;
	}

	public Table() {
		if (null == rows) {
			rows = new ArrayList<>();
		}
	}

}
