package at.technikumwien.webpersonapp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")     //testDBInitializer statt dbInitializer wird ausgefuehrt
@Transactional          // macht am Ende der Methode ein Rollback in der isoloerten Datenbankansicht - testPerson wandert somit nicht in die Datenbank
@Tag("integration-test")
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSave() {
        var countBefore = personRepository.count();

        var person = personRepository.save(
                new Person(null, Sex.MALE, "Maxi", "Musterkind", LocalDate.of(2010,3,5), true)
        );

        assertNotNull(person.getId());
        assertEquals(countBefore + 1, personRepository.count());
    }

    @Test
    public void testFindAllByActiveTrue() {
        var persons = personRepository.findAllByActiveTrue();

        assertEquals(2,persons.size());
    }
}
