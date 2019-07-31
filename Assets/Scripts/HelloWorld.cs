using System;
using UnityEngine;
using UnityEngine.UI;
 
public class HelloWorld : MonoBehaviour {
 
    Text textObj;
 
    void Start () {
 
        // Find the Text UI object attached to the game object this script is attached to
        textObj = gameObject.GetComponent<Text>();
 
        // Make the call using JNI to the Java Class and write out the response (or write 'Invalid Response From JNI' if there was a problem).
        textObj.text =JNIUtil.StaticCall("sayHello", "Invalid Response From JNI", "com.example.Utility.HelloWorld");
    }


    private void Update()
    {
        if (Input.GetKeyUp(KeyCode.Escape))
        {
            
        }
    }

    public void TestKotLin()
    {
        textObj.text =textObj.text =JNIUtil.StaticCall("callKotlin", "Invalid Response From JNI", "com.example.Utility.HelloWorld");
    }

    public void ShowDialogPopup()
    {
        AndroidNative.showDialog("SomeTi","Hi There","Yes","Cancel");
    }
}