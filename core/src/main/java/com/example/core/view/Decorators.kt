package com.example.core.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.core.utils.dp2px

class HorizontalDecorator(
    private val startOffsetDP : Int,
    private val betweenOffsetDP : Int,
    private val endOffsetDP: Int,
    private val topOffsetDP: Int = 0,
    private val bottomOffsetDP: Int = 0
) : RecyclerView.ItemDecoration(){
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val items = parent.adapter!!.itemCount
        if (position == 0){
            outRect.left += startOffsetDP.dp2px(parent.context)
            outRect.right += (betweenOffsetDP/2).dp2px(parent.context)
        }else if( position == items -1){
            outRect.left += (betweenOffsetDP/2).dp2px(parent.context)
            outRect.right += endOffsetDP.dp2px(parent.context)
        }else{
            outRect.right += (betweenOffsetDP/2).dp2px(parent.context)
            outRect.left += (betweenOffsetDP/2).dp2px(parent.context)
        }
        if (topOffsetDP != 0 ) outRect.top += topOffsetDP.dp2px(parent.context)
        if (bottomOffsetDP != 0) outRect.bottom += bottomOffsetDP.dp2px(parent.context)
    }
}

class VerticalDecorator(
    private val topOffsetDP : Int,
    private val betweenOffsetDP : Int,
    private val bottomOffsetDP: Int,
    private val startOffsetDP : Int = 0,
    private val endOffsetDP: Int = 0
) : RecyclerView.ItemDecoration(){
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val items = parent.adapter!!.itemCount
        if (position == 0){
            outRect.top += topOffsetDP.dp2px(parent.context)
            outRect.bottom += (betweenOffsetDP/2).dp2px(parent.context)
        }else if( position == items -1){
            outRect.top += (betweenOffsetDP/2).dp2px(parent.context)
            outRect.bottom += bottomOffsetDP.dp2px(parent.context)
        }else{
            outRect.top += (betweenOffsetDP/2).dp2px(parent.context)
            outRect.bottom += (betweenOffsetDP/2).dp2px(parent.context)
        }
        if (startOffsetDP != 0) outRect.left += startOffsetDP.dp2px(parent.context)
        if (endOffsetDP != 0) outRect.right += endOffsetDP.dp2px(parent.context)
    }
}