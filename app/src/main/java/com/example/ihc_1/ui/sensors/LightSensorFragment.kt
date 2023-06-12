package com.example.ihc_1.ui.sensors

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ihc_1.databinding.FragmentLightSensorBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class LightSensorFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentLightSensorBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorManager: SensorManager

    private lateinit var sensorLight: Sensor
    private lateinit var sensorTemperature: Sensor
    private lateinit var sensorPressure: Sensor

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitudeValue : Double = 0.0
    private var longitudeValue : Double = 0.0

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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        binding.gpsButton.setOnClickListener {
            onClickGPSButton()
        }

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

    @SuppressLint("MissingPermission")
    private fun getLastPosition() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    latitudeValue =  location.latitude
                    longitudeValue = location.longitude
                }
            }
    }

    private fun onClickGPSButton() {
        getLastPosition()
        Toast.makeText(activity?.applicationContext, "Latitude: $latitudeValue\nLongitude: $longitudeValue", Toast.LENGTH_LONG).show()
    }
}