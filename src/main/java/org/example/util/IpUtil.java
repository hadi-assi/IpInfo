package org.example.util;

import com.google.gson.Gson;
import org.example.DataBase.DBConnection;
import org.example.entities.IpInfo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IpUtil {
    public static void GetCurrentIpInfo(String ip) throws Exception
    {
        HttpClient httpClient = HttpClient.newHttpClient();
        String uri = "https://ipinfo.io";
        if(!ip.isEmpty())uri += "/" + ip;
        uri += "/json";

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();


        HttpResponse<String> getResponse =
                httpClient.send(getRequest , HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        IpInfo ipInfo = gson.fromJson(getResponse.body(),IpInfo.class);
        ipInfo.setLong_Lat_time();

        System.out.println("current ip info");
        System.out.println(ipInfo +"\n");

        DBConnection dbConnection = new DBConnection();
        dbConnection.Add(ipInfo);
        dbConnection.Close();
    }

    public static void GetCurrentIpInfo() throws Exception
    {
        GetCurrentIpInfo("");
    }

    public static void GetAllIpDB()
    {
        DBConnection dbConnection = new DBConnection();
        dbConnection.QueryAll();
        dbConnection.Close();
    }
}
