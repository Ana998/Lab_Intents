package mk.ukim.finki.lab_intents

import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import mk.ukim.finki.lab_intents.adapters.ActivityViewAdapter

class ImplicitActivity : AppCompatActivity() {

    private lateinit var listView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        listView = findViewById(R.id.listView)

        listView.adapter = ActivityViewAdapter(getActivities())

    }

    private fun getActivities(): MutableList<String>{
        val list: ArrayList<String> = ArrayList()
        val intent = Intent().setAction("android.intent.action.MAIN")
        intent.addCategory("android.intent.category.LAUNCHER")
        val resolveInfos: List<ResolveInfo> = packageManager.queryIntentActivities(intent, 0)
        for (info in resolveInfos){
            if(info.activityInfo.name != null) {
                list.add(info.activityInfo.name.toString())
            }
        }
        return list
    }
}