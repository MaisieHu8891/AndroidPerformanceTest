import utilclass.AppConfig;
import utilclass.LoggerUse;
import utilclass.PatternRule;

public class FrameStatsRead {

    private String frameStats;

    public FrameStatsRead(String frameStats) {
        this.frameStats = frameStats;
    }

    public String[] FrameData() {
        AppConfig appConfig = new AppConfig();
        appConfig.setMap();
        String activity = appConfig.getUiActivity();
        PatternRule patternRule = new PatternRule();
        //注意：下方正则中LiveRoomActivity为待测界面，根据需要修改！！！
        try {
            String[] FrameData = patternRule.regStr(frameStats, activity+"([\\s\\S]*)QueueBufferDuration,([\\s\\S]*)---PROFILEDATA---", 2).split("\\n");
            return FrameData;
        }catch (Exception e){
            System.out.println("待测界面消失/app当前不在待测界面");
            return null;
        }

    }


    public String[] JankyStatsData() {
        PatternRule patternRule = new PatternRule();
        String Total_frames_rendered = patternRule.regStr(frameStats, "Total frames rendered: (\\d+)", 1);
        String Janky_frames = patternRule.regStr(frameStats, "Janky frames: (\\d+)", 1);
        String Number_Missed_Vsync = patternRule.regStr(frameStats, "Number Missed Vsync: (\\d+)", 1);
        String Number_High_input_latency = patternRule.regStr(frameStats, "Number High input latency: (\\d+)", 1);
        String Number_Slow_UI_thread = patternRule.regStr(frameStats, "Number Slow UI thread: (\\d+)", 1);
        String Number_Slow_bitmap_uploads = patternRule.regStr(frameStats, "Number Slow bitmap uploads: (\\d+)", 1);
        String Number_Slow_issue_draw_commands = patternRule.regStr(frameStats, "Number Slow issue draw commands: (\\d+)", 1);
        String[] jankStats = {
                Total_frames_rendered,
                Janky_frames,
                Number_Missed_Vsync,
                Number_High_input_latency,
                Number_Slow_UI_thread,
                Number_Slow_bitmap_uploads,
                Number_Slow_issue_draw_commands
        };
//        LoggerUse.logobject.info("frame stats ：" +frameStats);
//        for (int i = 0; i < 7; i++) {
//            LoggerUse.logobject.info("value" + jankStats[i]);
//        }
        return jankStats;

    }
}
