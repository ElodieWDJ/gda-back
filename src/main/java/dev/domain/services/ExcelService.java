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
		int indexLigne = 1;

		// ----------------Creation du classeur onglet, ligne, cellule
		// vide----------------

		HSSFSheet ongletAbsence = classeurExcel.createSheet("ABSENCE");
		HSSFRow ligneIndice = ongletAbsence.createRow(0);
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

		for (Absence abs : listabsence) {

			ligneIndice = ongletAbsence.createRow(indexLigne++);

			cellule = CreationFichierExcel.creationCelluleExcel(ongletAbsence, indexLigne, 0, abs.getId().toString());
			cellule = CreationFichierExcel.creationCelluleExcel(ongletAbsence, indexLigne, 1,
					abs.getDatePremierJourAbsence().toString());
			cellule = CreationFichierExcel.creationCelluleExcel(ongletAbsence, indexLigne, 2,
					abs.getDateDernierJourAbsence().toString());
			cellule = CreationFichierExcel.creationCelluleExcel(ongletAbsence, indexLigne, 3,
					abs.getTypeConge().toString());
			cellule = CreationFichierExcel.creationCelluleExcel(ongletAbsence, indexLigne, 4,
					abs.getCommentaireAbsence());
			cellule = CreationFichierExcel.creationCelluleExcel(ongletAbsence, indexLigne, 5,
					abs.getStatutDemandeAbsence().toString());
			cellule = CreationFichierExcel.creationCelluleExcel(ongletAbsence, indexLigne, 6,
					abs.getCollegue().toString());

		}

		return classeurExcel;
	}

}
