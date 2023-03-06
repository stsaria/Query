package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.URL;

public class Check{
    public static Object[] network() throws MalformedURLException{
        Object[] networkResult = new Object[3];
        // get globalip
        BufferedReader in = null;
        try{
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        } catch (IOException e) {
            networkResult[0] = false;
        }  
        try {
            networkResult[2] = in.readLine();
            networkResult[0] = true;
        } catch (IOException e) {
            networkResult[0] = false;
            networkResult[2] = "";
        } finally {
            if (in != null) {
                try {
                    in.close();
                    networkResult[0] = true;
                } catch (IOException e) {
                    networkResult[0] = false;
                    networkResult[2] = "";
                }
            }
        }
        //get privateip
        try {
            InetAddress addr = InetAddress.getLocalHost();
            networkResult[1] = addr.getHostAddress();
          } catch (UnknownHostException e) {
            networkResult[0] = false;
            networkResult[1] = "";
          }
        return networkResult;
    }
    public static void run(){
        Object[] array;
        try {
            array = network();
            boolean working = (boolean) array[0];
            String privateIp = (String) array[1];
            String globalIp = (String) array[2];
            System.out.println("working: "+String.valueOf(working)+"\nGlobalIP: "+globalIp+"\nPrivateIP: "+privateIp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}