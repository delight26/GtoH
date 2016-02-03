package com.project.call.hyunsu.supprot;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;

@Repository
public class ScriptHandling {

	public void historyBack(HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.println("<script>");
	    out.println("alert('잘못된 정보가 입력되었습니다');");
	    out.println("history.back();");
	    out.println("</script>");
	    out.close();
	}
	
}
