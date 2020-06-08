package at.technikumwien.webpersonapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

public class PersonTest {
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person(1L, Sex.MALE, "Test","Testperson", LocalDate.of(1990,1,1),true);
    }

    @Test
    public void testGetName(){
        assertEquals("Test Testperson", person.getName());
    }

    @Test
    public void testGetNameWithFirstNameNull() {
       person.setFirstName(null);
        assertThrows(
                IllegalArgumentException.class,
                () -> person.getName()
        );
    }

    @Test
    public void testGetNameWithFirstNameEmpty() {
        person.setFirstName(" ");
        assertThrows(
                IllegalArgumentException.class,
                () -> person.getName()
        );
    }
}
