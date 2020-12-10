package Gui;

public class Progress implements Comparable<Progress>{
    String id;     //进程标识符
    int reachTime; //到达时间
    int cpuTime;   //运行时间
    int needTime;  //仍需时间
    char state;    //进程状态




    @Override
    public String toString() {
        System.out.println();
        return String.format("进程%s: %10d %7d %8d %7c\n", id, reachTime, cpuTime, needTime, state);
    }


    @Override
    public int compareTo( Progress b ) {
        //按reachTime从小到大排序
        return Float.compare(reachTime, b.reachTime);
    }


}