package com.slalom.sluber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2 home boy https://www.youtube.com/watch?v=gduKpLW_vdY&ab_channel=JavaBrains says we need this annotation to enable swagger when running...
//looks like the previous team put this in the SwaggerConfig class
public class SluberServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SluberServiceApplication.class, args);
	}

}
