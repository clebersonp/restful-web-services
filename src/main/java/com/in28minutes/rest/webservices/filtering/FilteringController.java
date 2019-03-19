package com.in28minutes.rest.webservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filtering-json")
public class FilteringController {

	@GetMapping
	public SomeBean getSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}

	@GetMapping("/list")
	public List<SomeBean> getListSomeBeans() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value11", "value22", "value33"));
	}
}