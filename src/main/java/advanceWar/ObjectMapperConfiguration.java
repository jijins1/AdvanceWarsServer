package advanceWar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import advanceWar.action.Action;
import advanceWar.action.ActionDeserializer;

@Configuration
public class ObjectMapperConfiguration {
	@Bean
	public ObjectMapper objectMapper() {
		SimpleModule module = new SimpleModule();
        module.addDeserializer(Action.class, new ActionDeserializer());
        return new ObjectMapper().registerModules(module);
	}
	
}
