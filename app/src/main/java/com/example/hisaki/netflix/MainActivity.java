package com.example.hisaki.netflix;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.hisaki.netflix.enteties.Movie;
import com.example.hisaki.netflix.fragments.MovieContent;
import com.example.hisaki.netflix.fragments.SavedMovies;
import com.example.hisaki.netflix.fragments.SearchMovie;


import org.greenrobot.greendao.database.Database;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    SavedMovies saved_fragment;
    SearchMovie search_title_fragment;
    SearchMovie search_director_fragment;
    MovieContent movie_fragment;


    FragmentManager.OnBackStackChangedListener stackListener =
            new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            shouldDisplayHomeUp();
        }
    };

    public void shouldDisplayHomeUp(){
        //Enable Up button only  if there are entries in the back stack
        boolean canback = getSupportFragmentManager().getBackStackEntryCount()>0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //mDrawerToggle.setDrawerIndicatorEnabled(false);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState == null) {
            saved_fragment = new SavedMovies();

            search_director_fragment = new SearchMovie();
            search_director_fragment.setType(2);

            search_title_fragment = new SearchMovie();
            search_title_fragment.setType(1);
            movie_fragment = new MovieContent();
        }
        choseFragment(R.id.saved_movies);

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        choseFragment(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void choseFragment(int id){
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch(id){
            case R.id.saved_movies:
                transaction.replace(R.id.content,saved_fragment);
                break;
            case R.id.search_movie_title:
                transaction.replace(R.id.content,search_title_fragment);
                break;
            case R.id.search_movie_director:
                transaction.replace(R.id.content,search_director_fragment);
                break;
        }
        transaction.commit();
    }
}
