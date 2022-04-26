package com.example.mvp_mvvm.ui.login

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mvp_mvvm.app
import com.example.mvp_mvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var viewModel: LoginContract.ViewModel? = null
    private val handler: Handler by lazy {Handler(Looper.getMainLooper())}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = restoreViewModel()


        binding.btnEnter.setOnClickListener {
            viewModel?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        viewModel?.shouldShowProgress?.subscribe(handler) { shouldShow ->
            if(shouldShow == true) {
                showProgress()
            } else {
                hideProgress()
            }
        }
        viewModel?.isSuccess?.subscribe(handler) {
            if(it == true) {
                setSuccess()
            }
        }
        viewModel?.errorText?.subscribe(handler) {
            it?.let {
                val success = viewModel?.isSuccess?.value
                if (success == false) {
                    setError(it)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.isSuccess?.unSubscribeAll()
        viewModel?.errorText?.unSubscribeAll()
        viewModel?.shouldShowProgress?.unSubscribeAll()
    }

    private fun restoreViewModel(): LoginViewModel {
        val viewModel =  lastCustomNonConfigurationInstance as? LoginViewModel
        return viewModel ?: LoginViewModel(app.loginUseCase)
    }

    // Метод чтобы взять объект
//    override fun getLastCustomNonConfigurationInstance(): Any? {
//        return super.getLastCustomNonConfigurationInstance()
//    }

    // Метод чтобы положить объект
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }

    @MainThread
    private fun setSuccess() {

        binding.btnEnter.isVisible = false
        binding.loginEditText.isVisible = false
        binding.passwordEditText.isVisible = false
        binding.root.setBackgroundColor(Color.GREEN)
    }
    @MainThread
    private fun setError(error: String) {
        Toast.makeText(this, "ERROR $error", Toast.LENGTH_SHORT).show()
    }

    private fun showProgress() {
        binding.btnEnter.isEnabled = false
        hideKeyboard(this)
    }

    private fun hideProgress() {
        binding.btnEnter.isEnabled = true
    }

//    override fun getHandler(): Handler {
//        return Handler(Looper.getMainLooper())
//    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}