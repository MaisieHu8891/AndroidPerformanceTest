package utilclass;

import java.io.IOException;
import java.util.logging.*;


public class LoggerUse {
    public static Logger logobject = Logger.getLogger("logobject ");

    static class LogFormatter extends Formatter{
        @Override
        public String format(LogRecord record) {
            return "<"+record.getLevel()+">___"+record.getMessage()+"\n";
        }
    }

    static {
        Level  defaultlevel= Level.ALL;
        logobject.setLevel(defaultlevel);
        FileHandler ch = null;
        try {
            //ch = new FileHandler("D:\\coding\\IdeaProjects\\PandaTest\\AndroidPerformanceTest\\out\\log\\LogFile.txt");
            ch = new FileHandler("..\\AndroidPerformanceTest\\out\\log\\LogFile.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
        ch.setFormatter(new LogFormatter());
        logobject.addHandler(ch);
    }


}
