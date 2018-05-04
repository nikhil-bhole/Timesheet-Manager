package bean;

public class RecordBean {
		private String id;
	    private String date;
	    private String tdayStart;
	    private String tlunchStart;
	    private String tlunchend;
	    private String tbreakStart;
	    private String tbreakEnd;
	    private String tDayEnd;
	    private String leaveType;
	    private String casualleave;
	    private String sickleave;
	    private String holidays;
	    private String leavewithoutpay;
	    
	    public String getCasualleave() {
			return casualleave;
		}

		public void setCasualleave(String casualleave) {
			this.casualleave = casualleave;
		}

		public String getSickleave() {
			return sickleave;
		}

		public void setSickleave(String sickleave) {
			this.sickleave = sickleave;
		}

		public String getHolidays() {
			return holidays;
		}

		public void setHolidays(String holidays) {
			this.holidays = holidays;
		}

		public String getLeavewithoutpay() {
			return leavewithoutpay;
		}

		public void setLeavewithoutpay(String leavewithoutpay) {
			this.leavewithoutpay = leavewithoutpay;
		}

		
	    public RecordBean() {
	    }
	    
	    public RecordBean(String date, String tdayStart,String tlunchStart, String tlunchend, String tbreakStart, String tbreakEnd, String tDayEnd, String casualleave, String sickleave, String holidays,String leavewithoutpay) {
	    	this.date = date;
	    	this.tdayStart = tdayStart;
	        this.tlunchStart = tlunchStart;
	        this.tlunchend = tlunchend;
	        this.tbreakStart = tbreakStart;
	        this.tbreakEnd = tbreakEnd;
	        this.tDayEnd = tDayEnd;
	        this.casualleave = casualleave;
	        this.sickleave = sickleave;
	        this.holidays = holidays;
	        this.leavewithoutpay = leavewithoutpay;
	        
	    }
	    public RecordBean(String id,String date, String tdayStart,String tlunchStart, String tlunchend, String tbreakStart, String tbreakEnd, String tDayEnd,String casualleave, String sickleave, String holidays,String leavewithoutpay) {
	        this.id = id;
	        this.date = date;
	    	this.tdayStart = tdayStart;
	        this.tlunchStart = tlunchStart;
	        this.tlunchend = tlunchend;
	        this.tbreakStart = tbreakStart;
	        this.tbreakEnd = tbreakEnd;
	        this.tDayEnd = tDayEnd;
	        this.casualleave = casualleave;
	        this.sickleave = sickleave;
	        this.holidays = holidays;
	        this.leavewithoutpay = leavewithoutpay;
	    }
	    
	    public String getID() {
	        return id;
	    }
	    public void setID(String id) {
	        this.id = id;
	    }
	    
	    public String getdate() {
	        return date;
	    }
	    public void setDate(String date) {
	        this.date = date;
	    }
	    
	    public String getTdayStart() {
			return tdayStart;
		}

		public void setTdayStart(String tdayStart) {
			this.tdayStart = tdayStart;
		}

		public String getTlunchStart() {
			return tlunchStart;
		}

		public void setTlunchStart(String tlunchStart) {
			this.tlunchStart = tlunchStart;
		}

		public String getTlunchend() {
			return tlunchend;
		}

		public void setTlunchend(String tlunchend) {
			this.tlunchend = tlunchend;
		}

		public String getTbreakStart() {
			return tbreakStart;
		}

		public void setTbreakStart(String tbreakStart) {
			this.tbreakStart = tbreakStart;
		}

		public String getTbreakEnd() {
			return tbreakEnd;
		}

		public void setTbreakEnd(String tbreakEnd) {
			this.tbreakEnd = tbreakEnd;
		}

		public String gettDayEnd() {
			return tDayEnd;
		}

		public void settDayEnd(String tDayEnd) {
			this.tDayEnd = tDayEnd;
		}

		public String getLeaveType() {
			return leaveType;
		}

		public void setLeaveType(String leaveType) {
			this.leaveType = leaveType;
		}

}


