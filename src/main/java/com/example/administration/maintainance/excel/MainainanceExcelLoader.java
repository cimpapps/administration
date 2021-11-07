package com.example.administration.maintainance.excel;

import com.example.administration.maintainance.repo.InMemoryMaintainanceRepo;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MainainanceExcelLoader {

    private final InMemoryMaintainanceRepo inMemoryMaintainanceRepo;

    public void loadExcel() {
        try (FileInputStream file = new FileInputStream("intretinere.xlsx");
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            int i = 0;
            for (Row row : sheet) {
                if (i == 0) {
                    i++;
                    continue;
                }
                int j = 0;
                for (Cell cell : row) {
                    if (cell.getCellTypeEnum().equals(CellType.STRING)) {
                        j++;
                        continue;
                    }
                    inMemoryMaintainanceRepo.addExpense(row.getCell(1).getStringCellValue()
                            , sheet.getRow(0).getCell(j).getStringCellValue(),
                            cell.getNumericCellValue());
                    j++;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        inMemoryMaintainanceRepo.print();

    }
}
