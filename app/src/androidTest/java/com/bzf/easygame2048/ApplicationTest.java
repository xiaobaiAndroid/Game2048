package com.bzf.easygame2048;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.bzf.easygame2048.file.FileUtils;
import com.bzf.easygame2048.commonutils.LogTool;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public ApplicationTest() {
        super(Application.class);
    }

    public void testFile(){
        String content = null;
        try {
            content = FileUtils.readFile(GameApplication.getIns(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogTool.i("bzf",content);
    }
}