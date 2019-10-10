package com.wmltyq.be;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoadServiceImpl implements LoadService{

    @Override
    public List<Step> castToStep(File file) {
        List<Step> steps = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        int num = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 0; i <num; i++) {
            Row row = sheet.getRow(i + 1);
            Step step = new Step();
            step.setPlatform(row.getCell(0).getStringCellValue());
            step.setAction(row.getCell(1).getStringCellValue());
            step.setPath(row.getCell(2).getStringCellValue());
            step.setValue(row.getCell(3).getStringCellValue());
            step.setWait((int)row.getCell(4).getNumericCellValue());
            step.setScreenshot(row.getCell(5).getStringCellValue());
            steps.add(step);
        }
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return steps;
    }
}
