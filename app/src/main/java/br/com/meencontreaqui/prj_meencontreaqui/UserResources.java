package br.com.meencontreaqui.prj_meencontreaqui;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserResources {
    private static final String URL =
            "https://projetomobile.herokuapp.com/api";


    public List<User> getUsers() throws IOException {
        //URL com a rota pronta para fazer login
        URL url = new URL("https://projetomobile.herokuapp.com/api/users");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");

        //Feedback do httprequest com codigo e menssagem caso tenha
        Log.i("zzz", "Response Code: " + conn.getResponseCode());
        Log.i("zzz", "Response Message: " + conn.getResponseMessage());

        //Isso transforma a resposta em uma string
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

                User user = jsonObjectToUser(jsonObject);
                Log.d("Objeto Json", user.toString());
                users.add(i, user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("zzz", "Corpo: " + corpo);

        return users;
    }

    //Rota de login
    public User login(User user) throws IOException {
        //URL com a rota pronta para fazer login
        URL url = new URL("https://projetomobile.herokuapp.com/api/user/name/"+user.getName());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");

        //Feedback do httprequest com codigo e menssagem caso tenha
        Log.i("zzz", "Response Code: " + conn.getResponseCode());
        Log.i("zzz", "Response Message: " + conn.getResponseMessage());

        //Isso transforma a resposta em uma string
        BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String corpo = "";
        String linha = br.readLine();
        while (linha != null) {
            corpo += linha + "\n";
            linha = br.readLine();
        }

        User users = new User();

        //esse bloco tenta transformar a resposta em string em um objeto do tipo Pessoa
        try {
            JSONObject obj = new JSONObject(corpo);
            users= jsonObjectToUser(obj);
            Log.d("Objeto Json", obj.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return users;
    }

    private JSONObject toJSON(User user)
            throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("name", user.getName());
        obj.put("password", user.getPassword());
        obj.put("longitude",user.getLongitude());
        obj.put("latitude",user.getLatitude());
        obj.put("active",user.getActive());
        return obj;
    }

    @NonNull
    private User jsonObjectToUser(JSONObject jsonObject) throws JSONException {
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        Double longitude = jsonObject.getDouble("longitude");
        Double latitude = jsonObject.getDouble("latitude");
        int active = jsonObject.getInt("active");

        return new User(name,password,longitude,latitude,active);
    }
    //metodo que faz um http request com post na rota de criação de usuario
    public boolean insertUser(User user) throws IOException, JSONException {
        boolean success = false;
        URL url = new URL("https://projetomobile.herokuapp.com/api/users/create");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        //essa linha garante que o formato enviado vai ser application/json
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        //esse bloco tenta criar um json object com os parametros passados para criação
        try {
            jsonObject.put("name", user.getName());
            jsonObject.put("password", user.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Erro em inserir!");
        }

        conn.setDoOutput(true); //fala que voce vai enviar algo


        PrintStream printStream = new PrintStream(conn.getOutputStream());
        printStream.println(jsonObject); //seta o que voce vai enviar

        conn.connect(); //envia para o servidor

        String jsonDeResposta = new Scanner(conn.getInputStream()).next();

        Log.i("zzz", "JSON enviado: " + jsonObject.toString());
        Log.i("zzz", "Response Code: " + conn.getResponseCode());

        if (conn.getResponseCode()==200){
            success = true;
        }



        return success;
    }
    public boolean updateState(User user) throws IOException, JSONException {
        boolean success = false;
        URL url = new URL("https://projetomobile.herokuapp.com/api/user/name/"+user.getName());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("PUT");
        //essa linha garante que o formato enviado vai ser application/json
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        //esse bloco tenta criar um json object com os parametros passados para criação
        try {
            jsonObject.put("name", user.getName());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("latitude", user.getLatitude());
            jsonObject.put("longitude", user.getLongitude());
            jsonObject.put("active",user.getActive());
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Erro ao editar o usuario!"+e.getMessage());
        }

        conn.setDoOutput(true); //fala que voce vai enviar algo


        PrintStream printStream = new PrintStream(conn.getOutputStream());
        printStream.println(jsonObject); //seta o que voce vai enviar

        conn.connect(); //envia para o servidor

        String jsonDeResposta = new Scanner(conn.getInputStream()).next();

        Log.i("zzz", "JSON enviado: " + jsonObject.toString());
        Log.i("zzz", "Response Code: " + conn.getResponseCode());

        if (conn.getResponseCode()==200){
            success = true;
        }



        return success;
    }
}