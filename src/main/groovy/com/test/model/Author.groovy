package com.test.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Created by Vadym Polishchuk on 1/5/16.
 * wadyasha@gmail.com
 */
@Entity
class Author {

	@Id
	@GeneratedValue
	private Long id

	@Column(name = "name", length = 100)
	private String name

	Long getId() {
		return id
	}

	void setId(Long id) {
		this.id = id
	}

	String getName() {
		return name
	}

	void setName(String name) {
		this.name = name
	}

	boolean equals(o) {
		if (this.is(o)) return true
		if (!(o instanceof Author)) return false

		Author author = (Author) o

		if (id != author.id) return false
		if (name != author.name) return false

		return true
	}

	int hashCode() {
		int result
		result = (id != null ? id.hashCode() : 0)
		result = 31 * result + (name != null ? name.hashCode() : 0)
		return result
	}


	@Override
	public String toString() {
		return "Author{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
