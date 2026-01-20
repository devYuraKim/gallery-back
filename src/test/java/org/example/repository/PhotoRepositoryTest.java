package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.entity.Photo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class PhotoRepositoryTest {

    private final PhotoRepository photoRepository;

    @Test
    @DisplayName("사진 저장 테스트")
    void save(){
        // 1.given 필요 데이터 설정
        Photo photo = Photo.builder()
                .title("test image")
                .description("test description")
                .imageUrl("/uploads/test.jpg")
                .build();

        // 2.when 테스트 동작 수행
        Photo saved = photoRepository.save(photo);

        // 3.then 결과 일치 여부 확인
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("test image");
    }

    @Test
    @DisplayName("사진 목록 조회")
    void findAll(){

        photoRepository.save(Photo.builder().title("test image 1").description("test description 1").imageUrl("/uplodas/test1.jpg").build());
        photoRepository.save(Photo.builder().title("test image 2").description("test description 2").imageUrl("/uplodas/test2.jpg").build());

        List<Photo> photos = photoRepository.findAll();

        assertThat(photos.size()).isEqualTo(2);
    }

}