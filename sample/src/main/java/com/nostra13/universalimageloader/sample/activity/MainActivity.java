package com.nostra13.universalimageloader.sample.activity;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.sample.Constants;
import com.nostra13.universalimageloader.sample.R;
import com.nostra13.universalimageloader.sample.fragment.ImagePagerFragment;
import com.nostra13.universalimageloader.utils.L;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected FrameLayout frameLayout;
    public static int choice;
    public static ChoiceNumber ch;
    ViewFlipper viewFlipper;
    private ProgressDialog spinner;

    private static final String TEST_FILE_NAME = "Universal Image Loader @#&=+-_.,!()~'%20.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        File testImageOnSdCard = new File("/mnt/sdcard", TEST_FILE_NAME);
        if (!testImageOnSdCard.exists()) {
            copyTestImageToSdCard(testImageOnSdCard);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(3000);
        spinner=new ProgressDialog(this) ;
        new LoadAll().execute();
    }



    @Override
    public void onBackPressed()
    {

       ImageLoader.getInstance().stop();
        super.onBackPressed();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
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
        if(id==R.id.item_clear_memory_cache)
        {
            ImageLoader.getInstance().clearMemoryCache();
            return true;
        }
        else if(id==R.id.item_clear_disc_cache)
        {
            ImageLoader.getInstance().clearDiskCache();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void inTheatreBTN(View view){
       //what to do when button pressed for the set menus

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        FragmentManager fragmentManager=getFragmentManager();
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        choice=id;

        try {
            if (id == R.id.desi) {
                Intent intent = new Intent(this, SimpleImageActivity.class);
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImagePagerFragment.INDEX);
                startActivity(intent);
            /*ComplexImageActivity comp=new ComplexImageActivity();
            Intent intent = new Intent(this, comp.getClass());
            startActivity(intent);*/
            } else if (id == R.id.indian) {
                Intent intent = new Intent(this, SimpleImageActivity.class);
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImagePagerFragment.INDEX);
                startActivity(intent);
             /*ComplexImageActivity comp=new ComplexImageActivity();
            Intent intent = new Intent(this, comp.getClass());
            startActivity(intent);*/
            } else if (id == R.id.chinese) {
                Intent intent = new Intent(this, SimpleImageActivity.class);
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImagePagerFragment.INDEX);
                startActivity(intent);
            /*ComplexImageActivity comp=new ComplexImageActivity();
            Intent intent = new Intent(this, comp.getClass());
            startActivity(intent);*/
            } else if (id == R.id.italian) {
                Intent intent = new Intent(this, SimpleImageActivity.class);
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImagePagerFragment.INDEX);
                startActivity(intent);
             /*ComplexImageActivity comp=new ComplexImageActivity();
            Intent intent = new Intent(this, comp.getClass());
            startActivity(intent);*/
            } else if (id == R.id.total) {
                Total_Bill comp = new Total_Bill();
                Intent intent = new Intent(this, comp.getClass());
                startActivity(intent);
            } else if (id == R.id.about) {
                About comp = new About();
                Intent intent = new Intent(this, comp.getClass());
                startActivity(intent);
            }
        }
        catch(Exception e)
        {
            AlertDialogueBuilder al=new AlertDialogueBuilder();
            al.DialogueBox(this,"No Internet Or Server Offline");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void copyTestImageToSdCard(final File testImageOnSdCard) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is = getAssets().open(TEST_FILE_NAME);
                    FileOutputStream fos = new FileOutputStream(testImageOnSdCard);
                    byte[] buffer = new byte[8192];
                    int read;
                    try {
                        while ((read = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, read);
                        }
                    } finally {
                        fos.flush();
                        fos.close();
                        is.close();
                    }
                } catch (IOException e) {
                    L.w("Can't copy test image onto SD card");
                }
            }
        }).start();
    }

    public static  int getChoice()
    {
        return choice;
    }
    public static  ChoiceNumber getChoiceClass()
    {
        return ch;
    }
    class LoadAll extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            spinner=ProgressDialog.show(MainActivity.this, "Please Wait !!!", "Loading...", true);
        }

        /**
         * getting All products from url
         */

        protected String doInBackground(String... args)
        {
            try
            {
                ch = new ChoiceNumber();
                ch.loadIndian();
                ch.loadChinese();
                ch.loadDesi();
                ch.loadItalian();


            }
            catch(Exception e)
            {

                AlertDialogueBuilder al=new AlertDialogueBuilder();
                al.DialogueBox(MainActivity.this,"No Internet or Server Down");
            }
            return  null;
        }

        @Override
        protected void onPostExecute(String s) {
            spinner.dismiss();
        }
    }
}
