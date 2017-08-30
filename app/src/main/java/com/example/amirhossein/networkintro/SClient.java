package com.example.amirhossein.networkintro;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class SClient implements Runnable {

    private Handler handler;

    public SClient(Handler uihandler) {
        handler = uihandler;
    }


    @Override
    public void run() {
        try {
            URL myUrl = new URL("http://users.metropolia.fi/~amirn/abc.txt");
            URLConnection myConn = myUrl.openConnection();
            /*BufferedWriter out = new BufferedWriter(new
                    OutputStreamWriter(myConn.getOutputStream()));
            out.write("Hello world this is writing and reading using multiThread thingee :D");
            out.close();*/
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(myConn.getInputStream()));
            String str="";
            String inStr;
            while ((inStr = in.readLine()) != null) {
                str += inStr;
            }
            /*URL url = new URL("http://127.0.0.1:8080/AndroidTest/abc.txt");

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                // str is one line of text; readLine() strips the newline character(s)
            }
*/
            in.close();
            Message msg = handler.obtainMessage();
            msg.obj = str;
            msg.what = 0;
            handler.sendMessage(msg);
        } catch (Exception e) {
            Log.e("TCP", "C: Error", e);
        }

    }
}
