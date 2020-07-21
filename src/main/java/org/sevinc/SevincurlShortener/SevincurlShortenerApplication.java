package org.sevinc.SevincurlShortener;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.logging.Handler;

@SpringBootApplication
@EnableScheduling
public class SevincurlShortenerApplication {
	public static void main(String[] args) {

		SpringApplication.run(SevincurlShortenerApplication.class, args);
	      Runnable runnable = new Runnable() {
			  @Override
			  public void run() {
				  System.out.println("running...");
			  }
		  };

	}
}
