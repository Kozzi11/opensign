package org.openoces.opensign.pdf.validator;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This is a test case for testing the PDF Validator engine.
 *
 * @author: Michael Martinsen (mmart), Openminds
 */
public class ValidatorTestCase {
    public static final String PATH_TO_TEST_RESOURCES =
        "src" + File.separator +
        "test" + File.separator +
        "resources" + File.separator;

    private PDFValidator subject;

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Before
    public void setup() throws Exception {
        subject = PDFValidatorFactory.getInstance().getPDFValidator("200");
    }

    @After
    public void tearDown() throws Exception {
        subject = null;
    }

    public void apiExampleWithFastFail() throws Exception {
        // First access the validator implementation
        PDFValidator validator =
                PDFValidatorFactory.getInstance().getPDFValidator("2.0.0");

        // Now point out a file to validate
        File fileToValidate = new File("my-pdf-file.pdf");

        // Validate the file using fast-fail option
        try {
            validator.readPDF(fileToValidate, true);
        } catch (Exception e) {
            // Validation failed - handle error
        }
    }

    public void apiExampleWithoutFastFail() throws Exception {
        // First access the validator implementation
        PDFValidator validator =
                PDFValidatorFactory.getInstance().getPDFValidator("2.0.0");

        List<String> supportedVersions = PDFValidatorFactory.getAllVersions();

        // Now point out a file to validate
        File fileToValidate = new File("my-pdf-file.pdf");

        // Validate the file using fast-fail option
        try {
            validator.readPDF(fileToValidate, false);
        } catch (Exception e) {
            // Validation failed - handle error(s)

            // Get the error handler
            ErrorHandler errorHandler = validator.getErrorHandler();

            // Get the list of errors (PDFEXception objects)
            List<PDFException> errors = errorHandler.getErrorsFound();
        }
    }

    /*invalid files tests below this line*/
    /*--------------------------------------------------------------------------------*/

    @Test
    public void testMissingFile() throws Exception {
        File nonExistingFile = new File("this-file-does-not-exist.pdf");    // No real file

       try {
           subject.readPDF(nonExistingFile, true);
       }  catch (Exception e) {
           // OK - this is expected...

       }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertFalse("Errors are reported via error handler although fast fail is set.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 0, errorHandler.getErrorsFound().size());
    }

    @Ignore
    @Test
    public void testInvalidFile_0() throws Exception {
        File corruptFile = new File(getPathToInvalidDir() + "PDF-K0_Corrupt.pdf");
        byte[] fileContents = new byte[0];
        try {
            fileContents = getBytesFromFile(corruptFile);
            subject.readPDF(fileContents, true);
            fail("Expected PDFException was thrown.");
        } catch (Exception e) {
            // OK - this is expected...
        }

        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertTrue("No errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 1, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testInvalidFileWithFastFail_2() throws Exception {
        File invalidFile = new File(getPathToInvalidDir() + "PDF-K2_PDFSigQFormalRep.pdf");
        try {
            byte[] fileContents = new byte[0];
            try {
                fileContents = getBytesFromFile(invalidFile);
            } catch (Exception e) {  }

            subject.readPDF(fileContents, true);
            fail("Expected PDFException was thrown.");
        } catch (Exception e) {
            // OK - this is expected...
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertFalse("Errors are reported via error handler although fast fail is set.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 0, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testInvalidFileWithoutFastFail_2() throws Exception {
        File invalidFile = new File(getPathToInvalidDir() + "PDF-K2_PDFSigQFormalRep.pdf");
        try {
            byte[] fileContents = new byte[0];
            try {
                fileContents = getBytesFromFile(invalidFile);
            } catch (Exception e) {  }
            subject.readPDF(fileContents, false);
            fail("Expected PDFException was not thrown.");
        } catch (Exception e) {
            // OK - this is expected...
        //    e.printStackTrace();
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertTrue("No errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 1, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testInvalidFile_4() throws Exception {
        File corruptFile = new File(getPathToInvalidDir() + "PDF-K4_launch-action-cmd.pdf");
        try {
            byte[] fileContents = new byte[0];
            try {
                fileContents = getBytesFromFile(corruptFile);
            } catch (Exception e) {  }
            subject.readPDF(fileContents, false);
            fail("Expected PDFException was not thrown.");
        } catch (Exception e) {
            // OK - this is expected...
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertTrue("No errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 1, errorHandler.getErrorsFound().size());
    }


    @Test
    public void testInvalidFileWithoutFastFail_5() throws Exception {
        File invalidFile = new File(getPathToInvalidDir() + "PDF-invalid_keywords.pdf");
        try {
            byte[] fileContents = new byte[0];
            try {
                fileContents = getBytesFromFile(invalidFile);
            } catch (Exception e) {  }
            subject.readPDF(fileContents, false);
            fail("Expected PDFException was not thrown.");
        } catch (Exception e) {
            // OK - this is expected...
            //    e.printStackTrace();
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertTrue("No errors found.", errorHandler.errorsFound());
        //invalid keywords
        assertEquals("Wrong number of errors returned.", 1, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testDataOutsideObject() throws Exception {
        File invalidFile = new File(getPathToInvalidDir() + "PDF_data_outside_objects.pdf");
        try {
            byte[] fileContents = new byte[0];
            try {
                fileContents = getBytesFromFile(invalidFile);
            } catch (Exception e) {  }
            subject.readPDF(fileContents, false);
            fail("Expected PDFException was not thrown.");
        } catch (Exception e) {
            // OK - this is expected...
            //    e.printStackTrace();
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertTrue("No errors found.", errorHandler.errorsFound());

        /*Object is corrupt. Found endobject outside object
        PDF document contains elements that are not closed Header, objects, stream, trailer and crossref must be closed for a valid document
        Document does not comply to whitelist. [/PANTONE Ref Blue CV, /EmbeddedDocument] does not exists on whitelist
        */
        assertEquals("Wrong number of errors returned.", 3, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testNot_a_PDF_File() throws Exception {
        File invalidFile = new File(getPathToInvalidDir() + "Txt-text-file.txt");
        try {
            byte[] fileContents = new byte[0];
            try {
                fileContents = getBytesFromFile(invalidFile);
            } catch (Exception e) {  }
            subject.readPDF(fileContents, false);
            fail("Expected PDFException was not thrown.");
        } catch (Exception e) {
            // OK - this is expected...
            //    e.printStackTrace();
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertTrue("No errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 1, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testLarge_file_when_uncompressed() throws Exception {
        File invalidFile = new File(getPathToInvalidDir() + "PDF_Compressed_data_685.pdf");
        try {
            byte[] fileContents = new byte[0];
            try {
                fileContents = getBytesFromFile(invalidFile);
            } catch (Exception e) {  }
            subject.readPDF(fileContents, false);
            fail("Expected PDFException was not thrown.");
        } catch (Exception e) {
            // OK - this is expected...
            //    e.printStackTrace();
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertTrue("No errors found.", errorHandler.errorsFound());
        /*Out of memory -> Document could not be parsed due to its size*/
        assertEquals("Wrong number of errors returned.", 1, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testEncryptedFile() throws Exception {
        File invalidFile = new File(getPathToInvalidDir() + "PDF_encrypted_open.pdf");
        try {
            byte[] fileContents = new byte[0];
            try {
                fileContents = getBytesFromFile(invalidFile);
            } catch (Exception e) {  }
            subject.readPDF(fileContents, false);
            fail("Expected PDFException was not thrown.");
        } catch (Exception e) {
            // OK - this is expected...
            //    e.printStackTrace();
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertTrue("No errors found.", errorHandler.errorsFound());
        /*Encrypt is not supported -> Encrypted documents are not supportedCould not parse streams -> [invalid compression]*/
        assertEquals("Wrong number of errors returned.", 2, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testEncryptedFile_open_edit() throws Exception {
        File invalidFile = new File(getPathToInvalidDir() + "PDF_encrypted_open_edit.pdf");
        try {
            byte[] fileContents = new byte[0];
            try {
                fileContents = getBytesFromFile(invalidFile);
            } catch (Exception e) {  }
            subject.readPDF(fileContents, false);
            fail("Expected PDFException was not thrown.");
        } catch (Exception e) {
            // OK - this is expected...
            //    e.printStackTrace();
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertTrue("No errors found.", errorHandler.errorsFound());
        /*Encrypt is not supported -> Encrypted documents are not supportedCould not parse streams -> [invalid compression]*/
        assertEquals("Wrong number of errors returned.", 2, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testnonEmbedded_nonstandad_font() throws Exception {
        File invalidFile = new File(getPathToInvalidDir() + "PDF_nonembedded-nonstandard-font.pdf.pdf");
        try {
            byte[] fileContents = new byte[0];
            try {
                fileContents = getBytesFromFile(invalidFile);
            } catch (Exception e) {  }
            subject.readPDF(fileContents, false);
            fail("Expected PDFException was not thrown.");
        } catch (Exception e) {
            // OK - this is expected...
            //    e.printStackTrace();
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertTrue("No errors found.", errorHandler.errorsFound());
        /*Nonstandard font found -> Nonstandard font found but file did not include the fontDoNotExist*/
        assertEquals("Wrong number of errors returned.", 1, errorHandler.getErrorsFound().size());
    }


    /*Valid files test below this line*/
    /*----------------------------------------------------------------*/

    @Test
    public void testValidFile1() throws Exception {
        File file = new File(getPathToValidDir() + "PDF-3_example2- simpel tekst.pdf");

        assertNotNull("File is null.", file);
        try {
            subject.readPDF(file, true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected PDFException was thrown.");
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertFalse("Errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 0, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testValidFile_output_from_powerpoint() throws Exception {
        File file = new File(getPathToValidDir() + "PDF_output_from_Powerpoint.pdf");

        assertNotNull("File is null.", file);
        try {
            subject.readPDF(file, true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected PDFException was thrown.");
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertFalse("Errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 0, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testValidFile_output_from_OO_Impress() throws Exception {
        File file = new File(getPathToValidDir() + "PDF_output_from_OO_Impress.pdf");

        assertNotNull("File is null.", file);
        try {
            subject.readPDF(file, true);
        } catch (Exception e) {
            fail("Unexpected PDFException was thrown.");
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertFalse("Errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 0, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testValidFile_output_from_OO_writer() throws Exception {
        File file = new File(getPathToValidDir() + "PDF_output_from_OO_Writer.pdf");

        assertNotNull("File is null.", file);
        try {
            subject.readPDF(file, true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected PDFException was thrown.");
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertFalse("Errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 0, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testValidFile_output_from_Word() throws Exception {
        File file = new File(getPathToValidDir() + "PDF_output_from_Word.pdf");

        assertNotNull("File is null.", file);
        try {
            subject.readPDF(file, true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected PDFException was thrown.");
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertFalse("Errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 0, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testValidFile_6() throws Exception {
        File file = new File(getPathToValidDir() + "PDF_validates1.pdf");

        assertNotNull("File is null.", file);
        try {
            subject.readPDF(file, true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected PDFException was thrown.");
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertFalse("Errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 0, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testValidFile_7() throws Exception {
        File file = new File(getPathToValidDir() + "PDF_validates2.pdf");

        assertNotNull("File is null.", file);
        try {
            subject.readPDF(file, true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected PDFException was thrown.");
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertFalse("Errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 0, errorHandler.getErrorsFound().size());
    }

    @Test
    public void testValidFile_8() throws Exception {
        File file = new File(getPathToValidDir() + "PDF_validates3.pdf");

        assertNotNull("File is null.", file);
        try {
            subject.readPDF(file, true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected PDFException was thrown.");
        }
        ErrorHandler errorHandler = subject.getErrorHandler();
        assertNotNull("ErrorHandler is null.", errorHandler);
        assertFalse("Errors found.", errorHandler.errorsFound());
        assertEquals("Wrong number of errors returned.", 0, errorHandler.getErrorsFound().size());
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // Private helper methods
    ///////////////////////////////////////////////////////////////////////////////////////////

    private File createTemporaryFile(String fileName) throws Exception {
        return tmpFolder.newFile(fileName);
    }

    private String getPathToTestResources() {
        return PATH_TO_TEST_RESOURCES;
    }

    private String getPathToValidDir() {
        return PATH_TO_TEST_RESOURCES + "valid" + File.separator;
    }

    private String getPathToInvalidDir() {
        return PATH_TO_TEST_RESOURCES + "invalid" + File.separator;
    }

    private byte[] getBytesFromFile(File file) throws Exception {
        if (file == null || !file.exists()) {
            throw new Exception("File is invalid, file was not found");
        }
        if (!file.canRead()) {
            throw new Exception("File could not be read. Check file permissions");
        }
        InputStream is = new FileInputStream(file);

        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File to large");
        }

        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }

}
