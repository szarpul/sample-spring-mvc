package com.intive.samples.spring.mvc.views;


import com.intive.samples.spring.mvc.samples.SampleData;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicExcelView extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(
            Map<String, Object> map,
            org.apache.poi.ss.usermodel.Workbook workbook,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {


        Sheet sheet = workbook.createSheet("Spring samples");
        Row row = sheet.createRow(0);
        Cell headerCell = row.createCell(0, CellType.STRING);
        headerCell.setCellValue("Spring MVC important classes/interfaces:");
        createBoldFontAndApply(headerCell, workbook);

        List<String> sampleClasses = SampleData.getSampleSpringClasses();

        String[] values = sampleClasses.toArray(new String[sampleClasses.size()]);

        IntStream.range(0, values.length).forEach(index -> {
            Row sheetRow = sheet.createRow(index + 1);
            Cell cell = sheetRow.createCell(0, CellType.STRING);
            cell.setCellValue(values[index]);
        });
    }

    private void createBoldFontAndApply(Cell headerCell, Workbook workbook) {

        //Very bad code for general purpose. Used only for sample code.
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        headerCell.setCellStyle(cellStyle);
    }
}
