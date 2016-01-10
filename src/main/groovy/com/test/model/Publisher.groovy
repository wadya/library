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
class Publisher {

	@Id
	@GeneratedValue
	private Long id

	@Column(name = "name", length = 100)
	private String name

//	@OneToMany(mappedBy = "publisher")
//	private Set<Book> books = new HashSet<Book>()

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

//	Set<Book> getBooks() {
//		return books
//	}
//
//	void setBooks(Set<Book> books) {
//		this.books = books
//	}

	boolean equals(o) {
		if (this.is(o)) return true
		if (!(o instanceof Publisher)) return false

		Publisher publisher = (Publisher) o

//		if (books != publisher.books) return false
		if (id != publisher.id) return false
		if (name != publisher.name) return false

		return true
	}

	int hashCode() {
		int result
		result = (id != null ? id.hashCode() : 0)
		result = 31 * result + (name != null ? name.hashCode() : 0)
//		result = 31 * result + (books != null ? books.hashCode() : 0)
		return result
	}


	@Override
	public String toString() {
		return "Publisher{" +
				"id=" + id +
				", name='" + name + '\'' +
//				", books=" + books?.toString() +
				'}';
	}
}
