package edu.stanford.rkpandey.covid19tracker.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import edu.stanford.rkpandey.covid19tracker.R
import edu.stanford.rkpandey.covid19tracker.viewmodels.ApiStatus

@BindingAdapter("errorDisplay")
fun displayErrorImage(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("errorVisibility")
fun displayDataInfo(view: View, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            view.visibility = View.VISIBLE
        }
        ApiStatus.ERROR -> {
            view.visibility = View.INVISIBLE
        }
        ApiStatus.DONE -> {
            view.visibility = View.VISIBLE
        }
    }
}