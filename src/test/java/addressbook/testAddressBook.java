package addressbook;

import org.junit.Assert;
import org.junit.Test;

public class testAddressBook {

    @Test
    public void givenFileNametoCreate_whenNotExist_shouldCreateFileandReturnTrue() {
        try {
            addressBookAnalysis addressBookManager = new addressBookAnalysis();
            Assert.assertEquals(true, addressBookManager.createFile("MyAddress.json"));
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }
}
