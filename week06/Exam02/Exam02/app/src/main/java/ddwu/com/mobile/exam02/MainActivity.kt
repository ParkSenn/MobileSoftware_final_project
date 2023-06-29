package ddwu.com.mobile.exam02

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import ddwu.com.mobile.exam02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myCustomView.setOnTouchListener(MyTouch())
        binding.myCustomView.setOnLongClickListener(MyLongClick())
    }

    inner class MyTouch : View.OnTouchListener {
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            binding.myCustomView.posX = event!!.x
            binding.myCustomView.posY = event!!.y
            binding.myCustomView.invalidate()
            return false
        }
    }

    inner class MyLongClick : View.OnLongClickListener {
        override fun onLongClick(v: View?): Boolean {
            binding.myCustomView.paintColor = Color.RED
            binding.myCustomView.invalidate()
            return true
        }
    }


}