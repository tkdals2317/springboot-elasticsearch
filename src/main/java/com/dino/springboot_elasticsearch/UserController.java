package com.dino.springboot_elasticsearch;

import com.dino.springboot_elasticsearch.dto.UserCreateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserDocumentRepository userDocumentRepository;

    public UserController(UserDocumentRepository userDocumentRepository) {
        this.userDocumentRepository = userDocumentRepository;
    }

    @PostMapping()
    public UserDocument createUser(@RequestBody UserCreateRequestDto requestDto) {
        UserDocument userDocument = new UserDocument(
                requestDto.getId(),
                requestDto.getName(),
                requestDto.getAge(),
                requestDto.getIsActive()
        );
        return userDocumentRepository.save(userDocument);
    }

    @GetMapping
    public Page<UserDocument> getUsers() {
        return userDocumentRepository.findAll(PageRequest.of(0, 10));
    }

    @GetMapping("/{id}")
    public UserDocument findUserById(@PathVariable String id) {
        return userDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
    }

    @PutMapping("/{id}")
    public UserDocument updateUser(@PathVariable String id, @RequestBody UserCreateRequestDto requestDto) {
        UserDocument existingUser = userDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
        existingUser.setName(requestDto.getName());
        existingUser.setAge(requestDto.getAge());
        existingUser.setIsActive(requestDto.getIsActive());
        return userDocumentRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        UserDocument user = userDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
        userDocumentRepository.delete(user);
    }

}
