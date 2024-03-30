package com.lucas.compreco.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.lucas.compreco.R
import com.lucas.compreco.dao.TarefasDAO
import com.lucas.compreco.modelo.Tarefa

class FormTarefaActivity : AppCompatActivity(R.layout.activity_form_tarefa) {
    private val dao = TarefasDAO
    private var descricaoExistente: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_tarefa)
        val btSalvar = findViewById<Button>(R.id.bt_salvar)


        val campoDescricao = findViewById<EditText>(R.id.editDesc)

         descricaoExistente = intent.getStringExtra("descricao")

        if (descricaoExistente != null) {
            campoDescricao.setText(descricaoExistente)
        }


        btSalvar.setOnClickListener {
            salvarTarefa(campoDescricao.text.toString())
        }
    }

    private fun salvarTarefa(descricao: String) {
        if (descricao.isNotBlank()) {
            if (descricaoExistente != null) {
                val tarefaExistente = dao.buscaTarefa(descricaoExistente!!)
                if (tarefaExistente != null) {
                    tarefaExistente.descricao = descricao
                    dao.atualizarTarefa(descricaoExistente!!, tarefaExistente)
                }
            } else {
                val novaTarefa = Tarefa(descricao = descricao)
                dao.add(novaTarefa)
            }
        }
        finish()
    }

}
