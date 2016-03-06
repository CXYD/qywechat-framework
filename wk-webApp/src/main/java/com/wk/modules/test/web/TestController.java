package com.wk.modules.test.web;

import com.wk.common.web.BaseController;
import com.wk.modules.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mac on 16/3/2.
 */

@Controller
@RequestMapping("test")
public class TestController extends BaseController {

    @Autowired
    private TestService testService;

    @RequestMapping
    @ResponseBody
    public Object list(){

       return testService.get("1");
    }
}
