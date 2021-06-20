package dev.ijh.howsmymining.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import dev.ijh.howsmymining.R

class MinerWidget : AppWidgetProvider() {
  override fun onUpdate(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetIds: IntArray
  ) {
    for (appWidgetId in appWidgetIds) {
      updateAppWidget(context, appWidgetManager, appWidgetId)
    }
    super.onUpdate(context, appWidgetManager, appWidgetIds)
  }

  override fun onEnabled(context: Context?) {}

  override fun onDisabled(context: Context?) {}

  companion object {
    fun updateAppWidget(
      context: Context, appWidgetManager: AppWidgetManager,
      appWidgetId: Int
    ) {
      val views = RemoteViews(context.packageName, R.layout.widget_provider)
      setRemoteAdapter(context, views)
      appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private fun setRemoteAdapter(context: Context, views: RemoteViews) {
      views.setRemoteAdapter(
        R.id.widget_list,
        Intent(context, WidgetService::class.java)
      )
    }
  }
}