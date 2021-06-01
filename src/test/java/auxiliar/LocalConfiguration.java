package auxiliar;

public class LocalConfiguration {
    public static class web {
        public final static String navegador = "chrome";
        public final static String extension = "speedTest";

        public final static boolean habilitarPantallaModificable=false;
        public final static int  ancho=1360;
        public final static int alto=768;
        public final static boolean habilitarPantallaModificableAleatoria=false;
        public final static int[][] resoluciones ={{1360,768},{1024,768},{600,768}};
    }

    public static class mobileCapabilities{
        /**
        public final static String deviceName = "Angel Torre";
        public final static String udId = "emulator-5554";
        public final static String platformName = "Android";
        public final static String platformVersion = "10";
        public final static String appPackage = "com.android.chrome";
        public final static String appActivity = "com.google.android.apps.chrome.Main";
        public final static String bundleId = "";
        public final static String xcodeOrgId = "";
         **/
        public final static String deviceName = "Mi A2 Lite";
        public final static String udId = "73a6a2480405";
        public final static String platformName = "Android";
        public final static String platformVersion = "10";
        public final static String appPackage = "com.poqstudio.app.platform.missguided";
        public final static String appActivity = "com.poqstudio.app.platform.presentation.splash.view.SplashActivity";
        public final static String bundleId = "";
        public final static String xcodeOrgId = "";
    }
}
