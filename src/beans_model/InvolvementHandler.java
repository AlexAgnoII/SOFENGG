package beans_model;

public class InvolvementHandler {
	
	private int iHId,
				involvementId;
	private String handler;

	public InvolvementHandler(int iHId, int involvementId, String handler) {
		super();
		this.iHId = iHId;
		this.involvementId = involvementId;
		this.handler = handler;
	}
	
	public int getiHId() {
		return iHId;
	}

	public void setiHId(int iHId) {
		this.iHId = iHId;
	}

	public int getInvolvementId() {
		return involvementId;
	}

	public void setInvolvementId(int involvementId) {
		this.involvementId = involvementId;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	@Override
	public String toString() {
		return "InvolvementHandler [iHId=" + iHId + ", involvementId=" + involvementId + ", handler=" + handler + "]";
	}
	
	

}
