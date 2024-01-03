package com.immortalidiot.wishes;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.immortalidiot.wishes.databinding.ActivityMainBinding;
import com.immortalidiot.wishes.ui.CallbackFragment;
import com.immortalidiot.wishes.ui.FragmentUtils;
import com.immortalidiot.wishes.ui.ModeSelectionFragment;

public class MainActivity extends AppCompatActivity implements CallbackFragment {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        changeFragment(new ModeSelectionFragment(), false);

        ViewGroup.LayoutParams layoutParams = binding.navView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        binding.navView.setLayoutParams(layoutParams);
    }

    @Override
    public void changeFragment(FragmentUtils fragment, boolean isReturning) {
        fragment.setCallbackFragment(this);
        if (isReturning) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_view, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_view, fragment)
                .commit();
    }
}