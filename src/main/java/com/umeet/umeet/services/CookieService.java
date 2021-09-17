
package com.umeet.umeet.services;

import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;


public class CookieService {

    public String setCookieUser(HttpServletResponse response, User user, int maxAge) {
        //Cookie usuario
        Cookie cookieUser = new Cookie("idUser",user.getId().toString());
        //30 * 24 * 60 * 60  --> un mes
        cookieUser.setMaxAge(maxAge);
        //Encripta la cookie
        cookieUser.setSecure(true);
        
        response.addCookie(cookieUser);

        return "idUser cookie creada";
    }
    
    public String setCookieServer(HttpServletResponse response,  Server server, int maxAge) {
        //Cookie servidor
        Cookie cookieServer = new Cookie("idServer",server.getId().toString());
        //30 * 24 * 60 * 60  --> un mes
        cookieServer.setMaxAge(maxAge);
        //Encripta la cookie
        cookieServer.setSecure(true);
        
        response.addCookie(cookieServer);

        return "idServer cookie creada";
    }
    
    public String deleteCookie(HttpServletResponse response,  String idUserServer) {
        Cookie cookieServer = new Cookie(idUserServer, null);
        cookieServer.setMaxAge(0);
        //Encripta la cookie
        cookieServer.setSecure(true);
        
        response.addCookie(cookieServer);

        return idUserServer+"eliminada";
    }
    
    public String readCookieIdUser(@CookieValue(value = "idUser") String idUser) {
        return idUser;
    }
    
    public String readCookieIdServer(@CookieValue(value = "idServer") String idServer) {
        return idServer;
    }
}
