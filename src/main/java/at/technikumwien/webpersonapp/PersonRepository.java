package at.technikumwien.webpersonapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByActiveTrue(); // "find" ist verpflichtend so anzugeben, "by" ebenso, Active und true sind austauschbar aber etwas muss dort sein
//    List<Person> findAllByLastNameAndFirstNameOrderByLastNameAsc(String lastName, String firstName);
}

