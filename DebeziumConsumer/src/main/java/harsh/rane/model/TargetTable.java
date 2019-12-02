package harsh.rane.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CAREFIRST")
public class TargetTable {
	@Id
	private Integer cfid;
	private String cfname;
	
	public Integer getCfid() {
		return cfid;
	}
	public void setCfid(Integer cfid) {
		this.cfid = cfid;
	}
	public String getCfname() {
		return cfname;
	}
	public void setCfname(String cfname) {
		this.cfname = cfname;
	}
	@Override
	public String toString() {
		return "TargetTable [cfid=" + cfid + ", cfname=" + cfname + "]";
	}
	

}
