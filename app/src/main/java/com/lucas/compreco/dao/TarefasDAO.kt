package com.lucas.compreco.dao

import com.lucas.compreco.modelo.Tarefa

 object TarefasDAO {
     private val tarefas = mutableListOf<Tarefa>()

    fun add(tarefa: Tarefa) {
        tarefas.add(tarefa)
    }

    fun buscaTodos(): List<Tarefa> {
        return tarefas.toList()
    }

     fun removerTarefa(position: Int){
         tarefas.removeAt(position)
     }

     fun buscaTarefa(descricao:String): Tarefa?{
         return tarefas.find { it.descricao == descricao }
     }

     fun atualizarTarefa(descricaoAntiga: String, tarefaAtualizada: Tarefa) {
         for (i in tarefas.indices) {
             val tarefa = tarefas[i]
             if (tarefa.descricao == descricaoAntiga) {
                 tarefas[i] = tarefaAtualizada
                 break
             }
         }
     }
 }




