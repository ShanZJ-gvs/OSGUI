public class Progress implements Comparable<Progress>{
    int id;     //进程标识符
    int reachTime; //到达时间
    int cpuTime;   //运行时间
    int needTime;  //仍需时间
    char state;    //进程状态
    int queue;   //当前队列
    int closeTime;  //完成时间

    @Override
    public int compareTo( Progress b ) {
        //按reachTime从小到大排序
        return Float.compare(reachTime, b.reachTime);
    }

    public Progress(int id, int reachTime, int cpuTime, int needTime, char state, int queue, int closeTime) {
        this.id = id;
        this.reachTime = reachTime;
        this.cpuTime = cpuTime;
        this.needTime = needTime;
        this.state = state;
        this.queue = queue;
        this.closeTime = closeTime;
    }

    public Progress() {
    }

    public int getId() {
        return id;
    }

    public int getCloseTime() {
        return closeTime;
    }


}