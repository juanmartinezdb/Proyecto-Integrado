package iesbelen.iterpolaris.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class PersonalData {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String gender; //male, female, other
    private int age; //derivado
    private LocalDate birthDate;
    private String city;
    private String country;
    private String phone;
    private String occupation;


}
