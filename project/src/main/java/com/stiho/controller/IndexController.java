package com.stiho.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author IS204_1
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    @RequestMapping(method = GET)
    public ModelAndView home() throws IOException{
        return new ModelAndView("index");
    }
    
    @RequestMapping(value = "faq", method = GET)
    public ModelAndView faq() throws IOException{
        return new ModelAndView("faq");
    }
    
        @RequestMapping(value = "test", method = GET)
    public ModelAndView test() throws IOException{
        return new ModelAndView("overview");
    }

}
