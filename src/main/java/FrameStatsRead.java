import utilclass.LoggerUse;
import utilclass.PatternRule;

public class FrameStatsRead {

    private String frameStats;

    public FrameStatsRead(String frameStats) {
        this.frameStats = frameStats;
    }

    public String[] JankyStatsData(){
        PatternRule patternRule = new PatternRule();

        String Total_frames_rendered = patternRule.regStr(frameStats,"Total frames rendered: \\d+");
        String Total_frames_rendered_value = patternRule.regStr(Total_frames_rendered,"\\d+");

        String Janky_frames = patternRule.regStr(frameStats,"Janky frames: \\d+");
        String Janky_frames_value = patternRule.regStr(Janky_frames,"\\d+");

        String Number_Missed_Vsync = patternRule.regStr(frameStats,"Number Missed Vsync: \\d+");
        String Number_Missed_Vsync_value = patternRule.regStr(Number_Missed_Vsync,"\\d+");

        String Number_High_input_latency = patternRule.regStr(frameStats,"Number High input latency: \\d+");
        String Number_High_input_latency_value = patternRule.regStr(Number_High_input_latency,"\\d+");

        String Number_Slow_UI_thread = patternRule.regStr(frameStats,"Number Slow UI thread: \\d+");
        String Number_Slow_UI_thread_value = patternRule.regStr(Number_Slow_UI_thread,"\\d+");

        String Number_Slow_bitmap_uploads = patternRule.regStr(frameStats,"Number Slow bitmap uploads: \\d+");
        String Number_Slow_bitmap_uploads_value = patternRule.regStr(Number_Slow_bitmap_uploads,"\\d+");

        String Number_Slow_issue_draw_commands = patternRule.regStr(frameStats,"Number Slow issue draw commands: \\d+");
        String Number_Slow_issue_draw_commands_value = patternRule.regStr(Number_Slow_issue_draw_commands,"\\d+");

        String[] jankStats = {
                Total_frames_rendered_value,
                Janky_frames_value,
                Number_Missed_Vsync_value,
                Number_High_input_latency_value,
                Number_Slow_UI_thread_value,
                Number_Slow_bitmap_uploads_value,
                Number_Slow_issue_draw_commands_value
        };
//        LoggerUse.logobject.info("frame stats ：" +frameStats);
//        for(int i = 0;i<7;i++){
//            LoggerUse.logobject.info("value" +jankStats[i]);
//        }

        return jankStats;

    }
}
