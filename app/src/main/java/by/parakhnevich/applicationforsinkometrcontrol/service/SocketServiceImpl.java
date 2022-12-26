package by.parakhnevich.applicationforsinkometrcontrol.service;

import android.content.Context;

import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import by.parakhnevich.applicationforsinkometrcontrol.R;

public class SocketServiceImpl implements SocketService {

    private static final String IP = "192.168.4.1";
    private static final int PORT = 80;

    @Override
    public List<DataPoint> getDataFromSensor(Context context, String dataType) {
        List<DataPoint> data = new ArrayList<>();

        try {

            String jsonString = readText(context, R.raw.long_memory);
            JSONObject obj = new JSONObject(jsonString);

            for (int i = 0; i < obj.getJSONArray("time").length(); ++i) {
                double x = Double.parseDouble(obj.getJSONArray("time").getString(i));
                double y = Double.parseDouble(obj.getJSONArray(dataType).getString(i).replaceAll("[^\\d.]", ""));

                data.add(new DataPoint(x, y));
            }
        } catch (Exception e) {
            System.err.println("File not found");
        }

        return data;
    }

    private String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

}
