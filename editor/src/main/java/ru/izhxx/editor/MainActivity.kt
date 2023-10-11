package ru.izhxx.editor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.izhxx.editor.presenter.MainScreen

internal class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val pickerResult = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
        if (it != null) {
            viewModel.onFileCreate(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initContent()
        initObservers()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            viewModelStore,
            MainViewModelFactory.getFactory()
        )[MainViewModelImpl::class.java]
    }

    private fun initContent() {
        setContent {
            val state by viewModel.state.collectAsState()

            MainScreen(
                state = state,
                emptyScreenOnButtonAddClick = { viewModel.emptyScreenOnButtonAddClick() },
                itemsScreenOnButtonAddClick = { viewModel.itemsScreenOnButtonAddClick() },
                itemsScreenOnItemClick = {
                    viewModel.itemsScreenOnItemClick(it)
                    pickerResult.launch(arrayOf(BuildConfig.FILE_PATH, "text/*"))
                },
                inputScreenOnButtonAddClick = { name, url ->
                    viewModel.inputScreenOnButtonAddClick(
                        name,
                        url
                    )
                },
                inputScreenOnButtonCancelClick = { viewModel.inputScreenOnButtonCancelClick() }
            )
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.writeResult.collectLatest {
                if (it != null)
                    Toast.makeText(
                        this@MainActivity,
                        getString(if (it) R.string.write_success else R.string.write_error),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }
}