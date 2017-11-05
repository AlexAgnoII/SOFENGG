package beans_model;

import java.util.Date;

public class Post {

	private String title,
				   body;
	private int postId = 0;
	private Date date;

	public Post(int postId, String title, String body, Date date) {
		super();
		this.body   = body;
		this.title  = title;
		this.postId = postId;
		this.date   = date;
	}

	public Post(String title, String body, Date date) {
		super();
		this.body  = body;
		this.title = title;
		this.date  = date;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Post [" + postId + " title=" + title + "]";
	}

	public void setPostId(int id){
		this.postId = id;
	}
	
	public int getPostId() {
		return postId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
