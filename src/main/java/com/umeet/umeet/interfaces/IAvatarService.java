
package com.umeet.umeet.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;


public interface IAvatarService {
    
    public ResponseEntity<Resource> avatar(String url);
}
