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
import com.example.ihc_1.databinding.FragmentCopyActivityBinding
import com.example.ihc_1.databinding.FragmentSpeedometerBinding
import com.example.ihc_1.databinding.FragmentSumActivityBinding
import com.example.ihc_1.ui.copyactivity.CopyFragmentViewModel

class SpeedometerFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentSpeedometerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val speedometerViewModel =
            ViewModelProvider(this).get(SpeedometerViewModel::class.java)

        _binding = FragmentSpeedometerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var mSensorManager = SENSOR_SERVICE as SensorManager
        var mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        return root
    }


    override fun onSensorChanged(event: SensorEvent?) {
        if( event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val sensorX = event.values[0]
            val sensorY = event.values[1]
            val sensorZ = event.values[2]

            binding.xAxis.setText(sensorX.toString())
            binding.yAxis.setText(sensorY.toString())
            binding.zAxis.setText(sensorZ.toString())

        }
    }

    override fun onAccuracyChanged(event: Sensor?, accuracy: Int) {
        //TODO("Not yet implemented")
    }

}