package vo;

import java.sql.Date;

public class Visit {
	private Date visit;



	public Visit() {
	
	} 
	
	public Visit(Date visit) {
		super();
		this.visit = visit; 
	}
	
	public Date getVisit() {
		return visit;
	}

	public void setVisit(Date visit) {
		this.visit = visit;
	}

	@Override
	public String toString() {
			
		return "Visit [visit=" + visit + "]";
	}

	
	
	
	

}
