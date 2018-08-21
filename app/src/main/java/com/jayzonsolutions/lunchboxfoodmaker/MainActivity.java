package com.jayzonsolutions.lunchboxfoodmaker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jayzonsolutions.lunchboxfoodmaker.Fragments.MainFragment;
import com.jayzonsolutions.lunchboxfoodmaker.Fragments.OrdersFragment;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.OrderService;
import com.jayzonsolutions.lunchboxfoodmaker.model.Foodmaker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
    final Context context = this;
    ToggleButton toggleButton;
    Switch aSwitch;
    FoodmakerService foodmakerServiceMain;
    com.rey.material.widget.Switch activation_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
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
                    new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_orders);
        }
        /*
         * toggle button working
                * start*/
/*        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        if(Constant.foodmaker != null){
            if(Constant.foodmaker.getFoodmakerActive() == 1){
                toggleButton.setChecked(true);
            }else{
                toggleButton.setChecked(false);
            }


        }



        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer foodmakerStatus = 1;
                if (isChecked) {
                     foodmakerStatus = 1;
                    Toast.makeText(getApplication(), "enabled", Toast.LENGTH_SHORT).show();
                } else {
                     foodmakerStatus = 2;
                    Toast.makeText(getApplication(), "disabled", Toast.LENGTH_SHORT).show();
                }

                foodmakerServiceMain = ApiUtils.getFoodmakerService();
                foodmakerServiceMain.updateFoodmakerStatus(Constant.foodmaker.getFoodmakerId(),foodmakerStatus).enqueue(new Callback<String>() { //email:foodmakernew@gmail.com pass:testtest
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });*/






        activation_switch =  findViewById(R.id.activation_switch);

      /*  if(Constant.foodmaker != null){
            if(Constant.foodmaker.getFoodmakerActive() == 1){
                activation_switch.setChecked(true);
            }else{
                activation_switch.setChecked(false);
            }


        }
*/


        activation_switch.setOnCheckedChangeListener(new com.rey.material.widget.Switch.OnCheckedChangeListener() {

            Integer foodmakerStatus = Constant.foodmaker.getFoodmakerActive();

            @Override
            public void onCheckedChanged(com.rey.material.widget.Switch view, boolean checked) {
                if(checked){
                     foodmakerStatus = 1;
                    Toast.makeText(getApplication(), "Active", Toast.LENGTH_SHORT).show();
                } else {
                     foodmakerStatus = 2;
                    Toast.makeText(getApplication(), "Un Active", Toast.LENGTH_SHORT).show();
                }
                foodmakerServiceMain = ApiUtils.getFoodmakerService();
                foodmakerServiceMain.updateFoodmakerStatus(1,foodmakerStatus).enqueue(new Callback<String>() { //email:foodmakernew@gmail.com pass:testtest
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Constant.foodmaker.setFoodmakerActive(foodmakerStatus);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });







        /**
         * toggle button working
         * end*/


        //switch
      //  setSwitch();



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
                    new MainFragment()).commit();

        } else if (id == R.id.nav_dishes) {

        } else if (id == R.id.nav_addDishes) {
            AddDish myFragment = new AddDish();
            myFragment.setFoodmakerDish(null);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AddDish()).commit();
        } else if (id == R.id.my_dishes) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DishesFragment()).commit();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            LayoutInflater inflater = getLayoutInflater();
            View convertView = (View) inflater.inflate(R.layout.custom, null);
            alertDialog.setView(convertView);
            alertDialog.setTitle("Logout");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(context, "Are you sure you wish to logout?", Toast.LENGTH_SHORT).show();

                  Constant.foodmaker = null;
                  Intent in = new Intent(MainActivity.this,signin.class);
                  startActivity(in);

                }
            });

            alertDialog.show();

            // Clear the session data
            // This will clear all session data and
            // redirect user to LoginActivity
         /*   session.logoutUser();

            Toast.makeText(MainActivity.this, "Logged Out ", Toast.LENGTH_SHORT).show();*/

        }else if(id == R.id.nav_profile){

            Intent in1 = new Intent(MainActivity.this,UserProfile.class);
            startActivity(in1);

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
    public void setSwitch()
    {
       final Switch simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);
*//*        simpleSwitch.setChecked(true);
        simpleSwitch.setTextOff("Off");
        simpleSwitch.setTextOn("ON");*//*


        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Integer foodmakerStatus;

                if(simpleSwitch.isChecked())
                {
                    foodmakerStatus = 1;
                    Toast.makeText(getApplication(), "enabled", Toast.LENGTH_SHORT).show();

                    foodmakerServiceMain = ApiUtils.getFoodmakerService();
                    foodmakerServiceMain.updateFoodmakerStatus(Constant.foodmaker.getFoodmakerId(),foodmakerStatus).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
                else
                {

                    foodmakerStatus = 2;
                    Toast.makeText(getApplication(), "disabled", Toast.LENGTH_SHORT).show();

                    foodmakerServiceMain = ApiUtils.getFoodmakerService();
                    foodmakerServiceMain.updateFoodmakerStatus(Constant.foodmaker.getFoodmakerId(),foodmakerStatus).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });













    }*/
}
