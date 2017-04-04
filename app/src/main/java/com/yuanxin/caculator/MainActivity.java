package com.yuanxin.caculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_about:
            {
                Intent i =new Intent(MainActivity.this,AboutActivity.class);
                startActivity(i);
            }
            break;
        }
        return true;
    }

    public void OnClick(View view) {

        switch (view.getId())
        {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_multi:
            case R.id.btn_divide:
            {
                Button btn = (Button) view;
                String StrAdded = btn.getText().toString();
                TextView input = (TextView)findViewById(R.id.input_area);
                String StrContent = input.getText().toString();
                String StrNewContent =StrContent+StrAdded;
                input.setText(StrNewContent);
            }
            break;

            case R.id.btn_equal:
            {
                TextView input = (TextView)findViewById(R.id.input_area);
                String StrContent = input.getText().toString();
                try
                {
                    Symbols s=new Symbols();
                    double res=s.eval(StrContent);
                    TextView result=(TextView)findViewById(R.id.result_area);
                    result.setText(String.valueOf(res));

                    input.setText("");

                }
                catch (SyntaxException e)
                {
                    String str=MainActivity.this.getString(R.string.error_info);
                    Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
                }
            }
            break;

            case R.id.btn_clean:
            {
                TextView input = (TextView)findViewById(R.id.input_area);
                input.setText("");
                TextView result = (TextView)findViewById(R.id.result_area);
                result.setText("");
            }
            break;

            case R.id.btn_delete:
            {
                TextView input = (TextView)findViewById(R.id.input_area);
                String StrContent = input.getText().toString();
                if(StrContent.length()>0)
                {
                    StrContent = StrContent.substring(0, StrContent.length() - 1);
                    input.setText(StrContent);
                }
            }
            break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView input = (TextView)findViewById(R.id.input_area);
        String StrInput = input.getText().toString();

        outState.putString("key_input_area",StrInput);

        TextView result = (TextView)findViewById(R.id.result_area);
        String StrResult = result.getText().toString();

        outState.putString("key_result_area",StrResult);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView input = (TextView)findViewById(R.id.input_area);
        String StrInput = savedInstanceState.getString("key_input_area");
        input.setText(StrInput);

        TextView result = (TextView)findViewById(R.id.result_area);
        String StrResult = savedInstanceState.getString("key_result_area");
        result.setText(StrResult);
    }
}
