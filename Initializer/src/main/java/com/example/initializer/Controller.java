package com.example.initializer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @RequestMapping("/hello")
    public String Hello() {
        System.out.print("Hello World");
        return "Hello World";
    }

    @RequestMapping("/request")
    public String request(HttpServletRequest request) {
        String name = request.getParameter("name");
        String uri = request.getRequestURI();
        String url = request.getRequestURI().toString();
        String userAgent = request.getHeader("User-Agent");
        String protocol =
        String method = request.getMethod();
        String queryString = request.getQueryString();
        System.out.printf("%s\n", request.toString());
        System.out.printf("%s\n", name);
        System.out.printf("%s\n", uri);
        System.out.printf("%s\n", url);
        System.out.printf("%s\n", userAgent);
        System.out.printf("%s\n", method);
        System.out.printf("%s\n", queryString);
        return "request success";
    }
}
