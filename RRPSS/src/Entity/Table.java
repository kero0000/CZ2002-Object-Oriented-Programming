package Entity;

public class Table {
	private String tableId;
	private String tableType;
	private String tableStatus;
	
	public Table() {
		
	}
	
	public Table(String tableId, String tableType, String tableStatus) {
		super();
		this.tableId = tableId;
		this.tableType = tableType;
		this.tableStatus = tableStatus;

	}

	public String gettableId() {
		return tableId;
	}

	public void settableId(String tableId) {
		this.tableId = tableId;
	}

	public String gettableType() {
		return tableType;
	}

	public void settableType(String tableType) {
		this.tableType = tableType;
	}


	public String gettableStatus() {
		return tableStatus;
	}

	public void settableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}
}