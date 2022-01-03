package levanhieu.lvh.ungdungtratudien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    ImageView imgVocabulary;
    ViewPager2 mViewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVocabulary = findViewById(R.id.imgVocabulary);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id.toolbar);
        //mViewPager2 = findViewById(R.id.content);
        setSupportActionBar(toolbar);

        display(R.id.mnuHome);
//        FragmentManager fragmentManager = getSupportFragmentManager();
////include fragment via layout xml
//        DiscoveryFragment fragment = (DiscoveryFragment)fragmentManager.findFragmentById(R.id.fragmentDiscovery);
//        fragment.yourPublicMethod();


//        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);
//        mViewPager2.setAdapter(myViewPagerAdapter);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                display(item.getItemId());
//                int id = item.getItemId();
//                if(id == R.id.mnuHome){
//                    mViewPager2.setCurrentItem(0);
//                }else
//                if(id == R.id.mnuHistory){
//                    mViewPager2.setCurrentItem(1);
//
//                }else
//                if(id == R.id.mnuVocabulary){
//                    mViewPager2.setCurrentItem(2);
//
//                }else if(id == R.id.mnuDiscovery){
//                    mViewPager2.setCurrentItem(3);
//                }
                return true;
            }
        });

    }
    public void display(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.mnuHome:
                toolbar.setTitle("Home");
                fragment = new HomeFragment();
                break;
            case R.id.mnuHistory:
                toolbar.setTitle("");
                fragment = new HistoryFragment();
                break;
            case R.id.mnuVocabulary:
                toolbar.setTitle("Vocabulary");
                fragment = new VocabularyFragment();
                break;
            case R.id.mnuDiscovery:
                toolbar.setTitle("Discovery");
                fragment = new DiscoveryFragment();
                break;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.commit();
    }




}