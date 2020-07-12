package com.vardges.ninjagoldgame;

import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AjpNioProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Random;
import java.util.Date;

@SpringBootApplication
@Controller
public class NinjagoldgameApplication {

	public static void main(String[] args) {
		SpringApplication.run(NinjagoldgameApplication.class, args);
	}
	
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        Connector ajpConnector = new Connector("AJP/1.3");
        ajpConnector.setPort(9090);
        ajpConnector.setSecure(false);
        ajpConnector.setAllowTrace(false);
        ajpConnector.setScheme("http");
        tomcat.addAdditionalTomcatConnectors(ajpConnector);
        AjpNioProtocol protocol= (AjpNioProtocol)ajpConnector.getProtocolHandler();
        protocol.setSecretRequired(false);
        return tomcat;
    }
	
	@RequestMapping("/")
	public String home(HttpSession session) {
		if (session.getAttribute("farm") == null) {
			session.setAttribute("farm", "");
			session.setAttribute("cave", "");
			session.setAttribute("house", "");
			session.setAttribute("casino", "");
			session.setAttribute("total", 0);
			session.setAttribute("activities", "");
			session.setAttribute("farm_remaining_attempts", 3);
			session.setAttribute("cave_remaining_attempts", 3);
			session.setAttribute("house_remaining_attempts", 3);
			session.setAttribute("casino_remaining_attempts", 3);
			session.setAttribute("score_history", "");
		}
		return "index.jsp";
	}
	
	@RequestMapping("/reset")
	public String reset(HttpSession session) {
			session.setAttribute("farm", "");
			session.setAttribute("cave", "");
			session.setAttribute("house", "");
			session.setAttribute("casino", "");
			session.setAttribute("total", 0);
			session.setAttribute("activities", "");
			session.setAttribute("farm_remaining_attempts", 3);
			session.setAttribute("cave_remaining_attempts", 3);
			session.setAttribute("house_remaining_attempts", 3);
			session.setAttribute("casino_remaining_attempts", 3);
		return "redirect:/";
	}
	
	@RequestMapping("/you_win")
	public String you_win(HttpSession session) {
		return "winPage.jsp";
	}
	
	@RequestMapping("/game_over")
	public String gameover(HttpSession session) {
		return "losePage.jsp";
	}
	
	@RequestMapping(value="/controller", method = RequestMethod.POST)
	public String find_gold(@RequestParam(value="place") String place, HttpSession session) {
		Random r = new Random();
		
		if (place.equals("farm")) {
			if((int) session.getAttribute("farm_remaining_attempts")== 0) {
				return "redirect:/";
			}
			session.setAttribute("farm", r.nextInt(11) + 10);  // random number range 10 to 20
			session.setAttribute("total", ((Integer) session.getAttribute("total")) + ((Integer) session.getAttribute("farm")));
			Date currentDate = new java.util.Date();
			session.setAttribute("activities", ((String) session.getAttribute("activities")) + ("Earned " + ((Integer) session.getAttribute("farm")) + " golds from the farm! " + currentDate + "\n" ));  
			session.setAttribute("farm_remaining_attempts", (int) session.getAttribute("farm_remaining_attempts") - 1);
		}
		else if (place.equals("cave")) {
			if((int) session.getAttribute("cave_remaining_attempts")== 0) {
				return "redirect:/";
			}
			session.setAttribute("cave", r.nextInt(6) + 5);    // random number range 5 to 10
			session.setAttribute("total", ((Integer) session.getAttribute("total")) + ((Integer) session.getAttribute("cave")));
			Date currentDate = new java.util.Date();
			session.setAttribute("activities", ((String) session.getAttribute("activities")) + ("Earned " + ((Integer) session.getAttribute("cave")) + " golds from the cave! " + currentDate + "\n" ));  
			session.setAttribute("cave_remaining_attempts", (int) session.getAttribute("cave_remaining_attempts") - 1);
		}
		else if (place.equals("house")) {
			if((int) session.getAttribute("house_remaining_attempts")== 0) {
				return "redirect:/";
			}
			session.setAttribute("house", r.nextInt(4) + 2);    // random number range 2 to 5
			session.setAttribute("total", ((Integer) session.getAttribute("total")) + ((Integer) session.getAttribute("house")));
			Date currentDate = new java.util.Date();
			session.setAttribute("activities", ((String) session.getAttribute("activities")) + ("Earned " + ((Integer) session.getAttribute("house")) + " golds from the house! " + currentDate + "\n" ));  
			session.setAttribute("house_remaining_attempts", (int) session.getAttribute("house_remaining_attempts") - 1);
		}
		else if (place.equals("casino")) {
			if((int) session.getAttribute("casino_remaining_attempts")== 0) {
				return "redirect:/";
			}
			session.setAttribute("casino", r.nextInt(101)-50);    // random number range -50 to 50
			session.setAttribute("total", ((Integer) session.getAttribute("total")) + ((Integer) session.getAttribute("casino")));
			Date currentDate = new java.util.Date();
			session.setAttribute("casino_remaining_attempts", (int) session.getAttribute("casino_remaining_attempts") - 1);
			if (((Integer) session.getAttribute("casino")) >= 0) {
				session.setAttribute("activities", ((String) session.getAttribute("activities")) + ("Earned " + ((Integer) session.getAttribute("casino")) + " golds from the casino! " + currentDate + "\n" ));
			}
			else {
				session.setAttribute("activities", ((String) session.getAttribute("activities")) + ("Entered a casino and lost " + ((Integer) session.getAttribute("casino")) + " golds. ... Ouch... " + currentDate + "\n" ));
			}
		}
		
		if ((int) session.getAttribute("total") > 100) {
			session.setAttribute("score_history", session.getAttribute("score_history").toString() + "score = " + session.getAttribute("total").toString() + "\n");
			return "redirect:/you_win"; 
		}
		
		if((int) session.getAttribute("farm_remaining_attempts")== 0 && (int) session.getAttribute("cave_remaining_attempts")== 0
				&& (int) session.getAttribute("house_remaining_attempts")== 0 && (int) session.getAttribute("casino_remaining_attempts")== 0
				&& (int) session.getAttribute("total") < 100) {
			session.setAttribute("score_history", (session.getAttribute("score_history").toString() + "score = " + session.getAttribute("total").toString() +"\n"));
			return "redirect:/game_over";
		}
		   
		return "redirect:/";
	}
}
