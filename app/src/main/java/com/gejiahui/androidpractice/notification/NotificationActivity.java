package com.gejiahui.androidpractice.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.widget.Button;
import android.widget.RemoteViews;

import com.gejiahui.androidpractice.MainActivity;
import com.gejiahui.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gejiahui on 2016/6/3.
 */
public class NotificationActivity extends AppCompatActivity {
    public static final int TYPE_Normal = 1;
    public static final int TYPE_Progress = 2;
    public static final int TYPE_BigText = 3;
    public static final int TYPE_Inbox = 4;
    public static final int TYPE_BigPicture = 5;
    public static final int TYPE_Hangup = 6;
    public static final int TYPE_Media = 7;
    public static final int TYPE_Customer = 8;

    @Bind(R.id.notification_normal)
    Button normal;
    @Bind(R.id.notification_download)
    Button download;
    @Bind(R.id.notification_bigtext)
    Button bigText;
    @Bind(R.id.notification_inbox)
    Button inbox;
    @Bind(R.id.notification_bigpicture)
    Button bigPicture;
    @Bind(R.id.notification_hangup)
    Button handup;
    @Bind(R.id.notification_MediaStyle)
    Button meida;
    @Bind(R.id.notification_Custom)
    Button custom;

    private NotificationManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }



    @OnClick(R.id.notification_normal)
    void normalClick(){

        simpleNotify();
    }

    @OnClick(R.id.notification_download)
    void downloadClick(){
        int i = 0;
        while(i<=100){
            downloadNotify(i);
            i+=1;

        }

    }

    @OnClick(R.id.notification_bigtext)
    void bigtextClick(){
        bigTextStyle();
    }

    @OnClick(R.id.notification_inbox)
    void inboxClick(){
        inBoxStyle();
    }

    @OnClick(R.id.notification_bigpicture)
    void bigpictureClick(){
        bigPictureStyle();
    }

    @OnClick(R.id.notification_hangup)
    void hangupClick(){
        handup();
    }

    @OnClick(R.id.notification_MediaStyle)
    void mediaStyleClick(){
        mediaStyle();
    }

    @OnClick(R.id.notification_Custom)
    void customeClick(){
        sendCustomerNotification();
    }


    private void simpleNotify(){
//        setTicker 通知到来时低版本上会在系统状态栏显示一小段时间 5.0以上版本好像没有用了
//        setContentInfo和setNumber同时使用 number会被隐藏
//        setSubText显示在通知栏的第三行文本，在低版本上不显示，比如4.0系统
//        setVibrate设置震动 参数是个long[]｛震动时长，间隔时长，震动时长，间隔时长…｝单位毫秒 设置提醒声音 setSound(Uri sound) 一般默认的就好
//        builder.setLights()设置呼吸灯的颜色 并不是所有颜色都被支持 个人感觉没什么用
//        清除通知栏特定通知 manager.cancel(id) id即为manger.notify()的第一个参数

        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Ticker是状态栏显示的提示
        builder.setTicker("简单Notification");
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle("标题");
        //第二行内容 通常是通知正文
        builder.setContentText("通知内容");
        //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
        builder.setSubText("这里显示的是通知第三行内容！");
        //ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
      //  builder.setContentInfo("2");
        //number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo则number会被隐藏
        builder.setNumber(5);
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(true);
        //系统状态栏显示的小图标
        builder.setSmallIcon(R.mipmap.ic_launcher);//
        //下拉显示的大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.a)).setColor(Color.parseColor("#EAA935"));
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        //点击跳转的intent
        builder.setContentIntent(pIntent);
        //通知默认的声音 震动 呼吸灯
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manager.notify(TYPE_Normal,notification);
    }

    private void downloadNotify(int progress){
//        1. setProgress的第三个bool类型的参数表示progressbar的Indeterminate属性 指是否使用不确定模式
//        2. 高版本上progressbar的进度值可以在setContentInfo显示，但是低版本上使用这个属性会导致progressbar不显示，setContentText一样
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.a));
        //禁止用户点击删除按钮删除
        builder.setAutoCancel(false);
        //禁止滑动删除
        builder.setOngoing(true);
        //取消右上角的时间显示
        builder.setShowWhen(false);
        builder.setContentTitle("下载中..."+progress+"%");
        builder.setProgress(100,progress,true);
        builder.setContentInfo(progress+"%");

        Notification notification = builder.build();
        manager.notify(TYPE_Progress,notification);
    }


    private void bigTextStyle(){
/*        1. 使用类 Android.support.v4.app.NotificationCompat.BigTextStyle
        2. 在低版本系统上只显示点击前的普通通知样式 如4.4可以点击展开，在4.0系统上就不行
        3. 点击前后的ContentTitle、ContentText可以不一致，bigText内容可以自动换行 好像最多5行的样子*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("BigTextStyle");
        builder.setContentText("BigTextStyle演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.b));
        android.support.v4.app.NotificationCompat.BigTextStyle style = new android.support.v4.app.NotificationCompat.BigTextStyle();
        style.bigText("这里是点击通知后要显示的正文，可以换行可以显示很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长");
        style.setBigContentTitle("点击后的标题");
        //SummaryText没什么用 可以不设置
        style.setSummaryText("末尾只一行的文字内容");
        builder.setStyle(style);
        builder.setAutoCancel(true);
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
       builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manager.notify(TYPE_BigText,notification);
    }


    private void inBoxStyle(){
//        1. 使用类android.support.v4.app.NotificationCompat.InboxStyle
//        2. 每行内容过长时并不会自动换行
//        3. addline可以添加多行 但是多余5行的时候每行高度会有截断
//        4. 同BigTextStyle 低版本上系统只能显示普通样式
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("InboxStyle");
        builder.setContentText("InboxStyle演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.c));
        android.support.v4.app.NotificationCompat.InboxStyle style = new android.support.v4.app.NotificationCompat.InboxStyle();
        style.setBigContentTitle("BigContentTitle")
                .addLine("第一行，第一行，第一行，第一行，第一行，第一行，第一行")
                .addLine("第二行")
                .addLine("第三行")
                .addLine("第四行")
                .addLine("第五行")
                .addLine("结束")
                .setSummaryText("SummaryText");
        builder.setStyle(style);
        builder.setAutoCancel(true);
        Intent intent = new Intent(this,NotificationActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manager.notify(TYPE_Inbox,notification);
    }

    private void bigPictureStyle(){
/*        1. 使用类android.support.v4.app.NotificationCompat.BigPictureStyle
        2. style.bigPicture传递的是个bitmap对象 所以也不应该传过大的图 否则会oom
        3. 同BigTextStyle 低版本上系统只能显示普通样式*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("BigPictureStyle");
        builder.setContentText("BigPicture演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.d));
        android.support.v4.app.NotificationCompat.BigPictureStyle style = new android.support.v4.app.NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("BigContentTitle");
        style.setSummaryText("SummaryText");
        style.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.e));
        builder.setStyle(style);
        builder.setAutoCancel(true);
     //   Intent intent = new Intent(this,ImageActivity.class);
     //   PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        //设置点击大图后跳转
     //   builder.setContentIntent(pIntent);
        Notification notification = builder.build();
        manager.notify(TYPE_BigPicture,notification);
    }

    private void handup(){
/*        1. 此种效果只在5.0以上系统中有效
        2. mainfest中需要添加<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
        3. 可能还需要在设置开启横幅通知权限（在设置通知管理中）
        4. 在部分改版rom上可能会直接弹出应用而不是显示横幅*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("横幅通知");
        builder.setContentText("请在设置通知管理中开启消息横幅提醒权限");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.b));
        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);
        //这句是重点
        builder.setFullScreenIntent(pIntent,true);
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        manager.notify(TYPE_Hangup,notification);
    }

    private void mediaStyle(){
/*        1. 使用类v7包下的NotificationCompat.MediaStyle
        2. addAction方法并普通样式也可以用，使用后普通通知可以点击展开，展开部分会显示一排添加的图标，并且可以给每个图标设置不同的点击事件
        3. 最多可以添加5哥action 并排显示在点击展开的部分
        4. setShowActionsInCompactView的参数是添加的action在所有action组成的数组中的下标，从0开始
        5. setShowActionsInCompactView设置的action会显示在点击前的通知的右侧，低版本上也可以显示
        6. setShowCancelButton(true)会在通知的右上部分显示一个删除图标 5.0以下有效*/

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("MediaStyle");
        builder.setContentText("Song Title");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.a));
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        try {
            pIntent.send(1);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
        builder.setContentIntent(pIntent);
        //第一个参数是图标资源id 第二个是图标显示的名称，第三个图标点击要启动的PendingIntent
        builder.addAction(R.drawable.ic_favorite_border_white_18dp,"",null);
        builder.addAction(R.drawable.ic_help_white_18dp,"",null);
        builder.addAction(R.drawable.ic_power_settings_new_white_18dp,"",null);
        builder.addAction(R.drawable.ic_search_white_18dp,"",null);
        NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle();
        style.setMediaSession(new MediaSessionCompat(this,"MediaSession",
                new ComponentName(this,Intent.ACTION_MEDIA_BUTTON),null).getSessionToken());
        //CancelButton在5.0以下的机器有效
        style.setCancelButtonIntent(pIntent);
        style.setShowCancelButton(true);
        //设置要现实在通知右方的图标 最多三个
        style.setShowActionsInCompactView(2,3);
        builder.setStyle(style);
        builder.setShowWhen(false);
        Notification notification = builder.build();
        manager.notify(TYPE_Media,notification);

    }



    private void sendCustomerNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Notification");
        builder.setContentText("自定义通知栏示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.f));
        builder.setAutoCancel(true);
        builder.setOngoing(false);
        builder.setShowWhen(false);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_custom);

        remoteViews.setTextViewText(R.id.title,"Notification");
        remoteViews.setTextViewText(R.id.text,"song");
//        if(command==CommandNext){
            remoteViews.setImageViewResource(R.id.btn1,R.drawable.triangle);
//        }else if(command==CommandPlay){
//            if(playerStatus==StatusStop){
//                remoteViews.setImageViewResource(R.id.btn1,R.drawable.ic_pause_white);
//            }else{
//                remoteViews.setImageViewResource(R.id.btn1,R.drawable.ic_play_arrow_white_18dp);
//            }
//        }
        Intent Intent1 = new Intent(this,MainActivity.class);
        Intent1.putExtra("command",1);
        //getService(Context context, int requestCode, @NonNull Intent intent, @Flags int flags)
        //不同控件的requestCode需要区分开 getActivity broadcoast同理
        PendingIntent PIntent1 =  PendingIntent.getActivity(this,5,Intent1,0);
        remoteViews.setOnClickPendingIntent(R.id.btn1,PIntent1);
//
//        Intent Intent2 = new Intent(this,MediaService.class);
//        Intent2.putExtra("command",CommandNext);
//        PendingIntent PIntent2 =  PendingIntent.getService(this,6,Intent2,0);
//        remoteViews.setOnClickPendingIntent(R.id.btn2,PIntent2);
//
//        Intent Intent3 = new Intent(this,MediaService.class);
//        Intent3.putExtra("command",CommandClose);
//        PendingIntent PIntent3 =  PendingIntent.getService(this,7,Intent3,0);
//        remoteViews.setOnClickPendingIntent(R.id.btn3,PIntent3);

        builder.setContent(remoteViews);
        Notification notification = builder.build();

        manager.notify(TYPE_Customer,notification);
    }

}
