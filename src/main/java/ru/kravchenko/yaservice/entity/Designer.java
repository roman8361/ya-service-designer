package ru.kravchenko.yaservice.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Roman Kravchenko
 */

@Getter
@Setter
public class Designer {

    private String id;

    private String firstName;

    private String lastName;

    private String phone;

    private String rating;

    private String description;

    private String city;

    private String vk;

    private String facebook;

    private String instagram;

    private String companyName;

    private String special = "Дизайнер интерьеров";

    @Override
    public String toString() {
        return "Designer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", vk='" + vk + '\'' +
                ", facebook='" + facebook + '\'' +
                ", instagram='" + instagram + '\'' +
                ", companyName='" + companyName + '\'' +
                ", special='" + special + '\'' +
                '}';
    }

}
