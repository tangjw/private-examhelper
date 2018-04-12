package com.zonsim.examhelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zonsim.examhelper.entity.ExamQuestion;
import com.zonsim.examhelper.entity.ExamQuestionDao;
import com.zonsim.examhelper.recycler.BaseAdapter;
import com.zonsim.examhelper.recycler.VH;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    
    private RecyclerView mRecyclerView;
    private List<ExamQuestion> mExamQuestions;
    private ViewPager mViewPager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        
        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        
        navigationView.inflateHeaderView(R.layout.content_scrolling);
        
        /*.findViewById(R.id.lv_key)*/
        mRecyclerView = (RecyclerView) navigationView.getHeaderView(0);
        
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
    
    
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
        
        ExamQuestionDao dao = App.getDaoInstance().getExamQuestionDao();
        mExamQuestions = dao.queryBuilder().list();
        mRecyclerView.setAdapter(new KeyListAdapter(mExamQuestions));
        
        mViewPager.setAdapter(new QuestionPagerAdapter(getSupportFragmentManager(), mExamQuestions));
    }
    
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    
    private class KeyListAdapter extends BaseAdapter<ExamQuestion> {
        
        
        public KeyListAdapter(@NonNull List<ExamQuestion> list) {
            super(list);
        }
        
        @Override
        protected int getResLayout() {
            return R.layout.item_key;
        }
        
        @Override
        protected void onBaseBindViewHolder(VH holder, int position) {
            if (position == 0) {
                setTextString((TextView) holder.getView(R.id.tv_key), "\n近代史\n");
            } else {
                ExamQuestion question = mList.get(position - 1);
                setTextString((TextView) holder.getView(R.id.tv_key), position + ". " + question.getQKey());
            }
        }
    }
    
    
    private class QuestionPagerAdapter extends FragmentStatePagerAdapter {
        List<ExamQuestion> list;
        
        public QuestionPagerAdapter(FragmentManager fm, List<ExamQuestion> list) {
            super(fm);
            this.list = list;
        }
        
        @Override
        public Fragment getItem(int position) {
            return QuestionFragment.newInstance(list.get(position));
        }
        
        @Override
        public int getCount() {
            return list.size();
        }
    }
    
}
