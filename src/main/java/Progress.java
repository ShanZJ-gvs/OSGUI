public class Progress implements Comparable<Progress>{
    int id;     //进程标识符
    int reachTime; //到达时间
    int cpuTime;   //运行时间
    int needTime;  //仍需时间
    char state;    //进程状态
    int queue;   //当前队列
    int closeTime;  //完成时间





    @Override
    public String toString() {
        System.out.println();
        return String.format("进程%s: %10d %7d %8d %7c %7d %8d\n", id, reachTime, cpuTime, needTime, state,queue,closeTime);
    }


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

    public void setId(int id) {
        this.id = id;
    }

    public int getReachTime() {
        return reachTime;
    }

    public void setReachTime(int reachTime) {
        this.reachTime = reachTime;
    }

    public int getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(int cpuTime) {
        this.cpuTime = cpuTime;
    }

    public int getNeedTime() {
        return needTime;
    }

    public void setNeedTime(int needTime) {
        this.needTime = needTime;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }
}