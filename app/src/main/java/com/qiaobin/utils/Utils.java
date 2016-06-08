package com.qiaobin.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;














import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.ProgressDialog;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Utils {
	private static boolean isLog = true;
	private static String log_tag = "ht";
	
	public static void mLogError(String msg){
		if(isLog)
			Log.e(log_tag, msg);
	}
	
	public static void mLog(String msg){
		if(isLog)
			Log.i(log_tag, msg);
	}
	public static void mLog_d(String msg){
		if(isLog)
			Log.d(log_tag, msg);
	}
	
	public static void mLog_v(String msg){
		if(isLog)
			Log.v(log_tag, msg);
	}
	
	public static void mLog_w(String msg){
		if(isLog)
			Log.w(log_tag, msg);
	}
	/**
	 * 是否WIFI联网
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkWIFI(Context context) {
		ConnectivityManager conn = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		 NetworkInfo networkInfo = conn.getActiveNetworkInfo();
		 if(null != networkInfo){
			 if(ConnectivityManager.TYPE_WIFI == networkInfo.getType())
				 return true;
		 }
		 return false;
//		String type = networkInfo.getTypeName();
//		return "WIFI".equalsIgnoreCase(type);
	}
	
	/**
	 * 是否网络可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkNet(Context context) {
		ConnectivityManager conn = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		 NetworkInfo networkInfo = conn.getActiveNetworkInfo();
		 if(null != networkInfo && networkInfo.isAvailable()){
			return true;
		 }
		 return false;
//		String type = networkInfo.getTypeName();
//		return "WIFI".equalsIgnoreCase(type);
	}
	/**
	 * 获取手机屏幕的宽高
	 * @param activity
	 * @return
	 */
	public static int[] getDisplayMetrics(Activity activity){
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return new int[]{dm.widthPixels, dm.heightPixels};
	}
	
	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
	/**
	 * 把毫秒为单位的时间格式化为0000-00-00 00:00:00
	 * @param time
	 * @return
	 */
	public static String formatDate(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date(time));
		if(date == null || "".equalsIgnoreCase(date))
			date = "0000-00-00 00:00:00";
		return date;
	}
	/**
	 * 把以毫秒为单位的时间格式化为0000-00-00
	 * @param time
	 * @return
	 */
	public static String formatDateShort(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date(time));
		if(date == null || "".equalsIgnoreCase(date))
			date = "0000-00-00";
		return date;
	}
    /**
     * 使double类型保留points位小数
     * @param num
     * @return
     */
    public static double formatDouble(double num, int points){
        BigDecimal bigDecimal = new BigDecimal(num);
        return bigDecimal.setScale(points,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     *  生成一个小于Max的随机数
     * @param max
     * @return
     */
    public static int getRandom(int max){
        Random rd = new Random();
        return rd.nextInt(max);
    }

	/**
	 * 把数据写入sd卡
	 * @param log 数据
	 * @param filename 文件名
	 * @param time 时间，系统 时间，单位毫秒
	 */
	public static void writeToSDCard(String log, String filename, long time){
		 
      	String path = Environment.getExternalStorageDirectory().getAbsolutePath() ;
		File dir = new File(path, "haotang");
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		File guiji = new File(dir, filename);
		try {
			FileWriter fw = new FileWriter(guiji, true);
			fw.append(formatDate(time)+" ::");
			fw.append(log +"\r\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取应用的文件夹
	 * @return
	 */
	public static String getOOPath(Context context){
		File file = null;
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), ".oo");
		}else{
			file = context.getCacheDir();
		}
		if(!file.exists()){
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}
	/**
	 * 计算球面上两个点的距离
	 * @param lat1
	 * @param lat2
	 * @param lon1
	 * @param lon2
	 * @return
	 */
	public static double getDistatce(double lat1, double lat2, double lon1,    double lon2) { 
        double R = 6371; 
        double distance = 0.0; 
        double dLat = (lat2 - lat1) * Math.PI / 180; 
        double dLon = (lon2 - lon1) * Math.PI / 180; 
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) 
                + Math.cos(lat1 * Math.PI / 180) 
                * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2) 
                * Math.sin(dLon / 2); 
        distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R; 
        return distance; 
    }
	/**
	 * 格式化double类型
	 * @param pattern 如#0.00为保留两位小数
	 * @param decimal
	 * @return
	 */
	//格式化里程和费用
		public static String formatDecimal(String pattern, double decimal){
			DecimalFormat df = new DecimalFormat();
			df.applyPattern(pattern);
			return df.format(decimal);
			
		}
		/**
		 * 把毫秒为单位的时间格式化为时分秒
		 * @param time
		 * @return
		 */
		//格式化时间格式
		public static String formatTime(long time){
			long totalss = time / 1000;
			long timess = totalss % 60;
			long totalmm = totalss / 60;
			long timemm = totalmm % 60;
			long totalhh = totalmm / 60;
			long timehh = totalhh % 60;
			StringBuffer sb = new StringBuffer();
			if(timehh < 10){
				sb.append("0");
			}
			sb.append(timehh);
			sb.append(":");
			if(timemm < 10){
				sb.append("0");
			}
			sb.append(timemm);
			sb.append(":");
			if(timess < 10){
				sb.append("0");
			}
			sb.append(timess);
			return sb.toString();
		}
		/**
		 * 把毫秒为单位的时间格式化为时分秒
		 * @param time
		 * @return
		 */
		//格式化时间格式
		public static String formatTimeFM(long time){
			long totalss = time / 1000;
			long timess = totalss % 60;
			long totalmm = totalss / 60;
			long timemm = totalmm % 60;
			long totalhh = totalmm / 60;
			long timehh = totalhh % 60;
			StringBuffer sb = new StringBuffer();
			if(timehh < 10){
//				sb.append("0");
			}
//			sb.append(timehh);
//			sb.append(":");
			if(timemm < 10){
				sb.append("0");
			}
			sb.append(timemm);
			sb.append(":");
			if(timess < 10){
				sb.append("0");
			}
			sb.append(timess);
			return sb.toString();
		}
		/**
		 * 格式化以分钟为单位的时间，转化为小时：分钟
		 * @param minutestr
		 * @return
		 */
		public static String formatMinutesToHour(String minutestr){
			int minutes = Integer.parseInt(minutestr);
			int minute = minutes % 60;
			int hour = minutes / 60;
			if(0 == hour){
				return minute + "分钟";
			}else{
				return hour + "小时"+minute + "分钟";
			}
		}
		//格式化时间格式
		public static long TimeToMinutes(long time){
			long totalss = time / 1000;
			long totalmm = totalss / 60;
//			if(totalss % 60 > 0){
//				totalmm += 1;
//			}
			return totalmm;
		}
		
		/**
		 * 转换文件大小格式
		 * 
		 * @param fileS
		 * @return
		 */
		public static String FormetFileSize(long fileS) {
//			if (0l == fileS)
//				return "";
			DecimalFormat df = new DecimalFormat("#.00");
			String fileSizeString = "";
			if(fileS <= 0){
				fileSizeString = "0B";
			}else if (fileS < 1024) {
				fileSizeString = df.format((double) fileS) + "B";
			} else if (fileS < 1048576) {
				fileSizeString = df.format((double) fileS / 1024) + "K";
			} else if (fileS < 1073741824) {
				fileSizeString = df.format((double) fileS / 1048576) + "M";
			} else {
				fileSizeString = df.format((double) fileS / 1073741824) + "G";
			}
			return fileSizeString;
		}
		
		/**
		 * 创建并获取文件夹的路径
		 * @param context
		 * @return
		 */
		public static String getDir(Context context){
			File file = null;
			if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
				file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "pet");
			}else{
				file = context.getCacheDir();
			}
			if(!file.exists()){
				file.mkdirs();
			}
			
			return file.getAbsolutePath();
		}
		/**
		 * 创建精度条对话框
		 * @param ctx
		 * @param info
		 * @return
		 */
		public  static ProgressDialog createProcessDialog(Context ctx, String info){
	    	ProgressDialog processDialog = new ProgressDialog(ctx); 
			processDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
			processDialog.setTitle(""); 
			processDialog.setMessage(info); 
			processDialog.setIcon(android.R.drawable.ic_dialog_map); 
			processDialog.setIndeterminate(false); 
			processDialog.setCancelable(true); 
			processDialog.setCanceledOnTouchOutside(false);
			processDialog.show(); 
			return processDialog;
	    
	    }
		/**
		 * 吐司
		 * @param context
		 * @param str
		 */
		public static void showTaost(Context context, String str){
//			Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
//			toast.setGravity(Gravity.CENTER, 0, 0);
//			toast.show();
//			ToastUtil.showToastShort(context, str);
		}
		
		/**
		 * 电话验证
		 * 
		 * @param
		 * @return
		 */
		public static boolean checkPhone(Context context, EditText phone_et) {
			String telPattern = "^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7])|(17[0-9]))\\d{8}$";
			String phone = phone_et.getText().toString().trim();
			boolean isAvail = startCheck(telPattern, phone);
			if("".equals(phone.trim())){
				showTaost(context, "请输入您的手机号码");
				phone_et.requestFocus();
				phone_et.setFocusableInTouchMode(true);
				return false;
			}
			if (!isAvail) {
				showTaost(context, "请输入正确的手机号码");
				phone_et.requestFocus();
				phone_et.setFocusableInTouchMode(true);
				return false;
			}
			return true;
		}
		
		/**
		 * 邮箱验证
		 * 
		 * @param context
		 * @param email_et
		 * @return
		 */
		public static boolean checkEmail(Context context, EditText email_et) {
			String emailPattern = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
			String email = email_et.getText().toString().trim();
			boolean isAvail = startCheck(emailPattern, email);
			
			if (!isAvail) {
				showTaost(context, "邮箱格式不正确，请重新输入");
				email_et.requestFocus();
				email_et.setFocusableInTouchMode(true);
				return false;
			}
			if("".equals(email.trim())){
				showTaost(context, "邮箱为空，请重新输入");
				email_et.requestFocus();
				email_et.setFocusableInTouchMode(true);
				return false;
			}
			if(email.length()>50){
				showTaost(context, "邮箱长度不能大于50，请重新输入");
				email_et.requestFocus();
				email_et.setFocusableInTouchMode(true);
				return false;
			}
			return true;
		}
		
		/**
		 * 正则规则验证
		 * @param reg
		 * @param string
		 * @return
		 */
		public static boolean startCheck(String reg, String string) {
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(string);		
			return matcher.matches();
		}
		/**
		 * app是否正在运行
		 * @param context
		 * @return
		 */
		public static boolean isAppAvailable(Context context){
			ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningTaskInfo> taskinfos = am.getRunningTasks(20);
			boolean result = false;
			for(int i = 0; i < taskinfos.size(); i++){
				if(context.getPackageName().equals(taskinfos.get(i).topActivity.getPackageName())){
					result = true;
					break;
				}
			}
			return result;
		}
		
		public static String getIPAddress(Context context){
			WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			if(manager.isWifiEnabled()){
				return getWifiAddress(context);
			}
			return getLocalIPAddress();
			
		}
		//获取wifi地址
		public static String getWifiAddress(Context context){
			WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			if(!manager.isWifiEnabled()){
				manager.setWifiEnabled(true);
			}
			WifiInfo info = manager.getConnectionInfo();
			int ip = info.getIpAddress();
			return ipToString(ip);
		}
		public static String ipToString(int ip){
			
			return  (ip & 0xFF ) + "." +     

	        ((ip >> 8 ) & 0xFF) + "." +     

	        ((ip >> 16 ) & 0xFF) + "." +     

	        ( ip >> 24 & 0xFF) ;
		}
		public static String getLocalIPAddress(){
			try{
				for(Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements();){
					NetworkInterface networkInterface = networkInterfaces.nextElement();
					for(Enumeration<InetAddress> addresses = networkInterface.getInetAddresses(); addresses.hasMoreElements();){
						InetAddress address = addresses.nextElement();
						if(!address.isLoopbackAddress() && (address instanceof Inet4Address)){
							return address.getHostAddress().toString();
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				return "";
			}
			return "";
		}


		public static Bitmap getbitmap(String imageFile, int length) {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inPreferredConfig = Bitmap.Config.RGB_565;
			opts.inJustDecodeBounds = true;

			BitmapFactory.decodeFile(imageFile, opts);
			int ins = computeSampleSize(opts, -1, length);
			opts.inSampleSize = ins;
			opts.inPurgeable = true;
			opts.inInputShareable = true;
			opts.inJustDecodeBounds = false;
			try {
				Bitmap bmp = BitmapFactory.decodeFile(imageFile, opts);
				return bmp;
			} catch (OutOfMemoryError err) {
				err.printStackTrace();
			}
			return null;
		}
		public static int computeSampleSize(BitmapFactory.Options options,
				int minSideLength, int maxNumOfPixels) {
			int initialSize = computeInitialSampleSize(options, minSideLength,
					maxNumOfPixels);

			int roundedSize;
			if (initialSize <= 8) {
				roundedSize = 1;
				while (roundedSize < initialSize) {
					roundedSize <<= 1;
				}
			} else {
				roundedSize = (initialSize + 7) / 8 * 8;
			}

			return roundedSize;
		}
		private static int computeInitialSampleSize(BitmapFactory.Options options,
				int minSideLength, int maxNumOfPixels) {
			double w = options.outWidth;
			double h = options.outHeight;

			int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
					.sqrt(w * h / maxNumOfPixels));
			int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
					Math.floor(w / minSideLength), Math.floor(h / minSideLength));

			if (upperBound < lowerBound) {
				return lowerBound;
			}

			if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
				return 1;
			} else if (minSideLength == -1) {
				return lowerBound;
			} else {
				return upperBound;
			}
		}

		
		public static String creatfile(Context pContext, Bitmap bm, String filename) {
			if (bm == null) {
				return "";
			}
			File f = new File(getSDCardPath(pContext) + "/" + filename
					+ ".jpg");
			try {
				FileOutputStream out = new FileOutputStream(f);
				if (bm.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
					out.flush();
					out.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return f.getAbsolutePath();
		}
		public static String getSDCardPath(Context pContext) {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				String path = Environment.getExternalStorageDirectory().getAbsolutePath()
						+ "/.jiuyi160_c" ;
				File PathDir = new File(path);
				if(!PathDir.exists()){
					PathDir.mkdirs();
				}
				return path;
			} else {
				return pContext.getCacheDir().getAbsolutePath();
			}
		}
		


		@SuppressLint({ "NewApi", "InlinedApi" })
		public static Bitmap getxtsldraw(Context c, String file) {
			File f = new File(file);
			Bitmap drawable = null;
			if (f.length() / 1024 < 100) {
				drawable = BitmapFactory.decodeFile(file);
			} else {
				Cursor cursor = c.getContentResolver().query(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						new String[] { MediaStore.Images.Media._ID },
						MediaStore.Images.Media.DATA + " like ?",
						new String[] { "%" + file }, null);
				if (cursor == null || cursor.getCount() == 0) {
					drawable = getbitmap(file, 720 * 1280);
				} else {
					if (cursor.moveToFirst()) {
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inPurgeable = true;
						options.inInputShareable = true;
						options.inPreferredConfig = Bitmap.Config.RGB_565;
						String videoId = cursor.getString(cursor
								.getColumnIndex(MediaStore.Images.Media._ID));
						long videoIdLong = Long.parseLong(videoId);
						Bitmap bitmap = Thumbnails.getThumbnail(
								c.getContentResolver(), videoIdLong,
								Thumbnails.MINI_KIND, options);
						return bitmap;
					} else {
						// drawable = BitmapFactory.decodeResource(c.getResources(),
						// R.drawable.ic_doctor);
					}
				}
			}
			int degree = 0;
			ExifInterface exifInterface;
			try {
				exifInterface = new ExifInterface(file);

				int orientation = exifInterface.getAttributeInt(
						ExifInterface.TAG_ORIENTATION,
						ExifInterface.ORIENTATION_NORMAL);
				switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
				}
				if (degree != 0 && drawable != null) {
					Matrix m = new Matrix();
					m.setRotate(degree, (float) drawable.getWidth() / 2,
							(float) drawable.getHeight() / 2);
					drawable = Bitmap.createBitmap(drawable, 0, 0,
							drawable.getWidth(), drawable.getHeight(), m, true);
				}
			} catch (OutOfMemoryError e) {
				// Toast.makeText(c, "牌照出错，请重新牌照", Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return drawable;
		}

		
		public static String getDataString(String data){
			String n = data.replace("年", "-");
			String y = n.replace("月", "-");
			String r = y.replace("日", " ");
			String s = r.replace("时", ":");
			String f = s.replace("分", ":");
			String overCreateDate = f+"00";
			return overCreateDate;
			
		}
		//设置弹窗背景
		public static void setAttribute(Activity activity,PopupWindow pWin){
			ColorDrawable cd = new ColorDrawable(0x000000);
			pWin.setBackgroundDrawable(cd); 
			WindowManager.LayoutParams lp=activity.getWindow().getAttributes();
			lp.alpha = 0.4f;
			activity.getWindow().setAttributes(lp);
		}
		//弹窗背景还原
		public static void onDismiss(Activity activity){
		    WindowManager.LayoutParams lp=activity.getWindow().getAttributes();
		    lp.alpha = 1f;
		    activity.getWindow().setAttributes(lp);	
		}
		//改变时间样式
		public static String ChangeTime(String dateOld){
			String n = dateOld.replace("年", "-");
			String y = n.replace("月", "-");
			String r = y.replace("日", " ");
			String s = r.replace("时", ":");
			String f = s.replace("分", "");
			return f;
		}

		public static void telePhoneBroadcast(Activity activity,String phone){
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_DIAL); 
			intent.setData(Uri.parse("tel:"+phone));
			activity.startActivity(intent); 	 
		}
}
