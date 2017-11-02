package beans_model;

public class Post {

	private String title,
				   description;

	public Post(String title, String description) {
		super();
		this.description = description;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescripti() {
		return description;
	}

	public void setDescripti(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Post [title=" + title + ", descripti=" + description + "]";
	}

}
