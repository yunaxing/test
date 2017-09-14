package com.qkxmall.mall.define.defines;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Sunshine on 10/6/2015.
 */
public class ReadFile {
    public ReadFile() {

    }

    public String readFile(String fileName, Context context){
        String line = null;
        try {
            InputStream inputStream = new FileInputStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null){
                System.out.print(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

}
