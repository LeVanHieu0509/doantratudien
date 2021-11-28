package levanhieu.lvh.ungdungtratudien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        display(R.id.mnuHome);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                display(item.getItemId());
                return true;
            }
        });
    }
    void display(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.mnuHome:
                toolbar.setTitle("Home");
                fragment = new HomeFragment();
                break;
            case R.id.mnuHistory:
                toolbar.setTitle("History");
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