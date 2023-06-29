package ddwu.com.mobile.adaptereventtest

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.adaptereventtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val dao = SubjectDao()
//        val dataList = dao.dataList

        val foods = FoodDao().foods
        val adapter = FoodAdapter(foods)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL    // 생략 가능

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        val listener = object  : FoodAdapter.OnItemLongClickListener {
            override fun OnItemLongClick(view: View, position: Int) {
//                Toast.makeText(this@MainActivity,
//                            "${foods[position]}이지롱",
//                            Toast.LENGTH_SHORT).show()


                val alertDialog = AlertDialog.Builder(this@MainActivity).run {
                    setTitle("삭제 요청")
                    setMessage("정말 삭제하시겠습니까?")
                    setPositiveButton("확인", object: DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            foods.removeAt(position)
                            adapter.notifyDataSetChanged()
                        }
                    })
                    setNegativeButton("취소", null)
                    show()
                }

                foods.removeAt(position)
            }
        }

        adapter.setOnItemListener(listener)

//        val adapter = MyAdapter(this, R.layout.list_view, dataList)
//        binding.recyclerView.adapter = adapter
//
//        binding.btnAdd.setOnClickListener{
//            dataList.add(binding.etText.text.toString())
//            adapter.notifyDataSetChanged()
//        }
    }
}