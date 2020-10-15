package dev.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class CreationFichierExcel {

	public static CellStyle styleCelluleEnTeteClasseurExcel(HSSFWorkbook workbook) {

		CellStyle headerCellStyle = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

		CellStyle numericCellStyle = workbook.createCellStyle();
		numericCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("0.00"));

		return headerCellStyle;

	}

	public static HSSFCell creationCelluleExcel(HSSFSheet onglet, Integer indiceLigne, Integer indiceCellule,
			String valeur) {

		HSSFRow ligne = onglet.createRow(indiceLigne);
		HSSFCell cellule;

		cellule = ligne.createCell(indiceCellule);
		cellule.setCellValue(valeur);

		return cellule;
	}

}
