package dev.controller;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import dev.domain.exceptions.AbsenceIntrouvableException;
import dev.domain.services.ExcelService;

@RestController
@RequestMapping("export")
public class ExcelControlleur {

	private ExcelService excelService;

	public ExcelControlleur(ExcelService excelService) {
		this.excelService = excelService;
	}

	@GetMapping("absence")
	public ResponseEntity<StreamingResponseBody> exportExcelAbsence() throws AbsenceIntrouvableException {

		Workbook workBook = excelService.excelFileExport();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"Visualisation des absences.xls\"")
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(workBook::write);
	}
}
