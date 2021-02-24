package com.unk.PoC.VideoStream;

public class DeltaTimer {
    private long last;
    private int index = 0;
    private long[] times = new long[30];

    public void Start()
    {
        last = System.currentTimeMillis();
    }

    public long Stop()
    {
        long tDelta =  System.currentTimeMillis() - last ;
        if (index>=30) index=0;
        times[index]=tDelta;
        index++;
        return tDelta;
    }

    public long Max()
    {
        int prom=0;
        for(int i=0; i<30;i++) {
            prom+= times[i];
        }
        prom=prom/30;
        return prom;
    }
}
