package ddwu.com.mobile.savestate

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import ddwu.com.mobile.savestate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.myView.x = savedInstanceState?.getInt("x") ?: 100
        Log.d(TAG, "Main: (${binding.myView.x}, ${binding.myView.y})")
//
        val pref = getSharedPreferences("save_state", 0)
        binding.myView.y = pref.getInt("y", 300)
        Log.d(TAG, "onCreate()")
    }

    val TAG = "LifeCycle"

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        val pref : SharedPreferences = getSharedPreferences("save_state", 0)
        val editor : SharedPreferences.Editor = pref.edit()
        editor.putInt("y", binding.myView.y)
        editor.commit()
        Log.d(TAG, "onPause()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("x", binding.myView.x)
        outState.putInt("y", binding.myView.y)
        Log.d(TAG, "Save: (${binding.myView.x}, ${binding.myView.y})")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.myView.x = savedInstanceState.getInt("x")
        binding.myView.y = savedInstanceState.getInt("y")
        Log.d(TAG, "Restore: (${binding.myView.x}, ${binding.myView.y})")
    }
}