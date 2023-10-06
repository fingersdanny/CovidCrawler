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
    public static void generateCovidListPdf(List<CovidStatus> covidStatusList, String destination) throws FileNotFoundException {
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
        document.setFont(font);
        //날짜 받아와서 넣을 것
        Paragraph titleParagraph = new Paragraph("");
        titleParagraph.setFontSize(12);
        titleParagraph.setTextAlignment(TextAlignment.LEFT);
        titleParagraph.setBold();
        document.add(titleParagraph);

        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 2, 2, 2}));
        table.setWidth(UnitValue.createPercentValue(100));
        table.setMarginTop(15);

        table.addHeaderCell(createCell("시도", true));
        table.addHeaderCell(createCell("합계", true));
        table.addHeaderCell(createCell("국내발생", true));
        table.addHeaderCell(createCell("해외유입", true));
        table.addHeaderCell(createCell("확진환자", true));
        table.addHeaderCell(createCell("사망자", true));
        table.addHeaderCell(createCell("발생률", true));

        CovidStatus totalCovidStatus = CovidStatusUtility.getTotalCovidStatus(covidStatusList);
        covidStatusList.add(0, totalCovidStatus);

        for (CovidStatus covidStatus : covidStatusList) {
            table.addCell(createCell(covidStatus.getCity(), false));
            table.addCell(createCell(covidStatus.getDailyTotal(), false));
            table.addCell(createCell(covidStatus.getDomesticOriented(), false));
            table.addCell(createCell(covidStatus.getForeignOriented(), false));
            table.addCell(createCell(covidStatus.getTotalConfirmed(), false));
            table.addCell(createCell(covidStatus.getTotalDeceased(), false));
            table.addCell(createCell(covidStatus.getTotalOccurrence(), false));
        }

        document.add(table);
        document.close();
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
