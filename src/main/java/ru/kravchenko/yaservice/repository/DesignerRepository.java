package ru.kravchenko.yaservice.repository;


import org.springframework.stereotype.Repository;
import ru.kravchenko.yaservice.entity.Designer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Kravchenko
 */

@Repository
public class DesignerRepository {

    private List<Designer> designerList = new ArrayList<>();

    public void insert(Designer designer) {
        if (designer == null) return;
        designerList.add(designer);
    }

    public List<Designer> getAll() { return designerList; }

    public void showAll() {
        designerList.forEach(System.out::println);
    }

    public void insertAll(List<Designer> nodeListAny) {
        designerList.addAll(nodeListAny);
    }

    public Integer getSize() {
        return designerList.size();
    }

}
