
package com.umeet.umeet.services;

import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.interfaces.IAvatarService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AvatarService implements IAvatarService{
    
    public ResponseEntity<Resource> avatar(String url){
        
        HttpHeaders cabeceras=new HttpHeaders();
        cabeceras.add("Content-Disposition", "attachment;");
        cabeceras.add("Cache-Control", "no-cache, no-store, must-revalidate");
        cabeceras.add("Pragma", "no-cache");
        cabeceras.add("Expires", "0");
        
        try{
            return ResponseEntity.ok()
                                 .headers(cabeceras)
                                 .contentLength((new File(url)).length())
                                 .contentType(MediaType.parseMediaType( "application/octet-stream" ))
                                 .body(new InputStreamResource(new FileInputStream( url )) );
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}
