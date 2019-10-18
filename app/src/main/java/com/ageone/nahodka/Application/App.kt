package com.ageone.nahodka.Application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Router.Router
import com.ageone.nahodka.BuildConfig
import com.ageone.nahodka.External.Base.Activity.BaseActivity
import com.ageone.nahodka.External.Extensions.Application.FTActivityLifecycleCallbacks
import com.ageone.nahodka.External.HTTP.API.API
import com.ageone.nahodka.Internal.Utilities.Utils
import com.ageone.nahodka.Models.RxData
import com.ageone.nahodka.Network.Socket.WebSocket
import com.ageone.nahodka.R
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import net.alexandroid.shpref.ShPref
import timber.log.Timber
import java.util.*

val router = Router()
val coordinator = FlowCoordinator()

val utils = Utils()
val api = API()
//val database = DataBase
val rxData = RxData()
var webSocket = WebSocket()

val currentActivity: BaseActivity?
    get() = App.instance?.mFTActivityLifecycleCallbacks?.currentActivity as BaseActivity

class App: Application()  {

    init {
        instance = this
    }

    val mFTActivityLifecycleCallbacks = FTActivityLifecycleCallbacks()

    override fun onCreate() {
        super.onCreate()

        // MARK: SharePreferences

        ShPref.init(this, ShPref.APPLY)

        // MARK: Timber
        if (BuildConfig.DEBUG) {
            val deviceManufacturer = android.os.Build.MANUFACTURER
            if (deviceManufacturer.toLowerCase().contains("huawei")) {
                Timber.plant(HuaweiTree())
            } else {
                Timber.plant(Timber.DebugTree())
            }
        }

        ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnectedToInternet ->
//                    Timber.i("Internet are allowed")
                    utils.isNetworkReachable = isConnectedToInternet
            }

        Realm.init(this)

        registerActivityLifecycleCallbacks(mFTActivityLifecycleCallbacks)
    }

    companion object {

        var instance: App? = null

    }
}

class HuaweiTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        var priority = priority
        if (priority == Log.VERBOSE || priority == Log.DEBUG)
            priority = Log.INFO
        super.log(priority, tag, message, t)
    }
}

class MyFirebaseMessagingService: FirebaseMessagingService() {

    private lateinit var notificationManager: NotificationManager
    private val ADMIN_CHANNEL_ID = "nortedhas"

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        var intent = Intent(this, currentActivity!!::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        var pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)

        Timber.i("${p0?.data}")
        p0?.let { message ->
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                setupNotificationChannels()
            }
            val notificationId = Random().nextInt(60000)

            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            val notificationBuilder = NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(message.data["title"])
                .setContentText(message.data["message"])
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(notificationId,notificationBuilder.build())
        }

    }

    override fun onNewToken(p0: String) {
        Timber.i("$p0")
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun setupNotificationChannels() {
        val adminChannelName = getString(R.string.notifications_admin_channel_name)
        val adminChannelDescription = getString(R.string.notifications_admin_channel_description)

        val adminChannel: NotificationChannel
        adminChannel = NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_LOW)
        adminChannel.description = adminChannelDescription
        adminChannel.enableLights(true)
        adminChannel.lightColor = Color.GRAY
        adminChannel.enableLights(true)
        notificationManager.createNotificationChannel(adminChannel)
    }
}