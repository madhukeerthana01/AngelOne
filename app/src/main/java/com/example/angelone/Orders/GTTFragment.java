package com.example.angelone.Orders;//package com.example.angelone.Orders;
//
//import android.app.DownloadManager;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.Toast;
//import androidx.fragment.app.Fragment;
//import com.example.angelone.R;
//public class GTTFragment extends Fragment {
//
//    private Button pdf;
//    private String pdfUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
//    private long downloadId = -1;
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_g_t_t, container, false);
//        pdf = view.findViewById(R.id.pdf);
//
//        pdf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                downloadPdf();
//            }
//        });
//        return view;
//    }
//    private void downloadPdf() {
//        Context context = requireContext();
//        String fileName = "dummy.pdf";
//        Uri uri = Uri.parse(pdfUrl);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, fileName);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
//        request.setTitle(fileName);
//        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//        downloadId = downloadManager.enqueue(request);
//        Toast.makeText(context, "Download started", Toast.LENGTH_SHORT).show();
//        context.registerReceiver(downloadProgressReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
//    }
//    private BroadcastReceiver downloadProgressReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            long receivedDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//            if (receivedDownloadId == downloadId) {
//                DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//                DownloadManager.Query query = new DownloadManager.Query();
//                query.setFilterById(downloadId);
//                final android.database.Cursor cursor = downloadManager.query(query);
//                if (cursor.moveToFirst()) {
//                    int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
//                    int status = cursor.getInt(columnIndex);
//                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
//                        Toast.makeText(context, "Download Finished", Toast.LENGTH_SHORT).show();
//                    } else if (status == DownloadManager.STATUS_FAILED) {
//                        Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show();
//                    } else {
//                        int progressIndex = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
//                        int totalIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
//                        int progress = cursor.getInt(progressIndex);
//                        int total = cursor.getInt(totalIndex);
//                        int percentage = (int) (progress * 100.0 / total);
//                        Toast.makeText(context, "Download in progress: " + percentage + "%", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                cursor.close();
//            }
//        }
//    };
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        requireContext().unregisterReceiver(downloadProgressReceiver);
//    }
//}
import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import com.example.angelone.R;

public class GTTFragment extends Fragment {

    private Button pdf;
    private String pdfUrl = "https://docs.google.com/file/d/0BwmZ0X8TCLyaZ045cUpnNHFnYWs/edit?resourcekey=0-Iy5YzKPoIT3GSv_K0YHnEA";
    private long downloadId = -1;
    private static final String CHANNEL_ID = "DO" +
            "" +
            "WNLOAD_CHANNEL";
    private static final int NOTIFICATION_ID = 1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_g_t_t, container, false);
        pdf = view.findViewById(R.id.pdf);

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadPdf();
            }
        });
        return view;
    }

    private void downloadPdf() {
        Context context = requireContext();
        String fileName = "dummy.pdf";
        Uri uri = Uri.parse(pdfUrl);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, fileName);
        request.setTitle(fileName);

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        downloadId = downloadManager.enqueue(request);

        Toast.makeText(context, "Download started", Toast.LENGTH_SHORT).show();

        // Register BroadcastReceiver to listen for the download progress
        context.registerReceiver(downloadProgressReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private BroadcastReceiver downloadProgressReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long receivedDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (receivedDownloadId == downloadId) {
                DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(downloadId);
                final android.database.Cursor cursor = downloadManager.query(query);
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    int status = cursor.getInt(columnIndex);
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        // Download is successful, show "Download Finished" message
                        Toast.makeText(context, "Download Finished", Toast.LENGTH_SHORT).show();
                        // Cancel the notification
                        cancelDownloadNotification();
                    } else if (status == DownloadManager.STATUS_FAILED) {
                        // Download failed, show error message
                        Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show();
                        // Cancel the notification
                        cancelDownloadNotification();
                    } else {
                        int progressIndex = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
                        int totalIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
                        int progress = cursor.getInt(progressIndex);
                        int total = cursor.getInt(totalIndex);
                        int percentage = (int) (progress * 100.0 / total);
                        Toast.makeText(context, "Download in progress: " + percentage + "%", Toast.LENGTH_SHORT).show();
                        // Update the notification with the download progress
                        updateDownloadNotification(percentage);
                    }
                }
                cursor.close();
            }
        }
    };

    private void createDownloadNotification() {
        Context context = requireContext();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a notification channel for Android Oreo and above
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Download Channel",
                    NotificationManager.IMPORTANCE_LOW);
            channel.setDescription("Download notifications");
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Create a notification with indeterminate progress
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_download)
                .setContentTitle("Download in progress")
                .setProgress(0, 0, true)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


    private void updateDownloadNotification(int progress) {
        Context context = requireContext();

        // Create a notification builder with progress
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_download)
                .setContentTitle("Download in progress")
                .setProgress(100, progress, false)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void cancelDownloadNotification() {
        Context context = requireContext();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancel(NOTIFICATION_ID);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the BroadcastReceiver when the fragment is destroyed
        requireContext().unregisterReceiver(downloadProgressReceiver);
    }
}
