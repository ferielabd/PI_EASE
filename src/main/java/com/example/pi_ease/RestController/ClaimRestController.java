package com.example.pi_ease.RestController;

import com.example.pi_ease.DAO.Entities.Claim;
import com.example.pi_ease.DAO.Entities.TypeClaim;
import com.example.pi_ease.DAO.Repositories.ClaimRepo;
import com.example.pi_ease.Services.Classes.ClaimService;
import com.example.pi_ease.Services.Classes.EmailService;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@RestController
@AllArgsConstructor
@RequestMapping("/claim")
public class ClaimRestController {
    private ClaimService claimService;
    private EmailService emailService;
    private ClaimRepo claimRepo;

    @Autowired
    ServletContext context;

    //------------------{ Affichage }------------------
    // http://localhost:8080/pidev/claim/afficher
    @GetMapping("/afficher")
    public List<Claim> afficher() {


        return claimService.selectAll();
    }


    //------------------{ Affichageavecid }------------------
    // http://localhost:8080/pidev/claim/afficheravecid/5
    @GetMapping("/afficheravecid/{id}")
    public Claim afficheravecid(@PathVariable("id") int id) {
        return claimService.selectById(id);
    }

    //------------------{ Ajouts simple }------------------
    // http://localhost:8080/pidev/claim/ajouter
    @PostMapping("/ajouter")
    public Claim ajoutClaim(/*@RequestParam("file") MultipartFile file,*/@RequestBody Claim cl) {
        return claimService.add(/*file,*/cl);
    }


    //------------------{ Ajouttous }------------------
    // http://localhost:8080/pidev/claim/ajoutertous
    @PostMapping("/ajoutertous")
    public List<Claim> ajouttousClaim(@RequestBody List<Claim> cl) {
        return claimService.addAll(cl);
    }

    //------------------{ Update }------------------
    // http://localhost:8080/pidev/claim/modifier
    @PutMapping("/modifier")
    public Claim UpdateClaim(@RequestBody Claim cl) {
        return claimService.edit(cl);


    }

    //------------------{ Supression }------------------
    // http://localhost:8080/pidev/claim/Supprimer/4
    @DeleteMapping("/Supprimer/{id}")
    public void DeleteClaim(@PathVariable("id") int id) {
        Claim cl = claimService.selectById(id);
        claimService.delete(cl);
    }


    //------------------{ Recherche par type claim }------------------
    //  http://localhost:8080/pidev/claim/search?ref=49.Sun Apr 02 23:47:19 EET 2023
    @GetMapping("/search")
    public List<Claim> searchClaims(@RequestParam("ref") String q) {
        return claimService.search(q);
    }


    //------------------{ Recherche par date }------------------
    //  http://localhost:8080/pidev/claim/search2?date=01-02-2023
    @GetMapping("/search2")
    public List<Claim> searchcl(@RequestParam("date") Date q) {
        return claimService.search2(q);
    }


    //------------------{ Export PDF }------------------
    //localhost:8080/pidev/claim/export/pdf
    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportToPdf() throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        com.lowagie.text.Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Claims List", fontTitle);
        Paragraph paragraph2 = new Paragraph("                        ", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        document.add(paragraph2);
        PdfPTable table = new PdfPTable(5);
        table.setWidths(new int[]{1, 1, 1, 1, 1});
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.gray);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("Date Claim", font));
        table.addCell(cell);
       // cell.setPhrase(new Phrase("Date trait", font));
       // table.addCell(cell);
        //cell.setPhrase(new Phrase("Attachment", font));
        //table.addCell(cell);
        cell.setPhrase(new Phrase("Desc Claim", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("User", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Credit", font));
        table.addCell(cell);cell.setPhrase(new Phrase("Transaction", font));
        table.addCell(cell);
        //table.addCell("User");


        List<Claim> myObjects = claimService.selectAll();
        for (Claim myObject : myObjects) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(myObject.getDateClaim());
            table.addCell(dateString);
            //String dateString1 = dateFormat.format(myObject.getDateTraite());
            //table.addCell(dateString1);
            //table.addCell(myObject.getAttachClaim());
            table.addCell(myObject.getDescClaim());
            table.addCell(myObject.getUserc().getFirst_name());
            table.addCell(myObject.getRefCR());
            table.addCell(myObject.getRefTR());
            // table.addCell(String.valueOf(myObject.getUser()));


        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "document.pdf");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    //localhost:8080/pidev/claim/message
    @PostMapping("/message")
    String sendEmailMessage() {
        this.emailService.sendSimpleEmail("ghassen.marzouk@esprit.tn", "etat de reclamation", "votre reclamation est traitée");
        return "Message sent";
    }


    //------------------{ taiter claim }------------------
    //http://localhost:8080/pidev/claim/modify/1
    @PutMapping ("/modify/{id}")
    public void changetraite(@PathVariable("id") Integer id ,
                             @RequestParam("traite") boolean traite)
    {
        claimService.modifier(id, traite);

    }


    //------------------{ ajout claim reel }------------------
    // http://localhost:8080/pidev/claim/add
    @PostMapping ("/add/{idt}/{idc}")
    public void add(@RequestParam("file") MultipartFile file,
                    @RequestParam("desc") String desc, @RequestParam("type") TypeClaim type, @PathVariable("idt") Integer idt, @PathVariable("idc") Integer idc)
    {
        claimService.ajouter(file,/*attach,*/ desc,type,idt, idc);

    }

    //------------------{ envoi de mail en cas de retard de traitement (automatique) }------------------
// check the claims that are not processed yet
    @Scheduled(cron = "0 0 9 * * *") // Run every day at 9:00 AM
    public  void compare () {

        List<Claim> myObjects = claimService.selectAll();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        List<String> list = new ArrayList<>();
        for (Claim claim : myObjects) {
            Date dateclaim = claim.getDateClaim();
            boolean traite =claim.getTraiteClaim() ;
            long diffInMillies = Math.abs(date.getTime() - dateclaim.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if ((diffInDays > 1) && (traite==false)) {

                list.add(claim.getRefclaim());


            }
        }
        if (list.isEmpty()){
            this.emailService.sendSimpleEmail("ghassen.marzouk@esprit.tn", "check claims","you have no claims to check today");

        }else
            this.emailService.sendSimpleEmail1("ghassen.marzouk@esprit.tn", "check claims",list);

    }
    @GetMapping("/nbclaim")
    public Float getnbclaims(){
        List<Claim> myObjects = claimService.selectAll();
        float count=0;
        for (Claim claim : myObjects) {
            count=count +1;

        }
        return count;

    }

    //http://localhost:8080/pidev/claim/nbclaim1
    @GetMapping("/nbclaim1")
    public String getpourcentagetraite(){
        List<Claim> myObjects = claimService.selectAll();
        float b = getnbclaims();
        float count=0;

        for (Claim claim : myObjects){

            if (claim.getTraiteClaim()==true){
                count =count+1;
            }

        }
        float result = (float) count / b;

        return String.valueOf(result*100)+"%";

    }

    //http://localhost:8080/pidev/claim/nbclaim2
    @GetMapping("/nbclaim2")
    public String getpourcentage(){
        List<Claim> myObjects = claimService.selectAll();
        float b = getnbclaims();
        float count=0;
        float count1=0;
        float count2=0;

        for (Claim claim : myObjects){

            if (claim.getType()==TypeClaim.Credit){
                count =count+1;
            } else if (claim.getType()==TypeClaim.Transaction) {
                count1=count1+1;

            }else {count2=count2+1;}

        }
        float result = (float) count / b;
        float result1 = (float) count1 / b;
        float result2 = (float) count2 / b;

        return "le pourcentage des reclamation de type Credit est "+String.valueOf(result*100)+"% / le pourcentage des reclamation de type Transaction est "+String.valueOf(result1*100)+"% / le pourcentage des reclamation de type Account est "+String.valueOf(result2*100)+"%";

    }
}
