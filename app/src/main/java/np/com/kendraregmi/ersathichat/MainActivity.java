package np.com.kendraregmi.ersathichat;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;








public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private android.support.v7.widget.Toolbar mToolbar;

    private ViewPager mViewPager;

    private SectionsPageAdaptor mSectionsPageAdaptor;

    private TabLayout mTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mToolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("erSathi Chat");

        //tabs
        mViewPager=(ViewPager) findViewById(R.id.main_tabPager);
        mSectionsPageAdaptor=new SectionsPageAdaptor(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPageAdaptor);
        mTablayout= (TabLayout) findViewById(R.id.main_tab);
        mTablayout.setupWithViewPager(mViewPager);

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser== null){
            sentToStart();
        }
    }

    private void sentToStart() {
        Intent startIntent=new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.main_menu,menu);
         return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
         if(item.getItemId()== R.id.main_logout_button){
             FirebaseAuth.getInstance().signOut();
             sentToStart();
         }

         if(item.getItemId()== R.id.main_setting_button){
             Intent settingIntent= new Intent(MainActivity.this, SettingsActivity.class);
             startActivity(settingIntent);
         }

         if(item.getItemId()==R.id.main_all_button){
             Intent settingIntent=new Intent(MainActivity.this, UsersActivity.class);
             startActivity(settingIntent);
         }

         return true;
    }
}
