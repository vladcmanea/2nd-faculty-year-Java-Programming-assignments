package lab3p1;

import java.io.*;
import java.util.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Vlad Manea
 * @author Vlad Tudose
 * @version 0.1
 * <b>Description</b>: HTMLReport Class
 */
public class PDFReport implements Report {

    /*
     * Data
     */
    String file;
    String layout;

    /**
     * <b>Description</b>: Constructs a PDFReport object
     * @param layout layout from which report is read
     * @param file file to which report is written
     */
    public PDFReport(String layout, String file) {
        this.file = file;
        this.layout = layout;
    }

    /**
     * <b>Description</b>: Creates a report
     * @param friends set of friends
     */
    public void create(Set<Friend> friends) {

        Document document = null;
        FileReader reader = null;
        BufferedReader buffer = null;

        try {

            reader = new FileReader(layout);
            buffer = new BufferedReader(reader);

            int backgroundWidth = Integer.parseInt(buffer.readLine());
            int backgroundHeight = Integer.parseInt(buffer.readLine());
            int backgroundRed = Integer.parseInt(buffer.readLine());
            int backgroundGreen = Integer.parseInt(buffer.readLine());
            int backgroundBlue = Integer.parseInt(buffer.readLine());

            String title = buffer.readLine();
            String subject = buffer.readLine();
            String keywords = buffer.readLine();
            String author = buffer.readLine();
            String creator = buffer.readLine();

            int titleSize = Integer.parseInt(buffer.readLine());
            int titleRed = Integer.parseInt(buffer.readLine());
            int titleGreen = Integer.parseInt(buffer.readLine());
            int titleBlue = Integer.parseInt(buffer.readLine());

            int nameSize = Integer.parseInt(buffer.readLine());
            int nameRed = Integer.parseInt(buffer.readLine());
            int nameGreen = Integer.parseInt(buffer.readLine());
            int nameBlue = Integer.parseInt(buffer.readLine());

            int boldSize = Integer.parseInt(buffer.readLine());
            int boldRed = Integer.parseInt(buffer.readLine());
            int boldGreen = Integer.parseInt(buffer.readLine());
            int boldBlue = Integer.parseInt(buffer.readLine());

            int normalSize = Integer.parseInt(buffer.readLine());
            int normalRed = Integer.parseInt(buffer.readLine());
            int normalGreen = Integer.parseInt(buffer.readLine());
            int normalBlue = Integer.parseInt(buffer.readLine());

            int unknownSize = Integer.parseInt(buffer.readLine());
            int unknownRed = Integer.parseInt(buffer.readLine());
            int unknownGreen = Integer.parseInt(buffer.readLine());
            int unknownBlue = Integer.parseInt(buffer.readLine());

            String unknown = buffer.readLine();
            String address = buffer.readLine();
            String phone = buffer.readLine();
            String web = buffer.readLine();
            String email = buffer.readLine();

            String path = buffer.readLine();
            String extension = buffer.readLine();

            Rectangle backgroundRect = new Rectangle(backgroundWidth, backgroundHeight);
            backgroundRect.setBackgroundColor(
                    new BaseColor(backgroundRed, backgroundGreen, backgroundBlue));

            document = new Document(backgroundRect);
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            document.addTitle(title);
            document.addSubject(subject);
            document.addKeywords(keywords);
            document.addAuthor(author);
            document.addCreator(creator);

            Font titleFont = new Font(Font.FontFamily.HELVETICA, titleSize, Font.BOLD);
            titleFont.setColor(titleRed, titleGreen, titleBlue);
            Font nameFont = new Font(Font.FontFamily.HELVETICA, nameSize, Font.BOLD);
            nameFont.setColor(nameRed, nameGreen, nameBlue);
            Font boldFont = new Font(Font.FontFamily.HELVETICA, boldSize, Font.BOLD);
            boldFont.setColor(boldRed, boldGreen, boldBlue);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, normalSize, Font.NORMAL);
            normalFont.setColor(normalRed, normalGreen, normalBlue);
            Font unknownFont = new Font(Font.FontFamily.HELVETICA, unknownSize, Font.BOLD);
            unknownFont.setColor(unknownRed, unknownGreen, unknownBlue);

            Paragraph preface = new Paragraph();
            preface.add(new Paragraph(" ", titleFont));
            preface.add(new Paragraph(title, titleFont));
            preface.add(new Paragraph(" ", normalFont));
            preface.add(new Paragraph(subject, nameFont));
            preface.add(new Paragraph(" "));
            document.add(preface);
            document.newPage();

            /* create and initialize map according to sign */
            Map<Integer, HashSet<Friend>> reported = new HashMap<Integer, HashSet<Friend>>();
            for (int i = 1; i <= 12; ++i) {
                /* iterate signs */
                reported.put(i, new HashSet<Friend>()); // initialize
            }

            /* add each friend to its sign */
            Iterator<Friend> it = friends.iterator();
            while (it.hasNext()) {
                /* iterate friends */
                Friend friend = it.next(); // get friend
                reported.get(Agenda.getSign(friend.getDay(), friend.getMonth())).add(friend); // add friend
            }

            int k = 0;
            for (Integer i = 1; i <= 12; ++i) {
                /* iterate signs */

                Set<Friend> signedFriends = reported.get(i); // get friends of sign

                /* put each friend as map in list for report */
                Iterator<Friend> kt = signedFriends.iterator();
                while (kt.hasNext()) {
                    /* iterate friends of sign */
                    Friend signedFriend = kt.next(); // get friend

                    //Image image = Image.getInstance("data/" + i + ".png");
                    Image image = Image.getInstance(path + "/" + i + "." + extension);
                    document.add(image);

                    Paragraph person = new Paragraph();
                    person.add(new Paragraph(" ", normalFont));
                    if (signedFriend.getName().length() > 0) {
                        person.add(new Paragraph(signedFriend.getName(), nameFont));
                    } else {
                        /*person.add(new Paragraph("Unknown Friend No. " + (++k), unknownFont));*/
                        person.add(new Paragraph(unknown + " " + (++k), unknownFont));
                    }

                    if (signedFriend.getAddress() != "") {
                        person.add(new Paragraph(" ", normalFont));
                        person.add(new Paragraph(address, boldFont));
                        person.add(new Paragraph(signedFriend.getAddress(), normalFont));
                    }

                    if (signedFriend.getPhone() != "") {
                        person.add(new Paragraph(" ", normalFont));
                        person.add(new Paragraph(phone, boldFont));
                        person.add(new Paragraph(signedFriend.getPhone(), normalFont));
                    }

                    if (signedFriend.getWeb() != "") {
                        person.add(new Paragraph(" ", normalFont));
                        person.add(new Paragraph(web, boldFont));
                        person.add(new Paragraph(signedFriend.getWeb(), normalFont));
                    }

                    if (signedFriend.getEmail() != "") {
                        person.add(new Paragraph(" ", normalFont));
                        person.add(new Paragraph(email, boldFont));
                        person.add(new Paragraph(signedFriend.getEmail(), normalFont));
                    }

                    document.add(person);
                    document.newPage();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
