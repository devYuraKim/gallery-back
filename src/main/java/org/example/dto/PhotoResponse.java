package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.Photo;

@Getter
@Builder
public class PhotoResponse {

    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    public static PhotoResponse from(Photo photo) {
        return PhotoResponse.builder()
                .id(photo.getId())
                .title(photo.getTitle())
                .description(photo.getDescription())
                .imageUrl(photo.getImageUrl())
                .build();
    }

}
