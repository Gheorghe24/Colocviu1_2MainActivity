package ro.pub.cs.systems.eim.colocviu1_2mainactivity

import android.content.Context
import android.content.Intent
import java.util.Date
import kotlin.random.Random

class ProcessThread(context: Context, sum : Int) : Thread() {

    private var isRunning = true
    private var sum = sum

    override fun run() {
        while (isRunning) {
            sendMessage()
            sleep()
        }
    }

    // propaga la nivelul sistemului de operare un mesaj cu difuzare , dupa 2 secunde, ce contine data si ora curenta, precum si suma

    private fun sendMessage() {
        val intent = Intent()

        intent
    }

    private fun sleep() {
        try {
            sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun stopThread() {
        isRunning = false
    }

}