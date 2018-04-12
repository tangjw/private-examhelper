package com.zonsim.examhelper;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.zonsim.examhelper.entity.ExamQuestion;
import com.zonsim.examhelper.entity.ExamQuestionDao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private ViewPager mViewPager;
    private FrameLayout mFrameLayout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        
        mFrameLayout = (FrameLayout) findViewById(R.id.fl_loading);
        
        String dbPath = getDatabasePath("question1.db").getAbsolutePath();
        
        File file = new File(dbPath);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            boolean mkdirs = fileParent.mkdirs();
        }
        
        try {
            boolean newFile = file.createNewFile();
            FileOutputStream out = new FileOutputStream(dbPath);
            InputStream in = getAssets().open("question1.db");
            byte[] buffer = new byte[1024];
            int readBytes = 0;
            while ((readBytes = in.read(buffer)) != -1)
                out.write(buffer, 0, readBytes);
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void click1(View view) {
        
        ExamQuestionDao dao = App.getDaoInstance().getExamQuestionDao();
        List<ExamQuestion> list = dao.queryBuilder().list();
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getQKey());
        }
        
    }
}
