package pl.indecoders.archetype.domain;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * The Class AbstractEntity.
 * 
 * @author Mateusz
 */
@MappedSuperclass
public abstract class AbstractEntity {
	
	@Column(name = "creation_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime creationDate;

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public AbstractEntity() {
	}

	public AbstractEntity(DateTime creationDate) {
		super();
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
