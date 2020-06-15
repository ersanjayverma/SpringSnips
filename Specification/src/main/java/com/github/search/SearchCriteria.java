package com.github.search;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SearchCriteria implements Serializable {
    private String key;
    private String operation;
    private Object value;
}
