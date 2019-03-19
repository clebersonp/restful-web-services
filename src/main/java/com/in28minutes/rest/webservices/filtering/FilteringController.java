package com.in28minutes.rest.webservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/filtering-json")
public class FilteringController {

	// removendo os campos field3 do response
	@GetMapping
	public MappingJacksonValue getSomeBean() {
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue jacksonValue = new MappingJacksonValue(new SomeBean("value1", "value2", "value3"));
		jacksonValue.setFilters(filters);
		
		return jacksonValue;
	}

	// removendo o campo field1 do response
	@GetMapping("/list")
	public MappingJacksonValue getListSomeBeans() {
		
		List<SomeBean> asList = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value11", "value22", "value33"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue jacksonValue = new MappingJacksonValue(asList);
		jacksonValue.setFilters(filters);
		
		return jacksonValue;
	}
}