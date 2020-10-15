package dev.domain.services;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;

import dev.domain.entite.Absence;
import dev.domain.exceptions.AbsenceIntrouvableException;
import dev.repository.AbsenceRepo;
import dev.utils.CreationFichierExcel;

@Service
public class ExcelService {

	private AbsenceRepo absenceRepo;

	public ExcelService(AbsenceRepo absenceRepo) {
		super();
		this.absenceRepo = absenceRepo;
	}

	public HSSFWorkbook excelFileExport() throws AbsenceIntrouvableException {

		HSSFWorkbook classeurExcel = new HSSFWorkbook();
		List<Absence> listabsence = absenceRepo.findAll();
		CellStyle styleCelluleEntête = CreationFichierExcel.styleCelluleEnTeteClasseurExcel(classeurExcel);
		Integer indexLigne = 0;

		// ----------------Creation du classeur onglet, ligne, cellule
		// vide----------------

		HSSFSheet ongletAbsence = classeurExcel.createSheet("ABSENCE");
		HSSFRow ligneIndice = ongletAbsence.createRow(indexLigne);
		HSSFCell cellule;

		// ----------------On applique le styleCelluleEntête à toute les cellules de la
		// ligne 0 ----------------

		ligneIndice.setRowStyle(styleCelluleEntête);

		// ----------------Remplissage des cellules de la ligne 0 (En
		// tête)----------------

		cellule = ligneIndice.createCell(0);
		cellule.setCellValue("ID");

		cellule = ligneIndice.createCell(1);
		cellule.setCellValue("Debut Absence");

		cellule = ligneIndice.createCell(2);
		cellule.setCellValue("Fin Absence");

		cellule = ligneIndice.createCell(3);
		cellule.setCellValue("Type Conge");

		cellule = ligneIndice.createCell(4);
		cellule.setCellValue("Commentaire");

		cellule = ligneIndice.createCell(5);
		cellule.setCellValue("Status");

		cellule = ligneIndice.createCell(6);
		cellule.setCellValue("Collegue");

		// ----------------Remplissage des cellules du tableau ligne par
		// ligne----------------

		if (listabsence.size() != 0) {
			for (Absence abs : listabsence) {

				indexLigne = indexLigne + 1;

				ligneIndice = ongletAbsence.createRow(indexLigne);

				cellule = ligneIndice.createCell(0);
				cellule.setCellValue(abs.getId().toString());

				cellule = ligneIndice.createCell(1);
				cellule.setCellValue(abs.getDatePremierJourAbsence().toString());

				cellule = ligneIndice.createCell(2);
				cellule.setCellValue(abs.getDateDernierJourAbsence().toString());

				cellule = ligneIndice.createCell(3);
				cellule.setCellValue(abs.getTypeConge().toString());

				cellule = ligneIndice.createCell(4);
				cellule.setCellValue(abs.getCommentaireAbsence().toString());

				cellule = ligneIndice.createCell(5);
				cellule.setCellValue(abs.getStatutDemandeAbsence().toString());

				cellule = ligneIndice.createCell(6);
				cellule.setCellValue(abs.getCollegue().toString());

			}
		} else {
			throw new AbsenceIntrouvableException("Pas d'absence correspondant à cet Id.");
		}
		return classeurExcel;
	}
}
