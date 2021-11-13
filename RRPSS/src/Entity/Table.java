package Entity;
/**
 * Represents  the table in the restaurant
 */
public class Table {
	/**
	 * id of the table object
	 */
	private String tableId;
	/**
	 * number of people who can sit in that table
	 */
	private String tableType;
	/**
	 * whether the table is occupied/reserved/vacant
	 */
	private String tableStatus;
	/**
	 * Default Table Constructor
	 */
	public Table() {
		
	}
	/**
	 * Table constructor with given parameters.
	 * @param tableId
	 * @param tableType
	 * @param tableStatus
	 */
	public Table(String tableId, String tableType, String tableStatus) {
		super();
		this.tableId = tableId;
		this.tableType = tableType;
		this.tableStatus = tableStatus;

	}
	/**
	 * get table object's id
	 * @return tableId.
	 */
	public String gettableId() {
		return tableId;
	}
	/**
	 * Set table object's id
	 * @param tableId.
	 */
	public void settableId(String tableId) {
		this.tableId = tableId;
	}
	/**
	 * get table object's type
	 * @return tableType.
	 */
	public String gettableType() {
		return tableType;
	}
	/**
	 * Set table object's id
	 * @param tableType.
	 */
	public void settableType(String tableType) {
		this.tableType = tableType;
	}

	/**
	 * get table object's status.
	 * @return tableStatus.
	 */
	public String gettableStatus() {
		return tableStatus;
	}
	/**
	 * Set table object's status
	 * @param tableStatus.
	 */
	public void settableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}
}