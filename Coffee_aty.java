package com.demo.neilhu.dialect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
public class Coffee_aty extends AppCompatActivity {
    int quantity = 1;
    boolean needWhippedCream;
    boolean needChocolate;
    double price;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_order);

    }

    public void checkBoolean() {
        CheckBox whipped_cream = (CheckBox) findViewById(R.id.has_cream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.has_chocolate);
        needWhippedCream = whipped_cream.isChecked();
        needChocolate = chocolate.isChecked();
        calculatorPrice(needWhippedCream, needChocolate);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "Sorry,you can only order less than 100 cups of coffee.", Toast.LENGTH_LONG).show();
            return;
        }//这种写法方便后期维护跟交互
        quantity++;
        display(quantity);
        displayPrice(calculatorPrice(needWhippedCream, needChocolate));
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "Sorry,you can't order below 1 cup of coffee.", Toast.LENGTH_LONG).show();
            return;
        }
        quantity--;
        display(quantity);
        displayPrice(calculatorPrice(needWhippedCream, needChocolate));
    }

    private void display(int i) {
        TextView quantityTextView = (TextView) findViewById(R.id.order);
        quantityTextView.setText("" + i);
    }

    public double calculatorPrice(Boolean WhippedCream, Boolean Chocolate) {
        price = quantity * 12;
        if (needWhippedCream) {
            price += 2.0;
        }
        if (needChocolate) {
            price += 3.0;
        }
        return price;
    }


    private void displayPrice(double number) {
        checkBoolean();
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void displayMessage(View v) {
        checkBoolean();
        EditText editText = (EditText) findViewById(R.id.Name);
        userName = editText.getText().toString();
        //************************************************************************
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain"); //模拟器请使用这行
        //i.setType("message/rfc822") ; // 真机上使用这行
        i.putExtra(Intent.EXTRA_EMAIL,"18297712662");
        i.putExtra(Intent.EXTRA_SUBJECT, userName + "'s Coffee Order");
        i.putExtra(Intent.EXTRA_TEXT,"咖啡订单:" + "\n" + "客户名称:" + userName + "\n是否加生奶油?\t"
                + needWhippedCream + "\n是否加巧克力?\t" + needChocolate + "\n总计:\t¥" + price
                + "\n" + getString(R.string.thank_you));
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }


    }
}
