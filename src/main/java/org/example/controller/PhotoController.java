package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.PhotoRequest;
import org.example.dto.PhotoResponse;
import org.example.service.PhotoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping
    public List<PhotoResponse> list() {
        return photoService.findAll();
    }

    @GetMapping("/{id}")
    public PhotoResponse detail(@PathVariable Long id){
        return photoService.findById(id);
    }

    @PostMapping
    public PhotoResponse upload(
            @Valid @ModelAttribute PhotoRequest request,
            @RequestParam MultipartFile file
    ) {
        return photoService.save(request, file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        photoService.delete(id);
    }
}
