import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCmmandInfo {
    private String cmmand;

    public GetCmmandInfo(String cmmand) {
        this.cmmand = cmmand;
    }

    public GetCmmandInfo() {
    }

    public String[] appInfo() throws Exception {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(this.cmmand);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String t = df.format(new Date());// new Date()为获取当前系统时间
            String cmdinfo = sb.toString();
            String[] perinfo = {t, cmdinfo};
            return perinfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String regStr(String s, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            String rs = matcher.group();
            return rs;
        } else {
            return null;
        }
    }
}
