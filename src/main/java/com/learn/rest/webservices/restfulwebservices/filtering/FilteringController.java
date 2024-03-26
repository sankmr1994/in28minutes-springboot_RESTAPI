package com.learn.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public FilteringBean singleBeanStatic() {
        return new FilteringBean();
    }

    @GetMapping("/filtering-list")
    public List<FilteringBean> manyBeanStatic() {
        return Arrays.asList(new FilteringBean(), new FilteringBean());
    }

    @GetMapping("/filteringDynamic")
    public MappingJacksonValue singleBeanDynamic() {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(new FilteringBean());
        SimpleBeanPropertyFilter beanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("beanFilter", beanPropertyFilter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }


    @GetMapping("/filteringDynamic-list")
    public MappingJacksonValue manyBeanDynamic() {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(new FilteringBean());
        SimpleBeanPropertyFilter beanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("beanFilter", beanPropertyFilter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
