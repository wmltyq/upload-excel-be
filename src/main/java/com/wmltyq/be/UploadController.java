package com.wmltyq.be;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
public class UploadController {
    private static String UPLOAD_FOLDER = "src/main/resources/static/temp";

    @Autowired
    private LoadService loadService;

    @PostMapping("/upload")
    public List<Step> singleFileUpload(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Step> result = loadService.castToStep(new File(UPLOAD_FOLDER + file.getOriginalFilename()));
        return result;
    }
}
