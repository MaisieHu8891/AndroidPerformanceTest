import java.util.logging.*;


public class LoggerUse {
    private Level  defaultlevel= Level.ALL;
    private LoggerUse(){
        Logger logobject = Logger.getLogger("logobject ");
        logobject.setLevel(defaultlevel);
    }

    static class LogFormatter extends Formatter{
        @Override
        public String format(LogRecord record) {
            return "<"+record.getLevel()+">:"+record.getMessage()+"\n";
        }
    }

    public Logger getLogobject(Logger logobject){
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(new LogFormatter());
        logobject.addHandler(ch);
        return logobject;
    }
}
