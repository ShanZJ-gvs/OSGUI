import java.util.*;

/**
 * @Class MSFQS
 * @Description 多级反馈队列调度算法
 * @Author Naren
 * @Date 2020/5/30 10:46
 * @Version 1.0
 */
public class OS{
    /*三个队列*/
    static Queue<Progress> firstQueue = new LinkedList<>();
    static Queue<Progress> secondQueue = new LinkedList<>();
    static Queue<Progress> thirdQueue = new LinkedList<>();
    static int firstTime;  //第一队列cpu时间片
    static int secondTime; //第二队列cpu时间片
    static int thirdTime;  //第三队列cpu时间片
    static int proNum;     //进程数量
    static Progress[] pro;//获取到进程数组
    static Progress[] pro2 = new Progress[]{new Progress(),new Progress(),new Progress(),new Progress(),new Progress()};//进程数组2
    static int i = 0;
    static ArrayList<Progress> AOO;
    static MyComponent myComponent;


    /**
     * 重新绘画
     */
    static void reload() {
            myComponent.repaint();
    }

    /**
     * 进程调度
     */
    static void progressScheduling(Progress[] pro){
        int firstCpu = firstTime;
        int secondCpu = secondTime;
        int thirdCpu = thirdTime;
        int currentTime = 0;
        int num = 0;

        /*当有进程未运行时或进程队列不为空时，以每1时间片为单位*/
        while(num < proNum || !firstQueue.isEmpty() || !secondQueue.isEmpty() || !thirdQueue.isEmpty()){
            /*当前时刻有进程到达，则添加入第一队列*/
            while(num < proNum && pro[num].reachTime == currentTime)
                firstQueue.offer(pro[num++]);
            /*当前为队列1在运行进程*/
            if(!firstQueue.isEmpty()){
                if (secondQueue.peek() != null) secondQueue.peek().state = 'R';
                if (thirdQueue.peek() != null) thirdQueue.peek().state = 'R';
                //仍需时间：-1
                firstQueue.peek().needTime -= 1;
                //CPU剩余时间片：-1
                firstTime -= 1;
                //更新当前时间：+1
                currentTime++;
                //进程正在运行，状态：E.
                if(firstQueue.peek().needTime > 0){
                    firstQueue.peek().state = 'E';
                    //当前队列CPU时间片用完而进程仍未运行完时，进程出队，入次优先级队尾
                    if(firstTime == 0) {
                        firstQueue.peek().state = 'R';
                        AOO.add(new Progress(firstQueue.peek().id,firstQueue.peek().reachTime,firstQueue.peek().cpuTime,
                                firstQueue.peek().needTime,firstQueue.peek().state,1,currentTime));
                        pro2[firstQueue.peek().id-1].queue= 1;

                        myComponent.paintComponent(myComponent.getGraphics());
                        secondQueue.offer(firstQueue.poll());
                        firstTime = firstCpu;
                    }

                }
                //进程运行完毕，状态：F，记录完成时刻并出队
                else if(firstQueue.peek().needTime == 0){
                    firstQueue.peek().state = 'F';
                    System.out.printf("\n当前时刻：%d,此进程运行结束：\n",currentTime);
                    AOO.add(new Progress(firstQueue.peek().id,firstQueue.peek().reachTime,firstQueue.peek().cpuTime,
                            firstQueue.peek().needTime,firstQueue.peek().state,1,currentTime));
                    pro2[firstQueue.peek().id-1].queue= 1;
                    myComponent.paintComponent(myComponent.getGraphics());
                    System.out.println(firstQueue.peek());
                    Objects.requireNonNull(firstQueue.poll());
                    firstTime = firstCpu;
                }
            }
            /*当前为队列2在运行进程*/
            else if(!secondQueue.isEmpty()){
                if (thirdQueue.peek() != null) thirdQueue.peek().state = 'R';
                //仍需时间：-1
                secondQueue.peek().needTime -= 1;
                //CPU剩余时间片：-1
                secondTime -= 1;
                //更新当前时间：+1
                currentTime++;

                //进程运行完毕，状态：F，记录完成时刻并出队
                if(secondQueue.peek().needTime == 0){
                    secondTime = secondCpu;
                    secondQueue.peek().state = 'F';
                    System.out.printf("\n当前时刻：%d,此进程运行结束：\n",currentTime);
                    AOO.add(new Progress(secondQueue.peek().id,secondQueue.peek().reachTime,secondQueue.peek().cpuTime,
                            secondQueue.peek().needTime,secondQueue.peek().state,2,currentTime));
                    pro2[secondQueue.peek().id-1].queue= 2;
                    myComponent.paintComponent(myComponent.getGraphics());
                    System.out.println(secondQueue.peek());
                    Objects.requireNonNull(secondQueue.poll());
                }
                //进程正在运行，状态：E.
                else if(secondQueue.peek().needTime > 0){
                    secondQueue.peek().state = 'E';
                    //当前队列CPU时间片用完而进程仍未运行完时，进程出队，入次优先级队尾
                    if(secondTime == 0) {
                        secondQueue.peek().state = 'R';
                        AOO.add(new Progress(secondQueue.peek().id,secondQueue.peek().reachTime,secondQueue.peek().cpuTime,
                                secondQueue.peek().needTime,secondQueue.peek().state,2,currentTime));
                        pro2[secondQueue.peek().id-1].queue= 2;
                        myComponent.paintComponent(myComponent.getGraphics());
                        thirdQueue.offer(secondQueue.poll());
                        secondTime = secondCpu;
                    }
                }
            }
            /*当前为队列3在运行进程*/
            else if(!thirdQueue.isEmpty()){
                //仍需时间：-1
                thirdQueue.peek().needTime -= 1;
                //CPU剩余时间片：-1
                thirdTime -= 1;
                //更新当前时间：+1
                currentTime++;

                //进程正在运行，状态：R.
                if(thirdQueue.peek().needTime > 0){
                    thirdQueue.peek().state = 'E';
                    //当前队列CPU时间片用完而进程仍未运行完时，进程出队，入次优先级队尾
                    if(thirdTime == 0) {
                        thirdQueue.peek().state = 'R';
                        AOO.add(new Progress(thirdQueue.peek().id,thirdQueue.peek().reachTime,thirdQueue.peek().cpuTime,
                                thirdQueue.peek().needTime,thirdQueue.peek().state,3,currentTime));
                        pro2[thirdQueue.peek().id-1].queue= 3;
                        myComponent.paintComponent(myComponent.getGraphics());
                        thirdQueue.offer(thirdQueue.poll());
                        thirdTime = thirdCpu;
                    }
                }
                //进程运行完毕，状态：F，记录完成时刻并出队
                else{
                    firstTime = firstCpu;
                    thirdQueue.peek().state = 'F';
                    System.out.printf("\n当前时刻：%d,此进程运行结束：\n",currentTime);
                    AOO.add(new Progress(thirdQueue.peek().id,thirdQueue.peek().reachTime,thirdQueue.peek().cpuTime,
                            thirdQueue.peek().needTime,thirdQueue.peek().state,3,currentTime));
                    pro2[thirdQueue.peek().id-1].queue= 3;
                    myComponent.paintComponent(myComponent.getGraphics());
                    System.out.println(thirdQueue.peek());
                    Objects.requireNonNull(thirdQueue.poll());
                }
            }
            System.out.println("num:"+num);
        }

    }

    /**
     * 输入面板：获取到进程数组
     */
    static Progress[] operator(int a,int b){

        /*获取到进程数组*/
        pro[i] = new Progress();
        pro[i].id = i+1;
        pro[i].reachTime = a;
        pro[i].cpuTime = b;
        pro[i].needTime = pro[i].cpuTime;
        pro[i].state = 'R';
        pro[i].queue = 0;
        pro[i].closeTime = 0;
        i++;
        return pro;
    }


}

