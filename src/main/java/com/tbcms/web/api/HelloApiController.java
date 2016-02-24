package com.tbcms.web.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2016-02-24,  下午11:31
 */
@RestController
public class HelloApiController {
    @RequestMapping("/")
    public Map hello(){
        Map m = new HashMap();
        m.put("hi","HelloWorld");
        m.put("i","大家好");
        return m;
    }
}
