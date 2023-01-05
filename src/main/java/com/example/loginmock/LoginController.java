package com.example.loginmock;

import com.example.loginmock.entity.LoginRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class LoginController {


    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin";

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody LoginRequest loginRequest) {
        if(loginRequest.getUsername().equals(ADMIN_USERNAME) && loginRequest.getUsername().equals(ADMIN_PASSWORD)){
            UUID uuid = UUID.randomUUID();
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("SESSIONID", uuid.toString());

            SessionManager.getInstance().addSession(uuid.toString());
            return ResponseEntity.ok()
                    .headers(responseHeaders);
        } else {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Object logout(@RequestHeader("SESSIONID") String sessionId) {
        if(SessionManager.getInstance().validateSession(sessionId)){
            SessionManager.getInstance().removeSession(sessionId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public Object validate(@RequestHeader("SESSIONID") String sessionId) {
        if(SessionManager.getInstance().validateSession(sessionId)){
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

}
