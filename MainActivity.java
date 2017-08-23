package com.demo.neilhu.dialect;
import android.content.Intent;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        Toast.makeText(this, "版本1.4,位置:黄山市", Toast.LENGTH_LONG).show();

    }
    public void CoffeeOrder(View view){
        Intent intent =new Intent();
        intent.setClass(this,Coffee_aty.class);
        startActivity(intent);
    }
    public void addAccount(View view){
        Intent intent1 =new Intent();
        intent1.setClass(this,LoginActivity.class);
        startActivity(intent1);
    }

}
