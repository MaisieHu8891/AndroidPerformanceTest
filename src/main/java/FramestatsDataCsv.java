import org.apache.commons.csv.CSVPrinter;
import utilclass.WriteLogFiles;

import java.util.HashMap;
import java.util.Map;

public class FramestatsDataCsv {

    private static String[] framesCsvHeader = {
            "AnimationStart-HandleInputStart(处理输入事件的时间(阀值2ms))",
            "PerformTraversalsStart-AnimationStart(运行动画花费的时间(阀值2ms))",
            "DrawStart-PerformTraversalsStart(布局和测量花费时间)",
            "SyncStart-DrawStart(所有无效视图调用View.draw()花费时间)",
            "SyncStart-SyncQueued(RenderThread渲染线程是否繁忙(阈值0.1ms))",
            "IssueDrawCommandsStart-SyncStart(超过阈值0.4ms代表已绘制了过多必须上传到GPU的新位图)",
            "FrameCompleted-IssueDrawCommandsStart(GPU工作绘图时间)",
            "FrameCompleted-IntendedVsync(UI框架渲染总时间(阀值16ms))",
            "FrameCompleted-NewestInputEvent(有新事件输入时的总时间（没有时该数据无效）)"
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


    public void FrameStats(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String[] argsdata = args[i].split(",");
            if (argsdata.length > 1) {
                Map<String, String> frameMap = new HashMap();

                frameMap.put("Flags", argsdata[0]);
                frameMap.put("IntendedVsync", argsdata[1]);
                frameMap.put("Vsync", argsdata[2]);
                frameMap.put("OldestInputEvent", argsdata[3]);
                frameMap.put("NewestInputEvent", argsdata[4]);
                frameMap.put("HandleInputStart", argsdata[5]);
                frameMap.put("AnimationStart", argsdata[6]);
                frameMap.put("PerformTraversalsStart", argsdata[7]);
                frameMap.put("DrawStart", argsdata[8]);
                frameMap.put("SyncQueued", argsdata[9]);
                frameMap.put("SyncStart", argsdata[10]);
                frameMap.put("IssueDrawCommandsStart", argsdata[11]);
                frameMap.put("SwapBuffers", argsdata[12]);
                frameMap.put("FrameCompleted", argsdata[13]);

                String[] frameStats = new String[9];
                //以下long类型数据相减，得值单位是纳秒，除以1000000转换为毫秒
                frameStats[0] = String.valueOf((Long.parseLong(frameMap.get("AnimationStart")) - Long.parseLong(frameMap.get("HandleInputStart"))) * 1.0 / 1000000);
                frameStats[1] = String.valueOf((Long.parseLong(frameMap.get("PerformTraversalsStart")) - Long.parseLong(frameMap.get("AnimationStart"))) * 1.0 / 1000000);
                frameStats[2] = String.valueOf((Long.parseLong(frameMap.get("DrawStart")) - Long.parseLong(frameMap.get("PerformTraversalsStart"))) * 1.0 / 1000000);
                frameStats[3] = String.valueOf((Long.parseLong(frameMap.get("SyncStart")) - Long.parseLong(frameMap.get("DrawStart"))) * 1.0 / 1000000);
                frameStats[4] = String.valueOf((Long.parseLong(frameMap.get("SyncStart")) - Long.parseLong(frameMap.get("SyncQueued"))) * 1.0 / 1000000);
                frameStats[5] = String.valueOf((Long.parseLong(frameMap.get("IssueDrawCommandsStart")) - Long.parseLong(frameMap.get("SyncStart"))) * 1.0 / 1000000);
                frameStats[6] = String.valueOf((Long.parseLong(frameMap.get("FrameCompleted")) - Long.parseLong(frameMap.get("IssueDrawCommandsStart"))) * 1.0 / 1000000);
                frameStats[7] = String.valueOf((Long.parseLong(frameMap.get("FrameCompleted")) - Long.parseLong(frameMap.get("IntendedVsync"))) * 1.0 / 1000000);
                //以下NewestInputEvent不一定存在，该值可能是纳米时间戳，不做额外处理
                frameStats[8] = String.valueOf(Long.parseLong(frameMap.get("FrameCompleted")) - Long.parseLong(frameMap.get("NewestInputEvent")));
                writeFramestatsfile.doWrite(writeFramestatsPrinter, frameStats);
            }
        }
    }

    public void JankyStats(String[] args) {
        writeJankstatsfile.doWrite(writeJankstatsPrinter, args);
    }

}
