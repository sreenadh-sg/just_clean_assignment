package com.assign.justclean.movie;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.assign.justclean.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MovieActivity extends AppCompatActivity implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);
        setContentView(R.layout.activity_movie);

        tabLayout= findViewById(R.id.tabs);
        setupTabs();

    }
    public void setupTabs(){
        tabLayout.addTab(tabLayout.newTab().setText("Popular"));
        tabLayout.addTab(tabLayout.newTab().setText("Top rated"));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming"));




        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tabLayout.getSelectedTabPosition() == 0){
                    //fetchPopularMovies();
                }else if(tabLayout.getSelectedTabPosition() == 1){
                    addFragment(1);
                }else {
                    //fetchUpcomingMovies();
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

    public void addFragment(int position){

           // Log.i(TAG, "onCreate: adding ImageSelectorFragment to MainActivity");

            // Add image selector fragment to the activity's container layout
            MoviesFragment movieFragment = new MoviesFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, movieFragment,
                    MoviesFragment.class.getName());
            Bundle arguments=new Bundle();
            arguments.putInt("SELECTED_TYPE",position);
            movieFragment.setArguments(arguments);

            // Commit the transaction
            fragmentTransaction.commit();
    }

   /* @Override
    public void fetchPopularMovies() {
        presenter.fetchMovie(0);

    }

    @Override
    public void fetchTopRatedMovies() {
        presenter.fetchMovie(1);
    }

    @Override
    public void fetchUpcomingMovies() {
        presenter.fetchMovie(2);

    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {

        if(movieResponse!=null){
            List<Movie> result=movieResponse.getResults();
            Log.v("Test","Size - "+result.size());
        }

    }

    @Override
    public void displayMovieError(Throwable throwable) {

    }
*/
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
