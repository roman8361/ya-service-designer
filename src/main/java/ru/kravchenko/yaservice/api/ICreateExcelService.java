package ru.kravchenko.yaservice.api;

import ru.kravchenko.yaservice.entity.Designer;

import java.util.List;

/**
 * @author Roman Kravchenko
 */
public interface ICreateExcelService {
    void createFirstFile(List<Designer> designerList);

    void appendDataToFile(List<Designer> designers);

}
