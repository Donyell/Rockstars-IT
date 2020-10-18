package nl.donyell.rockstars_it.generic

import android.os.Handler
import android.os.Looper
import android.widget.SearchView

abstract class DelayedOnQueryTextListener : SearchView.OnQueryTextListener {
    private val handler: Handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null

    override fun onQueryTextSubmit(s: String): Boolean {
        return false
    }

    override fun onQueryTextChange(s: String): Boolean {
        runnable?.let { handler.removeCallbacks(it) }
        runnable = Runnable { onDelayerQueryTextChange(s) }
        Handler(Looper.getMainLooper()).postDelayed(runnable!!, 300)
        return true
    }

    abstract fun onDelayerQueryTextChange(query: String)
}
