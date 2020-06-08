package at.technikumwien.webpersonapp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data   // includes getter setter equalshash and toString
@NoArgsConstructor
@AllArgsConstructor

@Entity // jede Entity brauch eine Id
@Table(name = "t_person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private boolean active;

    @JsonIgnore
    public String getName() {
        if(firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("FirstName is null or empty");
        }
        if(lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("LastName is null or empty");
        }
        return firstName + " " + lastName;
    }
}
