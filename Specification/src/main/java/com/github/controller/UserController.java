package com.github.controller;

import com.github.model.UserEntity;
import com.github.repository.UserRepository;
import com.github.search.SearchQuery;
import com.github.search.UserSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/search")
    public ResponseEntity<List<UserEntity>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "30") int limit,
            SearchQuery query) {

        UserSpecificationBuilder builder = new UserSpecificationBuilder();
        builder.with(query.buildSearchCriteria());

        Specification<UserEntity> spec = builder.build();
        return ResponseEntity.ok().body(userRepository.findAll(spec, PageRequest.of(page, limit)).getContent());
    }
}
