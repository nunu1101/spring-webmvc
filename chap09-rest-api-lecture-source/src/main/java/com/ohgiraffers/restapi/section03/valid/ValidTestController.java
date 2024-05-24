package com.ohgiraffers.restapi.section03.valid;

import com.ohgiraffers.restapi.section02.responseentity.ResponseMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/valid")
public class ValidTestController {

    private List<UserDTO> users;

    public ValidTestController(){
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "윤수빈", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "구예성", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "윤누누", new Date()));
    }

    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo) throws UserNotFoundException {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<UserDTO> foundUserList = users
                .stream()
                .filter(user -> user.getNo() == userNo)
                .collect(Collectors.toList());

        UserDTO foundUser = null;

        if (foundUserList.size() > 0) {
            foundUser = foundUserList.get(0);
        } else {
            throw new UserNotFoundException("회원 정보를 찾을 수 없습니다.");
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "조회 성공!", responseMap));

    }

    @PostMapping("/users")
    public ResponseEntity<?> registUser(@Valid  @RequestBody UserDTO newUser){

        return ResponseEntity.created(URI.create("/valid/users/" + "userNo")).build();
    }
}
