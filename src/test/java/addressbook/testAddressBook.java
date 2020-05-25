package addressbook;

import org.junit.Assert;
import org.junit.Test;

public class testAddressBook {

    addressBookAnalysis addressBookAnalyser = new addressBookAnalysis();

    @Test
    public void givenFileNametoCreate_whenNotExist_shouldCreateFileandReturnTrue() {
        try {

            Assert.assertEquals(true, addressBookAnalyser.createFile("MyAddress.json"));
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileNametoCreate_whenExists_shouldNotCreateFileandReturnFalse() {
        try {

            Assert.assertEquals(false, addressBookAnalyser.createFile("MyAddress.json"));
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }

}
