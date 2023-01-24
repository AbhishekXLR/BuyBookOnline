package com.rest.books.bootrestbooks.Helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
   // public final String uploadDirectory="D:\\SpringBoot Project\\BootRestBooks\\src\\main\\resources\\static\\image";
    public final String uploadDirectory=new ClassPathResource("static/image/").getFile().getAbsolutePath();  // IMPORTANT.. WE USED HERE DYNAMIC PATHING. ClassPathResource gave
                                                                                                            // pathing upto resource folder

    public FileUploadHelper() throws IOException {       // Class k constructor par apne ko exception handle karna pad raha which arise from above line cod
    }

    public boolean uploadFile(MultipartFile mf){

        boolean flag=false;

        // InputStream is=null;
        //FileOutputStream fos= null;

        try {
                //Reading and storing data in byte []
           /* InputStream is =mf.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);

            // Writing to our location  stored in uploadDirectory  variable
            FileOutputStream fos= new FileOutputStream(uploadDirectory+"\\"+mf.getOriginalFilename());
            fos.write(data);

            fos.close();
            is.close();
            flag=true;*/

                              //Above code ka REPLACEMENT via according to below formula
            // files.copy(InputStream in,Path Target, Option)
            Files.copy( mf.getInputStream(), Paths.get(uploadDirectory +"\\"+ mf.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING );
            flag=true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}