package CSV;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FileCSV {
    public final File csvFile;
    static ArrayList<ArrayList<String>> parsedFile;

    int podiumSize = 5;

    public FileCSV(){
        //Creating file object with given name for the file to create
        csvFile = new File("BestScores.csv");
        try{
            //Creating new file
            csvFile.createNewFile();
        }
        catch (IOException exception)
        {
            //Prints this throwable and its backtrace to the standard error stream
            exception.printStackTrace();
        }
        parsedFile = new ArrayList<>();
        parseFile();
    }

    private void parseFile()
    {
        try{
            String lineNumber;
            BufferedReader inputReader = new BufferedReader(new FileReader(csvFile));

            while((lineNumber = inputReader.readLine())!=null)
            {
                parsedFile.add(new ArrayList<>(Arrays.asList(lineNumber.split(","))));
            }
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }

    //Giving  access to parsedFile
    public ArrayList<ArrayList<String>> getParsedFile()
    {
        return parsedFile;
    }

    public void saveScore(int score, String nick)
    {
        //variable which return if player with given nick exists
        boolean ifExists = false;
        //variable which return if score has been updated
        boolean isScoreUpdated = false;
        for(int i =0; i < parsedFile.size(); i++)
        {
            if(parsedFile.get(i).get(0).equals(nick))
            {
                ifExists = true;
                if(Integer.parseInt(parsedFile.get(i).get(1)) < score)
                {
                    isScoreUpdated = true;
                    ArrayList<String> tmpList = parsedFile.get(i);
                    tmpList.set(1, String.valueOf(score));
                    parsedFile.set(i, tmpList);
                }
            }
        }
        if(ifExists && isScoreUpdated)
        {
            parsedFile.sort(Collections.reverseOrder(Comparator.comparingInt(sortVal -> Integer.parseInt(sortVal.get(1)))));
        }
        else if(!ifExists)
        {
            //Adding to file nick and scores got by this player
            parsedFile.add(new ArrayList<>(Arrays.asList(nick, String.valueOf(score))));
            //Sorting podium by scores
            parsedFile.sort(Collections.reverseOrder(Comparator.comparingInt(sortVal -> Integer.parseInt(sortVal.get(1)))));

            //If there is more number of scores scores than we want to see, last one is deleted
            if(parsedFile.size() > podiumSize)
            {
                parsedFile.remove(parsedFile.size() -1);
            }
        }
        saveInNewFile();
    }
    private void saveInNewFile()
    {
        try{
            FileWriter csvWriter = new FileWriter(csvFile);

            //Writing nick and scores to file
            for(ArrayList<String> strings : parsedFile)
            {
                csvWriter.append(strings.get(0));
                csvWriter.append(",");
                csvWriter.append(strings.get(1));
                csvWriter.append("\n");
            }
            //cleaning stream
            csvWriter.flush();
            //closing stream
            csvWriter.close();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

}
