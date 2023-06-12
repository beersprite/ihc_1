package com.example.ihc_1.ui.lightsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ihc_1.databinding.FragmentLightSensorBinding


class LightSensorFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentLightSensorBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorManager: SensorManager

    private lateinit var sensorLight: Sensor
//    private lateinit var lightValue: TextView

    private lateinit var sensorTemperature: Sensor
//    private lateinit var temperatureValue: TextView

    private lateinit var sensorPressure: Sensor
//    private lateinit var pressureValue: TextView

//    private lateinit var sensorGyro: Sensor
//    private lateinit var gyroscopeValue: TextView // 3 values
//    private lateinit var sensorMagnetometer: Sensor
//    private lateinit var magnetometerValue: TextView // 3 values


//    var sensorX: Float = 0.0f
//    var sensorY: Float = 0.0f
//    var sensorZ: Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLightSensorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        setupSensors()
        setupStrings()

        return root
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            binding.lightTextview.setText("Light Intensity\n" + event.values[0] + " lux")
        }
        else if (event?.sensor?.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            binding.temperatureTextview.setText("Ambient Temperature\n" + event.values[0] + " C")
        }
        else if (event?.sensor?.type == Sensor.TYPE_PRESSURE) {
            binding.pressureTextview.setText("Pressure\n" + event.values[0] + " hPa")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSensors(){
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        sensorPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
    }
    private fun setupStrings(){
        binding.lightTextview.setText("Light Intensity\n" + 0.0 + " lux")
        binding.temperatureTextview.setText("Ambient Temperature\n" + 0.0 + " C")
    }

}