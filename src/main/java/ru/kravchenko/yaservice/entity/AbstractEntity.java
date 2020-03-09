package ru.kravchenko.yaservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Roman Kravchenko
 */

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AbstractEntity {

    String id = UUID.randomUUID().toString();

}
