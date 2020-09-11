package com.adetoyan.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LearnAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageButton button;
    /*private final LinkedList<String> mWordList = new LinkedList<>();
    private final LinkedList<String> iWordList = new LinkedList<>();*/
    private PagerAdapter mAdapter;

    /*private RecyclerView mRecyclerView;
    private LearnAdapter dAdapter;

    private RecyclerView iRecyclerView;
    private SkillAdapter iAdapter;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar();
        getSupportActionBar().setTitle("     LEADERBOARD");

        button = (ImageButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
                startActivity(intent);
            }
        });

        TabItem tabChats = findViewById (R.id.tabLearn);
        TabItem tabStatus = findViewById (R.id.tabSkill);
        tabLayout = (TabLayout) findViewById (R.id. tablayout_id );
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        viewPager = (ViewPager) findViewById (R.id. viewpager_id );
        mAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(mAdapter);

        // Setting a listener for Clicks.
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
                /*for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    TabLayout.Tab stab = tabLayout.getTabAt(i);
                }

                View stab = LayoutInflater.from(MainActivity.this).inflate(R.layout.learn_item, null);
                TextView tv = (TextView) View.findViewById(R.id.skill_name);*/

                /*if (tab.getPosition() == 0) {
                    for (int i = 0; i < 5; i++) {
                        mWordList.addLast("Learn " + i);
                    }

                    // Get a handle to the RecyclerView.
                    mRecyclerView = findViewById(R.id.learn_recyclerview);
// Create an adapter and supply the data to be displayed.
                    dAdapter = new LearnAdapter(MainActivity.this, mWordList);
// Connect the adapter with the RecyclerView.
                    mRecyclerView.setAdapter(dAdapter);
// Give the RecyclerView a default layout manager.
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }*/


                /*for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    TabLayout.Tab stab = tabLayout.getTabAt(i);
                    tab.setCustomView(mAdapter.getTabView(i));
                }*/
                    /*else {
                        for (int i = 0; i < 5; i++) {
                            iWordList.addLast("Skill " + i);
                        }

                        // Get a handle to the RecyclerView.
                        iRecyclerView = findViewById(R.id.skill_recyclerview);
// Create an adapter and supply the data to be displayed.
                        iAdapter = new SkillAdapter(MainActivity.this, iWordList);
// Connect the adapter with the RecyclerView.
                        iRecyclerView.setAdapter(iAdapter);
// Give the RecyclerView a default layout manager.
                        iRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

//                viewPager.setCurrentItem(tab.getPosition());
                }*/
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);


    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    public View getTabView(int position) {
        View tab = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) tab.findViewById(R.id.custom_text);
        tv.setText(tabTitles[position]);
        return tab;
    }*/
}
