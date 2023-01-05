package com.example.loginmock;

import java.util.HashSet;
import java.util.Set;

public class SessionManager {

    private static SessionManager instance;
    private Set<String> sessions;

    private SessionManager(){
        sessions = new HashSet<>();
    }

    public  static SessionManager getInstance(){
        if(instance == null){
            instance = new SessionManager();
        }

        return instance;
    }

    public void addSession(String session){
        if(!sessions.contains(session)){
            sessions.add(session);
        }
    }

    public void removeSession(String session){
        if(sessions.contains(session)){
            sessions.remove(session);
        }
    }

    public boolean validateSession(String session){
        if(sessions.contains(session)){
            return true;
        }
        return false;
    }
}
