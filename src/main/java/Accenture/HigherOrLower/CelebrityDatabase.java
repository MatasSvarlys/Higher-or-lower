import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CelebrityDatabase {

    private final CelebrityRepository celebrityRepository;

    @Autowired
    public CelebrityDatabase(CelebrityRepository celebrityRepository) {
        this.celebrityRepository = celebrityRepository;

        // Load and insert data from Excel file
        try (InputStream excelInputStream = getClass().getResourceAsStream("/Celebrities.xlsx")) {
            insertCelebritiesFromExcel(excelInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void insertCelebritiesFromExcel(InputStream excelInputStream) {
        List<Celebrity> celebritiesFromExcel = parseExcel(excelInputStream);
        celebrityRepository.saveAll(celebritiesFromExcel);
    }

    private List<Celebrity> parseExcel(InputStream excelInputStream) {
        List<Celebrity> celebrities = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(excelInputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                String name = row.getCell(0).getStringCellValue();
                String country = row.getCell(1).getStringCellValue();
                int googleSearchCount = (int) row.getCell(2).getNumericCellValue();

                Celebrity celebrity = new Celebrity(name, country, googleSearchCount);
                celebrities.add(celebrity);
            }
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        return celebrities;
    }

    public List<Celebrity> getListOfCelebritiesByCountry(String countryCode) {
        return celebrityRepository.findByCountryIgnoreCase(countryCode);
    }
}
