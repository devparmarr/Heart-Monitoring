/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import static Algorithms.RandomForest.Hrate;
import static Algorithms.RandomForest.oxy;
import static Algorithms.RandomForest.qt;
import static Algorithms.RandomForest.tempreture;
import Master.Dbconn;
import static Master.Dbconn.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportShow {

    public static String prFinalResult = "", qtFinalResult = "", sysFinalResult = "", dysFinalResult = "", hpFinalResult ="", oxyFinalResult = "", tempFinalResult = "";

    public static void createreport(String id) {

        long yourvalue = 135;
        // String yourname = "jitu", yourgender = "Male", yourdr = "Dr.Jitu Patil";
        Document doc = new Document();
        try {

            // String Systolic = null, Diastolic = null, Heart_Rate = null, Cholestrol_LDL = null, Cholestrol_HDL = null, Stress = null, Suger = null, QT_Interval = null, PR_Interval = null, Oxygen_saturation = null, Hemoglobin = null, totalCholestrol = null, cvd = null, cardiac = null, vage = null;
            PdfWriter.getInstance(doc, new FileOutputStream(Dbconn.reportfile + "RF_Report_" + id + ".pdf"));
            doc.open();
            PdfPTable table3 = new PdfPTable(4);
            PdfPTable table1 = new PdfPTable(4);

            float[] columnWidthsa1 = {1f, 2.5f, 1f, 2.5f}; // Second column will be
            // twice as first and third
            table1.setWidths(columnWidthsa1);

            //Add Image
            Image image1 = Image.getInstance("G:\\BE2022-2023\\23 cs 806 grp qr code banking\\Heart_Monitoring_BSN\\src\\images\\iHeart.png");
            //Fixed Positioning
            image1.setAbsolutePosition(1f, 5f);
            //Scale to new height and new width of image
            image1.scaleAbsolute(50, 50);
            //Add to document

            // Patient
            PdfPCell cell40 = new PdfPCell(image1);
            Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC | Font.UNDERLINE, BaseColor.RED);
            PdfPCell cellt40 = new PdfPCell(new Phrase("PATIENT CLINICAL REPORT", redFont));
            PdfPCell cell41 = new PdfPCell(new Phrase("Patient ID :", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell42 = new PdfPCell(new Phrase(pid, FontFactory.getFont(FontFactory.HELVETICA, 8)));

            PdfPCell cell59 = new PdfPCell(new Phrase("DOB :", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell60 = new PdfPCell(new Phrase(dob, FontFactory.getFont(FontFactory.HELVETICA, 8)));

            PdfPCell cell43 = new PdfPCell(new Phrase("Patient Name :", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell44 = new PdfPCell(new Phrase(pname, FontFactory.getFont(FontFactory.HELVETICA, 8)));

            
            PdfPCell cell63 = new PdfPCell(new Phrase("Address :", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell64 = new PdfPCell(new Phrase(address, FontFactory.getFont(FontFactory.HELVETICA, 8)));

            
            PdfPCell cell67 = new PdfPCell(new Phrase("Mobile No :", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell68 = new PdfPCell(new Phrase(mobile, FontFactory.getFont(FontFactory.HELVETICA, 8)));

            PdfPCell cell45 = new PdfPCell(new Phrase("Gender :", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell46 = new PdfPCell(new Phrase(gender, FontFactory.getFont(FontFactory.HELVETICA, 8)));

            
            PdfPCell cell71 = new PdfPCell(new Phrase("Email-ID :", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell72 = new PdfPCell(new Phrase(emailid, FontFactory.getFont(FontFactory.HELVETICA, 8)));

            Date day = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(day);
            PdfPCell cellt71 = new PdfPCell(new Phrase("Date :", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cellt72 = new PdfPCell(new Phrase(strDate, FontFactory.getFont(FontFactory.HELVETICA, 8)));

            cellt40.setColspan(3);

            cellt72.setColspan(3);
            //cellt40.setBorderColor(BaseColor.YELLOW);
            cell40.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellt40.setHorizontalAlignment(Element.TITLE);

            cell40.setBorder(Rectangle.NO_BORDER);
            cellt40.setBorder(Rectangle.NO_BORDER);
            cell41.setBorder(Rectangle.NO_BORDER);
            cell42.setBorder(Rectangle.NO_BORDER);
            cell59.setBorder(Rectangle.NO_BORDER);
            cell60.setBorder(Rectangle.NO_BORDER);

            cell43.setBorder(Rectangle.NO_BORDER);
            cell44.setBorder(Rectangle.NO_BORDER);
//            cell65.setBorder(Rectangle.NO_BORDER);
//            cell66.setBorder(Rectangle.NO_BORDER);
            cell63.setBorder(Rectangle.NO_BORDER);

            cell64.setBorder(Rectangle.NO_BORDER);
//            cell61.setBorder(Rectangle.NO_BORDER);
//            cell62.setBorder(Rectangle.NO_BORDER);
            cell67.setBorder(Rectangle.NO_BORDER);
            cell68.setBorder(Rectangle.NO_BORDER);

            cell45.setBorder(Rectangle.NO_BORDER);
            cell46.setBorder(Rectangle.NO_BORDER);
//            cell69.setBorder(Rectangle.NO_BORDER);
//            cell70.setBorder(Rectangle.NO_BORDER);
            cell71.setBorder(Rectangle.NO_BORDER);
            cell72.setBorder(Rectangle.NO_BORDER);
            cellt71.setBorder(Rectangle.NO_BORDER);
            cellt72.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell40);
            table1.addCell(cellt40);
            table1.addCell(cell41);
            table1.addCell(cell42);

            table1.addCell(cell59);
            table1.addCell(cell60);

            table1.addCell(cell43);
            table1.addCell(cell44);

//            table1.addCell(cell65);
//            table1.addCell(cell66);

            table1.addCell(cell63);
            table1.addCell(cell64);

//            table1.addCell(cell61);
//            table1.addCell(cell62);

            table1.addCell(cell67);
            table1.addCell(cell68);

            table1.addCell(cell45);
            table1.addCell(cell46);

//            table1.addCell(cell69);
//            table1.addCell(cell70);
            table1.addCell(cell71);
            table1.addCell(cell72);

            table1.addCell(cellt71);
            table1.addCell(cellt72);

            table1.setSpacingAfter(20);
            // Set Column widths of table
            float[] columnWidthsa = {2.0f, 2.2f, 2.5f, 2.4f}; // Second column will be
            // twice as first and third
            table3.setWidths(columnWidthsa);
//Font whiteFont = new FontFactory;

            PdfPCell cellt1 = new PdfPCell(new Phrase("TEST", FontFactory.getFont(FontFactory.COURIER_BOLD, 8)));
            PdfPCell cellt2 = new PdfPCell(new Phrase("RESULTS", FontFactory.getFont(FontFactory.COURIER_BOLD, 8)));
            PdfPCell cellt4 = new PdfPCell(new Phrase("REF.RANGE", FontFactory.getFont(FontFactory.COURIER_BOLD, 8)));

            
//Heart_Rate
            PdfPCell cell6 = new PdfPCell(new Phrase("HEART RATE", FontFactory.getFont(FontFactory.COURIER_BOLD, 8)));
            cell6.setColspan(4);
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cell7 = new PdfPCell(new Phrase("Heart_Rate", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell8 = new PdfPCell(new Phrase(hpFinalResult, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell49 = new PdfPCell(new Phrase(Hrate + " (1/min)", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell87 = new PdfPCell(new Phrase("60 to 100", FontFactory.getFont(FontFactory.HELVETICA, 8)));

            //ECG
            PdfPCell cell20 = new PdfPCell(new Phrase("ECG", FontFactory.getFont(FontFactory.COURIER_BOLD, 8)));
            PdfPCell cell21 = new PdfPCell(new Phrase("QT_Interval", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell22 = new PdfPCell(new Phrase(qtFinalResult, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell55 = new PdfPCell(new Phrase(String.valueOf(RandomForest.qt), FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell92 = new PdfPCell(new Phrase("360 to 440", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell23 = new PdfPCell(new Phrase("PR_Interval", FontFactory.getFont(FontFactory.HELVETICA, 8)));
//            PdfPCell cell24 = new PdfPCell(new Phrase(prFinalResult, FontFactory.getFont(FontFactory.HELVETICA, 8)));
//            PdfPCell cell56 = new PdfPCell(new Phrase(String.valueOf(RandomForest.pr), FontFactory.getFont(FontFactory.HELVETICA, 8)));
//            PdfPCell cell93 = new PdfPCell(new Phrase("120 to 200(sec)", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            cell20.setColspan(4);
            cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
            //Oxygen_saturation
            PdfPCell cell25 = new PdfPCell(new Phrase("Tempreture", FontFactory.getFont(FontFactory.COURIER_BOLD, 8)));
            PdfPCell cell26 = new PdfPCell(new Phrase("Tempreture", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell27 = new PdfPCell(new Phrase(tempFinalResult, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell57 = new PdfPCell(new Phrase(tempreture + "", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell94 = new PdfPCell(new Phrase("(36.5-37.5 (°C))", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            cell25.setColspan(4);
            cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
            //Hemoglobin
            PdfPCell cell28 = new PdfPCell(new Phrase("OXYGEN SATURATION", FontFactory.getFont(FontFactory.COURIER_BOLD, 8)));
            PdfPCell cell29 = new PdfPCell(new Phrase("Oxygen_saturation", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell30 = new PdfPCell(new Phrase(oxyFinalResult, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell58 = new PdfPCell(new Phrase(oxy + " %", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            PdfPCell cell95 = new PdfPCell(new Phrase("(90-100%)", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            cell28.setColspan(4);
            cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
            table3.addCell(cellt1);
            cellt2.setColspan(2);
            table3.addCell(cellt2);
            table3.addCell(cellt4);
            table3.addCell(cell6);
            table3.addCell(cell7);
            table3.addCell(cell8);
            table3.addCell(cell49);
            table3.addCell(cell87);

            table3.addCell(cell20);
            table3.addCell(cell21);
            table3.addCell(cell22);
            table3.addCell(cell55);
            table3.addCell(cell92);
            table3.addCell(cell23);
//            table3.addCell(cell24);
//            table3.addCell(cell56);
//            table3.addCell(cell93);

            table3.addCell(cell25);
            table3.addCell(cell26);
            table3.addCell(cell27);
            table3.addCell(cell57);
            table3.addCell(cell94);

            table3.addCell(cell28);
            table3.addCell(cell29);
            table3.addCell(cell30);
            table3.addCell(cell58);
            table3.addCell(cell95);

            doc.add(table1);
            doc.add(table3);

        } catch (Exception e) {
        } finally {
            doc.close();
        }

    }

    public static void main(String[] args) throws IOException, DocumentException {
        createreport("1");
    }

}
