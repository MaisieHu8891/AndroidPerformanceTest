import org.apache.commons.csv.CSVPrinter;
import utilclass.WriteLogFiles;

import java.util.HashMap;
import java.util.Map;

public class FramestatsData {
    private static String[] framesCsvHeader = {
            "AnimationStart-HandleInputStart(处理输入事件的时间(阀值2ms))",
            "PerformTraversalsStart-AnimationStart(运行动画花费的时间(阀值2ms))",
            "DrawStart-PerformTraversalsStart(布局和测量花费时间)",
            "SyncStart-DrawStart(所有无效视图调用View.draw()花费时间)",
            "SyncStart-SyncQueued(RenderThread渲染线程是否繁忙(阈值0.1ms))",
            "IssueDrawCommandsStart-SyncStart(超过阈值0.4ms代表已绘制了过多必须上传到GPU的新位图)",
            "FrameCompleted-IssueDrawCommandsStart(GPU工作绘图时间)",
            "FrameCompleted-IntendedVsync(UI框架渲染总时间(阀值16ms))",
            "FrameCompleted-NewestInputEvent(视觉感官总时间)"
    };
    private static String[] jankCsvHeader = {
            "Total_frames_rendered",
            "Janky_frames",
            "Number_Missed_Vsync",
            "Number_High_input_latency",
            "Number_Slow_UI_thread",
            "Number_Slow_bitmap_uploads",
            "Number_Slow_issue_draw_commands"
    };
    private static WriteLogFiles writeFramestatsfile = new WriteLogFiles(".\\out\\log\\Framestats" + System.currentTimeMillis() + ".csv", framesCsvHeader);
    private static CSVPrinter writeFramestatsPrinter = writeFramestatsfile.initPrinter();
    private static WriteLogFiles writeJankstatsfile = new WriteLogFiles(".\\out\\log\\Jankstats" + System.currentTimeMillis() + ".csv", jankCsvHeader);
    private static CSVPrinter writeJankstatsPrinter = writeJankstatsfile.initPrinter();


    public void FrameStats(String[]args) {
        Map<String, String>frameMap = new HashMap();
        try {
            frameMap.put("Flags", args[0]);
            frameMap.put("IntendedVsync", args[1]);
            frameMap.put("Vsync", args[2]);
            frameMap.put("OldestInputEvent", args[3]);
            frameMap.put("NewestInputEvent", args[4]);
            frameMap.put("HandleInputStart", args[5]);
            frameMap.put("AnimationStart", args[6]);
            frameMap.put("PerformTraversalsStart", args[7]);
            frameMap.put("DrawStart", args[8]);
            frameMap.put("SyncQueued", args[9]);
            frameMap.put("SyncStart", args[10]);
            frameMap.put("IssueDrawCommandsStart", args[11]);
            frameMap.put("SwapBuffers", args[12]);
            frameMap.put("FrameCompleted", args[13]);
        } catch (Exception e) {
            System.out.print("获取最近120帧的数据PROFILEDATA异常");
        }
        //String to Long   ---》 Long to String
        String[] FrameStats = {};

        writeFramestatsfile.doWrite(writeFramestatsPrinter,FrameStats);


    }

    public void JankyStats(String[] args) {
        Map<String, String> jankMap = new HashMap();
        try {
            jankMap.put("Total_frames_rendered", args[0]);
            jankMap.put("Janky_frames", args[1]);
            jankMap.put("Number_Missed_Vsync", args[2]);
            jankMap.put("Number_High_input_latency", args[3]);
            jankMap.put("Number_Slow_UI_thread", args[4]);
            jankMap.put("Number_Slow_bitmap_uploads", args[5]);
            jankMap.put("Number_Slow_issue_draw_commands", args[6]);
        } catch (Exception e) {
            System.out.print("获取卡顿数据异常");
        }
        String[] JankStats = {};

        writeJankstatsfile.doWrite(writeJankstatsPrinter,JankStats);



    }


}
