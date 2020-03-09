package ru.kravchenko.yaservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kravchenko.yaservice.api.ICreateExcelService;
import ru.kravchenko.yaservice.api.IParserService;
import ru.kravchenko.yaservice.constant.Constant;
import ru.kravchenko.yaservice.repository.DesignerRepository;


/**
 * @author Roman Kravchenko
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ParserServiceAndCreateExcelTest {

    @Autowired
    IParserService parserService;

    @Autowired
    ICreateExcelService createExcelService;

    @Autowired
    DesignerRepository designerRepository;

    @Test
    public void testCreateFirstFile() {
        parserService.fillEntityDesigner(Constant.PATH_TO_FILE_JSON_SPB_0);
        createExcelService.createFirstFile(designerRepository.getAll());
    }

    @Test
    public void testAppendToExcelFile() {
        parserService.fillEntityDesigner(Constant.PATH_TO_FILE_JSON_MSK_9);
        createExcelService.appendDataToFile(designerRepository.getAll());
    }

}
