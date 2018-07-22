package com.jayzonsolutions.lunchboxfoodmaker;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;


public class CameraPhotoCapture extends AppCompatActivity {
    final ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.myCoolDialog);
    final Context context = this;
    ProgressDialog progress;
    String Result3 = "";
    Bitmap bitmap, resized, resized1;
    String imgString = "";
    byte[] sink;
    byte[] imgbyte;

    ImageView viewImage;
    Button b, b1, b2;

    public static Bitmap doBrightness(Bitmap src, int value) {
        // image size
        int width = src.getWidth();
        int height = src.getHeight();
        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        // color information
        int A, R, G, B;
        int pixel;

        // scan through all pixels
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);

                // increase/decrease each channel
                R += value;
                if (R > 255) {
                    R = 255;
                } else if (R < 0) {
                    R = 0;
                }

                G += value;
                if (G > 255) {
                    G = 255;
                } else if (G < 0) {
                    G = 0;
                }

                B += value;
                if (B > 255) {
                    B = 255;
                } else if (B < 0) {
                    B = 0;
                }

                // apply new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final image
        return bmOut;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_camera_photo_capture);


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //	GetData();

   /*     final GlobalVariables g = GlobalVariables.GetInstance();
        Temp_userkey = g.GetUserID();
        Temp_cmpkey = g.GetCmp_key();
    */    //Temp_cmpkey = "2165";

        b = (Button) findViewById(R.id.btnSelectPhoto);
        viewImage = (ImageView) findViewById(R.id.viewImage);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        b1 = (Button) findViewById(R.id.btnUpload);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgString.length() == 0) {
                    Builder alert = new Builder(ctw);
                    alert.setTitle("Error");
                    alert.setMessage("Please Select Picture First"
                            .toString());
                    alert.setPositiveButton("OK", null);
                    alert.show();
                } else {



                  //Call Retro Here//

                }
            }
        });


        b2 = (Button) findViewById(R.id.btnSkip);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Builder alert = new Builder(ctw);
                alert.setTitle("Alert");
                alert.setMessage("Do you really want to skip this ?"
                        .toString());
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(
                                CameraPhotoCapture.this,
                                LoginActivity.class);
                        startActivity(myIntent);
                        finish();

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                alert.show();

            }
        });

    }


    private void selectImage() {
        final ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.myCoolDialog);
        final CharSequence[] options = {"Take Photo", "Choose from Gallery",
                "Cancel"};

        Builder builder = new Builder(ctw);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment
                            .getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {

                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

//					resized = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.5), (int)(bitmap.getHeight()*0.5), true);

                    resized = Bitmap.createScaledBitmap(bitmap, 500, 500, true);
                    //	Bitmap greyimage =toGrayscale(resized);
                    Bitmap brightimage = doBrightness(resized, 50);

                    viewImage.setImageBitmap(brightimage);

                    String path = Environment
                            .getExternalStorageDirectory() + File.separator;

                    f.delete();

                    OutputStream outFile = null;

                    File file = new File(path, "temp.jpg");

                    try {

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();

                        brightimage.compress(Bitmap.CompressFormat.JPEG, 40, stream);
                        sink = stream.toByteArray();

                        imgString = Base64.encodeToString(sink,
                                Base64.NO_WRAP);


                        stream.flush();
                        stream.close();
                    } catch (FileNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "Failed to load ",
                                Toast.LENGTH_LONG).show();

                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Failed to load ",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath,
                        null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image ",
                        picturePath + "");

                resized1 = Bitmap.createScaledBitmap(thumbnail, 500, 500, true);
                Bitmap brightimage = doBrightness(resized1, 50);

                viewImage.setImageBitmap(brightimage);
                //added from here to convert
                File file = new File(picturePath, "");

                try {
//					outFile = new FileOutputStream(file);

//					bitmap.compress(Bitmap.CompressFormat.JPEG, 25, outFile);

                    // byte[] sink = ((ByteArrayOutputStream)
                    // outFile).toByteArray();

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    brightimage.compress(Bitmap.CompressFormat.JPEG, 40, stream);
                    byte[] sink = stream.toByteArray();

                    imgString = Base64.encodeToString(sink,
                            Base64.NO_WRAP);

                    //	Toast.makeText(getApplicationContext(), "Successfull",
                    //			Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Failed to Load",
                            Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                }


            }
        }
    }

    public Bitmap toGrayscale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

}