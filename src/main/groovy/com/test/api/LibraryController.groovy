package com.test.api

import com.test.service.Import
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
	private Import importService


	@Override
	void run(String... args) throws Exception {
		args.each{ arg ->
			importService.importBooks(arg)
		}
	}

}
