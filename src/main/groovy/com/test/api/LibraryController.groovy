package com.test.api

import com.test.service.ImportBooks
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Controller
/**
 * Created by Vadym Polishchuk on 1/7/16.
 * wadyasha@gmail.com
 */
@Controller
class LibraryController implements CommandLineRunner {

	@Autowired
	private ImportBooks importService

	private Logger logger = LoggerFactory.getLogger(this.getClass())

	@Override
	void run(String... args) throws Exception {
		if(!args) {
			logger.warn("You must pass at least one parametr. Example: bootRun -Pargs=\"books_0.json books_1.json books_0.json\"")
		}
		args?.each{ arg ->
			importService.importBooks(arg)
		}
	}

}
