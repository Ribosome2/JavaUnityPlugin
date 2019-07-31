# AndroidPluginWithUnity
主要知识点：
1.要使用 com.unity3d.player.UnityPlayer 里面的接口，
需要把Unity 安装目录下的 C:\Program Files\Unity\Editor\Data\PlaybackEngines\AndroidPlayer\Variations\mono\Release\Classes\classes.jar 
 文件导入到AndroidStudio 这里我们是在AS 工程的app目录下新建一个unityLibs文件夹（这个命名应该是可以随意的） 然后把classes.jar丢进去
 还要在 build.gradle 的dependencies块加入  一行依赖项： provided files('unityLibs/classes.jar')
 
