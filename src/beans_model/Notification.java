package beans_model;

public class Notification {

	private int notifId = 0,
				postId = 0,
				notifRead = 0;
	private String notifTitle,
				   notifContent;
	
	public Notification(int notifId, int postId, String notifTitle, String notifContent, int notifRead) {
		super();
		this.notifId = notifId;
		this.postId = postId;
		this.notifTitle = notifTitle;
		this.notifContent = notifContent;
		this.notifRead = notifRead;
	}
	
	public Notification(int postId, String notifTitle, String notifContent, int notifRead) {
		super();
		this.postId = postId;
		this.notifTitle = notifTitle;
		this.notifContent = notifContent;
		this.notifRead = notifRead;
	}

	@Override
	public String toString() {
		return "Notification [notifId=" + notifId + ", notifTitle=" + notifTitle + "]";
	}

	public int getNotifId() {
		return notifId;
	}

	public void setNotifId(int notifId) {
		this.notifId = notifId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getNotifRead() {
		return notifRead;
	}

	public void setNotifRead(int notifRead) {
		this.notifRead = notifRead;
	}

	public String getNotifTitle() {
		return notifTitle;
	}

	public void setNotifTitle(String notifTitle) {
		this.notifTitle = notifTitle;
	}

	public String getNotifContent() {
		return notifContent;
	}

	public void setNotifContent(String notifContent) {
		this.notifContent = notifContent;
	}
	
	
}