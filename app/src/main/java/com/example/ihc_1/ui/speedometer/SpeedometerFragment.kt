package com.example.ihc_1.ui.speedometer

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ihc_1.databinding.FragmentSpeedometerBinding

class SpeedometerFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentSpeedometerBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorAccelerometer: Sensor

    var sensorX: Float = 0.0f
    var sensorY: Float = 0.0f
    var sensorZ: Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val speedometerViewModel =
            ViewModelProvider(this)[SpeedometerViewModel::class.java]

        _binding = FragmentSpeedometerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        binding.speedSendButton.setOnClickListener {
            if (sensorX.toString() == "0.0" && sensorZ.toString() == "0.0")
                this.sendMessage(requireContext(), "Phone is vertical.")
            else if (sensorX.toString() == "-0.0" && sensorZ.toString() == "0.0")
                this.sendMessage(requireContext(), "Phone is inverted.")
            else
                this.sendMessage(requireContext(), "Invert your phone vertically.")
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
    override fun onSensorChanged(event: SensorEvent?) {

        if( event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            sensorX = event.values[0]
            sensorY = event.values[1]
            sensorZ = event.values[2]

            binding.xAxis.setText("X  $sensorX")
            binding.yAxis.setText("Y  $sensorY")
            binding.zAxis.setText("Z  $sensorZ")

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        //sensorManager.unregisterListener(this)

        super.onDestroyView()
        _binding = null
    }
    private fun sendMessage(context: Context, message: String){
        val intent = Intent(context, SpeedometerMessageActivity::class.java)

        intent.putExtra("sent_text_id", message)

        startActivity(intent)
    }

}