package com.github.search;


import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchQuery {

    private String name;

    private Integer age;

    private String email;

    public List<SearchCriteria> buildSearchCriteria() {
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(this) != null) {
                    searchCriteriaList.add(new SearchCriteria(field.getName(), ":", field.get(this)));
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        /*
        // if you need endpoint something like q=key:value,key>value then use this snippet
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(.*),");
        Matcher matcher = pattern.matcher(query + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }*/

        return  searchCriteriaList;
    }
}
