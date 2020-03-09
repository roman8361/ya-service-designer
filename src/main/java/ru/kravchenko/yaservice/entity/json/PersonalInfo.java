package ru.kravchenko.yaservice.entity.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Roman Kravchenko
 */

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalInfo {

    private String description;

    private String site;

    private SocialLinks socialLinks;

    private String firstName;

    private String lastName;

    private String companyName;

//    private AddressesList addressesList;

}
