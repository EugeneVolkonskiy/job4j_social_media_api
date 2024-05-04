package ru.job4j.socialmedia.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.dto.ImageDto;
import ru.job4j.socialmedia.model.Image;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.repository.ImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
public class SimpleImageService implements ImageService {

    private final ImageRepository imageRepository;
    private final String storageDirectory;

    public SimpleImageService(ImageRepository imageRepository,
                              @Value("${file.directory}") String storageDirectory) {
        this.imageRepository = imageRepository;
        this.storageDirectory = storageDirectory;
        createStorageDirectory(storageDirectory);
    }

    private void createStorageDirectory(String path) {
        try {
            Files.createDirectories(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Image save(ImageDto imageDto, Post post) {
        var path = getNewFilePath(imageDto.getName());
        writeFileBytes(path, imageDto.getContent());
        Image image = new Image();
        image.setName(imageDto.getName());
        image.setPath(path);
        image.setPost(post);
        return imageRepository.save(image);
    }

    private String getNewFilePath(String sourceName) {
        return storageDirectory + java.io.File.separator + UUID.randomUUID() + sourceName;
    }

    private void writeFileBytes(String path, byte[] content) {
        try {
            Files.write(Path.of(path), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ImageDto> findById(Long id) {
        var fileOptional = imageRepository.findById(id);
        if (fileOptional.isEmpty()) {
            return Optional.empty();
        }
        var content = readFileAsBytes(fileOptional.get().getPath());
        return Optional.of(new ImageDto(fileOptional.get().getId(), fileOptional.get().getName(), content));
    }

    @Override
    public List<Image> findByPostId(Long postId) {
        return imageRepository.findByPostId(postId);
    }

    @Override
    public int deleteImageByPostId(Long id) {
        List<Image> images = findByPostId(id);
        if (!images.isEmpty()) {
            images.forEach(i -> deleteFile(i.getPath()));
        }
        return imageRepository.deleteImageByPostId(id);
    }

    private byte[] readFileAsBytes(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        var fileOptional = imageRepository.findById(id);
        if (fileOptional.isPresent()) {
            deleteFile(fileOptional.get().getPath());
            imageRepository.deleteById(id);
        }
    }

    private void deleteFile(String path) {
        try {
            Files.deleteIfExists(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
