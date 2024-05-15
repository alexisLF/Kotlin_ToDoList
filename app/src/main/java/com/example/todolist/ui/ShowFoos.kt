import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.data.Task

@Composable
fun ShowTasks(items: List<Task>, onNavigate: () -> Unit, onCheckedTask: (Task, Boolean) -> Unit, onNodeRemoved: (Task) -> Unit) {
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
            Row {
                Text("Home Screen")
                IconButton(onClick = { showCheckedTasks = !showCheckedTasks }) {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Filter Checked Tasks")
                }
            }
        },
        content = { paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items (filteredItems) {item ->
                    Checkbox(
                        checked = item.checked,
                        onCheckedChange = { checked -> onCheckedTask(item, checked) }
                    )
                    Row {
                        Text(
                            text = item.title,
                            modifier = Modifier
                                .weight(1f, true)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = item.description,
                            modifier = Modifier
                                .weight(1f, true)
                                .align(Alignment.CenterVertically)
                        )
                        TextButton(
                            onClick = {
                                onNodeRemoved(item)
                            },
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        ) {
                            Icon(imageVector = Icons.Filled.Delete, contentDescription = "", modifier = Modifier.size(20.dp))
                        }
                    }
                }
            }
        }
    )

}