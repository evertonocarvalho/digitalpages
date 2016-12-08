package br.com.digitalpages.marvel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	private String thumbnailPath;
	private String thumbnailExtension;

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

	public void setDataModificacao(String dataModificacao) throws ParseException {
		this.modified = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dataModificacao);
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getThumbnailExtension() {
		return thumbnailExtension;
	}

	public void setThumbnailExtension(String thumbnailExtension) {
		this.thumbnailExtension = thumbnailExtension;
	}

	@JsonProperty("thumbnail")
	public void setThumbnail(Map<String, String> thumbnailMap) {
		this.thumbnailPath = thumbnailMap.get("path");
		this.thumbnailExtension = thumbnailMap.get("extension");
	}

	@Override
	public int compareTo(Character o) {
		return this.getName().compareTo(o.getName());
	}

}
