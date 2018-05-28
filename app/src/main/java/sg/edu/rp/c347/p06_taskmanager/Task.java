package sg.edu.rp.c347.p06_taskmanager;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String name;
    private String desc;
    private int time;

    public Task(int id, String name, String desc, int time) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
