import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.widget.RemoteViews
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.flutter_android_mood_home_widget.MoodHomeWidget
import com.example.flutter_android_mood_home_widget.R

class MoodBackgroundWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        // This background worker is changing the current mood to the question "How are you feeling today?".
        // only as an example. In real world it can be used to fetch some useful info from server.
        println("[DEBUG] Background worker updating home widget...");
        val context = applicationContext;
        val appWidgetManager = AppWidgetManager.getInstance(context);
        val widgetComponent = ComponentName(context, MoodHomeWidget::class.java);
        val appWidgetIds = appWidgetManager.getAppWidgetIds(widgetComponent);
        for (widgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.mood_tracker_home_widget);
            views.setTextViewText(R.id.mood, "How are you feeling today?");
            appWidgetManager.updateAppWidget(widgetId, views);
        }
        return Result.success();
    }
}