package com.wuzt.myblog.utils;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author wuzt
 * @date: 2018年7月20日 下午1:31:06
 */
public class CookieUtil {
	
	public static String MYDOMAIN = "";

	   public static void setCookieMinute(HttpServletResponse response, String key, String value, int minuts) {
	      setCookieMinuteDomain(response, key, value, minuts, MYDOMAIN);
	   }

	   public static void setCookieMinuteDomain(HttpServletResponse response, String key, String value, int minuts, String domain) {
	      if(key != null && value != null) {
	         Cookie cookie = new Cookie(key, value);
	         cookie.setMaxAge(minuts * 60);
	         cookie.setPath("/");
	         if(domain!=null && !"".equals(domain)) {
	            cookie.setDomain(domain);
	         }

	         response.addCookie(cookie);
	      }

	   }

	   public static void setCookieSessionTime(HttpServletResponse response, String key, String value) {
	      setCookieSessionTime(response, key, value, MYDOMAIN);
	   }

	   public static void setCookieSessionTime(HttpServletResponse response, String key, String value, String domain) {
	      if(key != null && value != null) {
	         Cookie cookie = new Cookie(key, value);
	         cookie.setMaxAge(-1);
	         cookie.setPath("/");
	         if(domain!=null && !"".equals(domain)) {
	            cookie.setDomain(domain);
	         }

	         response.addCookie(cookie);
	      }

	   }

	   public static void setCookie(HttpServletResponse response, String key, String value, int days) {
	      setCookie(response, key, value, days, MYDOMAIN);
	   }

	   public static void setCookie(HttpServletResponse response, String key, String value, int days, String domain) {
	      if(key != null && value != null) {
	         Cookie cookie = new Cookie(key, value);
	         cookie.setMaxAge(days * 24 * 60 * 60);
	         cookie.setPath("/");
	         if(domain!=null && !"".equals(domain)) {
	            cookie.setDomain(domain);
	         }

	         response.addCookie(cookie);
	      }

	   }

	   public static String getCookie(HttpServletRequest request, String key) {
	      Cookie[] cookies = request.getCookies();
	      String resValue = "";
	      if(cookies != null && cookies.length > 0) {
	         for(int i = 0; i < cookies.length; ++i) {
	            if(key.equalsIgnoreCase(cookies[i].getName()) && cookies[i].getValue()!=null) {
	               resValue = cookies[i].getValue();
	            }
	         }
	      }

	      return resValue;
	   }

	   public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
	      deleteCookieDomain(request, response, name, MYDOMAIN);
	   }

	   public static void deleteCookieDomain(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
	      Cookie[] cookies = request.getCookies();
	      if(cookies != null && cookies.length > 0) {
	         for(int i = 0; i < cookies.length; ++i) {
	            if(name.equalsIgnoreCase(cookies[i].getName())) {
	               Cookie ck = new Cookie(cookies[i].getName(), (String)null);
	               ck.setPath("/");
	               if(domain!=null && !"".equals(domain)) {
	                  ck.setDomain(domain);
	               }

	               ck.setMaxAge(0);
	               response.addCookie(ck);
	               return;
	            }
	         }
	      }

	   }

	   public static void createCookieFromMap(HttpServletResponse response, Hashtable<String, String> nameValues, int days) {
	      createCookieFromMapDomain(response, nameValues, days, MYDOMAIN);
	   }

	   public static void createCookieFromMapDomain(HttpServletResponse response, Hashtable<String, String> nameValues, int days, String domain) {
	      Set set = nameValues.keySet();
	      Iterator it = set.iterator();

	      while(it.hasNext()) {
	         String name = (String)it.next();
	         String value = (String)nameValues.get(name);
	         Cookie cookie = new Cookie(name, value);
	         if(domain!=null && !"".equals(domain)) {
	            cookie.setDomain(domain);
	         }

	         cookie.setSecure(false);
	         cookie.setMaxAge(days * 24 * 60 * 60);
	         cookie.setPath("/");
	         response.addCookie(cookie);
	      }

	   }

	   public static Hashtable<String, String> getCookiesForMap(HttpServletRequest request) {
	      Cookie[] cookies = request.getCookies();
	      Hashtable cookieHt = new Hashtable();
	      if(cookies.length > 0) {
	         for(int i = 0; i < cookies.length; ++i) {
	            Cookie cookie = cookies[i];
	            cookieHt.put(cookie.getName(), cookie.getValue());
	         }
	      }

	      return cookieHt;
	   }

	   public static void updateCookie(HttpServletRequest request, String name, String value) {
	      Cookie[] cookies = request.getCookies();
	      if(cookies.length > 0) {
	         for(int i = 0; i < cookies.length; ++i) {
	            if(name.equalsIgnoreCase(cookies[i].getName())) {
	               cookies[i].setValue(value);
	               return;
	            }
	         }
	      }

	   }

	   public static void deleteAllCookie(HttpServletRequest request, HttpServletResponse response) {
	      deleteAllCookieDomain(request, response, MYDOMAIN);
	   }

	   public static void deleteAllCookieDomain(HttpServletRequest request, HttpServletResponse response, String domain) {
	      Cookie[] cookies = request.getCookies();
	      if(cookies != null) {
	         for(int i = 0; i < cookies.length; ++i) {
	            Cookie cookie = cookies[i];
	            Cookie ck = new Cookie(cookie.getName(), (String)null);
	            ck.setPath("/");
	            if(domain!=null && !"".equals(domain)) {
	               ck.setDomain(domain);
	            }

	            ck.setMaxAge(0);
	            response.addCookie(ck);
	         }
	      }

	   }
	
}
