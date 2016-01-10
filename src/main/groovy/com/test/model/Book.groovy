package com.test.model

import javax.persistence.*

/**
 * Created by Vadym Polishchuk on 1/5/16.
 * wadyasha@gmail.com
 */
@Entity
class Book {

	@Id
	@GeneratedValue
	private Long id

	@Column(name="original_id", length = 25)
	private String originalId

	@Column(name="title", length = 250)
	private String title

	@Column(name = "published_date")
	private Date publishedDate

	@Column(name = "page_count")
	private Integer pageCount

	@ManyToOne
	@JoinColumn(name = "publisher_id")
	private Publisher publisher

	@ManyToMany(
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)
	@JoinTable(
			name = "Book_Author",
			joinColumns = @JoinColumn(name = "book_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "author_id",referencedColumnName = "id"))
	private Set<Author> authors

	Long getId() {
		return id
	}

	void setId(Long id) {
		this.id = id
	}

	String getTitle() {
		return title
	}

	void setTitle(String title) {
		this.title = title
	}

	Date getPublishedDate() {
		return publishedDate
	}

	void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate
	}

	Integer getPageCount() {
		return pageCount
	}

	void setPageCount(Integer pageCount) {
		this.pageCount = pageCount
	}

	Publisher getPublisher() {
		return publisher
	}

	void setPublisher(Publisher publisher) {
		this.publisher = publisher
	}

	Set<Author> getAuthors() {
		return authors
	}

	void setAuthors(Set<Author> authors) {
		this.authors = authors
	}

	String getOriginalId() {
		return originalId
	}

	void setOriginalId(String originalId) {
		this.originalId = originalId
	}

	boolean equals(o) {
		if (this.is(o)) return true
		if (!(o instanceof Book)) return false

		Book book = (Book) o

		if (authors != book.authors) return false
		if (originalId != book.originalId) return false
		if (pageCount != book.pageCount) return false
		if (publishedDate != book.publishedDate) return false
		if (publisher != book.publisher) return false
		if (title != book.title) return false

		return true
	}

	int hashCode() {
		int result
		result = (originalId != null ? originalId.hashCode() : 0)
		result = 31 * result + (title != null ? title.hashCode() : 0)
		result = 31 * result + (publishedDate != null ? publishedDate.hashCode() : 0)
		result = 31 * result + (pageCount != null ? pageCount.hashCode() : 0)
		return result
	}


	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", originalId='" + originalId + '\'' +
				", title='" + title + '\'' +
				", publishedDate=" + publishedDate +
				", pageCount=" + pageCount +
				", publisher=" + publisher +
//				", authors=" + authors*.toString() +
				'}';
	}
}
