import utilclass.CmdAdb;

import java.io.IOException;

public class UnitTest {
    public static void main(String[] args) throws IOException {
        String[] cmds = {"adb", "shell", "dumpsys", "gfxinfo", "com.panda.videoliveplatform", "framestats"};
        new CmdAdb().executeCMDfile(cmds, "D:\\coding\\IdeaProjects\\PandaTest\\AndroidPerformanceTest\\out\\log\\","D:\\coding\\IdeaProjects\\PandaTest\\AndroidPerformanceTest\\out\\log\\framestats.txt");
    }
}