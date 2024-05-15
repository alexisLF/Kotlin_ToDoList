import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.model.TaskEntity

@Composable
fun ShowTasksScreen(items: List<TaskEntity>, onNavigate: () -> Unit, onCheckedTask: (TaskEntity, Boolean) -> Unit, onNodeRemoved: (TaskEntity) -> Unit) {
    var showCheckedTasks by remember { mutableStateOf(false) }

    val filteredItems = if (showCheckedTasks) {
        items.filter { it.isDone }
    } else {
        items
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigate) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
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
                Text(
                    text = "Home Screen",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                IconButton(onClick = { showCheckedTasks = !showCheckedTasks }) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Filter Checked Tasks",
                        tint = Color.White
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(filteredItems) { item ->
                TaskItem(task = item, onCheckedTask = onCheckedTask, onNodeRemoved = onNodeRemoved)
            }
        }
    }

}

@Composable
fun TaskItem(task:TaskEntity, onCheckedTask: (TaskEntity, Boolean) -> Unit, onNodeRemoved: (TaskEntity) -> Unit){
    var isChecked by remember { mutableStateOf(task.isDone) }
    Checkbox(
        checked = isChecked,
        onCheckedChange = { checked -> isChecked = checked
            onCheckedTask(task, checked) }
    )
    Row {
        Text(
            text = task.title,
            modifier = Modifier
                .weight(1f, true)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = task.description,
            modifier = Modifier
                .weight(1f, true)
                .align(Alignment.CenterVertically)
        )
        TextButton(
            onClick = {
                onNodeRemoved(task)
            },
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}