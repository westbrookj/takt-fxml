/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktfxml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 *
 * @author jwgy5
 */
public class TAKTFXMLModel
{
    private static double width;
    private static double height;
    private static double taktTime;
    private static double secondsRemaining;
    private static double fontSize;
    
    private static boolean isRunning;
    private static boolean isPaused;
    
    private static int unitGoal;
    private static int units;
    private static int partNumber;
    private static int numberOfEmails = 12;
    
    private static String outputLocation;
    private static String outputFileName;
    private static String formatDate;
    private static String formatTime;
    
    private static ArrayList<String> emailList;
    
    private static InputStream input;
    private static OutputStream output;
    
    private static Properties prop;
    
    private static String fileName = "./config.properties";
    private static File file;
    private static File path;
    private static File inputFile;
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;
    
    private static LocalDateTime date;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            
    public TAKTFXMLModel()
    {
        importProperties();
    }
    
    private static void importProperties()
    {
        isRunning = false;
        fontSize = java.lang.Math.round(0.045 * height);
        prop = new Properties();
        inputFile = new File("./config.properties");
        
        try
        {
            if(!inputFile.exists())
            {
                try
                {
                   inputFile.createNewFile();
                }catch (IOException e)
                {
                   e.printStackTrace();
                }   
            }
            
            emailList = new ArrayList<>();
            input = new FileInputStream(inputFile);
            prop.load(input);

            if(prop.getProperty("windowWidth") != null){
                width = Integer.parseInt(prop.getProperty("windowWidth"));
            }else{width = 1920;}
            if(prop.getProperty("windowHeight") != null){
                height = Integer.parseInt(prop.getProperty("windowHeight"));
            }else{height = 1080;}
            if(prop.getProperty("taktTime") != null){
                taktTime = Double.parseDouble(prop.getProperty("taktTime")) * 60;
            }else{taktTime = 1500;}
            if(prop.getProperty("unitGoal") != null){
                unitGoal = Integer.parseInt(prop.getProperty("unitGoal"));
            }else{unitGoal = 20;}
            if(prop.getProperty("partNumber") != null){
                partNumber = Integer.parseInt(prop.getProperty("partNumber"));
            }else{partNumber = 1234;}
            if(prop.getProperty("units") != null){
                units = Integer.parseInt(prop.getProperty("units"));
            }else{units = 1;}
            if(prop.getProperty("secondsRemaining") != null){
                secondsRemaining = Double.parseDouble(prop.getProperty("secondsRemaining"));
            }else{secondsRemaining = taktTime;}
            if(prop.getProperty("outputLocation") != null){
                outputLocation = prop.getProperty("outputLocation");
            }else{outputLocation = "C:\\TAKT\\logs\\";}
            if(prop.getProperty("outputFileName") != null){
                outputFileName = prop.getProperty("outputFileName");
            }else{outputFileName = "TAKT.csv";}

            path = new File(outputLocation);

            for(int i = 0; i < numberOfEmails; i++)
            {
                if(prop.getProperty("email" + i) != null){
                    emailList.add(i, prop.getProperty("email" + i));
                }else{emailList.add(i, "");}
            }

        } catch (IOException ex) 
        {
            ex.printStackTrace();
        } finally 
        {
            if (input != null) {
                try 
                {
                    input.close();
                } catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void saveProperties()
    {
        if(!Integer.toString(unitGoal).equals(prop.getProperty("unitGoal")))
        {
            prop.setProperty("unitGoal", String.format("%d", unitGoal));
        }
        if(!Integer.toString(units).equals(prop.getProperty("units")))
        {
            prop.setProperty("units", String.format("%d", units));
        }
        if(!Double.toString(taktTime).equals(prop.getProperty("taktTime")))
        {
            prop.setProperty("taktTime", String.format("%f", taktTime / 60));
        }
        if(!Double.toString(secondsRemaining).equals(prop.getProperty("secondsRemaining")))
        {
            prop.setProperty("secondsRemaining", String.format("%d", (int) secondsRemaining));
        }
        if(!Integer.toString(partNumber).equals(prop.getProperty("partNumber")))
        {
            prop.setProperty("partNumber", String.format("%d", partNumber));
        }
        if(!outputFileName.equals(prop.getProperty("outputFileName")))
        {
            prop.setProperty("outputFileName", outputFileName);
        }
        if(!outputLocation.equals(prop.getProperty("outputLocation")))
        {
            prop.setProperty("outputLocation", outputLocation);
        }
        
        for(int i = 0; i < numberOfEmails; i++)
        {
            if(!emailList.get(i).equals(prop.getProperty("email" + i)))
            {
                prop.setProperty("email" + i, emailList.get(i));
            }
        }

        try
        {
            output = new FileOutputStream(fileName);
            prop.store(output, null);
        } catch (IOException ex) 
        {
            ex.printStackTrace();
        } finally 
        {
            if (input != null) {
                try 
                {
                    output.close();
                } catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
        
        importProperties();
    }
    
    public static double getWidth(){return width;}
    public static void setWidth(double w){width = w;}
    
    public static double getHeight(){return height;}
    public static void setHeight(double h){height = h;}
    
    public static double getTaktTime(){return taktTime;}
    public static void setTaktTime(double time){taktTime = time;}
    
    public static int getUnitGoal(){return unitGoal;}
    public static void setUnitGoal(int goal){unitGoal = goal;}
    
    public static int getPartNumber(){return partNumber;}
    public static void setPartNumber(int part){partNumber = part;}
    
    public static String getOutputLocation(){return outputLocation;}
    public static void setOutputLocation(String location){outputLocation = location;}
    
    public static String getOutputFileName(){return outputFileName;}
    public static void setOutputFileName(String name){outputFileName = name;}
    
    public static File getOutputFile(){return file;}
    
    public static ArrayList<String> getEmailList(){return emailList;}
    public static void setEmailList(int index, String email){emailList.set(index, email);}
    
    public static int getNumberOfEmails(){return numberOfEmails;}
    public static void setNumberOfEmails(int number){numberOfEmails = number;}
    
    public static boolean getIsRunning(){return isRunning;}
    public static void setIsRunning(boolean bool){isRunning = bool;}
    
    public static boolean getIsPaused(){return isPaused;}
    public static void setIsPaused(boolean bool){isPaused = bool;}
    
    public static int getUnits(){return units;}
    public static void setUnits(int number){units = number;}
    public static void incrementUnits(){units++;}
    
    public static double getfontSize(){return fontSize;}
    public static void setfontSize(double number){fontSize = number;}
    
    public static double getSecondsRemaining(){return secondsRemaining;}
    public static void setSecondsRemaining(double number){secondsRemaining = number;}
    public static void decrementSecondsRemaining(){secondsRemaining--;}
    
    public static void updateOutputFile()
    {        
        file = new File(outputLocation + outputFileName);
    }
    
    public static void appendToLog(String string)
    {
        try
        {
            if(!path.exists())
            {
                path.mkdirs();
            }
            if(!file.exists())
            {
                file.createNewFile();
            }
            
            fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            bufferedWriter = new BufferedWriter(fileWriter);
            
            bufferedWriter.write(string);
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }finally
        {
            try
            {
                if(bufferedWriter != null)
                    bufferedWriter.close();
                if(fileWriter != null)
                    fileWriter.close();
            }catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    public static String getCurrentDate()
    {
        date = LocalDateTime.now();
        formatDate = date.format(DATE_FORMATTER);
        
        return formatDate;
    }
    
    public static String getCurrentTime()
    {
        date = LocalDateTime.now();
        formatTime = date.format(TIME_FORMATTER);
        
        return formatTime;
    }
    
    public static String getDateString()
    {
        date = LocalDateTime.now();
        String formatMonth = date.format(DateTimeFormatter.ofPattern("MM"));
        String formatDay = date.format(DateTimeFormatter.ofPattern("dd"));
        String formatYear = date.format(DateTimeFormatter.ofPattern("yyyy"));
        
        
        int month = Integer.parseInt(formatMonth);
        String dateString = "";
        
        switch(month)
        {
            case 1:
                dateString = "January";
                break;
            case 2:
                dateString = "February";
                break;
            case 3:
                dateString = "March";
                break;
            case 4:
                dateString = "April";
                break;
            case 5:
                dateString = "May";
                break;
            case 6:
                dateString = "June";
                break;
            case 7:
                dateString = "July";
                break;
            case 8:
                dateString = "August";
                break;
            case 9:
                dateString = "September";
                break;
            case 10:
                dateString = "October";
                break;
            case 11:
                dateString = "November";
                break;
            case 12:
                dateString = "December";
                break;
            default:
                break;
        }
        
        dateString = dateString + " " + formatDay + ", " + formatYear;
        
        return dateString;
    }
}