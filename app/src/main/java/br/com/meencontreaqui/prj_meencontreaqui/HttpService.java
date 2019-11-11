package br.com.meencontreaqui.prj_meencontreaqui;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, User> {
    private final String user;
    private final String password;


    public HttpService(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    protected User doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if ((this.user != null && this.user.length() == 8 ) &&
                this.password != null && this.password.length() == 8 ) {
            try {
                URL url = new URL("http://localhost:8080/api/user/create" + this.user + this.password+"/json/");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PUT");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Gson().fromJson(resposta.toString(), User.class);
    }
}
