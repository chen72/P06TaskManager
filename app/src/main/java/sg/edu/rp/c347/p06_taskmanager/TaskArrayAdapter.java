package sg.edu.rp.c347.p06_taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskArrayAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Task> tasks;
    int resource;

    public TaskArrayAdapter(Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.tvName);
        TextView desc = (TextView) rowView.findViewById(R.id.tvDesc);


        Task task = tasks.get(position);

        name.setText(task.getId()+" "+task.getName());
        desc.setText(task.getDesc());

        return rowView;
    }





}
