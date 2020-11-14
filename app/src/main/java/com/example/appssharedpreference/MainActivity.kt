package com.example.appssharedpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appssharedpreference.Adapterdata.AdapterShop
import com.example.appssharedpreference.databinding.ActivityMainBinding
import com.example.appssharedpreference.model.ModelData
import com.example.appssharedpreference.util.ObjectData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private  val adapter by lazy { AdapterShop(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvProduct.adapter = adapter

        ObjectData.services.getAllProduct().enqueue(object : Callback<List<ModelData>> {
            override fun onResponse(
                call : Call<List<ModelData>>,
                response: Response<List<ModelData>>
            ){
                if (response.isSuccessful){
                    response.body()?.let { adapter.setData(it) }
                    Toast.makeText(this@MainActivity, "berhasil", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this@MainActivity, "gagal rekl", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            override fun onFailure(call: Call<List<ModelData>>, t: Throwable) {
                t.printStackTrace()

                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
