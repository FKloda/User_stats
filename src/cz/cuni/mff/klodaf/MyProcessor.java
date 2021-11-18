package cz.cuni.mff.klodaf;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class MyProcessor {
    FileInputStream reader;
    int usersCount, usesrsUID0, usesrsNoLogin, usersNoComment;
    public MyProcessor(String pathString){
        try{
            reader = new FileInputStream(pathString);
        }
        catch (IOException ex){
            System.exit(0);
        }
    }
    public void runStatistics(){
        while (processLine()){
            continue;
        }
        System.out.printf("Users: %d\n", usersCount);
        System.out.printf("Users with UID 0: %d\n", usesrsUID0);
        System.out.printf("Users with /sbin/nologin shell: %d\n", usesrsNoLogin);
        System.out.printf("Users with empty comment field: %d\n", usersNoComment);
    }

    private boolean processLine(){
        String line = myReadLine();
        if (line.equals("")){
            return false;
        }
        String[] words = line.split(":");
        usersCount ++;
        if (words[2].equals("0")) usesrsUID0 ++;
        if (words[4].equals("")) usersNoComment ++;
        if (words[6].equals("/sbin/nologin")) usesrsNoLogin ++;
        return true;
    }
    private String myReadLine(){
        StringBuilder word = new StringBuilder();
        char character;
        int charNum;
        try{
            while ((charNum = reader.read()) != -1){
                character = (char) charNum;
                if (character == '\n'){
                    return word.toString();
                }
                else{
                    word.append(character);
                }
            }
            return word.toString();
        }
        catch (IOException ex){
            return null;
        }
    }
}
