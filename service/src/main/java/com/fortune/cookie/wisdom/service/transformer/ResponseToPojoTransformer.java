package com.fortune.cookie.wisdom.service.transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

// There should be a way to add Jackson mapper to the configuration without having to explicitly write a transformer
@Component
public class ResponseToPojoTransformer {

    // I'd rethink the naming of this method. It does not do anything that is specific to the fortune cookie wisdoms,
    // so why restrict its use through the choice of naming
    // On the other hand there is nothing in the name that would indicate
    // that you are expecting a class parameter and converting it to a list of those
    //
    // what happens when you have
    //   foo extends baz
    //   bar extends baz
    //   responseBody contains foo and bar, and you want a List of baz?
    //   ? extends T
    //
    // instead of a utility-class usage, I'd move the type parameter into the class declaration,
    // and create separate instances for the separate types it is meant to handle
	public <T> List<T> convertResponseToWisdoms(String responseBody, Class<T> listType) {
		ObjectMapper mapper = new ObjectMapper();
		List<T> result = new ArrayList<>();
		try {
			result.addAll(mapper.readValue(responseBody,
					mapper.getTypeFactory().constructCollectionType(List.class, listType)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
