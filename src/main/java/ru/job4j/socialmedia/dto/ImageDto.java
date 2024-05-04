package ru.job4j.socialmedia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDto {

    private Long id;
    private String name;
    private byte[] content;

    public ImageDto(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public ImageDto(Long id, String name, byte[] content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }
}
