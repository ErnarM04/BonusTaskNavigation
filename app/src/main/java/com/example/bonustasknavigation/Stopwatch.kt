import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.TextView

class Stopwatch(private val timeDisplay: TextView) {
    private var startTime: Long = 0
    private var running: Boolean = false
    private val handler = Handler(Looper.getMainLooper())
    private var updateRunnable: Runnable = object : Runnable {
        override fun run() {
            if (running) {
                val elapsedTime = SystemClock.elapsedRealtime() - startTime
                timeDisplay.text = formatTime(elapsedTime)
                handler.postDelayed(this, 1000) // Update every second
            }
        }
    }

    fun start() {
        if (!running) {
            startTime = SystemClock.elapsedRealtime() - (startTime) // Save elapsed time
            running = true
            handler.post(updateRunnable)
        }
    }

    fun stop() {
        if (running) {
            running = false
            handler.removeCallbacks(updateRunnable)
        }
    }

    fun reset() {
        running = false
        handler.removeCallbacks(updateRunnable)
        startTime = 0
        timeDisplay.text = formatTime(0)
    }

    fun isRunning(): Boolean = running
    fun getElapsedTime(): Long = if (running) SystemClock.elapsedRealtime() - startTime else startTime

    private fun formatTime(elapsedMillis: Long): String {
        val seconds = (elapsedMillis / 1000) % 60
        val minutes = (elapsedMillis / 1000 / 60) % 60
        val hours = elapsedMillis / 1000 / 3600
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}
