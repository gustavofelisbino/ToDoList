package br.edu.satc.todolistcompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.satc.todolistcompose.data.TaskData
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme

@Composable
fun TaskCard(
    taskData: TaskData,
    onTaskCheckedChange: (isChecked: Boolean) -> Unit
) {

    var complete by remember { mutableStateOf(taskData.complete) }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp)
            .padding(bottom = 16.dp, start = 8.dp, end = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = taskData.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Serif
                    )
                )
                Checkbox(checked = complete, onCheckedChange = {
                    complete = it
                    onTaskCheckedChange(it)
                })
            }
            Text(text = taskData.description, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskCardPreview() {
    ToDoListComposeTheme {
        TaskCard(
            TaskData(
                title = "Estudar Jetpack Compose",
                description = "Estudar os principais componentes do Jetpack Compose",
                complete = false
            )
        ) {
            // Ação ao marcar/desmarcar a tarefa
        }
    }
}