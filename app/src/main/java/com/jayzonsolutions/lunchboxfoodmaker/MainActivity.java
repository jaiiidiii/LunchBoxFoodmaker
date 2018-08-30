package com.jayzonsolutions.lunchboxfoodmaker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
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
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jayzonsolutions.lunchboxfoodmaker.Fragments.MainFragment;
import com.jayzonsolutions.lunchboxfoodmaker.Fragments.OrdersFragment;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.OrderService;
import com.jayzonsolutions.lunchboxfoodmaker.model.Foodmaker;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
    ImageView selectImage;
    TextView foodmakerName;
    private static int RESULT_LOAD_IMAGE = 1;


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


        View  headerLayout = navigationView.getHeaderView(0);
        /* navigationView.inflateHeaderView(R.layout.nav_header);*/
        selectImage = headerLayout.findViewById(R.id.select_img);
        foodmakerName = headerLayout.findViewById(R.id.foodmaker_name);
        String strRatting = ((Constant.foodmaker.getAverageRatings() == null)?"":"("+Constant.foodmaker.getAverageRatings()+")" );
        foodmakerName.setText(""+Constant.foodmaker.getFoodmakerName()+" "+strRatting);
        /***
         * image working
         */
        if(Constant.foodmaker.getFoodmakerImagePath().length() > 21){
            String imagePath = Constant.foodmaker.getFoodmakerImagePath();;


            Glide.with(this).load(ApiUtils.BASE_URL+(imagePath.substring(21))).
                    apply(RequestOptions.
                            centerCropTransform().fitCenter().
                            diskCacheStrategy(DiskCacheStrategy.ALL)).
                    into(selectImage);
        }




        selectImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        /***
         * image working end
         */





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
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);

            // Setting Dialog Title
            alertDialog.setTitle("Logout...");

            // Setting Dialog Message
            alertDialog.setMessage("Are you sure you want logout?");

            // Setting Icon to Dialog
            // alertDialog.setIcon(R.drawable.delete);

            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    Constant.foodmaker = null;
                    Intent  in = new Intent(MainActivity.this,signin.class);
                    startActivity(in);
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to invoke NO event
                    dialog.cancel();
                }
            });

            // Showing Alert Message
            alertDialog.show();

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
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

        String[] permissions = {"android.permission.READ_EXTERNAL_STORAGE"};
        ActivityCompat.requestPermissions(this, permissions, 1); // without sdk version check
        if(isPermissionGranted()){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = this.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            String picturePath = null;
            if( cursor == null){
                picturePath =  selectedImage.getPath();
            }else{
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();
            }


            ImageView imageView = (ImageView) findViewById(R.id.select_img);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
           // customerService = ApiUtils.getCustomerService();
            File file = new File(picturePath);
               /* RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);
*/
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);

            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), requestFile);


          /*  if(file.exists()){
                customerService.uploadUserImage(1,body).enqueue(new Callback<ResponseBody>() {
                    // mAPIService.savePost(useremail.getText().toString(), userpass.getText().toString(),DeviceID).enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if(response.body() == null){
                        }else{
                        }



                        //
                    }
                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                    }
                });
            }*/



        }



    }


}
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // save file

                /*File imgFile = new  File("/storage/emulated/0/DCIM/Camera/IMG_20180812_172935709.jpg");

                if(imgFile.exists()){

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                   selectImage.setImageBitmap(myBitmap);

                }*/

            } else {
                Toast.makeText(getApplicationContext(), "PERMISSION_DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isPermissionGranted() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE")  == PackageManager.PERMISSION_GRANTED){
                Log.v("permission", "Permission is granted");
                return true;
            }


        }
        return false;
    }
}
