package com.ohgiraffers.file;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileUploadController {

    @PostMapping("/single-file")
    public String singleFileUpload(@RequestParam String singleFileDescription,
                                   @RequestParam MultipartFile singleFile,
                                   Model model){
        System.out.println("singleFileDescription = " + singleFileDescription);
        System.out.println("singleFile = " + singleFile);

        /* 서버로 전달 된 File을 서버에서 설정하는 경로에 저장한다. */
        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        /* 파일명 변경 처리 */
        String originFileName = singleFile.getOriginalFilename();
        System.out.println("originFileName = " + originFileName);
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext = " + ext);
        
        String savedName = UUID.randomUUID() + ext;
        System.out.println("savedName = " + savedName);

        /* 파일 저장 */
        try {
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message", "파일 업로드 성공 무야호~");
        } catch (IOException e) {
            model.addAttribute("message", "파일 업로드 실패 안무야호..");
        }


        return "result";
    }
}
