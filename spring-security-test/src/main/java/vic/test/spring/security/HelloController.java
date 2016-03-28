package vic.test.spring.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Vic Liu
 */
@Controller
public class HelloController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Hello World");
        model.addObject("message", "This is welcome page!");
        model.setViewName("hello");
        return model;
    }

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public ModelAndView adminDashboard() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Admin Dashboard");
        model.addObject("message", "This is admin dashboard, a protected page");
        model.setViewName("admin-dashboard");
        return model;
    }

    @RequestMapping(value = "/user/dashboard", method = RequestMethod.GET)
    public ModelAndView userDashboard() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "User Dashboard");
        model.addObject("message", "This is user protected page");
        model.setViewName("user-dashboard");
        return model;
    }
}
