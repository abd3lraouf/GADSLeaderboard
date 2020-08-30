package me.abdelraoufsabri.learn.gadsleaderboard.submission

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.dialog_confirmation.view.*
import kotlinx.android.synthetic.main.dialog_upload_result.view.*
import kotlinx.android.synthetic.main.fragment_project_submission.view.*
import me.abdelraoufsabri.learn.gadsleaderboard.R
import me.abdelraoufsabri.learn.gadsleaderboard.network.GateWays
import me.abdelraoufsabri.learn.gadsleaderboard.utils.Extensions.dp
import me.abdelraoufsabri.learn.gadsleaderboard.utils.Extensions.value
import retrofit2.Call
import retrofit2.Response

class ProjectSubmissionFragment : Fragment() {
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        navController = findNavController()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                navController.popBackStack()
            }
        })

        return inflater.inflate(R.layout.fragment_project_submission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            toolbar.setupWithNavController(navController)
            toolbar.title = ""
            toolbar.setNavigationIcon(R.drawable.ic_back)
            btnSubmit.setOnClickListener {
                val confirmView = LayoutInflater.from(context).inflate(R.layout.dialog_confirmation, null)

                val dialog = showCustomDialog(confirmView, 300.dp, 200.dp,true)
                confirmView.btnYes.setOnClickListener {
                    dialog.dismiss()
                    beginUpload(view)
                }
            }
        }
    }

    fun beginUpload(view: View) {
        val loadingView = LayoutInflater.from(context).inflate(R.layout.loading, null)
        val loadingDialog = showCustomDialog(loadingView, 200.dp, 100.dp, false)

        val firstName = view.etFirstName.value
        val lastName = view.etLastName.value
        val email = view.etEmail.value
        val link = view.etLink.value

        GateWays.GoogleForm.webService.projectSubmit(firstName, lastName,email, link, "AAD").enqueue(object :retrofit2.Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                loadingDialog.dismiss()
                showCustomDialog(getResultView(true), 350.dp, 200.dp, true)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                loadingDialog.dismiss()
                showCustomDialog(getResultView(false), 350.dp, 200.dp, true)
            }
        })
    }

    fun getResultView(success:Boolean):View{
        return LayoutInflater.from(context).inflate(R.layout.dialog_upload_result, null).apply {
            if (success){
                ivResultIcon.setImageResource(R.drawable.ic_ok)
                tvResultText.text = resources.getString(R.string.submission_was_successful)
            }else{
                ivResultIcon.setImageResource(R.drawable.ic_error)
                tvResultText.text = resources.getString(R.string.submission_was_not_successful)
            }
        }

    }

    private fun showCustomDialog(view: View, requestedWidth: Int, requestedHeight: Int, cancelable:Boolean): AlertDialog {
        val dialog = AlertDialog.Builder(context).apply {
            setCancelable(cancelable)
            setView(view)
        }.create()

        val dialogWindow = dialog.window!!

        LayoutParams().apply {
            copyFrom(dialogWindow.attributes)
            width = WRAP_CONTENT
            height = WRAP_CONTENT
            dialogWindow.attributes = this
            dialogWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setCanceledOnTouchOutside(cancelable)
        dialog.show()
        dialogWindow.setLayout(requestedWidth, requestedHeight)

        return dialog
    }


}