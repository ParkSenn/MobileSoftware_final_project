package ddwucom.mobile.week12.intenttest

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ddwucom.mobile.week12.intenttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    val DETAIL_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra("greeting", "한소희 개예뻐")
//
//            val dto = FoodDto(R.mipmap.ic_launcher, "치킨", 20)
//            intent.putExtra("food", dto)

            startActivityForResult(intent, DETAIL_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            DETAIL_CODE -> {
                if(resultCode == RESULT_OK) {
                    val result = data?.getStringExtra("result_data")
//                    Log.d(TAG, "$result")
                    Toast.makeText(this, result, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}