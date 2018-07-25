package com.jayzonsolutions.lunchboxfoodmaker;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    final Context context = this;

    // Session Manager Class
    //SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


  //      session = new SessionManager(context);
//        HashMap<String, String> User = session.getUserDetails();
    //    final GlobalVariables g = GlobalVariables.GetInstance();
        //    TempUserName = g.GetUserName();
        //    TempUserID = g.GetUserID();

     /*   TempUserIsAdmin = User.get("IS_ADMIN");
        TempUserID = User.get("USER_ID");
        TempGroupID = User.get("GROUP_ID");
        TempUserName = User.get("USER_NAME");
        MAC = User.get("MAC_ADDRESS");
        g.setIsAdmin(TempUserIsAdmin);
        g.setUserID(TempUserID);*/

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
/*
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
*/

        /**
         * Logout button click event
         * */


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //To Show First fragment on creation
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new OrdersFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_orders);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_orders) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new OrdersFragment()).commit();

        } else if (id == R.id.nav_dishes) {

        } else if (id == R.id.nav_addDishes) {
            AddDish myFragment = new AddDish();
            myFragment.setFoodmakerDish(null);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AddDish()).commit();
        } else if (id == R.id.nav_manage) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DishesFragment()).commit();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {

            // Clear the session data
            // This will clear all session data and
            // redirect user to LoginActivity
         /*   session.logoutUser();

            Toast.makeText(MainActivity.this, "Logged Out ", Toast.LENGTH_SHORT).show();*/

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
