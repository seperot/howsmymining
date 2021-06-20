package dev.ijh.howsmymining.widget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService.RemoteViewsFactory
import dev.ijh.howsmymining.R
import java.util.*


class WidgetProvider(context: Context?) :
  RemoteViewsFactory {
  private var collection: MutableList<String> = ArrayList()
  private var getContext: Context? = null
  override fun onCreate() {
    initData()
  }

  override fun onDataSetChanged() {
    initData()
  }

  override fun onDestroy() {}
  override fun getCount(): Int {
    return collection.size
  }

  override fun getViewAt(position: Int): RemoteViews {
    val view = RemoteViews(
      getContext!!.packageName,
      R.layout.miner_list_item
    )
    view.setTextViewText(R.id.tv_pool_info, "Turtle Solo Pool")
    view.setTextViewText(R.id.tv_workers_info, "1")
    view.setTextViewText(R.id.tv_hashrate_info, "25000")
    return view
  }

  override fun getLoadingView(): RemoteViews {
    return RemoteViews(getContext?.packageName, android.R.layout.simple_list_item_1)
  }

  override fun getViewTypeCount(): Int {
    return 1
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun hasStableIds(): Boolean {
    return true
  }

  private fun initData() {
    collection.clear()
    collection.add("ListView item 1")
  }

  init {
    getContext = context
  }
}