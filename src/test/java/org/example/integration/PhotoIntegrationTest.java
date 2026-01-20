package org.example.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.entity.Photo;
import org.example.repository.PhotoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class PhotoIntegrationTest {

    private final MockMvc mockMvc;
    private final PhotoRepository photoRepository;
    private final ObjectMapper objectMapper;

    @Test
    @DisplayName("사진 목록 조회")
    void getPhotos() throws Exception {
        photoRepository.save(
                Photo.builder().title("title1").description("description1").imageUrl("image1.jpg").build()
        );
        photoRepository.save(
                Photo.builder().title("title2").description("description2").imageUrl("image2.jpg").build()
        );
        mockMvc.perform(get("/api/photos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("title1"));
    }

}