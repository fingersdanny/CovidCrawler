package covid_crawler.src;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PdfExporter {
    public static void generateCovidListPdf(List<CovidStatus> covidStatusList, String destination, String date) throws FileNotFoundException {
        try {
            PdfWriter writer = new PdfWriter(destination);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            document.setFontSize(15);
            PdfFont font = null;
            try {
                font = PdfFontFactory.createFont("NanumBarunGothicBold.otf", PdfEncodings.IDENTITY_H, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setTitle(date, document, font);

            Table table = new Table(UnitValue.createPercentArray(new float[]{2, 2, 2, 2, 2, 2, 2}));
            table.setWidth(UnitValue.createPercentValue(100));
            table.setMarginTop(15);

            createHeader(table);

            for (CovidStatus covidStatus : covidStatusList) {
                addNewRow(table, covidStatus);
            }

            document.add(table);
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void setTitle(String date, Document document, PdfFont font) {
        document.setFont(font);
        Paragraph titleParagraph = new Paragraph(date);
        titleParagraph.setFontSize(12);
        titleParagraph.setTextAlignment(TextAlignment.LEFT);
        titleParagraph.setBold();
        document.add(titleParagraph);
    }

    private static void createHeader(Table table) {
        table.addHeaderCell(createCell("시도", true));
        table.addHeaderCell(createCell("일일확진", true));
        table.addHeaderCell(createCell("일일사망", true));
        table.addHeaderCell(createCell("국내발생", true));
        table.addHeaderCell(createCell("해외유입", true));
        table.addHeaderCell(createCell("누적확진", true));
        table.addHeaderCell(createCell("누적사망", true));
    }

    private static void addNewRow(Table table, CovidStatus covidStatus) {
        table.addCell(createCell(covidStatus.getCity(), false));
        table.addCell(createCell(covidStatus.getDailyTotal(), false));
        table.addCell(createCell(covidStatus.getDailyDeceased(), false));
        table.addCell(createCell(covidStatus.getDomesticOriented(), false));
        table.addCell(createCell(covidStatus.getForeignOriented(), false));
        table.addCell(createCell(covidStatus.getTotalConfirmed(), false));
        table.addCell(createCell(covidStatus.getTotalDeceased(), false));
    }

    private static Cell createCell(Object content, boolean isHeader) {
        Paragraph paragraph = new Paragraph(String.valueOf(content));
        Cell cell = new Cell().add(paragraph);
        cell.setPadding(5);
        if (isHeader) {
            cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            cell.setFontSize(13);
            cell.setBold();
        }
        return cell;
    }
}
