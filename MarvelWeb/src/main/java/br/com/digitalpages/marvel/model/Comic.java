package br.com.digitalpages.marvel.model;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
public class Comic {

	private Long id;
	private String title;
	private Integer issueNumber;
	private String description;
	private Thumbnail thumbnail;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIssueNumber() {
		return this.issueNumber;
	}

	public void setIssueNumber(Integer issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Thumbnail getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}

}
