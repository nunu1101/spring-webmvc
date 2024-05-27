package com.ohgiraffers.restapi.practice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "Practice Swagger 연동 (USER)")
@RestController
@RequestMapping("/practice")
public class PracticeController {

    private List<UserDTO> users;

    public PracticeController(){
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "윤수빈", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "구예성", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "윤누누", new Date()));
    }

    /* 회원 전체 조회 */
    @Operation(summary = "회원 전체 조회", description = "전체 회원 목록 조회")
    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers(){

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공!", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    /* 회원 상세 조회 */
    @Operation(summary = "회원 상세 조회", description = "회원 번호로 회원 정보 가져오기")
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);

        return ResponseEntity.ok().headers(headers).body(new ResponseMessage(200,"조회성공", responseMap));
    }

    /* 회원 추가 */
    @Operation(summary = "회원 추가", description = "회원 정보 추가")
    @PostMapping("/users")
    public ResponseEntity<?> registUser(@RequestBody UserDTO newUser){

        int lastUserNo = users.get(users.size() - 1).getNo();

        newUser.setNo(lastUserNo + 1);

        users.add(newUser);

        return ResponseEntity
                .created(URI.create("/entity/users"+users.get(users.size() -1).getNo()))
                .build();
    }

    /* 회원 수정 */
    @Operation(summary = "회원 정보 수정", description = "등록된 회원 번호로 회원 정보 수정")
    @PutMapping("/users/{userNo}")
    public ResponseEntity<?> modifyUser(@PathVariable int userNo, @RequestBody UserDTO modifyInfo) {

        UserDTO foundUser = users.stream()
                .filter(user -> user.getNo() == userNo)
                .collect(Collectors.toList()).get(0);

        foundUser.setId(modifyInfo.getId());
        foundUser.setPwd(modifyInfo.getPwd());
        foundUser.setName(modifyInfo.getName());

        return ResponseEntity
                .created(URI.create("/entity/users" + userNo))
                .build();
    }

    /* 회원 삭제 */
    @Operation(summary = "회원 정보 삭제" ,description = "저장된 회원 번호로 회원 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "회원 삭제 성공!"),
            @ApiResponse(responseCode = "400", description = "잘못 입력된 파라미터")
    })
    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(@PathVariable int userNo) {

        UserDTO foundUser = users.stream()
                .filter(user -> user.getNo() == userNo)
                .collect(Collectors.toList()).get(0);

        users.remove(foundUser);

        return ResponseEntity
                .noContent()
                .build();
    }


}
