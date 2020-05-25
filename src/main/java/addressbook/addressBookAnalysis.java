package addressbook;

import java.io.File;
import java.io.IOException;

public class addressBookAnalysis {


    public boolean createFile(String fileName) throws addressBookException {
        try {
            if (fileName.length() == 0)
                throw new addressBookException("File Name Cannot be empty", addressBookException.ExceptionType.ENTERED_EMPTY);
            File files = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            boolean isFileExist = files.exists();
            if (isFileExist) {
                return false;
            }
            files.createNewFile();
            return true;
        } catch (IOException e) {
            throw new addressBookException("Cannot Create File in path", addressBookException.ExceptionType.CANNOT_CREATE_FILE);
        }
    }
}