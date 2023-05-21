package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.io.File;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
}

@ShellComponent
class BugExample {
	private final static File POM_FILE = new File("./pom.xml");

	@ShellMethod("Hello")
	@ShellMethodAvailability("pomFile")
	public String hello(@ShellOption(defaultValue = "World") String version) {

		return String.format("Hello, %s", version);
	}

	public Availability pomFile() {
		return POM_FILE.exists() ? Availability.available()
				: Availability.unavailable(String.format("%s does not exist", POM_FILE.getName()));
	}
	
}