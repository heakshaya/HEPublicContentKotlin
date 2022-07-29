package com.example.hepubliccontent.presentation.util;

import static android.view.View.LAYER_TYPE_SOFTWARE;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.hubengage.network.BuildConfig;
import com.hubengage.network.R;
import com.hubengagesdk.HubEngage;
import com.hubengagesdk.app.activities.EnterKeyScreenActivity;
import com.hubengagesdk.base.constants.StringConstant;
import com.hubengagesdk.content.new_models.Content;
import com.hubengagesdk.content.wish.ActivityBirthdayWish;
import com.hubengagesdk.dragtodismiss.DragToDismissActivity;
import com.hubengagesdk.dragtodismiss.MediaData;
import com.hubengagesdk.pdfviewpager.activities.PDFViewPagerActivity;
import com.hubengagesdk.preferences.NewPreferenceHelper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okio.BufferedSource;
import okio.Okio;

public class Utilities {

    public static final String HUBENGAGE_CLASS = "com.hubengagesdk.HubEngage";


    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidthHalf() {

        return Resources.getSystem().getDisplayMetrics().widthPixels / 2;
    }

    public static String getCurrentDateTimeInUTC() {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String strCurrentDate = null;
        try {
            strCurrentDate = inputFormat.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strCurrentDate;
    }


    public static void appendLog(String text) {
       /* File logFile = new File(Environment.getExternalStorageDirectory().getPath(), "he_log.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }

    private static boolean isDebugable = false;
    private static HashMap<String, String> colorMap = new HashMap<>();
    private static HashMap<String, String> menucolorMap = new HashMap<>();
    private static Map<String, String> initialsColors = new HashMap<>();

    static {
        initialsColors.put("A", "#EF5350");
        initialsColors.put("B", "#F06292");
        initialsColors.put("C", "#BA68C8");
        initialsColors.put("D", "#9575CD");
        initialsColors.put("E", "#7986CB");
        initialsColors.put("F", "#2196F3");
        initialsColors.put("G", "#039BE5");
        initialsColors.put("H", "#0097A7");
        initialsColors.put("I", "#009688");
        initialsColors.put("J", "#43A047");
        initialsColors.put("K", "#689F38");
        initialsColors.put("L", "#827717");
        initialsColors.put("M", "#EF6C00");
        initialsColors.put("N", "#D32F2F");
        initialsColors.put("O", "#A1887F");
        initialsColors.put("P", "#757575");
        initialsColors.put("Q", "#78909C");
        initialsColors.put("R", "#01579B");
        initialsColors.put("S", "#00695C");
        initialsColors.put("T", "#FF5722");
        initialsColors.put("U", "#FF4081");
        initialsColors.put("V", "#9C27B0");
        initialsColors.put("W", "#673AB7");
        initialsColors.put("X", "#3F51B5");
        initialsColors.put("Y", "#1976D2");
        initialsColors.put("Z", "#00796B");
        initialsColors.put("DEFAULT", "#8a816e");

    }

    /**
     * Gets the width of the layout according
     * to the percentage given
     *
     * @param context
     * @return width of the layout in integer according to the percentage given
     */
    public static int GetWidthByPercentage(Context context, int percentage) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (metrics.widthPixels / 100) * percentage;
    }

    public static int GetDeviceWidth(Activity context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }


    public static float getDeviceWidthInChat(Activity context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return dpWidth;
    }


    public static float getDeviceHeightInChat(Activity context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        return dpHeight;
    }

    /**
     * Gets the height of the layout according
     * to the percentage given
     *
     * @param context
     * @return height of the layout in integer according to the percentage given
     */
    public static int GetHeightByPercentage(Context context, int percentage) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (metrics.heightPixels / 100) * percentage;
    }

    /**
     * To convert the InputStream to String we use the BufferedReader.readLine()
     * method. We iterate until the BufferedReader return null which means
     * there's no more data to read. Each line will appended to a StringBuilder
     * and returned as String.
     *
     * @param is
     * @return string
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * Converts string to input stream
     *
     * @param content string to be converted
     * @return Input stream of string
     */

    public static InputStream convertStringToStream(String content) {
        InputStream stream = null;
        try {
            stream = new ByteArrayInputStream(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        return stream;
    }

    /**
     * Checks whether the string is empty or null or having White space
     *
     * @param
     * @return
     */
    public static boolean IsNullOrEmptyWhiteSpace(String param) {
        return param == null || param.trim().length() == 0;
    }

    public static String generateUniqueID(String formID, Date date) {
        long millis = date.getTime();

        return (millis + "");
    }

    public static String generateUniqueMessageID() {
       /*
        old id format
       String unique_id = "android_";
        Long millis = System.currentTimeMillis() / 1000;

        unique_id = unique_id + millis.toString() + "_" + UUID.randomUUID();*/

        String unique_id = "a-";

        unique_id = unique_id + UUID.randomUUID();

        return unique_id;
    }

    public static int generateRandomID() {
        Random random = new Random();

        int unique_id = random.nextInt(4);

        return unique_id;
    }


/*    static final String UNIQUE_ID_CHARACTERS =
"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(){
        int len=16;
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( UNIQUE_ID_CHARACTERS.charAt( rnd.nextInt(UNIQUE_ID_CHARACTERS.length())));
        return sb.toString();
    }*/


    public static String getRoundedValueUpto5Decimals(double value) {
        DecimalFormat myFormat = new DecimalFormat("0.00000");
        return myFormat.format(value);
    }


    public static void setOrientation(Activity activity) {
        //	if(activity.getResources().getBoolean(R.bool.portrait_only)){
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //   }
    }


    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
/*
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);*/

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void singleButtonDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void okButtonDialog(
            Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static String getDateInRequiredFormat(String currentFormat, String requiredFormat,
                                                 String strDate) {

        DateFormat currentDateFormat = new SimpleDateFormat(currentFormat, Locale.getDefault());
        DateFormat requiredDateFormat = new SimpleDateFormat(requiredFormat);
        Date date = null;
        try {
            date = currentDateFormat.parse(strDate);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        String formattedDate = requiredDateFormat.format(date);
        return formattedDate;

    }


    public static boolean isAppInForeground(
            Context context) {
        List<RunningTaskInfo> task = ((ActivityManager)
                context.getSystemService(
                        Context.ACTIVITY_SERVICE))
                .getRunningTasks(1);
        if (task.isEmpty()) {
            return false;
        }
        return task
                .get(0)
                .topActivity
                .getPackageName()
                .equalsIgnoreCase(
                        context.getPackageName());
    }


    public static boolean isChatActivity(
            Context context) {
        List<RunningTaskInfo> task = ((ActivityManager)
                context.getSystemService(
                        Context.ACTIVITY_SERVICE))
                .getRunningTasks(1);
        if (task.isEmpty()) {
            return false;
        }
        return task
                .get(0)
                .topActivity
                .getClassName()
                .equalsIgnoreCase("com.hubengage.chat.arch.ChatActivityArch");
    }


    /* show action in hubengage class*/
    public static void showAction(Context context) {

        if (ReflectionUtils.isClassPresent(HUBENGAGE_CLASS)) {
            Class<?> chatPreferenceClass =
                    ReflectionUtils.getRequiredClass(HUBENGAGE_CLASS);
            try {
                Method method = chatPreferenceClass.getDeclaredMethod("showAction", Context.class);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                method.invoke(null, context);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /* show action in hubengage class*/
    public static void addActivity(Context context) {

        if (ReflectionUtils.isClassPresent(HUBENGAGE_CLASS)) {
            Class<?> chatPreferenceClass =
                    ReflectionUtils.getRequiredClass(HUBENGAGE_CLASS);
            try {
                Method method = chatPreferenceClass.getDeclaredMethod("addActivity", Context.class);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                method.invoke(null, context);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public static String getCurrentTopActivity(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);

        // get the info from the currently running task
        List<RunningTaskInfo> taskInfo = am.getRunningTasks(1);

        Utilities.d("topActivity", "CURRENT Activity ::"
                + taskInfo.get(0).topActivity.getClassName());

        ComponentName componentInfo = taskInfo.get(0).topActivity;
        componentInfo.getPackageName();
        return taskInfo.get(0).topActivity.getClassName();

    }


    public static long getDifferenceInSeconds(String start, String end) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        Date currentDate = null;
        long differenceInSeconds = 0;

        try {
            startDate = format.parse(start);
            currentDate = format.parse(end);

            long difference = currentDate.getTime() - startDate.getTime();
            differenceInSeconds = difference / 1000 % 60;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return differenceInSeconds;
    }

    // get package name from .properties
    public static String getPackageName(Context context) {
        Resources resources = context.getApplicationContext().getResources();
        AssetManager assetManager = resources.getAssets();

        // Read from the /assets directory
        try {
            InputStream inputStream = assetManager.open("hubengage.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            return properties.getProperty("APPLICATION_PACKAGE_NAME");
        } catch (IOException e) {
            System.err.println("Failed to open microlog property file");
            e.printStackTrace();
        }
        return "";
    }


    public static void saveNoteOnSD(String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(),
                    "MIARNotes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public static String formatToShowChatMsgDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dateTime = dateFormat.parse(date);
        ;//new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(date);dfgdf


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        Calendar today = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        DateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
        //  DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

        if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
            return "Today";
        } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)) {
            return "Yesterday";
        } else {
            return dateFormatter.format(dateTime);
        }
    }

    public static String convertLongToDate(long sentDate, String requiredFormat) {
        return new SimpleDateFormat(requiredFormat).format(new Date(sentDate));
    }

    public static String getDateInUTCFormat() {
        //   String delegate = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        ISO8601DATEFORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        //    String currentTime = (String) android.text.format.DateFormat.format(delegate, new
        //    Date());
        return ISO8601DATEFORMAT.format(new Date());
    }


    public static String getDateWithMilliSec() {
        //   String delegate = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS");
//        ISO8601DATEFORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        //    String currentTime = (String) android.text.format.DateFormat.format(delegate, new
        //    Date());
        return ISO8601DATEFORMAT.format(new Date());
    }


    // Make * red in color for manadatory question
    public static SpannableString getManadatoryQuestion(String strQuestion) {
        SpannableString ss = new SpannableString(strQuestion);
        ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, 0);
        return ss;
    }


    // check if top running activity is chatlist when notification comes for chat group
    public static boolean isChatListActivityRunning(Context context) {
        boolean isChatListRunning = false;
        List<RunningTaskInfo> task = ((ActivityManager)
                context.getSystemService(
                        Context.ACTIVITY_SERVICE))
                .getRunningTasks(1);


        for (RunningTaskInfo aTask : task) {
            String currentClassName = aTask.topActivity.getClassName();
            if (currentClassName.equals("com.hubengagesdk.chat.activities.ChatListActivity")) {
                isChatListRunning = true;
            } else {
                isChatListRunning = false;
                break;
            }
        }
        return isChatListRunning;
    }

    public static boolean checkChatListScreenPostLoliPop(Context context) {
        boolean isChatListRunning = false;
        ActivityManager manager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> tasks = manager.getRunningAppProcesses();

        try {

            for (int i = 0; i < tasks.size(); i++) {
                Utilities.i("current_app", tasks.get(i).importanceReasonComponent.getClassName());
            }

            if (tasks.get(0).processName.equals("com.hubengage.chat.activity.ChatListActivity")) {
                isChatListRunning = true;
            } else {
                isChatListRunning = false;

            }
        } catch (Exception e) {
            isChatListRunning = false;
            e.printStackTrace();
        }
        return isChatListRunning;
    }

    public static void hideSoftKeyboard(Context context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null && ((Activity) context).getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendHeartBeatsToGcm(Context context) {
        context.sendBroadcast(new Intent("com.google.android.intent.action.GTALK_HEARTBEAT"));
        context.sendBroadcast(new Intent("com.google.android.intent.action.MCS_HEARTBEAT"));
    }

    public static boolean isValidMobile(String phone) {
        boolean check;
        if (phone.length() > 6 && phone.length() <= 13) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }


    public static int getRequiredDrawableId(Context context, String strDrawable) {
        int icon = 0;

        String strPackageName = Utilities.getPackageName(context);

        try {
            ApplicationInfo ai;
            PackageManager manager = context.getPackageManager();
            Resources resources = manager
                    .getResourcesForApplication(strPackageName);
            ai = manager.getApplicationInfo(strPackageName, 0);
            icon = resources.getIdentifier(strDrawable, "drawable",
                    strPackageName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return icon;
    }

    public static HashMap<String, String> setColorsToHashMap() {
        HashMap<String, String> hmColors = new HashMap<String, String>();
        hmColors.put("A", "#EF5350");
        hmColors.put("B", "#F06292");
        hmColors.put("C", "#BA68C8");
        hmColors.put("D", "#9575CD");
        hmColors.put("E", "#7986CB");
        hmColors.put("F", "#2196F3");
        hmColors.put("G", "#039BE5");
        hmColors.put("H", "#0097A7");
        hmColors.put("I", "#009688");
        hmColors.put("J", "#43A047");
        hmColors.put("K", "#689F38");
        hmColors.put("L", "#827717");
        hmColors.put("M", "#EF6C00");
        hmColors.put("N", "#D32F2F");
        hmColors.put("O", "#A1887F");
        hmColors.put("P", "#757575");
        hmColors.put("Q", "#78909C");
        hmColors.put("R", "#01579B");
        hmColors.put("S", "#00695C");
        hmColors.put("T", "#FF5722");
        hmColors.put("U", "#FF4081");
        hmColors.put("V", "#9C27B0");
        hmColors.put("W", "#673AB7");
        hmColors.put("X", "#3F51B5");
        hmColors.put("Y", "#1976D2");
        hmColors.put("Z", "#00796B");
        hmColors.put("DEFAULT", "#8a816e");
        return hmColors;
    }

    public static String getBgColorAsPerName(String mLetter) {

        HashMap<String, String> hmColors = setColorsToHashMap();
        if (mLetter != null && mLetter.length() > 0) {
            if (mLetter.length() == 2) {
                char[] charArray = mLetter.toCharArray();

                if (hmColors.containsKey(String.valueOf(charArray[1]))) {
                    return hmColors.get(String.valueOf(charArray[1]));
                }
            } else {
                if (hmColors.containsKey(mLetter)) {
                    return hmColors.get(mLetter);
                }
            }
        } else {
            return hmColors.get("DEFAULT");
        }
        return hmColors.get("DEFAULT");
    }


    public static File getAudioDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {

            storageDir = new File(
                    Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "HubEngage/audio");

            if (storageDir != null) {
                if (!storageDir.mkdirs()) {
                    if (!storageDir.exists()) {
                        return null;
                    }
                }
            }

        } else {
        }
        return storageDir;
    }


    public static String getName(String firstName, String lastName) {
        String strLetters = "";

/*
 Removed now - handled from backend & Sushant suggested to remove it from app side
 Added this to handle last name null issue
 currently, if lastName getting null then application crashes */
        if (TextUtils.isEmpty(firstName)) {
            firstName = "";
        } else
            firstName = firstName.trim();

        if (lastName == null || TextUtils.isEmpty(lastName)) {
            lastName = "";
        } else
            lastName = lastName.trim();
//===================== handled null values ===================== //


        if (firstName.length() > 0 && lastName.length() > 0) {
            strLetters = "" + firstName.charAt(0) + lastName.charAt(0);
        } else {
            if (firstName != null && firstName.length() > 0) {
                strLetters = "" + firstName.charAt(0);
            }
            if (lastName != null && lastName.length() > 0) {
                strLetters = strLetters + lastName.charAt(0);
            }
        }
        return strLetters.toUpperCase();
    }

    public static String getUserName(String firstName, String lastName) {
        String strLetters = "";

        if (TextUtils.isEmpty(firstName)) {
            firstName = "";
        }

        if (TextUtils.isEmpty(lastName)) {
            lastName = "";
        }

        // if (!firstName.equals(null)) {
        firstName = firstName.trim();
        // }
        // if (!lastName.equals(null)) {
        lastName = lastName.trim();
        // }

        if (!TextUtils.isEmpty(firstName) && firstName.length() >= 2 && !firstName.equalsIgnoreCase("all")) {
            firstName = firstName.toLowerCase();
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        }else if (!TextUtils.isEmpty(firstName) && firstName.length()==1){
            firstName=firstName.toUpperCase();
        }

        if (!TextUtils.isEmpty(lastName) && lastName.length() >= 2) {
            lastName = lastName.toLowerCase();
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        }else if (!TextUtils.isEmpty(lastName) && lastName.length()==1){
            lastName=lastName.toUpperCase();
        }

        if (firstName.length() > 0 && lastName.length() > 0) {
            strLetters = "" + firstName + " " + lastName;
        } else {
            if (firstName.length() > 0) {
                strLetters = "" + firstName;
            }
            if (lastName.length() > 0) {
                strLetters = strLetters + " " + lastName;
            }
        }
        return strLetters;
    }

    public static String getUserName(String preferredName, String firstName, String lastName) {
        String strLetters = "";

        if (TextUtils.isEmpty(preferredName)) {
            preferredName = "";
        }

        if (TextUtils.isEmpty(firstName)) {
            firstName = "";
        }

        if (TextUtils.isEmpty(lastName)) {
            lastName = "";
        }

        // if (!firstName.equals(null)) {
        preferredName = preferredName.trim();
        firstName = firstName.trim();
        // }
        // if (!lastName.equals(null)) {
        lastName = lastName.trim();
        // }

        if (!TextUtils.isEmpty(preferredName) && preferredName.length() >= 2) {
            preferredName = preferredName.toLowerCase();
            preferredName = preferredName.substring(0, 1).toUpperCase() + preferredName.substring(1);
        }else if (!TextUtils.isEmpty(firstName) && firstName.length()==1){
            preferredName=preferredName.toUpperCase();
        }

        if (!TextUtils.isEmpty(firstName) && firstName.length() >= 2 && !firstName.equalsIgnoreCase("all")) {
            firstName = firstName.toLowerCase();
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        }else if (!TextUtils.isEmpty(firstName) && firstName.length()==1){
            firstName=firstName.toUpperCase();
        }

        if (!TextUtils.isEmpty(lastName) && lastName.length() >= 2) {
            lastName = lastName.toLowerCase();
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        }else if (!TextUtils.isEmpty(lastName) && lastName.length()==1){
            lastName=lastName.toUpperCase();
        }


        if (preferredName.length() > 0 && lastName.length() > 0) {
            strLetters = "" + preferredName + " " + lastName;
        } else if (firstName.length() > 0 && lastName.length() > 0) {
            strLetters = "" + firstName + " " + lastName;
        } else {
            if (preferredName.length() > 0) {
                strLetters = "" + preferredName;
            } else if (firstName.length() > 0) {
                strLetters = "" + firstName;
            }
            if (lastName.length() > 0) {
                strLetters = strLetters + " " + lastName;
            }
        }
        return strLetters;
    }

    public static String getNameWithLastInitials(String firstName, String lastName) {
        String strLetters = "";

        if (TextUtils.isEmpty(firstName)) {
            firstName = "";
        }

        if (TextUtils.isEmpty(lastName)) {
            lastName = "";
        }

        if (!firstName.equals(null)) {
            firstName = firstName.trim();
        }
        if (!lastName.equals(null)) {
            lastName = lastName.trim();
        }

        if (!TextUtils.isEmpty(firstName) && firstName.length() >= 2 && !firstName.equalsIgnoreCase("all")) {
            firstName = firstName.toLowerCase();
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        }else if (!TextUtils.isEmpty(firstName) && firstName.length()==1){
            firstName=firstName.toUpperCase();
        }

        if (!TextUtils.isEmpty(lastName) && lastName.length() >= 2) {
            lastName = lastName.toLowerCase();
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        }

        if (firstName != null && lastName != null && firstName.length() > 0 && lastName.length() > 0) {
            strLetters = "" + firstName + " " + lastName.charAt(0);
        } else {
            if (firstName != null && firstName.length() > 0) {
                strLetters = "" + firstName;
            }
            if (lastName != null && lastName.length() > 0) {
                strLetters = strLetters + " " + lastName.charAt(0) ;
            }
        }
        return strLetters;
    }


    public static String getMentionUserName(String firstName, String lastName) {
        String strLetters = "";
        if (firstName != null && lastName != null && firstName.length() > 0 && lastName.length() > 0) {
            strLetters = "" + firstName + "_" + lastName;
        } else {
            if (firstName != null && firstName.length() > 0) {
                strLetters = "" + firstName;
            }
            if (lastName != null && lastName.length() > 0) {
                strLetters = strLetters + " " + lastName;
            }
        }
        strLetters = strLetters.replace(" ", "_");
        return strLetters;
    }

    public static int getAppIcon(Context context) {
        int icon = 0;

        String strPackageName = Utilities.getPackageName(context);
        try {
            ApplicationInfo ai;
            PackageManager manager = context.getPackageManager();
            Resources resources = manager
                    .getResourcesForApplication(strPackageName);

            ai = manager.getApplicationInfo(strPackageName, 0);
            icon = resources.getIdentifier("app_icon", "drawable",
                    strPackageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (icon == 0)
            icon = R.drawable.he_logo;

        return icon;
    }

    public static int getNotificationIcon(Context context) {
        int icon = 0;

        String strPackageName = Utilities.getPackageName(context);

        try {
            ApplicationInfo ai;
            PackageManager manager = context.getPackageManager();
            Resources resources = manager
                    .getResourcesForApplication(strPackageName);

            ai = manager.getApplicationInfo(strPackageName, 0);
            icon = resources.getIdentifier("notification_icon_new", "drawable",
                    strPackageName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (icon == 0)
            icon = R.drawable.he_logo;

        return icon;
    }

    public static int getNotificationIconTransparent(Context context) {
        int icon = 0;

        String strPackageName = Utilities.getPackageName(context);

        try {
            ApplicationInfo ai;
            PackageManager manager = context.getPackageManager();
            Resources resources = manager
                    .getResourcesForApplication(strPackageName);

            ai = manager.getApplicationInfo(strPackageName, 0);
            icon = resources.getIdentifier("notification_icon_transparent", "drawable",
                    strPackageName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (icon == 0)
            icon = R.drawable.he_logo;

        return icon;
    }

    /*public static int getTransparentNotificationIcon(Context context) {
        int icon = 0;

        String strPackageName = Utilities.getPackageName(context);

        try {
            ApplicationInfo ai;
            PackageManager manager = context.getPackageManager();
            Resources resources = manager
                    .getResourcesForApplication(strPackageName);

            ai = manager.getApplicationInfo(strPackageName, 0);
            icon = resources.getIdentifier("notification_icon_new_transperent", "drawable",
                    strPackageName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (icon == 0)
            icon = R.drawable.he_logo;

        return icon;
    }*/

    public static String getAppName(Context context) {
        String applicationName = "";

        String strPackageName = Utilities.getPackageName(context);

        try {
            ApplicationInfo ai;
            PackageManager manager = context.getPackageManager();
            Resources resources = manager
                    .getResourcesForApplication(strPackageName);

            ai = manager.getApplicationInfo(strPackageName, 0);

            applicationName = (String) (ai != null ? manager
                    .getApplicationLabel(ai) : "(HubEngage)");

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return applicationName;

    }


    public static void v(String title, String value) {
        if (BuildConfig.DEBUG) {
            Log.v("" + title, "" + value);
        }
    }


    public static void i(String title, String value) {
        if (BuildConfig.DEBUG) {
            Log.i("" + title, "" + value);
        }
    }

    public static void w(String title, String value) {
        if (BuildConfig.DEBUG) {
            Log.w("" + title, "" + value);
        }
    }


    public static void e(String title, String value) {

        if (BuildConfig.DEBUG) {
            Log.e("" + title, "" + value);
        }

    }

    public static void e(String title, String value, Throwable tr) {
        if (BuildConfig.DEBUG) {
            Log.e("" + title, "" + value, tr);
        }
    }

    public static void d(String title, String value) {
        if (BuildConfig.DEBUG) {
            Log.d("" + title, "" + value);
        }
    }


    public static int dpToPx(int dp, Resources resources) {
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static boolean checkToShowGoogleDocViewer(String filenameOriginal) {
        // There does not seem to be a way to ask the OS or file itself for this
        // information, so unfortunately resorting to extension sniffing.

       /* if (filename.startsWith("ftp:")) {
            return false;
        }*/

        String filename = null;
        try {
            filename = getFileUrlOnWebActivity(filenameOriginal);
        } catch (Exception e) {
            filename = filenameOriginal;
            e.printStackTrace();
        }

        boolean isValidDocUrl = false;

        int pos = filename.lastIndexOf('.');
        if (pos != -1) {
            String ext = filename.substring(filename.lastIndexOf('.') + 1,
                    filename.length());

          /*Image files (.JPEG, .PNG, .GIF, .TIFF, .BMP)
            Video files (WebM, .MPEG4, .3GPP, .MOV, .AVI, .MPEGPS, .WMV, .FLV)
            Text files (.TXT)
            Markup/Code (.CSS, .HTML, .PHP, .C, .CPP, .H, .HPP, .JS)
            Microsoft Word (.DOC and .DOCX)
            Microsoft Excel (.XLS and .XLSX)
            Microsoft PowerPoint (.PPT and .PPTX)
            Adobe Portable Document Format (.PDF)
            Apple Pages (.PAGES)
            Adobe Illustrator (.AI)
            Adobe Photoshop (.PSD)
            Tagged Image File Format (.TIFF)
            Autodesk AutoCad (.DXF)
            Scalable Vector Graphics (.SVG)
            PostScript (.EPS, .PS)
            TrueType (.TTF)
            XML Paper Specification (.XPS)
            Archive file types (.ZIP and .RAR)
          */

            if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX"))
                isValidDocUrl = true;
            if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX"))
                isValidDocUrl = true;
            if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX"))
                isValidDocUrl = true;
           /* if (ext.equalsIgnoreCase("TXT"))
                isValidDocUrl = true;*/
            if (ext.equalsIgnoreCase("PDF"))
                isValidDocUrl = true;
            if (ext.equalsIgnoreCase("CSV"))
                isValidDocUrl = true;
        }
        return isValidDocUrl;
    }


    public static boolean checkIsDocument(String filename) {
        try {
            URL url = new URL(filename);
            URLConnection urlConnection = url.openConnection();
            String type = urlConnection.getContentType();
            if (!TextUtils.isEmpty(type) && type.toLowerCase().contains("html"))
                return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static boolean checkToShowOfficeAppsViewer(String filenameOriginal) {
//        // There does not seem to be a way to ask the OS or file itself for this
//        // information, so unfortunately resorting to extension sniffing.
//
//        String filename = null;
//        try {
//            filename = getFileUrlOnWebActivity(filenameOriginal);
//        } catch (Exception e) {
//            filename = filenameOriginal;
//            e.printStackTrace();
//        }
//
//
//        boolean isValidDocUrl = false;
//        int pos = filename.lastIndexOf('.');
//        if (pos != -1) {
//            String ext = filename.substring(filename.lastIndexOf('.') + 1,
//                    filename.length());
//
//            if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX") ||
//                    ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX") ||
//                    ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {
//                isValidDocUrl = true;
//            }
//        }
//        return isValidDocUrl;
//    }

   /* public static boolean checkToShowOfficeAppsViewer(String filename) {
        // There does not seem to be a way to ask the OS or file itself for this
        // information, so unfortunately resorting to extension sniffing.
        boolean isValidDocUrl = false;
        int pos = filename.lastIndexOf('.');
        if (pos != -1) {
            String ext = filename.substring(filename.lastIndexOf('.') + 1,
                    filename.length());

            if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX") ||
                    ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX") ||
                    ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {
                isValidDocUrl = true;
            }
        }
        return isValidDocUrl;
    }*/

    public static boolean checkToShowPdfViewer(String filenameOriginal) {
        // There does not seem to be a way to ask the OS or file itself for this
        // information, so unfortunately resorting to extension sniffing.


        String filename = null;
        try {
            filename = getFileUrlOnWebActivity(filenameOriginal);
        } catch (Exception e) {
            filename = filenameOriginal;
            e.printStackTrace();
        }

        boolean isValidDocUrl = false;
        int pos = filename.lastIndexOf('.');
        if (pos != -1) {
            String ext = filename.substring(filename.lastIndexOf('.') + 1,
                    filename.length());

            if (ext.equalsIgnoreCase("PDF")) {
                isValidDocUrl = true;
            }
        }
        return isValidDocUrl;
    }

    public static String extractUrlForAttachment(String label, String url) throws Exception {
        /*if (getFileExtension(label).equalsIgnoreCase("docx")) {
            url = url.substring(0, url.indexOf(".docx") + ".docx".length());
        } else if (getFileExtension(label).equalsIgnoreCase("doc")) {
            url = url.substring(0, url.indexOf(".doc") + ".doc".length());
        } else if (getFileExtension(label).equalsIgnoreCase("xlsx")) {
            url = url.substring(0, url.indexOf(".xlsx") + ".xlsx".length());
        } else if (getFileExtension(label).equalsIgnoreCase("xls")) {
            url = url.substring(0, url.indexOf(".xls") + ".xls".length());
        } else if (getFileExtension(label).equalsIgnoreCase("ppt")) {
            url = url.substring(0, url.indexOf(".ppt") + ".ppt".length());
        } else if (getFileExtension(label).equalsIgnoreCase("pptx")) {
            url = url.substring(0, url.indexOf(".pptx") + ".pptx".length());
        } else if (getFileExtension(label).equalsIgnoreCase("html")) {
            url = url.substring(0, url.indexOf(".html") + ".html".length());
        } else if (getFileExtension(label).equalsIgnoreCase("txt")) {
            url = url.substring(0, url.indexOf(".txt") + ".txt".length());
        } else if (getFileExtension(label).equalsIgnoreCase("pdf")) {
            url = url.substring(0, url.lastIndexOf(".pdf") + ".pdf".length());
        } else if (getFileExtension(label).equalsIgnoreCase("png")) {
            url = url.substring(0, url.indexOf(".png") + ".png".length());
        } else if (getFileExtension(label).equalsIgnoreCase("jpg")) {
            url = url.substring(0, url.indexOf(".jpg") + ".jpg".length());
        } else if (getFileExtension(label).equalsIgnoreCase("jpeg")) {
            url = url.substring(0, url.indexOf(".jpeg") + ".jpeg".length());
        } else if (getFileExtension(label).equalsIgnoreCase("gif")) {
            url = url.substring(0, url.indexOf(".gif") + ".gif".length());
        }*/
        return url;
    }


    public static String extractUrl(String label, String url) throws Exception {
        if (getFileExtension(label).equalsIgnoreCase("docx")) {
            url = url.substring(0, url.indexOf(".docx") + ".docx".length());
        } else if (getFileExtension(label).equalsIgnoreCase("doc")) {
            url = url.substring(0, url.indexOf(".doc") + ".doc".length());
        } else if (getFileExtension(label).equalsIgnoreCase("xlsx")) {
            url = url.substring(0, url.indexOf(".xlsx") + ".xlsx".length());
        } else if (getFileExtension(label).equalsIgnoreCase("xls")) {
            url = url.substring(0, url.indexOf(".xls") + ".xls".length());
        } else if (getFileExtension(label).equalsIgnoreCase("ppt")) {
            url = url.substring(0, url.indexOf(".ppt") + ".ppt".length());
        } else if (getFileExtension(label).equalsIgnoreCase("pptx")) {
            url = url.substring(0, url.indexOf(".pptx") + ".pptx".length());
        } else if (getFileExtension(label).equalsIgnoreCase("html")) {
            url = url.substring(0, url.indexOf(".html") + ".html".length());
        } else if (getFileExtension(label).equalsIgnoreCase("txt")) {
            url = url.substring(0, url.indexOf(".txt") + ".txt".length());
        } else if (getFileExtension(label).equalsIgnoreCase("pdf")) {
            url = url.substring(0, url.indexOf(".pdf") + ".pdf".length());
        } else if (getFileExtension(label).equalsIgnoreCase("png")) {
            url = url.substring(0, url.indexOf(".png") + ".png".length());
        } else if (getFileExtension(label).equalsIgnoreCase("jpg")) {
            url = url.substring(0, url.indexOf(".jpg") + ".jpg".length());
        } else if (getFileExtension(label).equalsIgnoreCase("jpeg")) {
            url = url.substring(0, url.indexOf(".jpeg") + ".jpeg".length());
        } else if (getFileExtension(label).equalsIgnoreCase("gif")) {
            url = url.substring(0, url.indexOf(".gif") + ".gif".length());
        }
        return url;
    }

//    public static String getFileUrl(String url) throws Exception {
//
//        try {
//            URI uri = new URI(url);
//            return new URI(uri.getScheme(),
//                    uri.getAuthority(),
//                    uri.getPath(),
//                    uri.getQuery(), // Ignore the query part of the input url
//                    null).toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return HubEngage.getEncodedUrl(url);
//            //return url;
//        }
//    }

    public static String getFileUrlOnWebActivity(String url) throws Exception {

        try {
            URI uri = new URI(url);
            return new URI(uri.getScheme(),
                    uri.getAuthority(),
                    uri.getPath(),
                    null, // Ignore the query part of the input url
                    null).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return HubEngage.getEncodedUrl(url);
            //return url;
        }
    }


    public static String getFileExtension(String file) throws Exception {
        String[] str = file.split("\\?", 1);
        if (str[0].lastIndexOf(".") != -1 && str[0].lastIndexOf(".") != 0)
            return str[0].substring(str[0].lastIndexOf(".") + 1);
        else return "";
    }

    public static boolean checkToShowTxtViewer(String filename) {
        // There does not seem to be a way to ask the OS or file itself for this
        // information, so unfortunately resorting to extension sniffing.
        boolean isValidDocUrl = false;
        int pos = filename.lastIndexOf('.');
        if (pos != -1) {
            String ext = filename.substring(filename.lastIndexOf('.') + 1,
                    filename.length());

            if (ext.equalsIgnoreCase("TXT")) {
                isValidDocUrl = true;
            }
        }
        return isValidDocUrl;
    }


    public static String getFileMimeType(String filename) {
        // There does not seem to be a way to ask the OS or file itself for this
        // information, so unfortunately resorting to extension sniffing.

        int pos = filename.lastIndexOf('.');
        if (pos != -1) {
            String ext = filename.substring(filename.lastIndexOf('.') + 1,
                    filename.length());

            if (ext.equalsIgnoreCase("mp3"))
                return "audio/mpeg";
            if (ext.equalsIgnoreCase("aac"))
                return "audio/aac";
            if (ext.equalsIgnoreCase("wav"))
                return "audio/wav";
            if (ext.equalsIgnoreCase("ogg"))
                return "audio/ogg";
            if (ext.equalsIgnoreCase("mid"))
                return "audio/midi";
            if (ext.equalsIgnoreCase("midi"))
                return "audio/midi";
            if (ext.equalsIgnoreCase("wma"))
                return "audio/x-ms-wma";

            if (ext.equalsIgnoreCase("mp4"))
                return "video/mp4";
            if (ext.equalsIgnoreCase("avi"))
                return "video/x-msvideo";
            if (ext.equalsIgnoreCase("wmv"))
                return "video/x-ms-wmv";

            if (ext.equalsIgnoreCase("png"))
                return "image/png";
            if (ext.equalsIgnoreCase("jpg"))
                return "image/jpeg";
            if (ext.equalsIgnoreCase("jpe"))
                return "image/jpeg";
            if (ext.equalsIgnoreCase("jpeg"))
                return "image/jpeg";
            if (ext.equalsIgnoreCase("gif"))
                return "image/gif";

            if (ext.equalsIgnoreCase("xml"))
                return "text/xml";
            if (ext.equalsIgnoreCase("txt"))
                return "text/plain";
            if (ext.equalsIgnoreCase("cfg"))
                return "text/plain";
            if (ext.equalsIgnoreCase("csv"))
                return "text/plain";
            if (ext.equalsIgnoreCase("conf"))
                return "text/plain";
            if (ext.equalsIgnoreCase("rc"))
                return "text/plain";
            if (ext.equalsIgnoreCase("htm"))
                return "text/html";
            if (ext.equalsIgnoreCase("html"))
                return "text/html";

            if (ext.equalsIgnoreCase("pdf"))
                return "application/pdf";
            if (ext.equalsIgnoreCase("apk"))
                return "application/vnd.android.package-archive";


            // Additions and corrections are welcomed.


            if (ext.equalsIgnoreCase("doc"))
                return "application/msword";

            if (ext.equalsIgnoreCase("docx"))
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

            if (ext.equalsIgnoreCase("XLS"))
                return "application/vnd.ms-excel";

            if (ext.equalsIgnoreCase("XLSX"))
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

            if (ext.equalsIgnoreCase("PPT"))
                return "application/vnd.ms-powerpoint";

            if (ext.equalsIgnoreCase("PPTX"))
                return "application/vnd.openxmlformats-officedocument.presentationml.presentation";

        }
        return "*/*";
    }

    public static int getWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static float convertDpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static int getHalfWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        // return displayMetrics.widthPixels * 3 / 4;
        return displayMetrics.widthPixels * 50 / 100;
    }

    public static int getHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        //int width = displayMetrics.widthPixels;
        //int height = displayMetrics.heightPixels;
        return displayMetrics.heightPixels;
    }

    public static int getDefaultHeight(Context context) {
        return (getWidth(context) * 19) / 32;
    }

    public static void setUpStatusBarColor(Activity context) {
        if (colorMap != null && colorMap.containsKey("APP_STATUS_BAR_COLOR")) {
            context.getWindow().setStatusBarColor(Color.parseColor(colorMap.get(
                    "APP_STATUS_BAR_COLOR")));
        } else
            context.getWindow().setStatusBarColor(context.getResources().getColor(com.hubengage.network.R.color.social_feed_username_color));

    }


    public static String getCountForDisplay(int notificationCount) {
        String count = "";
        if (notificationCount > 99) {
            count = "99+";
        } else if (notificationCount > 0 && notificationCount <= 99) {
            count = "" + notificationCount;
        }
        return count;
    }

    public static String createAppSession() {

        return "a-" + UUID.randomUUID() + "-" + (System.currentTimeMillis() / (1000));
    }

    public static Map<String, String> GetInitialsColorHashMap() {
        return initialsColors;
    }

    public static final int MIME_TYPE_IMAGE = 0;
    public static final int MIME_TYPE_VIDEO = 1;

    public static int checkMediaType(Context context, Uri uri) {
        ContentResolver cR = context.getContentResolver();
        String type = cR.getType(uri);
        if (!TextUtils.isEmpty(type)) {
            if (type.startsWith("image/")) {
                return MIME_TYPE_IMAGE;
            } else if (type.startsWith("video/")) {
                return MIME_TYPE_VIDEO;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
     /*   MimeTypeMap mime = MimeTypeMap.getSingleton();
        String type = mime.getExtensionFromMimeType(cR.getType(uri));*/
    }

    public static String getExtentionFromPath(String path) {
        String exten = "";
        int i = path.lastIndexOf('.');
        if (i > 0) {
            exten = path.substring(i + 1);
        }
        return exten;
    }

    public static void showDownloadToast(Context homeActivity) {
        Toast.makeText(homeActivity, homeActivity.getString(R.string.downloading),
                Toast.LENGTH_SHORT).show();
        //Toast.makeText(homeActivity, homeActivity.getString(R.string.download_click_message),
        // Toast.LENGTH_SHORT).show();
    }

    public static void errorAlertDialog(final Activity context, String message) {
        AlertDialog.Builder alertDialogBuilder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alertDialogBuilder = new AlertDialog.Builder(context,
                    android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            alertDialogBuilder = new AlertDialog.Builder(context,
                    R.style.Theme_AlertDialog);
        }

        alertDialogBuilder.setPositiveButton(context.getString(R.string.ok),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {

                        dialog.dismiss();
                        context.finish();
                    }
                });


        alertDialogBuilder.setCancelable(false);

        AlertDialog alertDialog = null;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        alertDialog = alertDialogBuilder.create();
        alertDialog.setTitle(context.getString(R.string.deeplink_dialog_title));
        alertDialog.setMessage(message);
        alertDialog.setInverseBackgroundForced(true);
        alertDialog.show();
    }

    /**
     * Return user's selected language code as per
     * {@{https://github.com/hubengage/admin-api/issues/976}
     *
     * @param context
     * @return
     */
    public static String GetKeyBoardLanguageCode(Context context) {
        /*InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
        String langCode;
        if (ims != null) {
            if (ims.getLocale().length() >= 2) {
                langCode = ims.getLocale().substring(0, 2);
            } else
                langCode = "en";
        } else
            langCode = "en";
        return langCode;*/
        return NewPreferenceHelper.getLocaleValue(context);
    }

    // to enable all the webview for testing - it's required for QA
    public static void enableWebViewDebugging(WebView webView) {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(true);
        }*/
    }


    /*public static String getCount(double value) {
        if (value > 0) {

            if (value < 1000) {
                NumberFormat formatter = new DecimalFormat("###.#");
                formatter.setRoundingMode(RoundingMode.UP);
                return formatter.format(value);
//                return Double.toString(value);
            } else {
                String numberString = "";
                NumberFormat formatter = new DecimalFormat("###.#");
                formatter.setRoundingMode(RoundingMode.DOWN);

                float formatCount = (float) value;

                if (value >= 1000 && value < 1000000) {
                    formatCount = formatCount / 1000.0f;

                    if (value > 100000) {
                        if (formatCount == 100 || formatCount == 100.0) {
                            numberString = "1M";
                        } else {
                            numberString = formatter.format(formatCount) + "K";
                        }
                    } else {
                        numberString = formatter.format(formatCount) + "K";
                    }
                    return numberString.length() > 4 ?
                            numberString.replaceAll("\\.[0-9]+", "").trim()
                            : numberString.trim();
                } else if (value >= 1000000) {
                    formatCount = formatCount / 1000000.0f;
                    numberString = formatter.format(formatCount) + "M";
                    return numberString.length() > 4 ?
                            numberString.replaceAll("\\.[0-9]+", "").trim()
                            : numberString.trim();
                } else {

                    formatter.setRoundingMode(RoundingMode.UP);
                    return formatter.format(value);
                    //                return Double.toString(value);
                }
            *//*if (value >= 1000000) {
                numberString = formatter.format(value / 1000000.0) + "M";

            } else if (value >= 1000 && value < 1000000) {
                numberString = formatter.format(value / 1000.0) + "K";
            } else {
                numberString = Double.toString(value);
            }


            return numberString.length() > 4 ? numberString.replaceAll("\\.[0-9]+", "").trim()
                    : numberString.trim();*//*
            }
      *//*   // test
            int power;
            String suffix = " KMBT";
            String formattedNumber = "";

            NumberFormat formatter = new DecimalFormat("#,###.#");
            formatter.setRoundingMode(RoundingMode.DOWN);
            power = (int) StrictMath.log10(value);
            value = value / (Math.pow(10, (power / 3) * 3));
            formattedNumber = formatter.format(value);
            formattedNumber = formattedNumber + suffix.charAt(power / 3);
            return formattedNumber.length() > 4 ? formattedNumber.replaceAll("\\.[0-9]+", "")
            .trim() :
                    formattedNumber.trim();*//*
        } else {
            return Double.toString(value);
        }
    }*/


    public static String getCount(double value) {
        if (value > 0) {
            if (value < 1000) {
                return getStringValue((float) value);
            } else {
                String numberString = "";
                float formatCount = (float) value;

                if (value >= 1000 && value < 1000000) {
                    formatCount = formatCount / 1000.0f;

                    if (value > 100000) {
                        if (formatCount == 100 || formatCount == 100.0) {
                            numberString = "1M";
                        } else {

                            numberString = getStringValue(formatCount) + "K";
                        }
                    } else {
                        numberString = getStringValue(formatCount) + "K";
                    }
                    return numberString.length() > 4 ?
                            numberString.replaceAll("\\.[0-9]+", "").trim()
                            : numberString.trim();
                } else if (value >= 1000000) {
                    formatCount = formatCount / 1000000.0f;
                    numberString = getStringValue(formatCount) + "M";
                    return numberString.length() > 4 ?
                            numberString.replaceAll("\\.[0-9]+", "").trim()
                            : numberString.trim();
                } else {
                    return getStringValue((float) value);
                    //                return Double.toString(value);
                }
            }
        } else {
            return getStringValue((float) value);
        }
    }


    public static String getStringValue(float formatCount) {
        String val = String.valueOf(formatCount);

        int index = val.indexOf(".");
        if (index != -1) {
            if (val.charAt(index + 1) == '0') {
                val = val.substring(0, index);
                index = -1;
            }
        }
        if (index != -1) {
            val = val.substring(0, index + 2);
        }
        return val;
    }

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();

    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

/*    public static String getCount(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return getCount(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + getCount(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }*/

    public static void setCommentCount(String commentCount, TextView tvComment) {
        if (!TextUtils.isEmpty(commentCount)) {
            if (commentCount.trim().length() >= 3) {
                tvComment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7);
                tvComment.setText(commentCount);
            } else {
                tvComment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                tvComment.setText(commentCount);
            }
        }
    }

    public static void setCommentCountAtDetails(String commentCount, TextView tvComment) {
        if (!TextUtils.isEmpty(commentCount)) {
            if (commentCount.trim().length() > 3) {
                tvComment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 6);
                tvComment.setText(commentCount);
            } else {
                tvComment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                tvComment.setText(commentCount);
            }
        }
    }

    /**
     * Logs Exceptions on CrashAnalytics
     *
     * @param throwable is to be log
     */
    public static void Log(Throwable throwable) {
        try {
            //Crashlytics.logException(throwable);
            String LOG_EXCEPTION = "Class name : %1$s, Line No: %2$d, Exception : %3$s";
            if (!TextUtils.isEmpty(throwable.getMessage()))
                if (throwable.getStackTrace().length > 0)
                    Utilities.e("Solve this Exception", String.format(Locale.getDefault(),
                            LOG_EXCEPTION, throwable.getStackTrace()[0].getClassName(),
                            throwable.getStackTrace()[0].getLineNumber(), throwable.getMessage()));
                else
                    Utilities.e("Solve this Exception", "Exception occur");

            else
                Utilities.e("Solve this Exception", "Exception occur");
        } catch (Exception e) {
            e.printStackTrace();
            //Crashlytics.logException(e);
        }
    }

    /**
     * Logs Exceptions on CrashAnalytics
     *
     * @param message is to be log
     */
    public static void Log(String message) {
        try {
            //Crashlytics.logException(throwable);
            if (!TextUtils.isEmpty(message))
                Utilities.e("Solve this Exception", message);

            else
                Utilities.e("Solve this Exception", "Exception occur");
        } catch (Exception e) {
            e.printStackTrace();
            //Crashlytics.logException(e);
        }
    }

    public static int getAttachmentIconByExtension(String fileExtension) {
        // Utilities.e("Extension", fileExtension);
        if (fileExtension.equalsIgnoreCase("pdf"))
            return R.drawable.ic_pdf;
        else if (fileExtension.equalsIgnoreCase("png") ||
                fileExtension.equalsIgnoreCase("jpg") ||
                fileExtension.equalsIgnoreCase("jpeg"))
            return R.drawable.ic_image;
        else if (fileExtension.equalsIgnoreCase("gif"))
            return R.drawable.ic_attach_gif;
        else if (fileExtension.equalsIgnoreCase("zip"))
            return R.drawable.ic_attach_zip;
        else
            return R.drawable.ic_doc;

    }

    public static void makeToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    public static void OpenAttachedDocument(Context context, int mediaId, String label,
                                            String url, boolean canDownload, boolean isForChat,
                                            boolean isUploadComplete, boolean isMsgFromMe, View view) {

        try {


            if (url != null && url.length() > 0) {

                if (isForChat) {
                    /*if (url.contains("?")) {
                        url = url.substring(0, url.indexOf('?'));
                        Utilities.e("AttachmentUrlForChat", url);
                    }*/
                }

                if (isMsgFromMe) {
                    /*if (isUploadComplete) {
                        if (!url.startsWith("http://") && !url.startsWith("https://"))
                            url = "http://" + url;
                    } else {
                        if (!url.startsWith("file://"))
                            url = "file://" + url;
                    }*/

                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        if (!url.startsWith("file://"))
                            url = "file://" + url;
                    }
                }
                Utilities.e("Url", url);
                if (Utilities.checkToShowPdfViewer(url)) {
                    if (!url.startsWith("file://"))
                        PDFViewPagerActivity.StartActivity(context,
                                extractUrlForAttachment(url, url),
                                label, true, canDownload);
                } else {

                    String fileExtension = getFileExtension(url);
                    if (fileExtension.equalsIgnoreCase("png") ||
                            fileExtension.equalsIgnoreCase("jpg") ||
                            fileExtension.equalsIgnoreCase("jpeg") || fileExtension.equalsIgnoreCase("gif")) {

                        // open image view activity
                        try {
                            ArrayList<MediaData> mediaList = new ArrayList<>();
                            MediaData mediaData = new MediaData();
                            mediaData.setId(mediaId);
                            mediaData.setImageUrl(url);
                            mediaList.add(mediaData);

                            DragToDismissActivity.onIntent((Activity) context,
                                    mediaList, mediaId, -1,
                                    view, canDownload);
                        } catch (Exception e) {
                            Utilities.Log(e);
                        }
                    } /*else if (fileExtension.equalsIgnoreCase("zip")) {
                        if (!url.startsWith("file://")) {
                            String path = FileUtils.getDataDir(context).getAbsolutePath();
                            String fileName = com.hubengagesdk.hemediapicker.videotrimmer.utils.FileUtils.getFileNameFromURL(url);
                            File file = new File(path, fileName);
                            String localPath = file.getAbsolutePath();
                            String unzipPath = FileUtils.getDataDir(context, "UnZipped").getAbsolutePath();
                            FileDownloadService.DownloadRequest downloadRequest = new FileDownloadService.DownloadRequest(url, localPath);
                            downloadRequest.setRequiresUnzip(true);
                            downloadRequest.setDeleteZipAfterExtract(false);
                            downloadRequest.setUnzipAtFilePath(unzipPath);

                            FileDownloadService.OnDownloadStatusListener listener = new FileDownloadService.OnDownloadStatusListener() {

                                @Override
                                public void onDownloadStarted() {
                                    Utilities.e("FileDownloadService", "onDownloadStarted");
                                }

                                @Override
                                public void onDownloadCompleted() {
                                    Utilities.e("FileDownloadService", "onDownloadCompleted");
                                }

                                @Override
                                public void onDownloadFailed() {
                                    Utilities.e("FileDownloadService", "onDownloadFailed");
                                }

                                @Override
                                public void onDownloadProgress(int progress) {
                                    Utilities.e("FileDownloadService", "onDownloadProgress " + progress);
                                }
                            };

                            FileDownloadService.FileDownloader downloader = FileDownloadService.FileDownloader.getInstance(downloadRequest, listener);
                            downloader.download(context);
                        }
                    }*/ else {
                        if (!url.startsWith("file://"))
                            HubEngage.openWebViewBrowserForAttachment(context,
                                    url,
                                    TextUtils.isEmpty(label) ? "" : label, true,
                                    canDownload);
                    }
                }
            } else {
                Toast.makeText(context, context.getString(com.hubengagesdk.R.string.url_not_found),
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log(e);
        }
    }


    public static String getLanguageCode(AppCompatActivity appCompatActivity) {
        String languageCode = null;
        try {
            languageCode = NewPreferenceHelper.getLocaleValue(appCompatActivity);
            if (TextUtils.isEmpty(languageCode)) {
                Locale locale = DateUtils.getCurrentLocale();
                languageCode = locale.getLanguage();

                if (!TextUtils.isEmpty(languageCode) && languageCode.contains("zh")) {
                    languageCode = locale.toLanguageTag();
                }

                if (!EnterKeyScreenActivity.languageContains(languageCode)) {
                    languageCode = NewPreferenceHelper.ENGLISH_CODE;
                }

               /* if (languageCode.contains("zh")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        languageCode = locale.toLanguageTag();
                    }
                }*/
            }
        } catch (Exception e) {
            Log(e);
        }
        return languageCode;
    }

    /**
     * @param context
     * @param skipCheckFromPreferances - to check wish screen needs to open with conditions
     *                                 (already shown or not)
     */
    public static void showTodayWish(Activity context, boolean skipCheckFromPreferances) {
        ArrayList<Content> contents = (ArrayList<Content>) NewPreferenceHelper.getTodayWish();
        boolean isBirthday = false;
        boolean isAnniversary = false;
        if (contents != null && contents.size() > 0) {
            for (int i = 0; i < contents.size(); i++) {
                if (contents.get(i).getMilestoneType() == 1) {
                    isBirthday = true;
                }
                if (contents.get(i).getMilestoneType() == 2) {
                    isAnniversary = true;
                }


                Bundle bundle = new Bundle();
                bundle.putBoolean(StringConstant.EXTRA_IS_BIRTHDAY, isBirthday);
                bundle.putBoolean(StringConstant.EXTRA_IS_ANNIVERSARY, isAnniversary);
                bundle.putBoolean(StringConstant.EXTRA_NEED_API_CALL, false);
                if (isBirthday && isAnniversary) {
                    bundle.putParcelableArrayList("DATA", contents);
                }

                boolean isNeedToCallWishScreen = false;

                if ((isBirthday && isAnniversary) && !(NewPreferenceHelper.GetBirthdayWishStatus(context) ||
                        NewPreferenceHelper.GetAnniversaryWishStatus(context))) {
                    isNeedToCallWishScreen = true;
                } else if (isBirthday && !NewPreferenceHelper.GetBirthdayWishStatus(context)) {
                    isNeedToCallWishScreen = true;
                } else if (isAnniversary && !NewPreferenceHelper.GetAnniversaryWishStatus(context)) {
                    isNeedToCallWishScreen = true;
                }


                if (!skipCheckFromPreferances) {
                    if (isNeedToCallWishScreen) {
                        try {
                          // ActivityWish.StartActivity(context, bundle);
                            ActivityBirthdayWish.StartActivity(context, bundle);
                            NewPreferenceHelper.saveAnniversaryWishStatus(isAnniversary);
                            NewPreferenceHelper.saveBirthdayWishStatus(isBirthday);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        //ActivityWish.StartActivity(context, bundle);
                        ActivityBirthdayWish.StartActivity(context, bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            if (!skipCheckFromPreferances) {
                NewPreferenceHelper.saveAnniversaryWishStatus(false);
                NewPreferenceHelper.saveBirthdayWishStatus(false);
                NewPreferenceHelper.saveTodayWish(new ArrayList<>());
            }
        }
    }

    public static void showTodayWish(boolean fromNotification) {
        try {
            Observable<Bundle> bundleObservable = Observable.fromCallable(new Callable<Bundle>() {
                @Override
                public Bundle call() throws Exception {
                    ArrayList<Content> contents = (ArrayList<Content>) NewPreferenceHelper.getTodayWish();
                    boolean isBirthday = false;
                    boolean isAnniversary = false;
                    if (contents != null && !contents.isEmpty()) {
                        for (Content content : contents) {
                            if (content.getMilestoneType() == StringConstant.MILESTONE_BIRTHDAY) {
                                isBirthday = true;
                            } else if (content.getMilestoneType() == StringConstant.MILESTONE_ANNIVERSARY) {
                                isAnniversary = true;
                            }
                        }
                    }
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(StringConstant.EXTRA_IS_BIRTHDAY, isBirthday);
                    bundle.putBoolean(StringConstant.EXTRA_IS_ANNIVERSARY, isAnniversary);
                    bundle.putBoolean(StringConstant.EXTRA_IS_FROM_NOTIFICATION, fromNotification);
                    return bundle;
                }
            });
            bundleObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Bundle>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Bundle bundle) {
                            try {
                                //ActivityWish.StartActivity(ContextSingletone.GetContext(), bundle);
                                ActivityBirthdayWish.StartActivity(ContextSingletone.GetContext(), bundle);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } catch (Exception e) {
            Log(e);
        }
    }

    public static void showTodayWish(ArrayList<Content> contents) {
        try {
            Single<Bundle> bundleObservable = Single.fromCallable(new Callable<Bundle>() {
                @Override
                public Bundle call() throws Exception {
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(StringConstant.EXTRA_WISHES, contents);
                    return bundle;
                }
            });
            bundleObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Bundle>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Bundle bundle) {
                            try {
                             // ActivityWish.StartActivity(ContextSingletone.GetContext(), bundle);
                             ActivityBirthdayWish.StartActivity(ContextSingletone.GetContext(), bundle);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log(e);
                        }
                    });

        } catch (Exception e) {
            Log(e);
        }
    }


    public static void showTodayWish(Activity activity, ArrayList<Content> contents) {
        try {
            Single<Bundle> bundleObservable = Single.fromCallable(new Callable<Bundle>() {
                @Override
                public Bundle call() throws Exception {
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(StringConstant.EXTRA_WISHES, contents);
                    return bundle;
                }
            });
            bundleObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Bundle>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Bundle bundle) {
                            try {
                               if(NewPreferenceHelper.GetGreetingScreenStatus(ContextSingletone.GetContext()) == false)
                               {
                                   //if (HelpOverlayManager.isHomeAtFront(activity))
//                                    ActivityWish.StartActivity(ContextSingletone.GetContext(), bundle);
                                   if(NewPreferenceHelper.IS_FIRST_LOGIN)
                                   {
                                       final Handler handler = new Handler();
                                       handler.postDelayed(new Runnable() {
                                           @Override
                                           public void run() {
                                               // Do something after 5s = 5000ms
                                               try {
                                                   if(NewPreferenceHelper.IS_USER_LOGGED_IN_VALUE) {
                                                       ActivityBirthdayWish.StartActivity(ContextSingletone.GetContext(), bundle);
                                                       NewPreferenceHelper.saveGreetingScreenShownStatus(true);
                                                   }
                                               } catch (Exception e) {
                                                   e.printStackTrace();
                                               }

                                           }
                                       }, 25000); //5 seconds

                                   }else {
                                       ActivityBirthdayWish.StartActivity(ContextSingletone.GetContext(), bundle);
                                       NewPreferenceHelper.saveGreetingScreenShownStatus(true);
                                   }
                               }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log(e);
                        }
                    });

        } catch (Exception e) {
            Log(e);
        }
    }


    public static void emitBalloons(Activity activity, final RelativeLayout relativeLayout,
                                    boolean largeBalloons) {
        int r = new Random().nextInt();
        Drawable heart = getBalloon(activity);
        final ImageView imageView;
        imageView = new ImageView(activity);
        imageView.setImageDrawable(heart);

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int randomX = new Random().nextInt(size.x);
        final int randomHeight = largeBalloons
                ? ThreadLocalRandom.current().nextInt(60, 120)
                : ThreadLocalRandom.current().nextInt(0, 16);


        RelativeLayout.LayoutParams paramsImage =
                new RelativeLayout.LayoutParams(Utilities.dpToPx(randomHeight,
                        activity.getResources()), Utilities.dpToPx(randomHeight,
                        activity.getResources()));
        if (r % 2 == 0) {
            paramsImage.setMargins(-randomX, 0, 0, -Utilities.dpToPx(randomHeight,
                    activity.getResources()));
        } else {
            paramsImage.setMargins(randomX, 0, 0, -Utilities.dpToPx(randomHeight,
                    activity.getResources()));
        }
        paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        imageView.setLayoutParams(paramsImage);
        relativeLayout.addView(imageView);


        if (r % 2 == 0) {
// diagonally
            AnimatorSet animSetXY = new AnimatorSet();
            ObjectAnimator y = ObjectAnimator.ofFloat(imageView,
                    "translationY", -size.y);

            ObjectAnimator x = ObjectAnimator.ofFloat(imageView,
                    "translationX", +size.x);

            animSetXY.playTogether(x, y);
            animSetXY.setDuration(15000);
            animSetXY.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    relativeLayout.removeView(imageView);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animSetXY.start();
        } else {


            ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", -size.y);
            animationY.setDuration(15000);
            animationY.start();
            animationY.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    relativeLayout.removeView(imageView);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

        }
    }

    public static void emitBalloonsInitially(Activity activity,
                                             final RelativeLayout relativeLayout,
                                             final boolean largeBalloons) {
        int r = new Random().nextInt();
        Drawable heart = getBalloon(activity);
        final ImageView imageView;
        imageView = new ImageView(activity);
        imageView.setImageDrawable(heart);

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int randomX = new Random().nextInt(size.x);
        final int randomHeight = largeBalloons
                ? ThreadLocalRandom.current().nextInt(40, 80)
                : ThreadLocalRandom.current().nextInt(0, 16);
        final int randomBottomMargin = largeBalloons ? new Random().nextInt(size.y) :
                new Random().nextInt(40);

        RelativeLayout.LayoutParams paramsImage =
                new RelativeLayout.LayoutParams(Utilities.dpToPx(randomHeight,
                        activity.getResources()), Utilities.dpToPx(randomHeight,
                        activity.getResources()));
        if (r % 2 == 0) {
            paramsImage.setMargins(-randomX, 0, 0, Utilities.dpToPx(randomBottomMargin,
                    activity.getResources()));
        } else {
            paramsImage.setMargins(randomX, 0, 0, Utilities.dpToPx(randomBottomMargin,
                    activity.getResources()));
        }
        paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        imageView.setLayoutParams(paramsImage);
        relativeLayout.addView(imageView);


        if (r % 2 == 0) {
// diagonally
            AnimatorSet animSetXY = new AnimatorSet();
            ObjectAnimator y = ObjectAnimator.ofFloat(imageView,
                    "translationY", -size.y);

            ObjectAnimator x = ObjectAnimator.ofFloat(imageView,
                    "translationX", +size.x);

            animSetXY.playTogether(x, y);
            animSetXY.setDuration(15000);
            animSetXY.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    relativeLayout.removeView(imageView);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animSetXY.start();
        } else {


            ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", -size.y);
            animationY.setDuration(15000);
            animationY.start();
            animationY.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    relativeLayout.removeView(imageView);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

        }
    }

    public static void popRibbons(Activity activity, final RelativeLayout relativeLayout) {
        int r = new Random().nextInt();
        Drawable heart = getRibbon(activity);
        final ImageView imageView;
        imageView = new ImageView(activity);
        imageView.setImageDrawable(heart);

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int randomX = new Random().nextInt(size.x);
        final int randomHeight = new Random().nextInt(16);

        RelativeLayout.LayoutParams paramsImage =
                new RelativeLayout.LayoutParams(Utilities.dpToPx(randomHeight,
                        activity.getResources()), Utilities.dpToPx(randomHeight,
                        activity.getResources()));
        paramsImage.setMargins(randomX, -Utilities.dpToPx(randomHeight, activity.getResources()),
                0, 0);
        paramsImage.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        imageView.setLayoutParams(paramsImage);
        relativeLayout.addView(imageView);
        ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", size.y);
        animationY.setDuration(14000);
        animationY.start();
        animationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                relativeLayout.removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        /*new CountDownTimer(20000, 20000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                relativeLayout.removeView(imageView);
            }
        }.start();*/
    }

    public static void popRibbonsInitially(Activity activity, final RelativeLayout relativeLayout
            , final boolean largeRibboons) {
        int r = new Random().nextInt();
        Drawable heart = getRibbon(activity);
        final ImageView imageView;
        imageView = new ImageView(activity);
        imageView.setImageDrawable(heart);

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int randomX = new Random().nextInt(size.x);
        final int randomHeight = new Random().nextInt(16);
        final int randomBottomMargin = largeRibboons ? new Random().nextInt(size.y) :
                new Random().nextInt(40);

        RelativeLayout.LayoutParams paramsImage =
                new RelativeLayout.LayoutParams(Utilities.dpToPx(randomHeight,
                        activity.getResources()), Utilities.dpToPx(randomHeight,
                        activity.getResources()));
        paramsImage.setMargins(randomX, Utilities.dpToPx(randomBottomMargin,
                activity.getResources()),
                0, 0);
        paramsImage.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        imageView.setLayoutParams(paramsImage);
        relativeLayout.addView(imageView);
        ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", size.y);
        animationY.setDuration(14000);
        animationY.start();
        animationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                relativeLayout.removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    /**
     * @param view            - view to which we are setting background
     * @param backgroundColor - view background color resource id
     * @param cornerRadius    - corner radius dimension resource id
     * @param shadowColor     - shadow Color resource id
     * @param elevation-      elevation resource id
     * @param shadowGravity   - any one from Gravity.LEFT, Gravity.TOP, Gravity.RIGHT, Gravity
     *                        .BOTTOM
     * @return
     */


    public static Drawable generateBackgroundWithShadow(View view, @ColorRes int backgroundColor,
                                                        @DimenRes int cornerRadius,
                                                        @ColorRes int shadowColor,
                                                        @DimenRes int elevation,
                                                        int shadowGravity) {
        float cornerRadiusValue = view.getContext().getResources().getDimension(cornerRadius);
        int elevationValue = (int) view.getContext().getResources().getDimension(elevation);
        int shadowColorValue = ContextCompat.getColor(view.getContext(), shadowColor);
        int backgroundColorValue = ContextCompat.getColor(view.getContext(), backgroundColor);

        float[] outerRadius = {cornerRadiusValue, cornerRadiusValue, cornerRadiusValue,
                cornerRadiusValue, cornerRadiusValue, cornerRadiusValue, cornerRadiusValue,
                cornerRadiusValue};

        Paint backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setShadowLayer(cornerRadiusValue, 0, 0, 0);

        Rect shapeDrawablePadding = new Rect();
        shapeDrawablePadding.left = elevationValue;
        shapeDrawablePadding.right = elevationValue;

        int DY;
        switch (shadowGravity) {
            case Gravity.CENTER:
                shapeDrawablePadding.top = elevationValue;
                shapeDrawablePadding.bottom = elevationValue;
                DY = 0;
                break;
            case Gravity.TOP:
                shapeDrawablePadding.top = elevationValue * 2;
                shapeDrawablePadding.bottom = elevationValue;
                DY = -1 * elevationValue / 3;
                break;
            default:
            case Gravity.BOTTOM:
                shapeDrawablePadding.top = elevationValue;
                shapeDrawablePadding.bottom = elevationValue * 2;
                DY = elevationValue / 3;
                break;
        }

        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setPadding(shapeDrawablePadding);

        shapeDrawable.getPaint().setColor(backgroundColorValue);
        shapeDrawable.getPaint().setShadowLayer(cornerRadiusValue / 3, 0, DY, shadowColorValue);

        view.setLayerType(LAYER_TYPE_SOFTWARE, shapeDrawable.getPaint());

        shapeDrawable.setShape(new RoundRectShape(outerRadius, null, null));

        LayerDrawable drawable = new LayerDrawable(new Drawable[]{shapeDrawable});
        drawable.setLayerInset(0, elevationValue, elevationValue * 2, elevationValue,
                elevationValue * 2);

        return drawable;

    }


    public static String getAnniversaryString(int year, String str) {
        if (year >= 11 && year <= 13) {
            return year + "th " + str;
        }
        switch (year % 10) {
            case 1:
                return year + "st " + str;
            case 2:
                return year + "nd " + str;
            case 3:
                return year + "rd " + str;
            default:
                return year + "th " + str;
        }
    }

    public static void emitBalloons(Activity activity, final RelativeLayout relativeLayout,
                                    int maxHeightBound) throws Exception {
        int r = new Random().nextInt();
        Drawable heart = getBalloon(activity);
        final ImageView imageView;
        imageView = new ImageView(activity);
        imageView.setImageDrawable(heart);

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int randomX = new Random().nextInt(size.x);
        final int randomHeight = ThreadLocalRandom.current().nextInt(maxHeightBound / 2,
                maxHeightBound);

        RelativeLayout.LayoutParams paramsImage =
                new RelativeLayout.LayoutParams(Utilities.dpToPx(randomHeight,
                        activity.getResources()), Utilities.dpToPx(randomHeight,
                        activity.getResources()));
        if (r % 2 == 0) {
            if (r % 3 == 0) {
                paramsImage.setMargins(Utilities.dpToPx(randomX, activity.getResources()), 0, 0,
                        -Utilities.dpToPx(maxHeightBound, activity.getResources()));
            } else {
                paramsImage.setMargins(-Utilities.dpToPx(randomX, activity.getResources()), 0, 0,
                        -Utilities.dpToPx(maxHeightBound, activity.getResources()));
            }
        } else {
            paramsImage.setMargins(Utilities.dpToPx(randomX, activity.getResources()), 0, 0,
                    -Utilities.dpToPx(maxHeightBound, activity.getResources()));
        }
        paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        imageView.setLayoutParams(paramsImage);
        relativeLayout.addView(imageView);
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (relativeLayout != null)
                    relativeLayout.removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                if (relativeLayout != null)
                    relativeLayout.removeView(imageView);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
        if (r % 2 == 0) {
            AnimatorSet animSetXY = new AnimatorSet();
            ObjectAnimator y = ObjectAnimator.ofFloat(imageView,
                    "translationY", -size.y);

            ObjectAnimator x = ObjectAnimator.ofFloat(imageView,
                    "translationX", +size.x);
            animSetXY.playTogether(x, y);
            animSetXY.setDuration(15000);
            animSetXY.addListener(animatorListener);
            animSetXY.start();
        } else {
            ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", -size.y);
            animationY.setDuration(15000);
            animationY.addListener(animatorListener);
            animationY.start();
        }
    }

    public static void popRibbons(Activity activity, final RelativeLayout relativeLayout,
                                  int maxHeightBound) throws Exception {
        int r = new Random().nextInt();
        Drawable heart = getRibbon(activity);
        final ImageView imageView;
        imageView = new ImageView(activity);
        imageView.setImageDrawable(heart);

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int randomX = new Random().nextInt(size.x);
        final int randomHeight = new Random().nextInt(maxHeightBound);

        RelativeLayout.LayoutParams paramsImage =
                new RelativeLayout.LayoutParams(Utilities.dpToPx(randomHeight,
                        activity.getResources()), Utilities.dpToPx(randomHeight,
                        activity.getResources()));
        paramsImage.setMargins(randomX, -Utilities.dpToPx(randomHeight, activity.getResources()),
                0, 0);
        paramsImage.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        imageView.setLayoutParams(paramsImage);
        relativeLayout.addView(imageView);
        ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", size.y);
        animationY.setDuration(12000);
        animationY.start();
        animationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (relativeLayout != null) {
                    relativeLayout.removeView(imageView);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                if (relativeLayout != null)
                    relativeLayout.removeView(imageView);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private static Drawable getRibbon(Activity activity) {
        TypedArray ribbons =
                activity.getResources().obtainTypedArray(com.hubengagesdk.R.array.ribbons);
        int randomRibbonId = ribbons.getResourceId(new Random().nextInt(ribbons.length()), 0);
        ribbons.recycle();
        return activity.getResources().getDrawable(randomRibbonId);
    }

    private static Drawable getBalloon(Activity activity) {
        TypedArray balloons =
                activity.getResources().obtainTypedArray(com.hubengagesdk.R.array.balloons);
        int randomBalloonId = balloons.getResourceId(new Random().nextInt(balloons.length()), 0);
        balloons.recycle();
        return activity.getResources().getDrawable(randomBalloonId);
    }


    public static String getApplicationName(Context context) throws Exception {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    public static String GetUrlForPreview(String url){
        if (url.toLowerCase().startsWith("https")) {
            url = url.replaceFirst("(?i)" +"https", "https");
        } else if (url.toLowerCase().startsWith("http")) {
            url = url.replaceFirst("(?i)" +"http", "http");
        } else if (!url.toLowerCase().startsWith("http")) {
            url = "http://" + url;
        }
        return url;
    }

    /**
     * Compares one version string to another version string by dotted ordinals.
     * eg. "1.0" > "0.09" ; "0.9.5" < "0.10",
     * also "1.0" < "1.0.0" but "1.0" == "01.00"
     *
     * @param left  the left hand version string
     * @param right the right hand version string
     * @return 0 if equal, -1 if thisVersion < comparedVersion and 1 otherwise.
     */
    public static int compare(String left, String right) {
        if (left.equals(right)) {
            return 0;
        }
        int leftStart = 0, rightStart = 0, result;
        do {
            int leftEnd = left.indexOf('.', leftStart);
            int rightEnd = right.indexOf('.', rightStart);
            Integer leftValue = Integer.parseInt(leftEnd < 0
                    ? left.substring(leftStart)
                    : left.substring(leftStart, leftEnd));
            Integer rightValue = Integer.parseInt(rightEnd < 0
                    ? right.substring(rightStart)
                    : right.substring(rightStart, rightEnd));
            result = leftValue.compareTo(rightValue);
            leftStart = leftEnd + 1;
            rightStart = rightEnd + 1;
        } while (result == 0 && leftStart > 0 && rightStart > 0);
        if (result == 0) {
            if (leftStart > rightStart) {
                return containsNonZeroValue(left, leftStart) ? 1 : 0;
            }
            if (leftStart < rightStart) {
                return containsNonZeroValue(right, rightStart) ? -1 : 0;
            }
        }
        return result;
    }

    private static boolean containsNonZeroValue(String str, int beginIndex) {
        for (int i = beginIndex; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '0' && c != '.') {
                return true;
            }
        }
        return false;
    }



    public static String getFileUrlWithoutQuery(String url) throws Exception {

        try {
            URI uri = new URI(url);
            return new URI(uri.getScheme(),
                    uri.getAuthority(),
                    uri.getPath(),
                    null, // Ignore the query part of the input url
                    null).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return HubEngage.getEncodedUrl(url);
            //return url;
        }
    }

    public static String getFileUrl(String url) throws Exception {

        try {
            URI uri = new URI(url);
            return new URI(uri.getScheme(),
                    uri.getAuthority(),
                    uri.getPath(),
                    uri.getQuery(), // Ignore the query part of the input url
                    null).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return HubEngage.getEncodedUrl(url);
            //return url;
        }
    }

//    public static String getFileUrlOnWebActivity(String url) throws Exception {
//
//        try {
//            URI uri = new URI(url);
//            return new URI(uri.getScheme(),
//                    uri.getAuthority(),
//                    uri.getPath(),
//                    null, // Ignore the query part of the input url
//                    null).toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return HubEngage.getEncodedUrl(url);
//            //return url;
//        }
//    }

//    public static boolean checkToShowPdfViewer(String filenameOriginal) {
//        // There does not seem to be a way to ask the OS or file itself for this
//        // information, so unfortunately resorting to extension sniffing.
//
//
//        String filename = null;
//        try {
//            filename = getFileUrlOnWebActivity(filenameOriginal);
//        } catch (Exception e) {
//            filename = filenameOriginal;
//            e.printStackTrace();
//        }
//
//        boolean isValidDocUrl = false;
//        int pos = filename.lastIndexOf('.');
//        if (pos != -1) {
//            String ext = filename.substring(filename.lastIndexOf('.') + 1,
//                    filename.length());
//
//            if (ext.equalsIgnoreCase("PDF")) {
//                isValidDocUrl = true;
//            }
//        }
//        return isValidDocUrl;
//    }
//    public static boolean checkToShowGoogleDocViewer(String filenameOriginal) {
//        // There does not seem to be a way to ask the OS or file itself for this
//        // information, so unfortunately resorting to extension sniffing.
//
//       /* if (filename.startsWith("ftp:")) {
//            return false;
//        }*/
//
//        String filename = null;
//        try {
//            filename = getFileUrlOnWebActivity(filenameOriginal);
//        } catch (Exception e) {
//            filename = filenameOriginal;
//            e.printStackTrace();
//        }
//
//        boolean isValidDocUrl = false;
//
//        int pos = filename.lastIndexOf('.');
//        if (pos != -1) {
//            String ext = filename.substring(filename.lastIndexOf('.') + 1,
//                    filename.length());
//
//          /*Image files (.JPEG, .PNG, .GIF, .TIFF, .BMP)
//            Video files (WebM, .MPEG4, .3GPP, .MOV, .AVI, .MPEGPS, .WMV, .FLV)
//            Text files (.TXT)
//            Markup/Code (.CSS, .HTML, .PHP, .C, .CPP, .H, .HPP, .JS)
//            Microsoft Word (.DOC and .DOCX)
//            Microsoft Excel (.XLS and .XLSX)
//            Microsoft PowerPoint (.PPT and .PPTX)
//            Adobe Portable Document Format (.PDF)
//            Apple Pages (.PAGES)
//            Adobe Illustrator (.AI)
//            Adobe Photoshop (.PSD)
//            Tagged Image File Format (.TIFF)
//            Autodesk AutoCad (.DXF)
//            Scalable Vector Graphics (.SVG)
//            PostScript (.EPS, .PS)
//            TrueType (.TTF)
//            XML Paper Specification (.XPS)
//            Archive file types (.ZIP and .RAR)
//          */
//
//            if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX"))
//                isValidDocUrl = true;
//            if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX"))
//                isValidDocUrl = true;
//            if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX"))
//                isValidDocUrl = true;
//           /* if (ext.equalsIgnoreCase("TXT"))
//                isValidDocUrl = true;*/
//            if (ext.equalsIgnoreCase("PDF"))
//                isValidDocUrl = true;
//            if (ext.equalsIgnoreCase("CSV"))
//                isValidDocUrl = true;
//        }
//        return isValidDocUrl;
//    }
    public static boolean checkToShowOfficeAppsViewer(String filenameOriginal) {
        // There does not seem to be a way to ask the OS or file itself for this
        // information, so unfortunately resorting to extension sniffing.

        String filename = null;
        try {
            filename = getFileUrlOnWebActivity(filenameOriginal);
        } catch (Exception e) {
            filename = filenameOriginal;
            e.printStackTrace();
        }

        boolean isValidDocUrl = false;
        int pos = filename.lastIndexOf('.');
        if (pos != -1) {
            String ext = filename.substring(filename.lastIndexOf('.') + 1,
                    filename.length());

            if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX") ||
                    ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX") ||
                    ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {
                isValidDocUrl = true;
            }
        }
        return isValidDocUrl;
    }
    public static String readJsonFromAssets(Context context, String filePath) {

        try {
            InputStream input = context.getAssets().open(filePath);
            BufferedSource source = Okio.buffer(Okio.source(input));
            return source.readByteString().string(Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}