package com.example.kitharam.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kitharam.R
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Handler
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class ToolUserFragment : Fragment() {

    private lateinit var soundPool: SoundPool
    private var tickSoundId: Int = 0
    private var isPlaying: Boolean = false
    private var tempo: Int = 120

    private lateinit var handler: Handler
    private val metronomeRunnable = object : Runnable {
        override fun run() {
            playTick()
            handler.postDelayed(this, calculateDelay())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_tool_user, container, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val attributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            soundPool = SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(attributes)
                .build()
        } else {
            soundPool = SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        }

        tickSoundId = soundPool.load(requireContext(), R.raw.tick_sound, 1)

        handler = Handler()

        val tempoSeekBar = rootView.findViewById<SeekBar>(R.id.tempoSeekBar)
        tempoSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tempo = progress
                updateTempoLabel(rootView)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        updateTempoLabel(rootView)

        val startStopButton = rootView.findViewById<Button>(R.id.startStopButton)
        startStopButton.setOnClickListener {
            toggleMetronome(startStopButton)
        }

        return rootView
    }

    private fun playTick() {
        soundPool.play(tickSoundId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    private fun calculateDelay(): Long {
        val beatsPerSecond = tempo / 60.0
        return (1000 / beatsPerSecond).toLong()
    }

    private fun updateTempoLabel(rootView: View) {
        val tempoLabel = rootView.findViewById<TextView>(R.id.tempoLabel)
        tempoLabel.text = "Tempo: $tempo BPM"
    }

    private fun toggleMetronome(startStopButton: Button) {
        if (isPlaying) {
            stopMetronome()
            startStopButton.text = "Stop"
        } else {
            startMetronome()
            startStopButton.text = "Start"
        }
    }

    private fun startMetronome() {
        isPlaying = true
        handler.postDelayed(metronomeRunnable, calculateDelay())
    }

    private fun stopMetronome() {
        isPlaying = false
        handler.removeCallbacks(metronomeRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
        handler.removeCallbacksAndMessages(null)
    }
}
