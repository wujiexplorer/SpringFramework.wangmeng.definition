package com.wangmeng.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException{
        System.out.println("UserServlet.....doGet.....");
        response.getWriter().write("Tomcat");
    }

}
