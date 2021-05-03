package pl.jbed.stud.SomeWebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SomeWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SomeWebServiceApplication.class, args);
	}

}
