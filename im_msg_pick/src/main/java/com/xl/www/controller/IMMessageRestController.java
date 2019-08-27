package com.xl.www.controller;

import com.xl.www.domain.IMMessage;
import com.xl.www.service.IMMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class IMMessageRestController {

    @Autowired
    private IMMessageService immessageService;

    @RequestMapping(value = "/api/v1/immessage/like", method = RequestMethod.GET)
    public List<String> findAllLikeMessage(IMMessage immessage) {
        return immessageService.findAllLikeMessage(immessage.getContent());
    }
}

