package com.rest.books.bootrestbooks.controllers;

import com.rest.books.bootrestbooks.Helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file1") MultipartFile file){            // as data coming from html form so, we are using @RequestParam.
        System.out.println(file.getOriginalFilename());                                            //file1 is the key name you write in postman - Body - form data.For Multiple images use (@RequestParam("file1") MultipartFile file,@RequestParam("file2") MultipartFile f){
        System.out.println(file.getName());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());

        try {
                  //Validation
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("You are Uploading an Empty file");
            }
            if (!file.getContentType().equals("image/png")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("image File must be of png only");
            }

                   // File Upload Code
            boolean flag=fileUploadHelper.uploadFile(file);
            if(flag)
            {
                //return ResponseEntity.ok("File is successfully uploaded");
                     // OR give Path on console
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong,Please try again!");  // it will run if, if condition mai flag false milta hai toh if ka ResponseEntity.ok nahi chalega
    }
}
