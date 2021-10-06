package com.umeet.umeet.feign;

import com.umeet.umeet.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(contextId = "feignAccess", name = "UMeetBack")
@RequestMapping("/b")
public interface AccesFeign {

    @PostMapping("/newregister")
    public Boolean newRegister(@SpringQueryMap UserDto user);
}
