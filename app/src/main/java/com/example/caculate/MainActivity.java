package com.example.caculate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText userInput;
    private TextView showResult;
    private TextView tip;
    private Button enter;
    private CheckBox manCheck;
    private CheckBox womanCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText)findViewById(R.id.userInput);
        showResult = (TextView)findViewById(R.id.showResult);
        enter = (Button)findViewById(R.id.enter);
        tip = (TextView)findViewById(R.id.tip);
        manCheck = (CheckBox)findViewById(R.id.manCheck);
        womanCheck = (CheckBox)findViewById(R.id.womanCheck);

        enter.setOnClickListener(new View.OnClickListener() {//按钮点击事件
                @Override
                public void onClick(View v) {
               // String tempStr = userInput.getText().toString();
                try {
                    if(!userInput.getText().toString().trim().equals("")){
                        if(manCheck.isChecked()||womanCheck.isChecked()){
                            Double weight=Double.parseDouble(userInput.getText().toString());
                            StringBuffer sb=new StringBuffer();
                            sb.append("- - - -评估结果- - - -\n");
                            if(manCheck.isChecked()){
                                sb.append("男性标准身高：");
                                double result=manHeight(weight,"男");
                                sb.append((int)result+"(厘米)");
                            }
                            if(womanCheck.isChecked()){
                                sb.append("女性标准身高：");
                                double result=manHeight(weight,"女");
                                sb.append((int)result+"(厘米)");
                            }
                            showResult.setText(sb.toString());
                        }else {
                            Toast.makeText(getApplicationContext() , "请选择性别！" , Toast.LENGTH_SHORT).show();//吐司提示
                        }
                    }else {
                        Toast.makeText(getApplicationContext() , "请输入体重！" , Toast.LENGTH_SHORT).show();//吐司提示
                    }

                    //showResult.setText(tempStr);
                    tip.setTextColor(Color.BLACK);
                    Toast.makeText(getApplicationContext() , "计算成功" , Toast.LENGTH_SHORT).show();//吐司提示
                } catch (Exception e) {//用户输入数字意外的数值时抛出异常
                    tip.setTextColor(Color.RED);
                    Toast.makeText(getApplicationContext() , "输入有误" , Toast.LENGTH_SHORT).show();//吐司提示
                }
            }
        });
    }
    private double manHeight(double weight,String sex){
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.65;
        }else {
            height=158-(52-weight)/0.55;
        }
        return height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
