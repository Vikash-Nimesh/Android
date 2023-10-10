package in.gurulabs.timecare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;

import in.gurulabs.timecare.R;
import in.gurulabs.timecare.dialog.AboutDialog;
import in.gurulabs.timecare.dialog.SettingDialog;
import in.gurulabs.timecare.fragment.Tasks;
import in.gurulabs.timecare.utils.PrefManager;
import in.gurulabs.timecare.utils.Tools;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    public static String PACKAGE_NAME;
    public static String currentVersion = Tools.currentVersion;
    public static String changeLog;
    MaterialButton createButton;
    ImageView aboutButton;
    ImageView settingButton;
    View lytParent;
    PrefManager prefManager;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        lytParent = findViewById(R.id.lytParent);


        PACKAGE_NAME = getApplicationContext().getPackageName();
        Tools.setSystemBarColor(this, R.color.colorPrimary);

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());

        viewPageAdapter.addFragment(new Tasks(), "");

        viewPager.setAdapter(viewPageAdapter);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);


        tabLayout.setupWithViewPager(viewPager);

        aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutDialog aboutDialog = new AboutDialog(MainActivity.this);
                aboutDialog.show(getSupportFragmentManager(),
                        "AboutDialog");
            }
        });


        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        settingButton = findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingDialog settingDialog = new SettingDialog(MainActivity.this);
                settingDialog.show(getSupportFragmentManager(),
                        "SettingDialog");
            }
        });


    }


    @Override
    public void onBackPressed() {
        doExitApp();
    }

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {

            Tools.showShortLengthSnack(lytParent, getString(R.string.press_again_exit_app));
            exitTime = System.currentTimeMillis();
        } else {
            finishAffinity();

        }
    }


    private class ViewPageAdapter extends FragmentPagerAdapter {

        private final ArrayList<Fragment> fragments;
        private final ArrayList<String> titles;

        ViewPageAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);

        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }


}