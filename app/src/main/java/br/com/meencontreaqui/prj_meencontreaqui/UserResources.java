package br.com.meencontreaqui.prj_meencontreaqui;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserResources {
        private static final String URL =
                "https://projetomobile.herokuapp.com/api";


    public List<User> getContatos() throws IOException {
        java.net.URL url = new URL("https://projetomobile.herokuapp.com/api/users");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        Log.i("zzz", "Response Code: " + conn.getResponseCode());

        BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String corpo = "";
        String linha = br.readLine();
        while (linha != null) {
            corpo += linha + "\n";
            linha = br.readLine();
        }

        ArrayList<User> users = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(corpo);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                User user = jsonObjectToContato(jsonObject);
                users.add(0, user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("zzz", "Corpo: " + corpo);

        return users;
    }

    private JSONObject toJSON(User user)
    throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("name", user.getName());
        obj.put("password", user.getPassword());
        return obj;
    }

    @NonNull
    private User jsonObjectToContato(JSONObject jsonObject) throws JSONException {
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");

        return new User(name,password);
    }

    public String insertUser(User teste) throws IOException, JSONException {
        URL url = new URL("https://projetomobile.herokuapp.com/api/users/create");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");

        User user = new User("bbb", "ccc");

        OutputStream os = conn.getOutputStream();
        PrintStream ps = new PrintStream(os);

        JSONObject jsonObject = new JSONObject();
        Log.i("========criar o json","erro linha 78");
        try {
            jsonObject.put("name", user.getName());
            jsonObject.put("password", user.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Erro em inserir!");
        }
        Log.i("========criar o json","erro linha 85");

        Log.i("zzz", "JSON enviado: " + jsonObject.toString());
        ps.print(jsonObject.toString());

        Log.i("zzz", "Response Code: " + conn.getResponseCode());

        BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String corpo = "";
        String linha = br.readLine();
        while (linha != null) {
            corpo += linha + "\n";
            linha = br.readLine();
        }

        String name = "";
        try {
            JSONObject objName = new JSONObject(corpo);
             name = objName.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Erro em inserir!");
        }


        return name;
    }
}

