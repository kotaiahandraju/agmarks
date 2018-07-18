package com.aurospaces.neighbourhood.filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {
    private List<String> sessions = new ArrayList<>();
    public static final String COUNTER = "session-counter";

    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("SessionCounter.sessionCreated");
        HttpSession session = event.getSession();
		
		if (session.getAttribute("cacheUserBean") == null) {
			 sessions.add(session.getId());
			 session.setAttribute(SessionCounter.COUNTER, this);
		}
       
        
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("SessionCounter.sessionDestroyed");
        HttpSession session = event.getSession();
        sessions.remove(session.getId());
        session.setAttribute(SessionCounter.COUNTER, this);
    }

    public int getActiveSessionNumber() {
        return sessions.size();
    }
}
