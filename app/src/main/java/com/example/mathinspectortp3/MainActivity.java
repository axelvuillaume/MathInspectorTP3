package com.example.mathinspectortp3;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mathinspectortp3.databinding.ActivityMainBinding;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {


    private MaterialToolbar tool_bar;
    private ActivityMainBinding binding;
    private int selectedNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tool_bar = binding.toolBar;
        setSupportActionBar(tool_bar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        binding.userGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFragment myFragment = new MyFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction  = manager.beginTransaction().add(R.id.fragment, myFragment, "myfragment");  // .addToBackStack(null);
                transaction.commit();
            }
        });



        for (int i = 1; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("button" + i, "id", getPackageName());

            final int selectedValue = i;
            binding.getRoot().findViewById(buttonId).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectedNumber = selectedValue; // Set the selected number
                    binding.modulo.setText("Modulo of " + selectedValue);
                    return true;
                }
            });
        }

        for (int i = 0; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("button" + i, "id", getPackageName());
            final int selectedValue = i;
            binding.getRoot().findViewById(buttonId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String currentInput = binding.input.getText().toString();
                    binding.input.setText(currentInput + selectedValue);
                }
            });
        }


        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.input.getText().toString().isEmpty())
                    binding.input.setText(binding.input.getText().toString().substring(0, binding.input.getText().length() - 1));
            }
        });

        binding.buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.input.setText("");
            }
        });

        binding.modulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputValue = binding.input.getText().toString();
                if (!inputValue.isEmpty() && selectedNumber != -1) {
                    int inputNumber = Integer.parseInt(inputValue);
                    int moduloResult = inputNumber % selectedNumber;
                    binding.result.setText("Result: " + moduloResult);
                    if (moduloResult == 0) {
                        Toast.makeText(MainActivity.this, "It's a multiple", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Please enter a number or a modulo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about) {
            binding.toolBar.setTitle("BOMBAA");
        }

        return super.onOptionsItemSelected(item);

    }

}