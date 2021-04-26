package com.example.gsonsimpleconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2;
    Button button;
    String FILENAME = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
    }

    String text, exitText, gsonString;

    public void remakeUser(View view){
        //StringHolder stringHolder = new StringHolder(); //Cat barsik = new Cat();
        //System.out.println(stringHolder);
        //stringHolder.text = textView1.getText().toString();
        //System.out.println(stringHolder.text);
        User user1 = new User();
        user1.firstname = "Denis";
        user1.lastname = "Barilov";
        user1.school = 1511;
        Gson gson = new Gson();
        Log.i("GSON", gson.toJson(user1));


        //String jsonText = "{\"name\":\"Мурзик\",\"color\":-16777216,\"age\":9}";
        gsonString = gson.toJson(user1);

        writeFile(gsonString);
        gsonString = readFile();

        System.out.println(gsonString);

        GsonBuilder builder = new GsonBuilder();
        Gson gson2 = builder.create();
        System.out.println(gson2.fromJson(gsonString, StringHolder.class).toString());
        //StringHolder stringHolder2 = gson2.fromJson(gsonString, StringHolder.class);
        //textView2.setText(stringHolder2.text);
        User user2 = gson2.fromJson(gsonString, User.class);
        textView1.setText(user2.firstname + " " + user2.lastname);
        textView2.setText(user2.school + "");
    }

    public void remake(View view){
        StringHolder stringHolder = new StringHolder(); //Cat barsik = new Cat();
        System.out.println(stringHolder);
//        barsik.name = "Барсик";
//        barsik.age = 8;
        stringHolder.text = textView1.getText().toString();
        System.out.println(stringHolder.text);
        Gson gson = new Gson();
        Log.i("GSON", gson.toJson(stringHolder));


        //String jsonText = "{\"name\":\"Мурзик\",\"color\":-16777216,\"age\":9}";
        gsonString = gson.toJson(stringHolder);

        writeFile(gsonString);
        gsonString = readFile();

        System.out.println(gsonString);

        GsonBuilder builder = new GsonBuilder();
        Gson gson2 = builder.create();
        //Cat murzik = gson.fromJson(jsonText, Cat.class);
        System.out.println(gson2.fromJson(gsonString, StringHolder.class).toString());
        StringHolder stringHolder2 = gson2.fromJson(gsonString, StringHolder.class);
        //System.out.println("---" + stringHolder2.text);
        textView2.setText(stringHolder2.text);

        //Log.i("GSON", "Имя: " + murzik.name + "\nВозраст: " + murzik.age);

//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = gsonBuilder.create();
//        text = textView1.getText().toString();
//        System.out.println(text);
//        gson.toJson(text);
//        gsonString = gson.toString();
//
//
//        System.out.println(gsonString);
//        gson.fromJson(gsonString, String.class);
//        //System.out.println(gson.fromJson(gsonString, String.class));
//        //exitText = gson.fromJson(gsonString, String.class).toString();
//        System.out.println(exitText);
//        textView2.setText(exitText);
    }

    void writeFile(String value) {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            // пишем данные
            bw.write(value);
            // закрываем поток
            bw.close();
            //Log.d(LOG_TAG, "Файл записан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String readFile() {
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
//            String str = "";
            // читаем содержимое
//            while ((str = br.readLine()) != null) {
//                //Log.d(LOG_TAG, str);
//            }
            return br.readLine().toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "nothing";
    }
}