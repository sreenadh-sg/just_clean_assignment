package com.assign.justclean.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.assign.justclean.R;
import com.assign.justclean.misc.AppConstants;
import com.assign.justclean.movie.search.MovieSearchActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MovieActivity extends AppCompatActivity implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    Toolbar toolbar;

    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);
        setContentView(R.layout.activity_movie);
        initView();
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setupTabs();

    }

    public void initView(){
        toolbar=findViewById(R.id.toolbar);
        tabLayout= findViewById(R.id.tabs);
    }
    public void setupTabs(){
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.popular_text)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.top_rated_text)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.upcoming_text)));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tabLayout.getSelectedTabPosition() == 0){
                    addFragment(0);
                }else if(tabLayout.getSelectedTabPosition() == 1){
                    addFragment(1);
                }else {
                    addFragment(2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        TabLayout.Tab tab = tabLayout.getTabAt(1);
        if(tab!=null)
            tab.select();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.movies_menu,menu);
        return true;
    }

    public void startSearchActivity(){
        Intent intent=new Intent(this, MovieSearchActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int selectedItemID=item.getItemId();

        if(selectedItemID==R.id.action_search){
            startSearchActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addFragment(int position){


            MoviesFragment movieFragment = new MoviesFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, movieFragment,
                    MoviesFragment.class.getName());
            Bundle arguments=new Bundle();
            arguments.putInt(AppConstants.SELECTED_MOVIE_LIST_TYPE,position);
            movieFragment.setArguments(arguments);

            // Commit the transaction
            fragmentTransaction.commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
