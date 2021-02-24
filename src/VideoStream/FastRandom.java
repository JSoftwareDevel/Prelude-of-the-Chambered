package com.unk.PoC.VideoStream;

import java.util.Random;

public class FastRandom {
     private static final int maxCount = 50000;
     private static double[]rndDouble = new double[maxCount];
     private static int[]rndInt = new int[maxCount];
     private static double[]rndGaussian = new double[maxCount];
     private static int[]rndIntUnsigned = new int[maxCount];

     private static int intCount = 0;
     private static int doubleCount = 0;
     private static int gaussianCount = 0;
     private static int intUnsignedCount = 0;

     static void Randomize(int r)
     {
         Random random = new Random(r);
         for(int i=0; i<maxCount; i++) {
             rndDouble[i]=random.nextDouble();
             rndInt[i]=random.nextInt();
             rndGaussian[i]=random.nextGaussian();
         }
         getIntUnsigned();
     }

     private static void getIntUnsigned()
     {
         for(int i=0; i<maxCount; i++) {
             int num = rndInt[i];
             if (num<0) num*=-1;
             rndIntUnsigned[i] = num;
         }
     }

     public static double nextGaussian()
     {
         gaussianCount = (++gaussianCount)%maxCount;
         return rndGaussian[gaussianCount];
     }

     public static double nextDouble()
     {
         doubleCount = (++doubleCount)%maxCount;
         return rndDouble[doubleCount];
     }

    public static int nextInt()
    {
        intCount = (++intCount)%maxCount;
        return rndInt[intCount];
    }

    public static int nextInt(int x)
    {
        intUnsignedCount = (++intUnsignedCount)%maxCount;
        return (  rndIntUnsigned[intUnsignedCount]%x );
    }
}
