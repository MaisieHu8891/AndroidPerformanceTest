package utilclass;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriteLogFiles {

    private  String filePath;
    private  String[] headers;

    public WriteLogFiles(String filePath, String[] headers)  {
        this.filePath = filePath;
        this.headers = headers;
    }

    public  CSVPrinter initPrinter() {
        final String NEW_LINE_SEPARATOR="\n";
        CSVFormat format= CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            Writer out = new FileWriter(filePath);
            CSVPrinter printer = new CSVPrinter(out, format);
            printer.printRecord(headers);
            return  printer;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("初始化csv文件失败");
            return null;
        }
    }

    public void doWrite(CSVPrinter printer, String[] str) {
        try{
            printer.printRecord(str);
            printer.flush();
        }catch (IOException e){
            System.out.println("写入csv文件失败");
        }

    }

}
