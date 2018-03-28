import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

class WriteLogFiles {

    String filePath;
    String[] headers;

    public WriteLogFiles(String filePath, String[] headers) throws IOException {
        this.filePath = filePath;
        this.headers = headers;

    }

    public CSVPrinter initPrinter() throws IOException {

        final String NEW_LINE_SEPARATOR="\n";
        CSVFormat format= CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        Writer out = new FileWriter(this.filePath);
        CSVPrinter printer = new CSVPrinter(out, format);
        printer.printRecord(this.headers);
        return  printer;

    }


    public void doWrite(CSVPrinter printer, String[] str) throws IOException {
        printer.printRecord(str);
        printer.flush();
    }
}

