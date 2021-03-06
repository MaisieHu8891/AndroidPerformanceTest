package utilclass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternRule {

    public PatternRule(){}

    public String regStr(String s, String reg, int i) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            String rs = matcher.group(i);
            return rs;
        } else {
            return null;
        }
    }
}
