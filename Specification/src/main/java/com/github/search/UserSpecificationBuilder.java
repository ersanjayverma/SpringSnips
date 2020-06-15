package com.github.search;

import com.github.model.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserSpecificationBuilder {

    private final List<SearchCriteria> params;

    public UserSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public UserSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public UserSpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }
    public UserSpecificationBuilder with(List<SearchCriteria> searchCriteriaList) {
        params.addAll(searchCriteriaList);
        return this;
    }

    public Specification<UserEntity> build() {
        if (params.isEmpty()) {
            return null;
        }

        List<Specification<UserEntity>> specs = params.stream()
                .map(UserSpecification::new)
                .collect(Collectors.toList());

        Specification<UserEntity> result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Objects.requireNonNull(Specification.where(result)).and(specs.get(i));
        }
        return result;
    }
}