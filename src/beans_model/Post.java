package beans_model;

public class Post {

	private String title,
				   body;
	private int postId = 0;

	public Post(String title, String body, int postId) {
		super();
		this.body  = body;
		this.title = title;
		this.postId    = postId;
	}

	public Post(String title, String body) {
		super();
		this.body  = body;
		this.title = title;
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

}
