
package com.example.mid_exam_farah_shurrab;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    Button btnSetMode;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        btnSetMode = findViewById(R.id.btnSetMode);
        toolbar = findViewById(R.id.toolbar);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {

                    switch (position) {
                        case 0:
                            tab.setText("First");
                            break;

                        case 1:
                            tab.setText("Second");
                            break;

                        case 2:
                            tab.setText("Third");
                            break;
                    }

                }).attach();

        toolbar.setNavigationOnClickListener(v -> finish());

        btnSetMode.setOnClickListener(v -> showModeMenu(v));

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {

                    @Override
                    public void handleOnBackPressed() {

                        if (viewPager.getCurrentItem() != 0) {

                            viewPager.setCurrentItem(0, true);

                        } else {

                            finish();

                        }
                    }
                });
    }

    private void showModeMenu(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);

        popupMenu.getMenu().add("Light");
        popupMenu.getMenu().add("Night");
        popupMenu.getMenu().add("System");

        popupMenu.setOnMenuItemClickListener(item -> {

            String mode = item.getTitle().toString();

            switch (mode) {

                case "Light":
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_NO);
                    break;

                case "Night":
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_YES);
                    break;

                case "System":
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    break;
            }

            return true;
        });

        popupMenu.show();
    }
}
