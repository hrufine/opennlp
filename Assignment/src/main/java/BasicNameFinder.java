

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.util.*;


/**
 * Hello world OpenNLP!
 * 
 */
class openNlp{
	
	public void DoCalculation(String data_to_pass) throws InvalidFormatException, IOException{
		Logger log = LoggerFactory.getLogger(BasicNameFinder.class);

        String[] sentences = {data_to_pass};
        //sentences[0] = data_to_pass;
        // Load the model file downloaded from OpenNLP
        // http://opennlp.sourceforge.net/models-1.5/en-ner-person.bin
        TokenNameFinderModel model = new TokenNameFinderModel(new File(
                "./input/en_ner_person.bin"));

        // Create a NameFinder using the model
        NameFinderME finder = new NameFinderME(model);

        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;

        for (String sentence : sentences) {

            // Split the sentence into tokens
            String[] tokens = tokenizer.tokenize(sentence);

            // Find the names in the tokens and return Span objects
            Span[] nameSpans = finder.find(tokens);

            // Print the names extracted from the tokens using the Span data
            log.info(Arrays.toString(Span.spansToStrings(nameSpans, tokens)));
		
	}
}
}

class PDFWithText {
	void CreatePDF(){
	PDDocument doc = null;
    PDPage page = null;
    try{
        doc = new PDDocument();
        page = new PDPage();

        doc.addPage(page);
        PDFont font = PDType1Font.HELVETICA_BOLD;

        PDPageContentStream content = new PDPageContentStream(doc, page);
        content.beginText();
        content.setFont( font, 12 );
        content.moveTextPositionByAmount( 100, 700 );
        content.drawString("Hello from www.printmyfolders.com");

        content.endText();
        content.close();
       doc.save("./PDFWithText.pdf");
       doc.close();
 } catch (Exception e){
     System.out.println(e);
 }
	}
}
class PDFRead{
	String ReadPdf()
	{   String data = new String();
		PDDocument pd;
		 BufferedWriter wr;
		 try {
	         File input = new File("C:\\Users\\ji287396\\Downloads\\THE_AES_CORPORATION_Proxy_Statement_14_A.pdf");  // The PDF file from where you would like to extract
	         File output = new File("D:\\SampleText.txt"); // The text file where you are going to store the extracted data
	         pd = PDDocument.load(input);
	         System.out.println(pd.getNumberOfPages());
	         System.out.println(pd.isEncrypted());
	         pd.save("THE_AES_CORPORATION_Proxy_Statement_14_A.pdf"); // Creates a copy called "CopyOfInvoice.pdf"
	         PDFTextStripper stripper = new PDFTextStripper();
	         //System.out.println(stripper.getText(pd));
	         data=new String(stripper.getText(pd));
	         
	        stripper.setStartPage(3); //Start extracting from page 3
	         stripper.setEndPage(5); //Extract till page 5
	         wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
	         stripper.writeText(pd, wr);
	         
	         if (pd != null) {
	             pd.close();
	         }
	        // I use close() to flush the stream
	        wr.close();
	        
	 } catch (Exception e){
	         e.printStackTrace();
	        }
		 return data;
		 
	}
}
public class BasicNameFinder {
    public static void main(String[] args) throws InvalidFormatException, IOException  {
       String data_to_pass=null;
    	openNlp abcd=new openNlp();
    	PDFWithText pdf=new PDFWithText();
    	
    	pdf.CreatePDF();
    	System.out.println("1st call done");
    	
    	PDFRead pdfread=new PDFRead();
    	data_to_pass=pdfread.ReadPdf();
    	System.out.println("2nd call done");
    	
    	abcd.DoCalculation(data_to_pass);   
    	
    	System.out.println("3rd call done");
    	//System.out.println(data_to_pass);    	
    	System.out.println("Executed both");
        }
}
    
