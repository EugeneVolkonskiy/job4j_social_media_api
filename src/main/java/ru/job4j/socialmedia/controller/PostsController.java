package ru.job4j.socialmedia.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.socialmedia.dto.UserDto;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.service.PostService;
import ru.job4j.socialmedia.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUserDtoList(@RequestBody List<Long> userId) {
        List<UserDto> usersDto = userService.findByUsersId((userId));
        return usersDto.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(usersDto);
    }
}
