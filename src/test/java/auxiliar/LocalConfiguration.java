package auxiliar;

public class LocalConfiguration {
    public static class web {
        public final static String navegador = "firefox";
        //public final static String navegador = "chromeExtension";
        public final static String extension = "speedTest";

        public final static boolean habilitarPantallaModificable=false;
        public final static int  ancho=1200;
        public final static int alto=600;
        public final static boolean habilitarPantallaModificableAleatoria=false;
        public final static int[][] resoluciones ={{1200,800},{600,400},{800,1200}};
    }

    public static class mobileCapabilities{
        public final static String deviceName = "Angel Torre";
        public final static String udId = "emulator-5554";
        public final static String platformName = "Android";
        public final static String platformVersion = "10";
        public final static String appPackage = "com.android.chrome";
        public final static String appActivity = "com.google.android.apps.chrome.Main";
        public final static String bundleId = "";
        public final static String xcodeOrgId = "";
    }
}
