package ru.kravchenko.yaservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kravchenko.yaservice.entity.json.Hobby;

import java.util.List;
import java.util.Map;

/**
 * @author Roman Kravchenko
 */

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends AbstractEntity{

    private String login;

    private String password;

    private List<String> telephone;

    private Map <String, Map<String, Hobby>> hobbyUser;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", telephone=" + telephone +
                ", hobbyUser=" + hobbyUser +
                '}';
    }
}
