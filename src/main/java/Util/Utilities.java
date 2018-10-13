/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author german.ramirez
 */
public class Utilities {
 
    public static final String FULLDATE = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORDATE = "yyyy-MM-dd";
    private static final Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    
    /**
     * Format the exception message.
     * @param ex
     * @return
     */
    public static String formatException(Exception ex)
    {
        return String.format("%s: (%s)", ex.getMessage(), ex.getCause().getMessage());
    }   
    
    /**
     * Get the IP address of local machine.
     * @return
     */
    public static String GetIpLocal()
    {
        String ipAddress = "";
        
        try {
            InetAddress IP=InetAddress.getLocalHost();
            ipAddress = IP.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ipAddress;
    }
    
    /**
     * Get the collection List of local IP Address.
     * @return 
     */
    public static ArrayList<String> GetIpsLocal()
    {
        ArrayList<String> ipList = new ArrayList<>();
        Enumeration<NetworkInterface> nets;
        
        try {
            Enumeration<InetAddress> inetAddresses;
            nets = NetworkInterface.getNetworkInterfaces();
            
            for (NetworkInterface netint : Collections.list(nets))
            {
                inetAddresses = netint.getInetAddresses();
                
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {   
                    if(isValidIp(inetAddress.getHostAddress()))
                    {
                        ipList.add(inetAddress.getHostAddress());
                        System.out.printf("InetAddress: %s\n", inetAddress.getHostAddress());
                    }
                }
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return ipList;
    }

    /**
     * Check if string is valid IP Address.
     * @param ip
     * @return 
     */
    public static boolean isValidIp(final String ip) {
        return PATTERN.matcher(ip).matches();
    }
    
    /**
     * Sets the maximum time to the date provided, therefore, the hour 23:59:59.
     * @param date
     * @return
     */
    public static Date setMaximunDateTime(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        calendar.add(Calendar.MINUTE, -1);
        return calendar.getTime();               
    }
    
    /**
     * Get the minimum date of the current month.
     * @return
     */
    public static Date getMinimumDateCurrentMonth()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);        
        return calendar.getTime();
    }
    
    /**
     * Get the maximum date of the current month.
     * @return
     */
    public static Date getMaximumDateCurrentMonth()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();    
    }
    
    /**
     * Get date formatted according to regular Expression pattern.
     * @param pattern
     * @param date
     * @return
     */
    public static String formatDate(String pattern, Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    
    /**
     * Get property value by Class Type and property name.
     * @param <T>
     * @param type
     * @param object
     * @param propertyName
     * @return 
     */
    public static <T> Object getPropertyValue(Class<T> type, T object, String propertyName)
    {        
        Object value = null;
        
        try { 
            Field field = object.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            value = field.get(object);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }
}