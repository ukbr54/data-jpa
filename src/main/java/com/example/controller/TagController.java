package com.example.controller;

import com.example.projection.TagInfo;
import com.example.respository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagRepository tagRepository;

    @Autowired
    public TagController(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @GetMapping("/most/popular")
    public ResponseEntity<List<TagInfo>> getMostPopularTags(){
        List<TagInfo> tagInfos = tagRepository.topFiveMostCommonlyUsedTag(Limit.of(5));
        return ResponseEntity.status(HttpStatus.OK).body(tagInfos);
    }
}
