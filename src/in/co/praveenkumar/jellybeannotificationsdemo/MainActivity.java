package in.co.praveenkumar.jellybeannotificationsdemo;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public PendingIntent getPendingIntent() {
		return PendingIntent.getActivity(this, 0, new Intent(this,
				NotificationHandlerActivity.class), 0);
	}

	public NotificationManager getNotificationManager() {
		return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	public void sendBasicNotification(View view) {
		final Switch persistentSwitch = (Switch) findViewById(R.id.persistentSwitch);
		boolean persistanceValue = persistentSwitch.isChecked();
		Notification notification = new Notification.Builder(this)
				.setContentTitle("Basic notification title")
				.setContentText("Basic notification Text")
				.setSmallIcon(R.drawable.ic_launcher)
				.setSubText("third subtext").setContentInfo("Content info")
				.setOngoing(persistanceValue).build();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		NotificationManager notificationManager = getNotificationManager();
		notificationManager.notify(0, notification);
	}

	public void sendBigTextNotification(View view) {
		final Switch persistentSwitch = (Switch) findViewById(R.id.persistentSwitch);
		boolean persistanceValue = persistentSwitch.isChecked();
		String msgtxt = "A big text notification content!!"
				+ "Apparently we can put how much ever long content we want, in to this"
				+ "This is awesome! Isn't it ?"
				+ "Just adding some junk to see, How long a text this notification can possibly support"
				+ "Repeating stuff again. Never mind as this is just a trial application";
		NotificationManager notificationManager = getNotificationManager();
		PendingIntent pi = getPendingIntent();
		Builder builder = new Notification.Builder(this);
		builder.setContentTitle("Big text notifi. Title")
				.setContentText("Big text notifi. Text")
				.setSmallIcon(R.drawable.ic_launcher)
				.addAction(R.drawable.ic_action_search, "Show", pi)
				.addAction(R.drawable.ic_launcher, "Reply", pi)
				.setAutoCancel(true).setOngoing(persistanceValue);
		Notification notification = new Notification.BigTextStyle(builder)
				.bigText(msgtxt).build();
		notificationManager.notify(1, notification);
	}

	public void sendBigPictureNotification(View view) {
		final Switch persistentSwitch = (Switch) findViewById(R.id.persistentSwitch);
		boolean persistanceValue = persistentSwitch.isChecked();
		PendingIntent pi = getPendingIntent();
		Builder builder = new Notification.Builder(this);
		builder.setContentText("Big Pic noti content text")
				.setContentInfo("BP noti content info")
				.setContentTitle("Bp noti content title")
				.setSmallIcon(R.drawable.ic_launcher)
				.addAction(R.drawable.ic_launcher, "Show", pi)
				// Alternative way of showing activity. Here the presnt activity
				// is got back from notification
				.addAction(
						R.drawable.ic_launcher_share,
						"share",
						PendingIntent.getActivity(getApplicationContext(), 0,
								getIntent(), 0, null))
				.setOngoing(persistanceValue);
		// Now create the Big picture notification.
		Notification notification = new Notification.BigPictureStyle(builder)
				.bigPicture(
						BitmapFactory.decodeResource(getResources(),
								R.drawable.dev)).build();
		// Put the auto cancel notification flag
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		NotificationManager notificationManager = getNotificationManager();
		notificationManager.notify(2, notification);
	}

	public void sendMessageInboxNotificationButton(View view) {
		final Switch persistentSwitch = (Switch) findViewById(R.id.persistentSwitch);
		boolean persistanceValue = persistentSwitch.isChecked();
		PendingIntent pi = getPendingIntent();
		Builder builder = new Notification.Builder(this)
				.setContentTitle("Mess.Inbox Notification Title")
				.setContentText("Mess.Inbox Notification Text")
				.setSmallIcon(R.drawable.ic_launcher)
				.addAction(R.drawable.ic_launcher_web, "show activity", pi)
				.setOngoing(persistanceValue);

		Notification notification = new Notification.InboxStyle(builder)
				.addLine("First message").addLine("Second message")
				.addLine("Thrid message").addLine("Fourth Message")
				.addLine("Fifth message").addLine("Sixth Message")
				.addLine("seventh message").addLine("eighth Message")
				.addLine("Nineth message").addLine("tenth Message")
				.setSummaryText("+2 more").build();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		NotificationManager notificationManager = getNotificationManager();
		notificationManager.notify(3, notification);
	}

}

