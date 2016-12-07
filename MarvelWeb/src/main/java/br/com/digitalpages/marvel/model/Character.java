package br.com.digitalpages.marvel.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
@Entity
@Table(name = "characters")
public class Character implements Comparable<Character> {

	@Id
	private Long id;

	private String name;

	@Column(columnDefinition = "LONGVARCHAR")
	private String description;

	private Date modified;

	@OneToOne(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
	private Thumbnail thumbnail;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getModified() {
		return this.modified;
	}

	public String getDataModificacao() {
		if (this.modified != null) {
			return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(this.modified);
		}
		return "";
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Thumbnail getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;

		// Used to persist Thumbnail with the FK reference from this class.
		this.thumbnail.setCharacter(this);
	}

	@Override
	public int compareTo(Character o) {
		return this.getName().compareTo(o.getName());
	}

}
