package com.example.appssharedpreference.Adapterdata

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appssharedpreference.model.ModelData
import com.example.appssharedpreference.databinding.ShopListBinding

import java.security.AccessControlContext

class AdapterShop (private val context: Context):RecyclerView.Adapter<AdapterShop.ViewHolder>() {
    private var datas = listOf<ModelData>()

    fun setData(data: List<ModelData>){
        datas = data
        notifyDataSetChanged()
    }



    inner class ViewHolder (private val binding: ShopListBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun binData(letak:ModelData){

            binding.tvTittle.text = letak.title
            binding.tvPrice.text = "Rp."+ letak.price.toString()
            binding.tvCategory.text = "Kategori :"+letak.category
            binding.tvDescription.text = letak.description
            Glide.with(binding.root).load(letak.image).into(binding.ivImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ShopListBinding.inflate(
                LayoutInflater.from(context),
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}