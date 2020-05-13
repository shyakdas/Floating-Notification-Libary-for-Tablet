# FloatingNotification - A notification library for Android

## Sample

https://github.com/shyakdas/FloatingNotificationLibaryForTablet/blob/master/newgif.gif

### Overview of FloatingNotification library
* Floating notification is designed for Tablet and Phone.
* A new way to show notification(Just like of JIRA).
* Main purpose of building this libary to give a new look for notification.
* Floating notification increase the user experience, because user can see or do actions into same screen.
* No need to check notification into notification tray.

### Using FloatingNotification Library in your Android application

Add this in your project level build.gradle
```

implementation 'com.github.shyakdas:FloatingNotificationLibaryForTablet:v1.0.0'

```

Add the JitPack repository to your build file
```

maven { url 'https://jitpack.io' }

```
Then initialize the ViewModel class

```
var viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(FloatNotificationViewModel::class.java)
```

Add Data into Queue

```
private fun goToQueue() {
        initData() // Simple get the list of Data
        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (position < notificationItems!!.size) {
                    viewModel!!.addNotifications(notificationItems!![position]) // Here NotiicationItems is a list of data item, Add notification will add the list of data.
                }
                position++
            }

            override fun onFinish() {}
        }.start()
    }
```

To add custom data

```

Use MessageParser Class

```
