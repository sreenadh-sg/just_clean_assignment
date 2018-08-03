package com.assign.justclean.movie.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.assign.justclean.R;
import com.assign.justclean.model.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MovieSearchActivity extends AppCompatActivity implements SearchView
        .OnQueryTextListener,MovieSearchContract.MovieSearchView,SearchView.OnCloseListener{



    @Inject
    MovieSearchPresenter movieSearchPresenter;

    Toolbar toolbar;
    ProgressBar loadingProgressBar;
    TextView resultStatusTV;

    RecyclerView movieListRecyclerView;
    MoviesAdapter moviesAdapter;
    List<Movie> moviesList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_movie_search);
        initView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        moviesList=new ArrayList<>();



        moviesAdapter = new MoviesAdapter(this,moviesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        movieListRecyclerView.setLayoutManager(mLayoutManager);
        movieListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        movieListRecyclerView.setAdapter(moviesAdapter);


    }
    private void initView(){
        toolbar=findViewById(R.id.toolbar);
        loadingProgressBar=findViewById(R.id.loading_progress_bar);
        resultStatusTV=findViewById(R.id.result_status);
        movieListRecyclerView =  findViewById(R.id.movie_recyclerview);
    }

    @Override
    public void startProgressBar() {
        loadingProgressBar.setVisibility(View.VISIBLE);
        movieListRecyclerView.setVisibility(View.GONE);
        resultStatusTV.setVisibility(View.GONE);


    }

    @Override
    public void stopProgressBar() {
        loadingProgressBar.setVisibility(View.GONE);
        movieListRecyclerView.setVisibility(View.VISIBLE);
        //resultStatusTV.setVisibility(View.GONE);

    }

    @Override
    public void displayMovies(List<Movie> movies) {

        Log.v("Test","Size-"+movies.size());
        if(movies.isEmpty()){
            movieListRecyclerView.setVisibility(View.GONE);
            resultStatusTV.setVisibility(View.VISIBLE);
            resultStatusTV.setText(getString(R.string.no_data_found));
        } else {
            moviesList.clear();
            moviesList.addAll(movies);
            moviesAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void displayMovieError(Throwable throwable) {

        loadingProgressBar.setVisibility(View.GONE);
        movieListRecyclerView.setVisibility(View.GONE);
        resultStatusTV.setVisibility(View.VISIBLE);
        resultStatusTV.setText(getString(R.string.something_went_wrong));

    }

    @Override
    public boolean onClose() {
        if(moviesAdapter!=null){
            moviesList.clear();
            moviesAdapter.notifyDataSetChanged();
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.app_menu_search,menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setQueryHint("Movie Name");
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.onActionViewExpanded();

        searchView.performClick();
        searchView.requestFocus();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int selectedID = item.getItemId();

        if (selectedID == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        movieSearchPresenter.searchMovie(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if(newText.isEmpty()&&moviesAdapter!=null){
            moviesList.clear();
            moviesAdapter.notifyDataSetChanged();
        }

        return false;
    }
}

