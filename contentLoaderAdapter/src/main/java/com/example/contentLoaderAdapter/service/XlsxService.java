package com.example.contentLoaderAdapter.service;

import com.example.contentLoaderAdapter.dto.BuildingDto;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class XlsxService {

    public List<BuildingDto> processFile(File file, String source, int startRow, int lastRow) throws IOException {
        List<BuildingDto> buildings = new ArrayList<>();
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден: " + file.getAbsolutePath());
        }
        try (Workbook workbook = WorkbookFactory.create(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            int count = 0;

            for (int i = startRow; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || row.getRowNum() == 0) continue;
                BuildingDto dto = new BuildingDto(
                        row.getCell(4).getStringCellValue(),
                        row.getCell(2).getStringCellValue(),
                        row.getCell(5).getStringCellValue(),
                        row.getCell(8).getStringCellValue(),
                        source
                );
                buildings.add(dto);

                count++;
                if (count >= lastRow) break;
            }
        }
        return buildings;
    }
}
