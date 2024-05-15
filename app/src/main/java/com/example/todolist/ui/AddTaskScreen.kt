import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.model.TaskEntity

@Composable
fun AddTaskScreen(onNavigate: () -> Unit, onTaskAdded: (TaskEntity) -> Unit) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF6200EE)) // Custom color for your top bar
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onNavigate) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to Home",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Add Screen",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        },
        content = {paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                val textTitle = remember { mutableStateOf(TextFieldValue("")) }
                val textDesc = remember { mutableStateOf(TextFieldValue("")) }
                TextField(
                    value = textTitle.value,
                    onValueChange = { textTitle.value = it },
                    label = {
                        Text(text = "Titre")
                    },
                    modifier = Modifier
                        .weight(1f, false)
                )
                TextField(
                    value = textDesc.value,
                    onValueChange = { textDesc.value = it },
                    label = {
                        Text(text = "Desc")
                    },
                    modifier = Modifier
                        .weight(1f, false)
                )
                Button(
                    onClick = {
                        val newTaskTitle = textTitle.value.text
                        val newTaskDesc = textDesc.value.text
                        if (newTaskTitle.isNotBlank() && newTaskDesc.isNotBlank()) {
                            onTaskAdded(
                                TaskEntity(title = newTaskTitle, description = newTaskDesc)
                            )
                            textTitle.value = TextFieldValue("")
                            textDesc.value = TextFieldValue("")
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "", modifier = Modifier.size(20.dp))
                }
            }
        }
    )
}
