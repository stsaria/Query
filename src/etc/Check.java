package etc;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.InetAddress;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.net.URL;

public class Check{
    public static boolean file(String path, String attribute){
        File file = new File(path);
        if (!file.exists()) {
            if (attribute == "file"){
                try{
                    Files.createFile(file.toPath());
                } catch (IOException e){
                    return false;
                }
                return true;
            } else if (attribute == "dir"){
                try{
                    Files.createDirectory(file.toPath());
                } catch (IOException e){
                    return false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
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
        System.out.print("Network ");
        Object[] networkArray;
        try {
            for (int i = 0; i < 14; i ++){
                System.out.print(".");
                networkArray = network();
                boolean working = (boolean) networkArray[0];
                if (working){
                    break;
                }
            }
            networkArray = network();
            boolean working = (boolean) networkArray[0];
            if (working){
                System.out.println(" OK");
            } else {
                System.out.println(" Error");
                System.exit(1);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.print("File ");
        String[] paths = {"data", "data/minecraft-list.txt", "data/minecraft-dir-list.txt", "minecraft"};
        String[] attributes = {"dir", "file", "file", "dir"};
        for (int i = 0; i < paths.length; i ++){
            if (file(paths[i], attributes[i])){
                System.out.print(".");
            } else {
                System.out.print("Error");
                System.exit(2);
            }
        }
        System.out.println(" OK");
        System.out.println("Start!\n");
    }
}