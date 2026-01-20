package org.example.service;

import org.example.dto.PhotoResponse;
import org.example.entity.Photo;
import org.example.repository.PhotoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PhotoServiceTest {

    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private FileStorageService fileStorageService;

    @InjectMocks
    private PhotoService photoService;

    @Test
    @DisplayName("사진 목록 조회")
    void findAll() {
        List<Photo> photos = List.of(
                createPhoto(1),
                createPhoto(2)
        );

        given(photoRepository.findAll()).willReturn(photos);

        List<PhotoResponse> result = photoService.findAll();
        assertThat(result).hasSize(2);
    }

    private Photo createPhoto(int number){
        return Photo.builder().title("test image " + number).imageUrl("/uploads/test"+number+".jpg").build();
    }

    @Test
    @DisplayName("사진 상세 조회")
    void findById() {
        Photo photo = createPhoto(1);

        given(photoRepository.findById(1L)).willReturn(Optional.of(photo));

        PhotoResponse result = photoService.findById(1L);
        assertThat(result.getTitle()).isEqualTo("test image 1");

    }

}
