package com.example.gbpiet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbpiet.authentication.LoginActivity;
import com.example.gbpiet.ui.Ebook.EbookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationMenu;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int checkedItem;
    private String selected;
    private FirebaseAuth auth ;
    private final String CHECKEDITEM = "checked_item";




  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseMessaging.getInstance().subscribeToTopic("notification");
        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();




        auth = FirebaseAuth.getInstance();



        switch (getCheckedItem()){
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case  1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;

        }
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this,R.id.frameLayout);
        bottomNavigationView.setItemIconTintList(null);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationMenu = findViewById(R.id.navigationMenu);
        navigationMenu.setItemIconTintList(null);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        navigationMenu.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        if (!isConnected(MainActivity.this)){
            buildDialoag(MainActivity.this).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    public Boolean isConnected(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()){
            android.net.NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return  true;
            else return false;
        }else
            return false;
    }
     public  AlertDialog.Builder buildDialoag(Context context){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please check the Internet Connectivity");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
            }
        });
        return builder;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        if (item.getItemId() == R.id.logout) {
            auth.signOut();
            openLogin();

        }
        return true;
    }

    private void openLogin() {
        startActivity(new Intent(MainActivity.this , LoginActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() == null) {
            openLogin();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigationDev:
                startActivity(new Intent(this, DeveloperActivity.class));
                Toast.makeText(this, "Developer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationAboutUs:
                break;
            case R.id.navigationEbook:
                startActivity(new Intent(this, EbookActivity.class));
                Toast.makeText(this, "E-Books", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationVideo:
                Intent Youtube = new Intent(Intent.ACTION_VIEW);
                Youtube.setData(Uri.parse("https://www.youtube.com"));
                startActivity(Youtube);
                break;
            case R.id.navigationWebsite:
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse("http://gbpec.ac.in/index.php"));
                startActivity(webIntent);
                break;
            case R.id.navigationRating:
                Toast.makeText(this, "This App is Not In PlayStore Yet", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationShare:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationColor:
                showDialog();
                break;
        }

        return true;
    }

    private void goToUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }


    private void showDialog(){
        String[] themes = this.getResources().getStringArray(R.array.theme);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Select Theme");
        builder.setSingleChoiceItems(R.array.theme, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                selected = themes[i];
                checkedItem = i;

            }
        });



        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (selected ==null){
                    selected = themes[i];
                    checkedItem = i;
                }
                switch (selected){
                    case "System Default":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case "Dark":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    case "Light":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;

                }
                setCheckedItem(checkedItem);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private int getCheckedItem(){
        return sharedPreferences.getInt(CHECKEDITEM,0);
    }

    private void setCheckedItem(int i){
        editor.putInt(CHECKEDITEM,i);
        editor.apply();

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();

    }
}