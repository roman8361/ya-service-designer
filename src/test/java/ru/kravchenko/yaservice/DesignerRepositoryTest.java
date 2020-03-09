package ru.kravchenko.yaservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kravchenko.yaservice.entity.Designer;
import ru.kravchenko.yaservice.repository.DesignerRepository;

/**
 * @author Roman Kravchenko
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class DesignerRepositoryTest {

    @Autowired
    DesignerRepository designerRepository;

    @Test
    public void testRepository() {
        designerRepository.insert(new Designer());
        designerRepository.showAll();
    }

}
