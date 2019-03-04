package lectorpdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import java.io.IOException;
import java.util.Map;

public class inicio {
   
    public String[][] obtenData(String archivo) throws IOException {
        int i = 0;
        String[][] valores;
        valores = null;
        PdfReader reader;
        reader = new PdfReader(archivo);
        PdfDocument document;
        document = new PdfDocument(reader);
        PdfAcroForm acroForm = PdfAcroForm.getAcroForm(document, false);
        Map<String,PdfFormField> fields = acroForm.getFormFields();
        valores = new String[2][fields.keySet().size()];
        for (String fldName : fields.keySet()) {
            valores[0][i] = fldName;
            valores[1][i] = fields.get( fldName ).getValueAsString();
            i = i + 1;
        }
        document.close();
        reader.close();
        return valores;
    }
}
