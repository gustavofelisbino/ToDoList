@file:OptIn(ExperimentalMaterial3Api::class)

package br.edu.satc.todolistcompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.satc.todolistcompose.data.TaskDao
import br.edu.satc.todolistcompose.data.TaskData
import br.edu.satc.todolistcompose.data.ThemeMode
import br.edu.satc.todolistcompose.ui.components.TaskCard
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    taskDao: TaskDao,
    themeMode: ThemeMode,
    onThemeModeChange: (ThemeMode) -> Unit
) {
    // Carrega as tarefas do banco de dados
    val taskList = remember { mutableStateListOf<TaskData>() }

    // Carrega do banco ao abrir o app
    if (taskList.isEmpty()) {
        val tasksFromDb = taskDao.getAll()
        taskList.addAll(tasksFromDb)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        // Botao de tema no topo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Minhas Tarefas",
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            )
            TextButton(onClick = {
                val nextMode = when (themeMode) {
                    ThemeMode.AUTO -> ThemeMode.LIGHT
                    ThemeMode.LIGHT -> ThemeMode.DARK
                    ThemeMode.DARK -> ThemeMode.AUTO
                }
                onThemeModeChange(nextMode)
            }) {
                val label = when (themeMode) {
                    ThemeMode.LIGHT -> "Claro"
                    ThemeMode.DARK -> "Escuro"
                    ThemeMode.AUTO -> "Auto"
                }
                Text(text = "Tema: $label")
            }
        }

        // Lista de tarefas
        LazyColumn(
            modifier = Modifier.padding(top = 48.dp)
        ) {
            items(items = taskList) { task ->
                TaskCard(taskData = task, onTaskCheckedChange = { isChecked ->
                    val updated = task.copy(complete = isChecked)
                    taskDao.update(updated)
                    val index = taskList.indexOfFirst { it.id == task.id }
                    if (index != -1) {
                        taskList[index] = updated
                    }
                })
            }
        }

        // Botao nova tarefa
        NewTask(onSave = { title, description ->
            val newTask = TaskData(title = title, description = description, complete = false)
            taskDao.insert(newTask)
            // Recarrega a lista do banco pra pegar o ID gerado
            taskList.clear()
            taskList.addAll(taskDao.getAll())
        })
    }
}

@Composable
fun NewTask(onSave: (title: String, description: String) -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ExtendedFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            text = { Text("Nova tarefa") },
            icon = { Icon(Icons.Filled.Add, contentDescription = "") },
            onClick = {
                showBottomSheet = true
            }
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    label = { Text(text = "Título da tarefa") }
                )
                OutlinedTextField(
                    value = taskDescription,
                    onValueChange = { taskDescription = it },
                    label = { Text(text = "Descrição da tarefa") }
                )
                Button(
                    modifier = Modifier.padding(top = 4.dp),
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }

                        // Salva no banco de dados
                        onSave(taskTitle, taskDescription)

                        // Limpa os campos
                        taskTitle = ""
                        taskDescription = ""
                    }
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}
