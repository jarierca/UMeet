/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umeet.umeet.feign;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "UMeetBack", url= "http://localhost:8082")
public interface Feign extends MessagesFeign, ServerFeign{
    
}
