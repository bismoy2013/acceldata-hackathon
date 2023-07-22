package com.hackathon.acceldata.controller;

import com.hackathon.acceldata.exception.ResourceNotFoundException;
import com.hackathon.acceldata.model.Content;
import com.hackathon.acceldata.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
Mainly for testing purpose
 */

@RestController
@RequestMapping("/v1/apis")
public class ContentController {

    @Autowired
    ContentRepository contentRepository;

    @GetMapping("/contents")
    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    @PostMapping("/contents")
    public Content createContent(@Valid @RequestBody Content content) {
        return contentRepository.save(content);
    }

    @GetMapping("/contents/{id}")
    public Content getContentById(@PathVariable(value = "id") Long contentId) {
        return contentRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content", "id", contentId));
    }

    @PutMapping("/contents/{id}")
    public Content updateContent(@PathVariable(value = "id") Long contentId,
                                 @Valid @RequestBody Content contentDetail) {

        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content", "id", contentId));

        content.setTitle(contentDetail.getTitle());
        content.setTextItem(contentDetail.getTextItem());

        Content updatedContent = contentRepository.save(content);
        return updatedContent;
    }

    @DeleteMapping("/contents/{id}")
    public ResponseEntity<?> deleteContent(@PathVariable(value = "id") Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content", "id", contentId));

        contentRepository.delete(content);

        return ResponseEntity.ok().build();
    }
}
