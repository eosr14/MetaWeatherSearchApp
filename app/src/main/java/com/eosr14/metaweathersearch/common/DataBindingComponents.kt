package com.eosr14.metaweathersearch.common

import android.graphics.drawable.Animatable
import android.net.Uri
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eosr14.metaweathersearch.model.LocalWeather
import com.eosr14.metaweathersearch.ui.main.MainListAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.RotationOptions
import com.facebook.imagepipeline.image.ImageInfo
import com.facebook.imagepipeline.request.ImageRequestBuilder

object DataBindingComponents {

    @JvmStatic
    @BindingAdapter("localWeatherItems")
    fun setLocalWeatherItems(recyclerView: RecyclerView, items: MutableList<LocalWeather>) {
        recyclerView.adapter?.let { adapter ->
            if (adapter is MainListAdapter) {
                adapter.setItems(items)
                adapter.setWithHeader(true)
                recyclerView.scheduleLayoutAnimation()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("urlToImage")
    fun setUrlToImage(view: SimpleDraweeView, url: String) {
        val imageRequest = ImageRequestBuilder
            .newBuilderWithSource(Uri.parse(url))
            .setRotationOptions(RotationOptions.autoRotate())
            .setProgressiveRenderingEnabled(true)
            .build()

        view.controller = Fresco.newDraweeControllerBuilder().run {
            this.oldController = view.controller
            this.imageRequest = imageRequest
            this.autoPlayAnimations = true
            this.controllerListener = object : BaseControllerListener<ImageInfo>() {
                override fun onFinalImageSet(
                    id: String?,
                    imageInfo: ImageInfo?,
                    animatable: Animatable?
                ) {
                    super.onFinalImageSet(id, imageInfo, animatable)
                    imageInfo?.let { info ->
                        view.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        view.aspectRatio = info.width.toFloat() / info.height
                    }
                }
            }
            this.build()
        }
    }

}