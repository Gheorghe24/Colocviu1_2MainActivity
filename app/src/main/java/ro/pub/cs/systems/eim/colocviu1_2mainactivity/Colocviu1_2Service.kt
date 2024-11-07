package ro.pub.cs.systems.eim.colocviu1_2mainactivity

import android.app.Service
import android.content.Intent
import android.os.IBinder

class Colocviu1_2Service : Service() {

    private var processingThread: ProcessThread? = null

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}

