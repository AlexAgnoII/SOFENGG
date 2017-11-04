package beans_model;

public class Post {

	private String title,
				   body;

	public Post(String title, String body) {
		super();
		this.body = body;
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
		return "Post [title=" + title + ", body=" + body + "]";
	}

}
