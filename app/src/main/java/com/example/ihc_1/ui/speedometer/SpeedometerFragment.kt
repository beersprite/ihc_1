package com.example.ihc_1.ui.speedometer

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import com.example.ihc_1.R
import com.example.ihc_1.databinding.FragmentSpeedometerBinding
import com.example.ihc_1.databinding.FragmentSumActivityBinding

class SpeedometerFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentSpeedometerBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = SpeedometerFragment()
    }

    private lateinit var viewModel: SpeedometerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speedometer, container, false)
    }

    var mSensorManager = SENSOR_SERVICE as SensorManager
    var mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    var xAxis = binding.xAxis
    var yAxis = binding.yAxis
    var zAxis = binding.zAxis

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpeedometerViewModel::class.java)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if( event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val sensorX = event.values[0]
            val sensorY = event.values[1]
            val sensorZ = event.values[2]

            xAxis.setText(sensorX.toString())
            yAxis.setText(sensorY.toString())
            zAxis.setText(sensorZ.toString())

        }
    }

    override fun onAccuracyChanged(event: Sensor?, accuracy: Int) {
        //TODO("Not yet implemented")
    }

}