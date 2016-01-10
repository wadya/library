package com.test.repository

import com.test.model.Publisher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
/**
 * Created by Vadym Polishchuk on 1/6/16.
 * wadyasha@gmail.com
 */
@Repository
interface PublisherRepository extends JpaRepository<Publisher, Long> {

	Publisher findByName(String name)
}