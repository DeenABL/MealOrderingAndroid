package com.netaq.mealordering.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.netaq.mealordering.R;
import com.netaq.mealordering.adapters.Cart.CartAdapter;
import com.netaq.mealordering.adapters.menuItem.MenuItemsAdapter;
import com.netaq.mealordering.classes.MenuItems;
import com.netaq.mealordering.fragments.CartFragment;
import com.netaq.mealordering.fragments.InformationFragment;
import com.netaq.mealordering.fragments.ItemsFragment;
import com.netaq.mealordering.fragments.MainMenuFragment;

import java.util.ArrayList;

import static com.netaq.mealordering.classes.MenuItems.getOrderList;
import static com.netaq.mealordering.classes.MenuItems.orderList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainMenuFragment.CallMenuFragmentInterface {

    RecyclerView.LayoutManager layoutManager,cartLayoutManager;
    NavigationView navigationView;
    DrawerLayout drawer;
    Toolbar toolbar;
    ImageButton cartBtn;
    TextView toolbarTtile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //  toolbar.setLogo(R.drawable.ic_menu_camera);

       toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
        cartBtn =(ImageButton) findViewById(R.id.cart_btn);
        navigationView=(NavigationView) findViewById(R.id.nav_view);
        toolbarTtile= (TextView) findViewById(R.id.toolbar_title);

        toolbarTtile.setText("Roessler's");


//        getCategories();

//        setupNavigationView();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        toggle.syncState();
//clickListeners for items of the category in navigation drawer
        navigationView.setNavigationItemSelectedListener(this);


    cartBtn.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View view) {

              Fragment fragment= new CartFragment();

                FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
               ft.replace(R.id.content_main,fragment);
                ft.commit();
              toolbarTtile.setText("My Cart");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    //    private void setupNavigationView() {
//
//        RecyclerView drawerList = (RecyclerView) findViewById(R.id.nav_recycler_view);
//        ArrayList<String> drawerItemList = new ArrayList<>();
//
//
//
//
//      //  NavgationItemsAdapter drawerAdapter = new NavgationItemsAdapter(drawerItemList);
//       // drawerList.setAdapter(drawerAdapter);
//
//    }

//    private void getCategories() {
//        Perfecto.with(this)
//                .fromUrl("https://api.myjson.com/bins/1e0wgd")
//                .ofTypeGet()
//                .connect(new OnNetworkRequest() {
//                    @Override
//                    public void onStart() {
//                    }
//
//                    @Override
//                    public void onSuccess(String response) {
//
//                        Gson gson= new Gson();
//
//                        NavigationItems[] convertedObj =gson.fromJson(response,NavigationItems[].class);
//                        String items =convertedObj[0].getCategory();
//
//                       NavgationItemsAdapter navgationItemsAdapter =new NavgationItemsAdapter(convertedObj);
//                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.nav_recycler_view);
//
//                        layoutManager = new LinearLayoutManager(MainActivity.this);
//                        recyclerView.setLayoutManager(layoutManager);
//
//                        recyclerView.setAdapter(navgationItemsAdapter);
//
//
//                       }
//
//                    @Override
//                    public void onFailure(int i, String s, String s1) {
//
//                    }
//                });
//    }


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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    private void onCategorySelected(int id) {
//            Fragment fragment =null;
//
//        switch (id)
//        {
//            case 1:
//                fragment= new ItemsFragment();
//                 break;
//
//        }
//        if(fragment!=null)
//        {
//            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.content_main,fragment);
//            ft.commit();
//        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//
//
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // get the order by item.getOrder()
        //Pass this order in the items fragment

        drawer.closeDrawer(Gravity.START);
        int order = item.getOrder();

        switch (order)
        {
            case 0:
              Fragment fragments =new InformationFragment();
                FragmentTransaction ftt =getSupportFragmentManager().beginTransaction();
                ftt.replace(R.id.content_main,fragments);
                ftt.commit();
                toolbarTtile.setText("Contact Information");

                break;
            case 1:
                toolbarTtile.setText("Starters");
                loadFragment(1);
                break;
            case 2:
                toolbarTtile.setText("Sandwiches");
                loadFragment(2);
                break;
            case 3:
                toolbarTtile.setText("Steaks");
                loadFragment(3);
                break;
            case 4:
                toolbarTtile.setText("Beverages");
                loadFragment(4);
                break;
            case 5:
                Fragment fragment =  new MainMenuFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main,fragment);
                ft.commit();

                toolbarTtile.setText("Main Menu");break;
        }

        return false;
    }

    void loadFragment(int index){
        //sending the fragment the categoryItem position
        Bundle bundle = new Bundle();

        bundle.putInt("ORDER",index);
        Fragment fragment= new ItemsFragment();
        fragment.setArguments(bundle);

        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main,fragment);
        ft.commit();
    }

    @Override
    public void CallMenuFragment(Integer position) {
        //sending the fragment the categoryItem position
        Bundle bundle = new Bundle();

        bundle.putInt("ORDER",position);
        Fragment fragment= new ItemsFragment();
        fragment.setArguments(bundle);

        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main,fragment);
        ft.commit();

    }
}
