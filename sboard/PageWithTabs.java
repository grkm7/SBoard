package com.info.sboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class PageWithTabs extends AppCompatActivity {
    private Toolbar pageToolbar;
    private TabLayout tablayout;
    private ViewPager2 viewpager2;


    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<String> fragmentTitleList = new ArrayList<>();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_with_tabs);

        tablayout=findViewById(R.id.tablayout);
        viewpager2=findViewById(R.id.viewpager2);

        fragmentList.add(new FragmentCatalogue());
        fragmentList.add(new FragmentFavList());

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(this);
        viewpager2.setAdapter(adapter);

        fragmentTitleList.add(getResources().getString(R.string.catalogue));
        fragmentTitleList.add(getResources().getString(R.string.favoriteList));

        new TabLayoutMediator(tablayout,viewpager2,(tab,position)->tab.setText(fragmentTitleList.get(position))).attach();

        pageToolbar=findViewById(R.id.pageToolbar);
        setSupportActionBar(pageToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_profile:
                Intent intent = new Intent(PageWithTabs.this,ProfileActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class MyViewPagerAdapter extends FragmentStateAdapter{

        public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }
}