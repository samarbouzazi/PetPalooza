package petpalooza.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Event;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ExportpdfService {

    public ByteArrayInputStream eventExport(List<Event> events) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph("Liste des evenements", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);

            Stream.of("titre", "description", "datedebut", "datefin","Les participants").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                com.itextpdf.text.Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.BLUE);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });

            for (Event event : events) {
                PdfPCell NomCell = new PdfPCell(new Phrase(event.getTitre()));
                NomCell.setPaddingLeft(1);
                NomCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                NomCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(NomCell);

                PdfPCell adresseCell = new PdfPCell(new Phrase(event.getDescription()));
                adresseCell.setPaddingLeft(1);
                adresseCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                adresseCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(adresseCell);

                PdfPCell emailCell = new PdfPCell(new Phrase(String.valueOf(event.getDateDebut())));
                emailCell.setPaddingLeft(1);
                emailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(emailCell);

                PdfPCell telCell = new PdfPCell(new Phrase(String.valueOf(event.getDateFin())));
                telCell.setPaddingLeft(1);
                telCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                telCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(telCell);

                PdfPCell userCell = new PdfPCell(new Phrase(String.valueOf(event.getParticipants())));
                telCell.setPaddingLeft(1);
                telCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                telCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(userCell);
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
