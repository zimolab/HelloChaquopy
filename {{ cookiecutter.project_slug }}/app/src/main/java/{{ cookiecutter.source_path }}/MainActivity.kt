package {{ cookiecutter.package_name }}

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.chaquo.python.Python
import {{ cookiecutter.package_name }}.ui.theme.{{ cookiecutter.project_slug }}Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            {{ cookiecutter.project_slug }}Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { paddings ->
                    Content(paddings)
                }
            }
        }
    }


    @Composable
    fun Content(paddings: PaddingValues) {
        var currentText by remember { mutableStateOf("") }
        val context = LocalContext.current
        Column(modifier = Modifier.padding(paddings)) {
            Text(text = "Input your name:")
            TextField(modifier = Modifier.fillMaxWidth(), value = currentText, onValueChange = {text -> currentText = text})
            Button(modifier = Modifier.fillMaxWidth(), onClick = { callPythonFunction(currentText, context) }) {
                Text(text = "Say hello to Python!")
            }
        }

    }

    private fun callPythonFunction(arg: String, context: Context) {
        // call python method 'say_hello()' in hello.py
        val py = Python.getInstance()
        // get python module by its name
        val module = py.getModule("hello")
        // call python function by its name and pass a string arg to it
        module.callAttr("say_hello", arg, context)
    }
}

