package com.source.serviceImpl;

import com.source.dao.CustomerDataRepository;
import com.source.model.CustomerData;
import com.source.service.LendService;
import com.source.service.TrackingIdService;
import com.source.utils.PawnShopUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class LendServiceImpl implements LendService {

    @Autowired
    TrackingIdService trackingIdService;




    private static final Logger logger = LoggerFactory.getLogger(LendServiceImpl.class);

    @Autowired
    CustomerDataRepository customerDataRepo;

    @Autowired
    PawnShopUtils utils;

    private final ModelMapper modelMapper;


    public LendServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<Resource> processRecord(Map<String, String> customerData) {
        ResponseEntity<Resource> res=null;

        boolean dbInsert;
        CustomerData customer = modelMapper.map(customerData,CustomerData.class);
        try{
            customerDataRepo.save(customer);
            dbInsert=true;
        }catch(Exception e){
            dbInsert=false;
            logger.info("Exception occurred while CustomerData insertion "+e);
        }

        if(dbInsert){
            logger.info("Customer Data Table insertion success");
            trackingIdService.updateTrackingId(customer.getTrackingId(),customer.getTrackingId()+1);
            res =generateCustomerReceipt(customer);

        }else{
            logger.info("Customer Data Table insertion Failed");

        }

        return res;
    }

    private ResponseEntity<Resource> generateCustomerReceipt(CustomerData customer) {
        ResponseEntity<Resource> res = null;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                // Draw a border around the page
                contentStream.setLineWidth(1); // Set border line width
                contentStream.moveTo(50, page.getMediaBox().getHeight() - 50); // Move to top-left corner
                contentStream.lineTo(page.getMediaBox().getWidth() - 50, page.getMediaBox().getHeight() - 50); // Line to top-right corner
                contentStream.lineTo(page.getMediaBox().getWidth() - 50, 50); // Line to bottom-right corner
                contentStream.lineTo(50, 50); // Line to bottom-left corner
                contentStream.lineTo(50, page.getMediaBox().getHeight() - 50); // Line back to top-left corner
                contentStream.stroke(); // Draw the lines

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                contentStream.beginText();
                contentStream.newLineAtOffset(150, 700);
                contentStream.showText("My Pawn Shop Material Receipt");
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 650);
                contentStream.showText("Dear " + customer.getName() + ",");
                contentStream.newLineAtOffset(10, -25);
                contentStream.showText("Please find the below details of your material");
                contentStream.newLineAtOffset(0, -25);
                contentStream.showText("Tracking Id                    :: " + customer.getTrackingId());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Name                            :: " + customer.getName());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Father/Mother Name     :: " + customer.getParentName());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Requested Date            :: " + customer.getRequestedDate());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Place                             :: " + customer.getPlace());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Material Nature             :: " + customer.getMaterialNature());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Material Type                :: " + customer.getMaterialType());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Weight (in grams)          :: " + customer.getWeightInGrams());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Amount given                :: " + customer.getAmountGiven());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Material Market Value   :: " + customer.getMaterialMarketValue());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Phone Number              :: " + customer.getPhoneNumber());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Comments                     :: " + customer.getComments());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Returned Date               :: " );
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Amount collected           :: " );
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Interest Amount             :: " );
                contentStream.newLineAtOffset(0, -20);


                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("This Letter has been generated by My Pawn Shop as you lend a item to us.");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Keep this letter confidential .Do not share this letter with unknown person.");
                contentStream.newLineAtOffset(0, -15);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.showText("Keep the Receipt safe. Please bring the letter while coming take away ");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("the material.");
                contentStream.endText();

                // Signature section
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(400, 170);
                contentStream.showText("");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Seal of My Pawn Shop");
                contentStream.newLineAtOffset(-320, 0);
                contentStream.showText("Signature of Customer");
                contentStream.newLineAtOffset(0, -15);
                contentStream.endText();

                // Draw a line below Regards section
                contentStream.setLineWidth(1);
                contentStream.moveTo(50, 50);
                contentStream.lineTo(page.getMediaBox().getWidth() - 50, 50);
                contentStream.stroke();

                // Footer
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(180, 100);
                contentStream.showText("                © My Pawn Shop");
                contentStream.newLineAtOffset(8, -15);
                contentStream.showText("Anna Nagar ,Chennai  600028.");
                contentStream.newLineAtOffset(53, -15);
                contentStream.showText("All rights reserved.");
                contentStream.endText();
            }

            String fileName = "My Pawn Shop_" + customer.getTrackingId() + ".pdf";
            String pdfFilePath = utils.getPdfFilePath();
            pdfFilePath = pdfFilePath + "\\" + fileName;
            document.save(pdfFilePath);
            logger.info("PDF generated successfully.");
            res = utils.downloadPDF(pdfFilePath,fileName);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
