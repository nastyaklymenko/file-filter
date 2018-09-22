package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/")
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<h1> Query text file with servlet based API </h1>");
        out.write("<h2><a href='/api/files/textfileAPI.txt?includeMetaData=true'>Read</a></h1>");
        out.write("<h2><a href='/api/files/textfileAPI.txt?includeMetaData=true&limit=1000&q=Java&length=5'>Query/filter</a></h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        super.doPost(request, response);
    }

}