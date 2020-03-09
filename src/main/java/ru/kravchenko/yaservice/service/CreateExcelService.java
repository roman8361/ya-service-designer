package ru.kravchenko.yaservice.service;

import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kravchenko.yaservice.api.ICreateExcelService;
import ru.kravchenko.yaservice.constant.Constant;
import ru.kravchenko.yaservice.entity.Designer;
import ru.kravchenko.yaservice.repository.DesignerRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Roman Kravchenko
 */

@Service
public class CreateExcelService implements ICreateExcelService {

    @Autowired
    private DesignerRepository designerRepository;

    @Override
    @SneakyThrows
    public void createFirstFile(List<Designer> designerList) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("FirstSheet");

        HSSFRow rowhead = sheet.createRow((short) 0);
        rowhead.createCell(0).setCellValue("No.");
        rowhead.createCell(1).setCellValue("Имя");
        rowhead.createCell(2).setCellValue("Фамилия");
        rowhead.createCell(3).setCellValue("Телефон");
        rowhead.createCell(4).setCellValue("Описание");
        rowhead.createCell(5).setCellValue("Город");
        rowhead.createCell(6).setCellValue("Вконтакте");
        rowhead.createCell(7).setCellValue("Файсбук");
        rowhead.createCell(8).setCellValue("Инстаграм");
        rowhead.createCell(9).setCellValue("Компания");
        rowhead.createCell(10).setCellValue("Специальность");

        int count = 1;
        for (Designer designer : designerList) {
            HSSFRow row = sheet.createRow((short) count);
            row.createCell(0).setCellValue(count);
            row.createCell(1).setCellValue(designer.getFirstName());
            row.createCell(2).setCellValue(designer.getLastName());
            row.createCell(3).setCellValue(designer.getPhone());
            row.createCell(4).setCellValue(designer.getDescription());
            row.createCell(5).setCellValue("Москва");
            row.createCell(6).setCellValue(designer.getVk());
            row.createCell(7).setCellValue(designer.getFacebook());
            row.createCell(8).setCellValue(designer.getInstagram());
            row.createCell(9).setCellValue(designer.getCompanyName());
            row.createCell(10).setCellValue(designer.getSpecial());
            count++;
        }
        FileOutputStream fileOut = new FileOutputStream(Constant.FILE_NAME);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
        System.out.println("Your excel file has been generated!");
    }

    @Override
    @SneakyThrows
    public void appendDataToFile(List<Designer> designers) {
        FileInputStream inputStream = new FileInputStream(new File(Constant.FILE_NAME));
        Workbook workbook = WorkbookFactory.create(inputStream);

        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getLastRowNum();
        System.out.println(rowCount);
        for (Designer designer : designers) {
            Row row = sheet.createRow(++rowCount);
            row.createCell(0).setCellValue(rowCount);
            row.createCell(1).setCellValue(designer.getFirstName());
            row.createCell(2).setCellValue(designer.getLastName());
            row.createCell(3).setCellValue(designer.getPhone());
            row.createCell(4).setCellValue(designer.getDescription());
            row.createCell(5).setCellValue(designer.getCity());
            row.createCell(6).setCellValue(designer.getVk());
            row.createCell(7).setCellValue(designer.getFacebook());
            row.createCell(8).setCellValue(designer.getInstagram());
            row.createCell(9).setCellValue(designer.getCompanyName());
            row.createCell(10).setCellValue(designer.getSpecial());
        }
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(Constant.FILE_NAME);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
