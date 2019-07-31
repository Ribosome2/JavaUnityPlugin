package com.example.Utility;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;

import com.unity3d.player.UnityPlayer;

public class PopupManager
{
    public static void ShowMessagePopup(String title, String message, String okButtonText) {
        AlertDialog.Builder messagePopup = new AlertDialog.Builder(new ContextThemeWrapper(UnityPlayer.currentActivity, GetTheme()));
        messagePopup.setTitle(title);
        messagePopup.setMessage(message);
        messagePopup.setPositiveButton(okButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("AndroidMessagePopup", "OnMessagePopUpCallBack", "0");
            }
        });
        messagePopup.setOnKeyListener(KeyListener);
        messagePopup.setCancelable(false);
        messagePopup.show();
    }

    public static void ShowDialogPopup(String title, String message, String yesButtonText, String noButtonText) {
        AlertDialog.Builder dialogPopupBuilder = new AlertDialog.Builder(new ContextThemeWrapper(UnityPlayer.currentActivity, GetTheme()));
        dialogPopupBuilder.setTitle(title);
        dialogPopupBuilder.setMessage(message);
        dialogPopupBuilder.setPositiveButton(yesButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("AndroidDialogPopup", "OnDialogPopUpCallBack", "0");
            }
        });
        dialogPopupBuilder.setNegativeButton(noButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("AndroidDialogPopup", "OnDialogPopUpCallBack", "1");
            }
        });
        dialogPopupBuilder.setOnKeyListener(KeyListener);
        dialogPopupBuilder.setCancelable(false);
        dialogPopupBuilder.show();
    }

    public static void ShowRatePopup(String title, String message, String yesButtonText, String laterButtonText, String noButtonText) {
        AlertDialog.Builder ratePopupBuilder = new AlertDialog.Builder(new ContextThemeWrapper(UnityPlayer.currentActivity, GetTheme()));
        ratePopupBuilder.setTitle(title);
        ratePopupBuilder.setMessage(message);
        ratePopupBuilder.setPositiveButton(yesButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("AndroidRateUsPopUp", "OnRatePopUpCallBack", "0");
            }
        });
        ratePopupBuilder.setNegativeButton(noButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("AndroidRateUsPopUp", "OnRatePopUpCallBack", "2");
            }
        });

        ratePopupBuilder.setNeutralButton(laterButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("AndroidRateUsPopUp", "OnRatePopUpCallBack", "1");
            }
        });
        ratePopupBuilder.setOnKeyListener(KeyListener);
        ratePopupBuilder.setCancelable(false);
        ratePopupBuilder.show();
    }

    @SuppressLint("InlinedApi")
    private static int GetTheme(){
        int theme = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            theme = android.R.style.Theme_Material_Light_Dialog;
        } else {
            theme = android.R.style.Theme_Holo_Dialog;
        }
        return theme;
    }

    public static void OpenAppRatingPage(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        UnityPlayer.currentActivity.startActivity(intent);
    }

    public static void OpenWebPage(String webUrl){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
        UnityPlayer.currentActivity.startActivity(browserIntent);
    }

    private static DialogInterface.OnKeyListener KeyListener = new OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                Log.d("AndroidNative", "AndroidPopUp");
                UnityPlayer.UnitySendMessage("AndroidMessagePopup", "OnMessagePopUpCallBack", "0");
                UnityPlayer.UnitySendMessage("AndroidDialogPopup", "OnDialogPopUpCallBack", "1");
                UnityPlayer.UnitySendMessage("AndroidRateUsPopUp", "OnRatePopUpCallBack", "2");
                dialog.dismiss();
            }
            return false;
        }
    };
}