package sg.edu.rp.c347.p06_taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    int reqCode = 12345;
    Button btnAdd, btnCancel;
    EditText etName,etDesc,etTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        etName = (EditText)findViewById(R.id.etName);
        etDesc = (EditText)findViewById(R.id.etDesc);
        etTime = (EditText)findViewById(R.id.etTime);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();
                int time = Integer.parseInt((etTime.getText().toString()));
                DBHelper dbh = new DBHelper(SecondActivity.this);
                long row_affected = dbh.insertTask(name,desc,time);
                dbh.close();
                if (row_affected != -1){

                    Toast.makeText(SecondActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();

                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.SECOND, time);
                    Intent intent = new Intent(SecondActivity.this,
                            ScheduledNotificationReceiver.class);
                    intent.putExtra("name",name);
                    intent.putExtra("desc",desc);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(
                            SecondActivity.this, reqCode,
                            intent, PendingIntent.FLAG_CANCEL_CURRENT);

                    AlarmManager am = (AlarmManager)
                            getSystemService(Activity.ALARM_SERVICE);
                    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                            pendingIntent);

                    Intent i = new Intent();
                    setResult(RESULT_OK,i);
                    finish();

                }


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });


    }
}
