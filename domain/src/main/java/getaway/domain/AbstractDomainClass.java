package getaway.domain;

import javax.persistence.*;

import java.util.Date;

@MappedSuperclass
public class AbstractDomainClass implements DomainObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Integer version;

	private Date createdDate;
	private Date updatedDate;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		updatedDate = new Date();
		if (createdDate == null) {
			createdDate = new Date();
		}
	}
}
