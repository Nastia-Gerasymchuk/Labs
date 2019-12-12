package main.io;

import com.google.gson.Gson;
import main.model.AllDepartments;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONAllDepartments implements IO {
    @Override
    public void serialize(AllDepartments departments, String path) throws IOException {
        Gson gson = new Gson();
        File file = new File(path);
        FileWriter fw = new FileWriter(file);
        fw.write(gson.toJson(departments));
        fw.close();
    }

    @Override
    public AllDepartments deserialize(String path) throws IOException {
        return new Gson().fromJson(new FileReader(path), AllDepartments.class);
    }}