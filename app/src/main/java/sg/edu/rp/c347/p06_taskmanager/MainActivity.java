package sg.edu.rp.c347.p06_taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    TaskArrayAdapter adapter;
    ArrayList<Task> tasks;
    DBHelper dbh = new DBHelper(this);
    Button btnNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) this.findViewById(R.id.lv);

        tasks = dbh.getAllTask();
        adapter = new TaskArrayAdapter(this, R.layout.row, tasks);
        lv.setAdapter(adapter);

        btnNew = (Button)findViewById(R.id.btnNew);


        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        SecondActivity.class);
                startActivityForResult(i, 5);

            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Task t = tasks.get(position);

                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.deleteTask( t.getId());
                dbh.close();
                tasks.clear();
                tasks.addAll(dbh.getAllTask());
                adapter.notifyDataSetChanged();

                return true;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 5){

            tasks.clear();
            DBHelper dbh = new DBHelper(this);
            tasks.addAll(dbh.getAllTask());
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Update successful",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
