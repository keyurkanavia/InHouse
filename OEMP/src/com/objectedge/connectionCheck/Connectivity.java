package com.objectedge.connectionCheck;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.net.telnet.TelnetClient;
class Connectivity extends TimerTask
{
	public static String[] ips1={"172.22.146.1",	
		"172.22.146.2",	
		"172.22.146.3",	
		"172.22.146.13",
		"172.22.140.1",	
		"172.22.140.7",	
		"172.22.140.2",	
		"172.22.140.8"};
	
	public static String[] ips={"10.153.226.117",
		"10.152.10.106",
		"10.152.10.134",
		"10.152.10.137",
		"10.152.10.142",
		"10.152.10.144",
		"10.152.10.173",
		"10.152.10.190",
		"10.152.10.73",
		"10.152.10.96",
		"10.152.12.15",
		"10.152.12.19",
		"10.152.12.21",
		"10.152.12.23",
		"10.152.12.40",
		"10.152.16.102",
		"10.152.16.131",
		"10.152.16.132",
		"10.152.16.166",
		"10.152.16.189",
		"10.152.16.19",
		"10.152.16.72",
		"10.152.16.89",
		"10.152.17.10",
		"10.152.17.137",
		"10.152.17.14",
		"10.152.17.15",
		"10.152.17.173",
		"10.152.17.18",
		"10.152.17.180",
		"10.152.17.181",
		"10.152.17.182",
		"10.152.17.183",
		"10.152.17.19",
		"10.152.17.190",
		"10.152.17.20",
		"10.152.17.21",
		"10.152.17.9",
		"10.152.5.195",
		"10.152.5.203",
		"10.152.5.206",
		"10.152.5.208",
		"10.152.5.210",
		"10.152.5.222",
		"10.152.5.223",
		"10.152.5.224",
		"10.152.5.225",
		"10.152.5.226",
		"10.153.235.33",
		"10.153.235.91"};
	
	public static int[] ports = {22,80,443,1443,1500,1600,3306,3389,5101,5900,7001,7777,8000,8088,8982,8083,9000,9999,15000,7003,7103,8006};
	
	public static int[] ports1 = {7003,7103,8006};
	
    public static void main(String args[])
    {
    
        	for(int i =0;i<ips.length;i++){
        		for(int j=0;j<ports.length;j++){
        			 try
        		        {
                    String ip=ips[i];

                    TimerTask con  = new Connectivity();

                    int port=ports[j];
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(con,1,1000);
                    Socket s1=new Socket(ip,port);
                    InputStream is=s1.getInputStream();
                    DataInputStream dis=new DataInputStream(is);
                    if(dis!=null)
                    {
                        System.out.println("Connected with ip "+ip+" and port "+port+"					SUCCESS");
                    }
                    else
                    {
                        System.out.println("ip "+ips[i]+" and port "+ports[j]+"					FAILED");
                    }
                     
                    dis.close();
                    s1.close();
        		}
                catch(Exception e)
                {
                    System.out.println("ip "+ips[i]+" and port "+ports[j]+"					FAILED");
                     
                }
        		}
        	}
    }


	@Override
    public void run() {
        // TODO Auto-generated method stub
         
    }
}