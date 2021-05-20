package file.opener;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Reader {
    private final FileInputStream fis = new FileInputStream("D:\\Desktop\\CODE\\JAVA\\AllegroParcelShipmentAnalyser\\src\\main\\resources\\raportMini.xlsx");
    private final XSSFWorkbook workbook = new XSSFWorkbook(fis);
    private final XSSFSheet sheet = workbook.getSheetAt(0);
    private Iterator<Row> rowIterator = sheet.rowIterator();
    private Map<Integer, Integer> Id = new TreeMap<>();
    private Map<Integer, Integer> waybill = new TreeMap<>();
    private Map<Integer, Integer> isNoteAddedAtTime = new TreeMap<>();
    private Map<Integer, Integer> isSendInDeclaredTime = new TreeMap<>();
    private Map<Integer, Integer> doesCountToSalesQuality = new TreeMap<>();

    public Reader() throws IOException {
    }

    public void getId(int rowCount) {
        rowIterator.next(); //for first blank element
        Row row;
        do {
            row = rowIterator.next();
            row.setRowNum(row.getRowNum());
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = cellIterator.next();

            switch (cell.getCellType().toString()) {
                case "NUMERIC" -> Id.put((int) cell.getNumericCellValue(), row.getRowNum());

                case "STRING" -> {
                    if (cell.getStringCellValue().contains(".") && cell.getStringCellValue().contains(" "))
                        Id.put(Integer.valueOf(cell.getStringCellValue().replace(".", "").replace(" ", "")), row.getRowNum());
                    else if (cell.getStringCellValue().contains("."))
                        Id.put(Integer.valueOf(cell.getStringCellValue().replace(".", "")), row.getRowNum());
                    else if (cell.getStringCellValue().contains(" "))
                        Id.put(Integer.valueOf(cell.getStringCellValue().replace(" ", "")), row.getRowNum());
                    else
                        Id.put(Integer.valueOf(cell.getStringCellValue()), row.getRowNum());
                }
            }
        } while (row.getRowNum() < rowCount);
    }

    public void printId(){
        Id.forEach((key, value) -> System.out.printf("%4d: %2d\n", key, value));
    }
}
