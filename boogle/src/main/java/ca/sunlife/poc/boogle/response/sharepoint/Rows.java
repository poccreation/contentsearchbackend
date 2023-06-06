package ca.sunlife.poc.boogle.response.sharepoint;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rows {
	@JsonProperty("Cells")
	List<Cells> cells;

	public void setCells(List<Cells> cells) {
		this.cells = cells;
	}

	public List<Cells> getCells() {
		return cells;
	}

	public Rows() {
		if (null == cells) {
			cells = new ArrayList<>();
		}
	}
}
