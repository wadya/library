package com.test.repository
import com.test.model.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by Vadym Polishchuk on 1/6/16.
 * wadyasha@gmail.com
 */
@Repository
interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByName(String name)
}