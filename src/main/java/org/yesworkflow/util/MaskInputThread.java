package org.yesworkflow.util;

import java.io.*;

public class MaskInputThread implements Runnable
{
    private boolean stop;
    private PrintStream out = null;
    private String mask;


    public MaskInputThread(String prompt, PrintStream out)
    {
        this(prompt, "", out);
    }

    public MaskInputThread(String prompt, String mask, PrintStream out)
    {
        this.out = out;
        this.mask = mask;

        if(out == null)
            out = System.out;

        out.print(prompt);
    }

    public void run()
    {
        stop = false;
        while(!stop)
        {
            out.print("\010" + mask);
            try
            {
                Thread.currentThread().sleep(1);
            } catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void stopMasking()
    {
        stop = true;
    }
}
