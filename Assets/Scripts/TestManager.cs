using System;
using UnityEngine;
using UnityEngine.UI;
 
public class TestManager : MonoBehaviour {
 
    public Text textObj;
 
    void Start () {
 
        // Make the call using JNI to the Java Class and write out the response (or write 'Invalid Response From JNI' if there was a problem).
//        textObj.text =JNIUtil.StaticCall("sayHello", "Invalid Response From JNI", "com.example.test.HelloWorld");
    }


    private void Update()
    {
        if (Input.GetKeyUp(KeyCode.Escape))
        {
            
        }
    }

    public void TestKotLin()
    {
        textObj.text =textObj.text =JNIUtil.StaticCall("callKotlin", "Invalid Response From JNI", "com.example.test.HelloWorld");
    }

    public void ShowDialogPopup()
    {
        AndroidNative.showDialog("SomeTi","Hi There","Yes","Cancel");
    }


    public void ShowDialogWithCallBack()
    {
        
        // Android的Java接口  
        AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
        // 参数  
        string[] mObject = new string[2];
        mObject[0] = "Jar4Android";
        mObject[1] = "Wow,Amazing!It's worked!";
        // 调用方法  
        string ret = jo.Call<string>("ShowDialog", mObject);
        
    }


    public void OnDialogPopUpCallBack(object obj)
    {
        textObj.text = "got dialog call back:  "+obj;
    }

    public void DiaCallback()
    {
        textObj.text = "got call back:  ";
    }
}