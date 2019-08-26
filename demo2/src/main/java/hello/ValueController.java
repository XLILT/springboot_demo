/*************************************************************************
* COPYRIGHT NOTICE
*  Copyright (c) 2019, Wuhan Youxuan Stream Education Co., Ltd.
*  All rights reserved.
*
*  @version : 1.0
*  @author : mxl
*  @E-mail : xiaolongicx@gmail.com
*  @date : 2019-08-26 16:05
*
*  Revision Notes :
*/

package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ValueController {
    @Autowired
    TestProperties tp;

    @RequestMapping("/value")
    public TestProperties showValue() {
        return tp;
    }
}

