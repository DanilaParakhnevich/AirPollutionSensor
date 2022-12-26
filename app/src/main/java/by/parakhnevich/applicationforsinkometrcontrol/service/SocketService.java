package by.parakhnevich.applicationforsinkometrcontrol.service;

import android.content.Context;

import com.jjoe64.graphview.series.DataPoint;

import java.util.List;

public interface SocketService {

    List<DataPoint> getDataFromSensor(Context context, String dataType);

}
