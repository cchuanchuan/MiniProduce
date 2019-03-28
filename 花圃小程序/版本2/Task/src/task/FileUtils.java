/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;
/**
 * @author Xitroen
 * My Chinese name is Tielong Xue
 * My student id is 1821649
 */
import static com.sun.javafx.util.Utils.split;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class FileUtils {
    public static BufferedImage loadImage(String imagePath){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
        	System.out.println("Problem loading image: "+imagePath);
            e.printStackTrace();
        }
        return image;
    }
    
    public static ArrayList<String> getFileNames(String dirPath){
        ArrayList<String> names = new ArrayList<String>();
        File dir = new File(dirPath);
        if(dir.isDirectory()){
            for(File f: dir.listFiles()){
                names.add(f.getPath());
            }
        }
        return names;
    }

    public static boolean saveFlowerbedToFile(ArrayList<ImageDisplay> myFlowerbeds,ArrayList<Flowerbed> toWrite2) {
        try (FileWriter fw = new FileWriter("garden.txt");
                PrintWriter out = new PrintWriter(fw)) {
        			for(Flowerbed flow: toWrite2) {
        				out.println(flow.toString());
        			}
                    for(ImageDisplay im: myFlowerbeds){
                        out.println(im.toString());
                    }
                    out.close();
                    fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Flowerbed saved to file");
        return true; 
    } 
    
    public static boolean saveGardenToFile(String toWrite) {
         try (FileWriter fw = new FileWriter("garden.txt");
                PrintWriter out = new PrintWriter(fw)) {
                    out.println(toWrite);
                    out.flush();
                    out.close();
                    fw.close();
                    fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Flowerbed saved to file");
        return true;
    }
    
    public static void readFlowerbedFromFile(){
        Path filePath = Paths.get("garden.txt");
        if(!Files.exists(filePath)){
            System.out.println("There is no file to read from.");
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                if(line.startsWith("ImageDisplay")){
                    imageFromString2(line);
                }
                else if(line.startsWith("Flowerbed")){
                	imageFromString3(line);
                }else{
                    System.out.println("Bad line in file: "+line);
                }   
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file:" + filePath);
            e.printStackTrace();
        } 
    }
    
    private static void imageFromString2(String line) {
        String text = line.replace("ImageDisplay,myX='","#").replace("',myY='", "#").replace("',myWidth='", "#").replace("',myHeight='","#").replace("',myPath='", "#").replace("',flowerbedID=,", "#");
        String[] arr = text.split("#");  
        String x1 = arr[1].toString();
        String y1 = arr[2].toString();
        String width1 = arr[3].toString();
        String height1 = arr[4].toString();
        String path1 = arr[5].toString();
        String id1 = arr[6].toString();
        int x2 = Integer. parseInt  (x1);
        int y2 = Integer. parseInt  (y1);
        int width2 = Integer. parseInt  (width1);
        int height2 = Integer. parseInt  (height1);
        int id2 = Integer. parseInt  (id1);
        Task.addImage(path1, x2, y2, 30 , 30,id2);
    }
    
    public static void imageFromString3(String line) {
        String text = line.replace("Flowerbed Id=","#").replace(",x=", "#").replace(",y=", "#").replace(",width=","#").replace(",height=", "#").replace(",type=", "#").replace(",path=", "#");
        String[] arr = text.split("#");  
        
        String Id1 = arr[1].toString();
        String x1 = arr[2].toString();
        String y1 = arr[3].toString();
        String path = arr[7];
        String width1 = arr[4].toString();
        String height1 = arr[5].toString();
        String type1 = arr[6].toString();
        int x2 = Integer. parseInt  (x1);
        int y2 = Integer. parseInt  (y1);
        int width2 = Integer. parseInt  (width1);
        int height2 = Integer. parseInt  (height1);
        int id2 = Integer. parseInt  (Id1);
        Flowerbed flower = new Flowerbed(x2,y2,width2,height2,id2, type1);
        Task.addFlower(flower);
       /* System.out.println(width2);
        String garden = "Flowerbed Id="+id2+",x="+x2+",y="+y2+",width="+width2+",height="+height2+",type="+type1+",path="+path;
        String garden2 = "Flowerbed Id="+id2+",x="+x2+",y="+y2+",width="+width2+",height="+height2+",type="+type1;
        System.out.println(id2+". "+garden2);*/
    }

}
 
    
    

