package by.parakhnevich.applicationforsinkometrcontrol

import android.os.Bundle
import android.view.View
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import by.parakhnevich.applicationforsinkometrcontrol.service.SocketService
import by.parakhnevich.applicationforsinkometrcontrol.service.SocketServiceImpl
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries


class MainActivity : AppCompatActivity() {
    var socketService: SocketService = SocketServiceImpl()
    lateinit var graph: GraphView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        graph = findViewById(R.id.graph);
        graph.viewport.isScrollable = true
    }

    fun onClick(view: View) {
        val findViewById: Spinner = findViewById(R.id.spinner);
        findViewById.selectedItem as String?;
        val dataType = when (findViewById.selectedItem as String?) {
            "Temperature" -> "temp"
            "Pressure" -> "pres"
            "Humidity" -> "hum"
            else -> {"co2"}
        }

        graph.removeAllSeries()
        graph.addSeries(LineGraphSeries(socketService.getDataFromSensor(this, dataType).toTypedArray()))
    }
}