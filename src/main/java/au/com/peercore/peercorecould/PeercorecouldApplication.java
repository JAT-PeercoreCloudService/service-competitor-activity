package au.com.peercore.peercorecould;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication

@EnableMongoAuditing

public class PeercorecouldApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeercorecouldApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper(){
		return new  ModelMapper();
	}

}
