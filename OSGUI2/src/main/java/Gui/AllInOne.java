package Gui;

public class AllInOne {
    static String id;
    static int reachTime;
    static int cpuTime;
    static int needTime;
    static char state;

    public AllInOne() {
    }



    public AllInOne(String id, int reachTime, int cpuTime, int needTime, char state) {
        this.id = id;
        this.reachTime = reachTime;
        this.cpuTime = cpuTime;
        this.needTime = needTime;
        this.state = state;


    }
}
