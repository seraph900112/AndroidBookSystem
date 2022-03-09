package com.example.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myproject.ViewModel.main_ViewModel;
import com.example.myproject.ViewModel.fragment_home_ViewModel;
import com.example.myproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    public static int UserId;
    private  SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());



        setContentView(binding.getRoot());
        Intent intent = getIntent();
        UserId = intent.getIntExtra("userId", 0);
        preferences = getSharedPreferences("USER", MODE_PRIVATE);
        if(UserId !=0) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("USERID", UserId);
            editor.apply();
        }

        main_ViewModel viewModel = ViewModelProviders.of(this).get(main_ViewModel.class);
        viewModel.setUserid(preferences.getInt("USERID",0));

        //BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottomNav);
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(
                R.id.navigation_information, R.id.navigation_home, R.id.navigation_me).build();
        NavController controller = Navigation.findNavController(this, R.id.main_fragment);
        //NavigationUI.setupActionBarWithNavController(this, controller, configuration);
        NavigationUI.setupWithNavController(bottomNavigationView, controller);
    }
}