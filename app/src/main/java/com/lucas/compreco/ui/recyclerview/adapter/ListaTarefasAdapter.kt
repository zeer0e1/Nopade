package com.lucas.compreco.ui.recyclerview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucas.compreco.R
import com.lucas.compreco.dao.TarefasDAO
import com.lucas.compreco.modelo.Tarefa
import com.lucas.compreco.ui.activity.FormTarefaActivity

class ListaTarefasAdapter(
    private  val context: Context,

) : RecyclerView.Adapter<ListaTarefasAdapter.ViewHolder>() {
    private val dao = TarefasDAO

    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {



        val btDelete : ImageButton = itemView.findViewById(R.id.bt_delete)
        val btCheck : ImageButton = itemView.findViewById(R.id.bt_check)
        val btEdit : ImageButton = itemView.findViewById(R.id.bt_edit)
        val terafaDescricao: TextView = itemView.findViewById(R.id.editDesc)

        fun vincula(tarefa: Tarefa) {
            val terafaDescricao = itemView.findViewById<TextView>(R.id.editDesc)
            terafaDescricao.text = tarefa.descricao
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.tarefa, parent, false)
        return  ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarefa = dao.buscaTodos()[position]

        holder.vincula(tarefa)
        btnDelete(holder, position)
        btnCheck(holder)

        holder.btEdit.setOnClickListener {
            val intent = Intent(context, FormTarefaActivity::class.java).apply {
                putExtra("descricao",tarefa.descricao)
            }
           context.startActivity(intent)



        }

    }
    @SuppressLint("NotifyDataSetChanged")
    private fun btnDelete(
        holder: ViewHolder,
        position: Int
    ) {
        holder.btDelete.setOnClickListener {
            dao.removerTarefa(position)
            notifyDataSetChanged()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun btnCheck(holder: ViewHolder) {
        holder.btCheck.setOnClickListener {

            if (holder.terafaDescricao.paintFlags and
                Paint.STRIKE_THRU_TEXT_FLAG == 0
            ) {
                holder.terafaDescricao.paintFlags =
                    holder.terafaDescricao.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                notifyDataSetChanged()

            } else {
                holder.terafaDescricao.paintFlags =
                    holder.terafaDescricao.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                notifyDataSetChanged()
            }
        }
    }


    override fun getItemCount(): Int = dao.buscaTodos().size




}
